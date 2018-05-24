package com.sina.rwxchina.aichediandianbussiness.my.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.jnyilin.basiclib.base.BaseFragment;
import com.sina.rwxchina.aichediandianbussiness.R;

import java.util.List;

/**
 * @author HRR
 * @datetime 2018/2/2
 * @describe 预约统计碎片
 * @modifyRecord
 */

public class ConsumptionStatisticsFragment extends BaseFragment {
    private View view;
    private ListView lvReservation;
//    private List<Order> reservationOrders;
    private String store_id;
    private TextView tvCount;//订单总数
    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_reservation_statistics;
    }

    @Override
    public void initView(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
