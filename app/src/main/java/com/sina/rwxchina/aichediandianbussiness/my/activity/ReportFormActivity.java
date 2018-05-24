package com.sina.rwxchina.aichediandianbussiness.my.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.jnyilin.basiclib.base.BaseActivity;
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sina.rwxchina.aichediandianbussiness.my.adapter.ReportPagerAdapter;
import com.sina.rwxchina.aichediandianbussiness.my.fragment.ReservationStatisticsFragment;

/**
 * @author HRR
 * @datetime 2018/2/2
 * @describe 统计报表
 * @modifyRecord
 */

public class ReportFormActivity extends BaseActivity {
    private TabLayout tlReportReport;
    private ViewPager vpReport;
    private ImageView ivBack;//返回
    private FrameLayout frameLayout;
    @Override
    public void initParms(Bundle params) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_report_form;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        frameLayout=findViewById(R.id.fragment_reservation);
        tlReportReport = findViewById(R.id.activity_report_form_tab);
        vpReport = findViewById(R.id.activity_report_form_vp);
        ivBack = findViewById(R.id.activity_report_form_back);
    }

    @Override
    public void setListener() {
        ivBack.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.activity_report_form_back:
                finish();
                // startActivity(new Intent(Report_form_Activity.this, MainActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void doBusiness(Context mContext) {
//        setTab();
        showFragment();
    }

    private void showFragment(){
        ReservationStatisticsFragment fragment=new ReservationStatisticsFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_reservation,fragment)
                .show(fragment)
                .commit();
    }

    private void setTab() {
        ReportPagerAdapter adapter = new ReportPagerAdapter(getSupportFragmentManager(), this);
        vpReport.setAdapter(adapter);
        tlReportReport.setupWithViewPager(vpReport);
        tlReportReport.setTabMode(TabLayout.MODE_FIXED);

    }
}
