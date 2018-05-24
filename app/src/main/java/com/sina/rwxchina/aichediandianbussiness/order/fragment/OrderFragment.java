package com.sina.rwxchina.aichediandianbussiness.order.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jnyilin.basiclib.base.BaseFragment;
import com.sina.rwxchina.aichediandianbussiness.R;

/**
 * @author HRR
 * @datetime 2018/1/31
 * @describe 订单管理
 * @modifyRecord
 */

public class OrderFragment extends BaseFragment {
    private TabLayout tlOrder;
    private ViewPager vpOrder;
    private ImageView ivSearch;
    private EditText etOrderNum;
    private RelativeLayout RelativeLayout_ed;
    private String order_sn;
    private int fragmentnum = 0;
    private OrderListFragment completeFragment;
    private OrderListFragment carriedoutFragment;
    public static OrderFragment activity;
    private ImageView oder_sousuo;
    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_order;
    }

    @Override
    public void initView(View view, @Nullable Bundle savedInstanceState) {
        tlOrder = view.findViewById(R.id.fragment_order_management_tab);
        vpOrder = view.findViewById(R.id.fragment_order_management_viewpager);
        ivSearch =view.findViewById(R.id.activity_order_management_search);
        etOrderNum = view.findViewById(R.id.fragment_merchant_management_edit);
        oder_sousuo =view.findViewById(R.id.oder_sousuo);
        RelativeLayout_ed =view.findViewById(R.id.RelativeLayout_ed);
        completeFragment = new OrderListFragment();
        Bundle complete=new Bundle();
        complete.putString("type","complete");
        completeFragment.setArguments(complete);
        carriedoutFragment = new OrderListFragment();
        Bundle carriedout=new Bundle();
        carriedout.putString("type","doing");
        carriedoutFragment.setArguments(carriedout);
    }

    @Override
    public void setListener() {
        //搜索
        etOrderNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                order_sn = etOrderNum.getText().toString();
                if (fragmentnum == 0) {
                    carriedoutFragment.updata(order_sn);
                } else {
                    completeFragment.updata(order_sn);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        oder_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (RelativeLayout_ed.getVisibility() != View.GONE) {
                    RelativeLayout_ed.setVisibility(View.GONE);
                    Log.e("wh", "开");
                } else {
                    RelativeLayout_ed.setVisibility(View.VISIBLE);
                    Log.e("wh", "关");
                }

            }
        });


        vpOrder.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                fragmentnum = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        activity = this;
        setTab();
    }

    private void setTab() {

        OrderStatePagerAdapter adapter = new OrderStatePagerAdapter(getActivity().getSupportFragmentManager(), getContext());
        vpOrder.setAdapter(adapter);
        tlOrder.setupWithViewPager(vpOrder);
        tlOrder.setTabMode(TabLayout.MODE_FIXED);

    }

    /**
     * 订单管理中，进行中和已支付的viewPager的适配器
     *
     * @author zhangkunlun
     *         2016/06/15
     */
    public class OrderStatePagerAdapter extends FragmentPagerAdapter {
        private static final int PAY_COUNT = 2;
        private String tabTitles[] = new String[]{"进行中", "已完成"};
        private Context context;

        public OrderStatePagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        public OrderStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return carriedoutFragment;
            } else if (position == 1) {
                return completeFragment;
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
}
