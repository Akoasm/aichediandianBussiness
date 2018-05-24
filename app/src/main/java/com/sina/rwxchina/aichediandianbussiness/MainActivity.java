package com.sina.rwxchina.aichediandianbussiness;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.base.BaseActivity;
import com.sina.rwxchina.aichediandianbussiness.commodity.WebCommodityFragment;
import com.sina.rwxchina.aichediandianbussiness.home.fragment.HomeFragment;
import com.sina.rwxchina.aichediandianbussiness.my.fragment.MerchantFragment;
import com.sina.rwxchina.aichediandianbussiness.order.fragment.OrderFragment;


/**
 * @author HRR
 * @datetime 2018/1/31
 * @describe
 * @modifyRecord
 */

public class MainActivity extends BaseActivity {
    private long exitTime = 0;
    private LinearLayout mHome; //首页
    private LinearLayout mCommodity;//商品管理
    private LinearLayout mOrder; //订单管理
    private LinearLayout mMerchant; //商户中心

    private HomeFragment mHomeFragment; //首页homefragment
//    private CommodityFragment mCommodityFragment; //商品管理Fragment
    private WebCommodityFragment webViewFragment;
    private OrderFragment mOrderFragment; //订单管理fragment
    private MerchantFragment mMerchantFragment; //商户中心fragment

    //最下方菜单栏图片和文字
    private ImageView mHomeImg;
    private ImageView mCommodityImg;
    private ImageView mOrderImg;
    private ImageView mMerchantImg;
    private TextView mHomeText;
    private TextView mCommodityText;
    private TextView mOrderText;
    private TextView mMerchantText;

    @Override
    public void initParms(Bundle params) {
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        mHome= findViewById(R.id.activity_main_home);
        mCommodity=findViewById(R.id.activity_main_commodity);
        mOrder= findViewById(R.id.activity_main_order);
        mMerchant= findViewById(R.id.activity_main_merchant);

        mHomeImg=findViewById(R.id.activity_main_home_img);
        mCommodityImg=findViewById(R.id.activity_main_commodity_img);
        mOrderImg= findViewById(R.id.activity_main_order_img);
        mMerchantImg=findViewById(R.id.activity_main_merchant_img);

        mHomeText=findViewById(R.id.activity_main_home_text);
        mCommodityText= findViewById(R.id.activity_main_commodity_text);
        mOrderText= findViewById(R.id.activity_main_order_text);
        mMerchantText= findViewById(R.id.activity_main_merchant_text);
    }

    @Override
    public void setListener() {
        mHome.setOnClickListener(this);
        mCommodity.setOnClickListener(this);
        mOrder.setOnClickListener(this);
        mMerchant.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        FragmentManager mFragmentManager=getSupportFragmentManager();//fragment管理器
        FragmentTransaction mTransaction= mFragmentManager.beginTransaction();
        hideFragment(mTransaction);
        switch (v.getId()) {
            case R.id.activity_main_home:
                changeImgAndColor(0);
                setFragment(0);
                break;
            case R.id.activity_main_commodity:
                changeImgAndColor(1);
                setFragment(1);
                break;
            case R.id.activity_main_order:
                changeImgAndColor(2);
                setFragment(2);
                break;
            case R.id.activity_main_merchant:
                changeImgAndColor(3);
                setFragment(3);
                break;
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        init();
    }

    private void init() {
        getpermissions();
        addFragment();
        getNotidication();
    }

    //如果是从通知跳转过来的情况
    private void getNotidication() {
        if (getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            if ("notidication".equals(bundle.getString("notidication"))){
                changeImgAndColor(2);
                setFragment(2);
            }
        }
    }

    /**
     * @param i 2016/06/08
     * @auther zhangkunlun
     * 改变菜单栏的图标和字体
     */
    public void changeImgAndColor(int i) {
        mHomeImg.setImageResource(R.drawable.btn_main_home_f);
        mHomeText.setTextColor(Color.parseColor("#595A5A"));
        mCommodityImg.setImageResource(R.drawable.btn_main_business_f);
        mCommodityText.setTextColor(Color.parseColor("#595A5A"));
        mOrderImg.setImageResource(R.drawable.btn_main_order_f);
        mOrderText.setTextColor(Color.parseColor("#595A5A"));
        mMerchantImg.setImageResource(R.drawable.icon_main_me_f);
        mMerchantText.setTextColor(Color.parseColor("#595A5A"));

        switch (i) {
            case 0:
                mHomeImg.setImageResource(R.drawable.btn_main_home_t);
                mHomeText.setTextColor(Color.parseColor("#ff720a"));
                break;
            case 1:
                mCommodityImg.setImageResource(R.drawable.btn_main_business_t);
                mCommodityText.setTextColor(Color.parseColor("#ff720a"));
                break;
            case 2:
                mOrderImg.setImageResource(R.drawable.btn_main_order_t);
                mOrderText.setTextColor(Color.parseColor("#ff720a"));
                break;
            case 3:
                mMerchantImg.setImageResource(R.drawable.icon_main_me_t);
                mMerchantText.setTextColor(Color.parseColor("#ff720a"));
                break;
        }
    }

    /**
     * 设置fragment
     *
     * @param i 2016/06/17
     * @auther liuxianghua
     */
    public void setFragment(int i) {
        FragmentManager mFragmentManager=getSupportFragmentManager();//fragment管理器
        FragmentTransaction mTransaction= mFragmentManager.beginTransaction();
        hideFragment(mTransaction);

        switch (i) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    mTransaction.add(R.id.activity_main_content, mHomeFragment);
                } else {
                    mTransaction.show(mHomeFragment);
                }
                break;
            case 1:
                if (webViewFragment == null) {
                    webViewFragment = new WebCommodityFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("url",HttpPath.H5COMMODITY);
                    webViewFragment.setArguments(bundle);
                    mTransaction.add(R.id.activity_main_content, webViewFragment);
                } else {
                    mTransaction.show(webViewFragment);
                }
                break;
            case 2:
                if (mOrderFragment == null) {
                    mOrderFragment = new OrderFragment();
                    mTransaction.add(R.id.activity_main_content, mOrderFragment);
                    Log.i("lkymsg","5");
                } else {
                    mTransaction.show(mOrderFragment);
                    Log.i("lkymsg","6");
                }
                break;
            case 3:
                if (mMerchantFragment == null) {
                    mMerchantFragment = new MerchantFragment();
                    mTransaction.add(R.id.activity_main_content, mMerchantFragment);
                    Log.i("lkymsg","7");
                } else {
                    mTransaction.show(mMerchantFragment);
                }
                break;
            default:
                break;
        }
        mTransaction.commit();
    }

    private void addFragment() {
        FragmentManager mFragmentManager=getSupportFragmentManager();//fragment管理器
        FragmentTransaction mTransaction= mFragmentManager.beginTransaction();
        setFragment(0);
    }

    //检测是否获取了系统权限
    private void getpermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.CAMERA,},
                    0);
        }
    }


    private final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    break;
                default:
                    Log.e("kunlun","Unhandled msg - " + msg.what);
            }
        }
    };




    /**
     * @param mTransaction 2016/06/17
     * @auther liuxianghua
     * 隐藏所有Fragment
     */
    private void hideFragment(FragmentTransaction mTransaction) {
        if (mHomeFragment != null) {
            mTransaction.hide(mHomeFragment);
        }
        if (webViewFragment != null) {
            mTransaction.hide(webViewFragment);
        }
        if (mOrderFragment != null) {
            mTransaction.hide(mOrderFragment);
        }
        if (mMerchantFragment != null) {
            mTransaction.hide(mMerchantFragment);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 连按两次退出
     * @author liuxianghua
     * 2016/07/22
     */
    public void exit() {
        Log.i("hrr",System.currentTimeMillis()+" exitTime="+exitTime);
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
//            System.exit(0);
        }
    }



}
