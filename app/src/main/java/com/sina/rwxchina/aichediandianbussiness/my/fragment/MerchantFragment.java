package com.sina.rwxchina.aichediandianbussiness.my.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.base.BaseFragment;
import com.jnyilin.basiclib.utils.appUtils.CallPhoneUtils;
import com.jnyilin.basiclib.utils.imageUtils.ImageUtils;
import com.jnyilin.basiclib.utils.retrofitutils.ApiManager;
import com.jnyilin.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.jnyilin.basiclib.utils.retrofitutils.commonparametersutils.CommonParametersUtils;
import com.jnyilin.basiclib.utils.retrofitutils.entity.PageInfo;
import com.jnyilin.basiclib.utils.webViewUtils.activity.DefaultWebViewActivity;
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sina.rwxchina.aichediandianbussiness.login.activity.LoginActivity;
import com.sina.rwxchina.aichediandianbussiness.my.activity.ReportFormActivity;
import com.sina.rwxchina.aichediandianbussiness.my.activity.ShareWebActivity;
import com.sina.rwxchina.aichediandianbussiness.my.activity.UpdatePasswordActivity;
import com.sina.rwxchina.aichediandianbussiness.my.activity.WithdrawActivity;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.ShopInfo;
import com.sina.rwxchina.aichediandianbussiness.userutils.UserUtils;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

/**
 * @author HRR
 * @datetime 2018/1/31
 * @describe 用户中心
 * @modifyRecord
 */

public class MerchantFragment extends BaseFragment {
    /**店铺信息*/
    private ShopInfo shopInfo;
    /**统计报表*/
    private LinearLayout reportfrom;
    /**修改密码*/
    private LinearLayout update_password;
    /**修改资料*/
    private LinearLayout updateShopData;
    private LinearLayout myShare;
    private LinearLayout withDraw;
    private ImageView cvPhoto;
    private TextView tvName;
    /**推广联盟*/
    private LinearLayout exchange;
    /**客服电话*/
    private LinearLayout custom_phone;
    /**退出登陆*/
    private Button exitLogin;
    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_merchant;
    }

    @Override
    public void initView(View view, @Nullable Bundle savedInstanceState) {
        reportfrom=view.findViewById(R.id.fragment_merchant_reportform);
        update_password = view.findViewById(R.id.fragment_merchant_update_password);
        updateShopData=view.findViewById(R.id.fragment_merchant_update_information);
        cvPhoto=view.findViewById(R.id.fragment_merchant_logo);
        tvName =view.findViewById(R.id.fragment_merchant_name);
        custom_phone = view.findViewById(R.id.fragment_merchante_phone);
        exchange = view.findViewById(R.id.fragment_merchante_exchange);
        exitLogin=view.findViewById(R.id.fragment_merchant_exit);
        myShare = view.findViewById(R.id.fragment_merchante_myshare);
        withDraw = view.findViewById(R.id.fragment_merchante_withdraw);
    }

    @Override
    public void setListener() {
        reportfrom.setOnClickListener(this);
        update_password.setOnClickListener(this);
        updateShopData.setOnClickListener(this);
        exitLogin.setOnClickListener(this);
        custom_phone.setOnClickListener(this);
        myShare.setOnClickListener(this);
        withDraw.setOnClickListener(this);
        exchange.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()){
            case R.id.fragment_merchant_reportform:
                Intent intent=new Intent(getActivity(), ReportFormActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment_merchant_update_password:
                bundle.putString("type","修改密码");
                startActivity(UpdatePasswordActivity.class,bundle);
                break;
            case R.id.fragment_merchant_update_information:
                Bundle bundle1=new Bundle();
                bundle1.putString("url", HttpPath.MODIFY_SHOP);
                startActivity(DefaultWebViewActivity.class,bundle1);
                break;
            case R.id.fragment_merchant_exit:
                exit();
                break;
            case R.id.fragment_merchante_phone:
                CallPhoneUtils.call(getActivity(),"4008004656");
                break;
            case R.id.fragment_merchante_myshare:
                if (UserUtils.isLogin(getActivity())){
                    bundle.putString("uid",CommonParametersUtils.getUid(getActivity()));
                    startActivity(ShareWebActivity.class,bundle);
                }
                break;
            case R.id.fragment_merchante_withdraw:
                if (UserUtils.isLogin(getActivity()))
                    startActivity(WithdrawActivity.class);
                break;
            case R.id.fragment_merchante_exchange:
                bundle.putString("url",HttpPath.WEB_BUSINESSALLIANCE);
                startActivity(DefaultWebViewActivity.class,bundle);
                break;
            default:
                break;
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        getShop();
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
     * 获取商铺信息
     */
    private void getShop(){
        ApiManager manager=new ApiManager((BaseActivity) getActivity(), new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                shopInfo=new Gson().fromJson(result,ShopInfo.class);
                tvName.setText(shopInfo.getShop_name());
                ImageUtils.showCircleImage(getContext(),HttpPath.IMAGEURL+shopInfo.getShop_logo(),cvPhoto);
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        manager.post(HttpPath.GETSHOP,new HashMap<String, String>());
    }

    /**
     * 退出登陆
     */
    private void exit(){
        CommonParametersUtils.clearParams(getActivity().getApplicationContext());
        Intent login=new Intent(getActivity(), LoginActivity.class);
        startActivity(login);
        getActivity().finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEvent(String string){
        switch (string){
            case "infomationUpdateSuccess":
                getShop();
                break;
            default:
                break;
        }
    }
}
