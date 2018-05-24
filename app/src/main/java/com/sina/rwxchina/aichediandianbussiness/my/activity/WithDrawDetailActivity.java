package com.sina.rwxchina.aichediandianbussiness.my.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.utils.retrofitutils.entity.PageInfo;
import com.jnyilin.basiclib.utils.toastUtils.ToastUtils;
import com.jnyilin.basiclib.view.PickerViewUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sina.rwxchina.aichediandianbussiness.my.adapter.WithDrawDetailAdapter;
import com.sina.rwxchina.aichediandianbussiness.my.contract.WithdrawDetailContract;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.WithdrawDetailBean;
import com.sina.rwxchina.aichediandianbussiness.my.presenter.WithdrawDetailPresenter;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/11/29
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class WithDrawDetailActivity extends BaseActivity implements WithdrawDetailContract.View {
    private ListView withdrawDetail_lv;
    private SmartRefreshLayout refreshLayout;
    private RelativeLayout all_rl, success_rl, fail_rl, checking_rl;
    private ImageView filter, all_iv, success_iv, fail_iv, checking_iv, filterDate, back;
    private TextView date_tv, balance, title, rightIcon;
    private WithdrawDetailPresenter withdrawDetailPresenter;
    private WithDrawDetailAdapter withDrawDetailAdapter;
    private PageInfo pageInfo;
    private PopupWindow popupWindow;
    private View fview, head, titleView,background;



    @Override
    public void initParms(Bundle parms) {

        setSetStatusBar(true);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_withdrawdetail;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        withdrawDetail_lv = view.findViewById(R.id.withdrawDetail_lv);
        refreshLayout = view.findViewById(R.id.withdrawDetail_srl);
        filter = view.findViewById(R.id.filterConditions_iv);
        View popItem = LayoutInflater.from(this).inflate(R.layout.pop_balancefilter, null);
        checking_iv = popItem.findViewById(R.id.popWithdrawFilter_checking_iv);
        all_iv = popItem.findViewById(R.id.popWithdrawFilter_all_iv);
        fail_iv = popItem.findViewById(R.id.popWithdrawFilter_fail_iv);
        success_iv = popItem.findViewById(R.id.popWithdrawFilter_success_iv);
        checking_rl = popItem.findViewById(R.id.popWithdrawFilter_checking_rl);
        all_rl = popItem.findViewById(R.id.popWithdrawFilter_all_rl);
        fail_rl = popItem.findViewById(R.id.popWithdrawFilter_fail_rl);
        success_rl = popItem.findViewById(R.id.popWithdrawFilter_success_rl);
        popupWindow = new PopupWindow(popItem, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        head = LayoutInflater.from(this).inflate(R.layout.item_withdrawdetail_header, null);
        filterDate = head.findViewById(R.id.date_iv);
        date_tv = head.findViewById(R.id.withdraw_date_tv);
        balance = head.findViewById(R.id.withdraw_balance_tv);
        titleView = view.findViewById(R.id.withdrawDetail_title);
        fview = titleView;
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        back = titleView.findViewById(R.id.back);
        rightIcon = titleView.findViewById(R.id.rightIcon_tv);
        background = view.findViewById(R.id.withdrawDetail_background);
    }

    @Override
    public void setListener() {
        Calendar calendar = Calendar.getInstance();
        date_tv.setText(calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月");
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (pageInfo!=null&&pageInfo.getIsMore() == 1) {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("page", pageInfo.getPage() + 1 + "");
                    withdrawDetailPresenter.loadMore(params);
                } else {
                    refreshlayout.finishLoadmoreWithNoMoreData();
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                withdrawDetailPresenter.getData(null, true);
            }
        });
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        all_rl.setOnClickListener(this);
        fail_rl.setOnClickListener(this);
        checking_rl.setOnClickListener(this);
        success_rl.setOnClickListener(this);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                background.setVisibility(View.GONE);
            }
        });
        filter.setOnClickListener(this);
        filterDate.setOnClickListener(this);
        back.setOnClickListener(this);
        rightIcon.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        if (i == R.id.filterConditions_iv) {
            showPop();
        } else if (i == R.id.popWithdrawFilter_all_rl) {
            filterData(0);
        } else if (i == R.id.popWithdrawFilter_checking_rl) {
            filterData(1);
        } else if (i == R.id.popWithdrawFilter_fail_rl) {
            filterData(2);
        } else if (i == R.id.popWithdrawFilter_success_rl) {
            filterData(3);
        } else if (i == R.id.date_iv) {
            PickerViewUtils.initTimePicker(this, new boolean[]{true, true, false, false, false, false}, new TimePickerView.OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    date_tv.setText(PickerViewUtils.getTime(date));
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("time", date.getTime() + "");
                    withdrawDetailPresenter.getData(map, false);
                }
            });
        } else if (i == R.id.rightIcon_tv){
            startActivity(BalanceDetailActivity.class);
        }else if (i == R.id.back) {
            finish();
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        setTitle();
        withdrawDetail_lv.addHeaderView(head);
        withdrawDetailPresenter = new WithdrawDetailPresenter(this);
        withdrawDetailPresenter.attachView(this);
        withdrawDetailPresenter.getData(null, false);
    }

    private void showPop() {
        background.setVisibility(View.VISIBLE);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(fview);
    }

    @Override
    public void showView(WithdrawDetailBean withdrawDetailBean, boolean isRefresh) {
        setListData(withdrawDetailBean, isRefresh);
    }

    private void setListData(WithdrawDetailBean withdrawDetailBean, boolean isRefresh) {
        if (withDrawDetailAdapter != null && withdrawDetailBean != null) {
            updateData(withdrawDetailBean.getList());
            balance.setText("提现总额￥" + withdrawDetailBean.getTotal().getTotal_money());
            isStopRefresh(isRefresh);
        } else if (withDrawDetailAdapter != null && withdrawDetailBean == null) {
            List<WithdrawDetailBean.ListBean> listData = new ArrayList<>();
            updateData(listData);
            isStopRefresh(isRefresh);
            ToastUtils.showShort("暂无数据");
        } else if (withdrawDetailBean != null && withDrawDetailAdapter == null) {
            setAdapter(withdrawDetailBean.getList());
            balance.setText("提现总额￥" + withdrawDetailBean.getTotal().getTotal_money());
        } else {
            List<WithdrawDetailBean.ListBean> listData = new ArrayList<>();
            setAdapter(listData);
            balance.setText("提现总额￥" + 0);
            ToastUtils.showShort("暂无数据");
        }
    }

    private void updateData(List<WithdrawDetailBean.ListBean> listBeen) {
        withDrawDetailAdapter.setListBeen(listBeen);
        withDrawDetailAdapter.notifyDataSetChanged();
    }

    private void setAdapter(List<WithdrawDetailBean.ListBean> listBeen) {
        withDrawDetailAdapter = new WithDrawDetailAdapter(listBeen, this);
        withdrawDetail_lv.setAdapter(withDrawDetailAdapter);
    }

    private void isStopRefresh(boolean isRefresh) {
        if (isRefresh)
            refreshLayout.finishRefresh();
    }

    @Override
    public void getPage(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public void showLoadMore(WithdrawDetailBean withdrawDetailBean) {
        List<WithdrawDetailBean.ListBean> listBeen = withDrawDetailAdapter.getListBeen();
        listBeen.addAll(withdrawDetailBean.getList());
        withDrawDetailAdapter.setListBeen(listBeen);
        withDrawDetailAdapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore();
    }

    private void filterData(int param) {
        if (param == 0) {
            withdrawDetailPresenter.getData(null, false);
        } else {
            Map<String, String> params = new HashMap<>();
            params.put("status", param + "");
            withdrawDetailPresenter.getData(params, false);
        }
        switch (param) {
            case 0:
                all_iv.setVisibility(View.VISIBLE);
                success_iv.setVisibility(View.GONE);
                fail_iv.setVisibility(View.GONE);
                checking_iv.setVisibility(View.GONE);
                break;
            case 1:
                checking_iv.setVisibility(View.VISIBLE);
                all_iv.setVisibility(View.GONE);
                success_iv.setVisibility(View.GONE);
                fail_iv.setVisibility(View.GONE);
                break;
            case 2:
                fail_iv.setVisibility(View.VISIBLE);
                all_iv.setVisibility(View.GONE);
                success_iv.setVisibility(View.GONE);
                checking_iv.setVisibility(View.GONE);
                break;
            case 3:
                success_iv.setVisibility(View.VISIBLE);
                fail_iv.setVisibility(View.GONE);
                checking_iv.setVisibility(View.GONE);
                all_iv.setVisibility(View.GONE);
                break;
        }
        popupWindow.dismiss();
    }

    private void setTitle() {
        title.setText("提现记录");
        rightIcon.setText("收支明细");
        rightIcon.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        withdrawDetailPresenter.detachView();
        super.onDestroy();
    }

}
