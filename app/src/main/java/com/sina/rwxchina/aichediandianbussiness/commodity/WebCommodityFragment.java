package com.sina.rwxchina.aichediandianbussiness.commodity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.base.BaseFragment;
import com.jnyilin.basiclib.utils.logUtils.LogUtils;
import com.jnyilin.basiclib.utils.retrofitutils.commonparametersutils.CommonParametersUtils;
import com.jnyilin.basiclib.utils.webViewUtils.activity.DefaultWebViewActivity;
import com.sina.rwxchina.aichediandianbussiness.R;

/**
 * @author HRR
 * @datetime 2018/2/1
 * @describe 商品管理
 * @modifyRecord
 */

public class WebCommodityFragment  extends BaseFragment{
    private WebView webView;
    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_webcommodity;
    }

    @Override
    public void initView(View view, @Nullable Bundle savedInstanceState) {
        webView=view.findViewById(R.id.activity_webcommodity_webview);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {
        setWebView();
        String uid=CommonParametersUtils.getUid(getContext());
        String token=CommonParametersUtils.getToken(getContext());
        String url=HttpPath.H5COMMODITY+"?uid="+uid +"&token="+token;
        LogUtils.e("WebCommodityFragment","url:"+url);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.e("WebCommodityFragment","WebCommodityFragment:"+url);
                if (!url.contains(HttpPath.H5COMMODITY)){
                    Intent intent=new Intent(getActivity(), DefaultWebViewActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("url",url);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    return true;
                }
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(url);
    }

    /**
     * 设置webview
     */
    private void setWebView(){
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        settings.setSupportZoom(false);
        // 设置出现缩放工具
        settings.setBuiltInZoomControls(true);
        //扩大比例的缩放
        settings.setUseWideViewPort(true);
        //不显示webview缩放按钮false:隐藏收缩图标
        settings.setDisplayZoomControls(false);
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
    }
}
