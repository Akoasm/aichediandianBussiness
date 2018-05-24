package com.jnyilin.basiclib.utils.webViewUtils;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.utils.appUtils.CallPhoneUtils;
import com.jnyilin.basiclib.utils.logUtils.LogUtils;

import org.greenrobot.eventbus.EventBus;


/**
 * @author HRR
 * @datetime 2017/11/28
 * @describe 默认WebViewClient
 * @modifyRecord
 */

public class DefaultWebViewClient extends WebViewClient {
    private BaseActivity mC;

    public DefaultWebViewClient(BaseActivity mC) {
        this.mC = mC;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        if (url.startsWith("tel:")) {//拨打电话
            String phoneNumber = url.substring(4, url.length());
            LogUtils.e("DefaultWebViewClient", "url:" + url + "  phoneNumber:" + phoneNumber);
            CallPhoneUtils.call(mC, phoneNumber);
        }else if (url.contains("#app")){
            mC.finish();
        }else if (url.contains("#infomationUpdateSuccess")){
            EventBus.getDefault().post("infomationUpdateSuccess");
            return true;
        }else if (url.contains("login")){
            LogUtils.e("需要登录哦1");
            try {
                Class cla=Class.forName("com.sina.rwxchina.aichediandianbussiness.login.activity.LoginActivity");
                Intent login=new Intent(mC,cla);
                mC.startActivity(login);
                mC.finish();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return true;
        }else {
            view.loadUrl(url);
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        LogUtils.e("DefaultWebViewClient", "url:" + request.getUrl());
        if (request.getUrl().getFragment()!=null&&request.getUrl().getFragment().equals("app")){
            mC.finish();
        }
        if (request.getUrl().getFragment()!=null&&request.getUrl().getFragment().equals("login")){
            LogUtils.e("需要登录哦");
            try {
                Class cla=Class.forName("com.sina.rwxchina.aichediandianbussiness.login.activity.LoginActivity");
                Intent login=new Intent(mC,cla);
                mC.startActivity(login);
                mC.finish();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return true;
        }
        return super.shouldOverrideUrlLoading(view, request);
    }
}
