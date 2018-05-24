package com.sina.rwxchina.aichediandianbussiness.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.base.BaseFragment;
import com.jnyilin.basiclib.utils.imageUtils.ImageUtils;
import com.jnyilin.basiclib.utils.logUtils.LogUtils;
import com.jnyilin.basiclib.utils.retrofitutils.ApiManager;
import com.jnyilin.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.jnyilin.basiclib.utils.retrofitutils.entity.PageInfo;
import com.jnyilin.basiclib.utils.toastUtils.ToastUtils;
import com.sina.rwxchina.aichediandianbussiness.MainActivity;

import com.sina.rwxchina.aichediandianbussiness.R;
import com.sina.rwxchina.aichediandianbussiness.home.activity.ScanCodeActivity;
import com.sina.rwxchina.aichediandianbussiness.home.entity.HomeOrderInfo;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;



/**
 * @author HRR
 * @datetime 2018/1/31
 * @describe 首页
 * @modifyRecord
 */

public class HomeFragment extends BaseFragment {
    //订单验证
    private EditText scan_code;
    private Button verification_btn;
    private LinearLayout scan_code_verification,order_record;
    //店铺基本信息
    private ImageView shop_logo;
    private TextView shop_name,shop_score,shop_selled,shop_phone,shop_address;
    private RatingBar ratingBar;
    //订单收入
    private ImageView order_refresh;
    private TextView month_income,month_order_number;
    private TextView day_income,day_order_number;
    private TextView total_income;
    private HomeOrderInfo orderInfos;

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view, @Nullable Bundle savedInstanceState) {
        scan_code =  view.findViewById(R.id.fragment_home_code);
        verification_btn = view.findViewById(R.id.fragment_home_btncode);
        scan_code_verification = view.findViewById(R.id.fragment_home_verification_scan_code);
        order_record = view.findViewById(R.id.fragment_home_order_record);
        shop_logo = view.findViewById(R.id.fragment_home_trademark);
        shop_name = view.findViewById(R.id.fragment_home_name);
        shop_score = view.findViewById(R.id.fragment_home_score);
        shop_selled = view.findViewById(R.id.fragment_home_selled);
        shop_phone = view.findViewById(R.id.fragment_home_phone);
        shop_address = view.findViewById(R.id.fragment_home_address);
        order_refresh = view.findViewById(R.id.fragment_home_refresh);
        month_income = view.findViewById(R.id.fragment_home_this_month_income);
        month_order_number = view.findViewById(R.id.fragment_home_this_month_order_number);
        day_income = view.findViewById(R.id.fragment_home_this_day_income);
        day_order_number = view.findViewById(R.id.fragment_home_today_order_num);
        total_income = view.findViewById(R.id.fragment_home_total_income);
        ratingBar = view.findViewById(R.id.fragment_home_ratingBar);
    }

    @Override
    public void setListener() {
        verification_btn.setOnClickListener(this);
        scan_code_verification.setOnClickListener(this);
        order_record.setOnClickListener(this);
        order_refresh.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        int i = v.getId();
        if(i == R.id.fragment_home_btncode){
            if(TextUtils.isEmpty(scan_code.getText().toString())){
                ToastUtils.showShort("请输入订单验证码");
            }else{
                Verfication(scan_code.getText().toString());
            }

        }else if(i == R.id.fragment_home_verification_scan_code){
            startActivity(ScanCodeActivity.class);
        }else if( i == R.id.fragment_home_order_record){
            MainActivity activity = (MainActivity) getActivity();
            activity.setFragment(2);
            activity.changeImgAndColor(2);
        }else if(i == R.id.fragment_home_refresh){
            getHomeOrder();
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        getHomeOrder();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * 获取店铺信息
     */
    private void getHomeOrder(){
        Map<String, String> params = new HashMap<String, String>();
        ApiManager apiManager = new ApiManager((BaseActivity) getActivity(), new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                LogUtils.e("result="+result);
                Gson gson = new Gson();
                orderInfos =  gson.fromJson(result,HomeOrderInfo.class);
                setOrder();
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.HOME_ORDER,params);
    }


    /**
     * 设置店铺信息
     */
    private void setOrder(){
        month_income.setText("￥" + orderInfos.getMonth_income());
        month_order_number.setText("共消费" + orderInfos.getMonth_num()+"单");
        day_income.setText("￥" + orderInfos.getToday_income());
        day_order_number.setText("共消费" +orderInfos.getToday_num()+"单");
        total_income.setText("我的总收入￥" + orderInfos.getAll_income());
        shop_name.setText(orderInfos.getShopinfo().getShop_name());
        shop_address.setText("地址：" + orderInfos.getShopinfo().getShop_address());
        shop_phone.setText("电话：" + orderInfos.getShopinfo().getShop_telephone());
        shop_selled.setText(Html.fromHtml("已售" + "<font color='#FF9100'>" +orderInfos.getShopinfo().getShop_sale() + "</font>" + "份"));
        ratingBar.setRating(Float.parseFloat(orderInfos.getShopinfo().getShop_starlevel()));
        shop_score.setText(orderInfos.getShopinfo().getShop_starlevel()+"分");
        ImageUtils.showImage(getContext(),HttpPath.IMAGEURL+orderInfos.getShopinfo().getShop_logo(),shop_logo);
    }


    /**
     * 订单验证
     */
    private void Verfication(String code){
        Map<String, String> params = new HashMap<String, String>();
        params.put("codestr", code);
        ApiManager apiManager = new ApiManager((BaseActivity) getActivity(), new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                ToastUtils.showLong(msg);
            }
            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.ORDER_VERIFICATION, params);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEvent(String string){
        switch (string){
            case "infomationUpdateSuccess":
                getHomeOrder();
                break;
            default:
                break;
        }
    }
}
