package com.sina.rwxchina.aichediandianbussiness.my.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sina.rwxchina.aichediandianbussiness.my.fragment.ConsumptionStatisticsFragment;
import com.sina.rwxchina.aichediandianbussiness.my.fragment.ReservationStatisticsFragment;

/**
 * @author HRR
 * @datetime 2018/2/2
 * @describe
 * @modifyRecord
 */

public class ReportPagerAdapter extends FragmentPagerAdapter {
    private static final int PAY_COUNT = 2;
    private String tabTitles[] = new String[]{"预约统计", "消费统计"};
    private Context context;

    public ReportPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public ReportPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ReservationStatisticsFragment();
        } else if (position == 1) {
            return new ConsumptionStatisticsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAY_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
