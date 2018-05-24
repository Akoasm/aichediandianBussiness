package com.sina.rwxchina.aichediandianbussiness.my.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.utils.retrofitutils.entity.PageInfo;
import com.jnyilin.basiclib.utils.toastUtils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sina.rwxchina.aichediandianbussiness.my.adapter.BalanceDetailAdapter;
import com.sina.rwxchina.aichediandianbussiness.my.contract.BalanceDetailContract;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.BalanceDetailBean;
import com.sina.rwxchina.aichediandianbussiness.my.presenter.BalanceDetailPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/11/20
 * @describe：收支明细
 * @modifyRecord:修改记录
 */

public class BalanceDetailActivity extends BaseActivity implements BalanceDetailContract.View {
    private ListView balanceDetail_lv;
    private BalanceDetailPresenter balanceDetailPresenter;
    private BalanceDetailAdapter balanceDetailAdapter;
    private ImageView back;
    private TextView title, rightIcon,noData;
    private SmartRefreshLayout refreshLayout;
    private PageInfo pageInfo;


    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_balancedetail;
    }

    @Override
    public void initView(View view, Bundle bundle) {
        balanceDetail_lv = view.findViewById(R.id.balanceDetail_lv);
        View titleView = view.findViewById(R.id.balanceDetail_title);
        back = titleView.findViewById(R.id.back);
        rightIcon = titleView.findViewById(R.id.rightIcon_tv);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        refreshLayout = view.findViewById(R.id.balanceDetail_srl);
        noData = findViewById(R.id.img_noData_tv);
    }

    @Override
    public void setListener() {
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                Map<String, String> params = new HashMap<String, String>();
                if (pageInfo!=null&&pageInfo.getIsMore() == 1) {
                    int page = pageInfo.getPage() + 1;
                    params.put("page", page + "");
                    balanceDetailPresenter.loadMore(params);
                }
                else {
                    refreshlayout.finishLoadmoreWithNoMoreData();
                }

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                balanceDetailPresenter.getData(true);
            }
        });
        back.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        if (i == R.id.back) {
            finish();
        }

    }

    @Override
    public void doBusiness(Context mContext) {
        setTitle();
        balanceDetailPresenter = new BalanceDetailPresenter(this);
        balanceDetailPresenter.attachView(this);
        balanceDetailPresenter.getData(false);

    }


    @Override
    public void showView(List<BalanceDetailBean> balanceDetailBeen, boolean isRefresh) {
        setListData(balanceDetailBeen,isRefresh);
    }

    private void setListData(List<BalanceDetailBean> balanceDetailBeen, boolean isRefresh) {
        if (balanceDetailBeen != null && balanceDetailAdapter != null) {
            updateData(balanceDetailBeen);
            isStopRefresh(isRefresh);
            setNoDataVisibility(View.GONE,View.VISIBLE);
        } else if (balanceDetailBeen == null && balanceDetailAdapter != null) {
            balanceDetailBeen = new ArrayList<>();
            updateData(balanceDetailBeen);
            isStopRefresh(isRefresh);
            setNoDataVisibility(View.VISIBLE,View.GONE);
        } else if (balanceDetailBeen != null && balanceDetailAdapter == null) {
            setAdapter(balanceDetailBeen);
        } else {
            balanceDetailBeen = new ArrayList<>();
            setAdapter(balanceDetailBeen);
            setNoDataVisibility(View.VISIBLE,View.GONE);
        }
    }

    private void updateData(List<BalanceDetailBean> balanceDetailBeen) {
        balanceDetailAdapter.setList(balanceDetailBeen);
        balanceDetailAdapter.notifyDataSetChanged();
    }

    private void setAdapter(List<BalanceDetailBean> balanceDetailBeen) {
        balanceDetailAdapter = new BalanceDetailAdapter(this, balanceDetailBeen);
        balanceDetail_lv.setAdapter(balanceDetailAdapter);
    }

    private void isStopRefresh(boolean isRefresh) {
        if (isRefresh)
            refreshLayout.finishRefresh();
    }

    private void setNoDataVisibility(int noDataVisibility,int lvVisibility){
        noData.setVisibility(noDataVisibility);
        balanceDetail_lv.setVisibility(lvVisibility);
    }
    @Override
    public void getPage(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    @Override
    public void showLoadMore(List<BalanceDetailBean> balanceDetailBeen) {
        List<BalanceDetailBean> list = balanceDetailAdapter.getList();
        list.addAll(balanceDetailBeen);
        balanceDetailAdapter.setList(list);
        balanceDetailAdapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore();
    }

    private void setTitle() {
        title.setText("收支明细");
    }

    @Override
    protected void onDestroy() {
        balanceDetailPresenter.detachView();
        super.onDestroy();
    }

}
