package com.sina.rwxchina.aichediandianbussiness.my.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.base.BaseFragment;
import com.jnyilin.basiclib.utils.retrofitutils.ApiManager;
import com.jnyilin.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.jnyilin.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sina.rwxchina.aichediandianbussiness.my.adapter.OrderAdapter;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.ReportInfo;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.ReportOrderInfo;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HRR
 * @datetime 2018/2/2
 * @describe 消费统计碎片
 * @modifyRecord
 */

public class ReservationStatisticsFragment extends BaseFragment {
    private TextView tvCount;
    private TextView tvAmount;
    private ListView lvReservation;
    private List<ReportOrderInfo> reservationOrders;
    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_consumption_statistics;
    }

    @Override
    public void initView(View view, @Nullable Bundle savedInstanceState) {
        reservationOrders = new ArrayList<ReportOrderInfo>();
        lvReservation = view.findViewById(R.id.fragment_reservation_statics_listview);
        tvCount =  view.findViewById(R.id.fragment_consumption_statics_count);
        tvAmount =view.findViewById(R.id.fragment_consumption_statics_amount);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        getReport();
    }

    private void getReport(){
        Map<String,String> params=new HashMap<String,String>();
        ApiManager manager=new ApiManager((BaseActivity) getActivity(), new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                ReportInfo info=new Gson().fromJson(result,ReportInfo.class);
                reservationOrders=info.getList();
                setAdapter();
                tvCount.setText(Html.fromHtml("共" + "<font color='#FF9100'>"+info.getTotal()+"</font>" + "单"));
                tvAmount.setText("￥" + info.getConsumption());
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        manager.post(HttpPath.REPORTFROM,params);
    }

    private void setAdapter() {
        OrderAdapter adapter = new OrderAdapter(getContext(), reservationOrders);
        lvReservation.setAdapter(adapter);
        setListViewHeightBasedOnChildren(lvReservation);
    }

    private void setListViewHeightBasedOnChildren(ListView lvReservation) {
        ListAdapter listAdapter = lvReservation.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, lvReservation);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = lvReservation.getLayoutParams();
        params.height = totalHeight
                + (lvReservation.getDividerHeight() * (listAdapter.getCount() - 1));
        lvReservation.setLayoutParams(params);
    }
}
