package com.sina.rwxchina.aichediandianbussiness.commodity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.utils.logUtils.LogUtils;
import com.jnyilin.basiclib.utils.webViewUtils.activity.DefaultWebViewActivity;
import com.sina.rwxchina.aichediandianbussiness.R;

/**
 * @author HRR
 * @datetime 2018/2/5
 * @describe
 * @modifyRecord
 */

public class WebActivity extends BaseActivity {
    private WebView webView;
    @Override
    public void initParms(Bundle params) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_couponrule;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        webView=findViewById(R.id.couponRule_web);
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
        String url=getIntent().getExtras().getString("url");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                return super.shouldOverrideUrlLoading(view, request);
//            }

            //            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return super.shouldOverrideUrlLoading(view, url);
//            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.e("WebCommodityFragment","WebCommodityFragment:"+url);
                Intent intent=new Intent(WebActivity.this, DefaultWebViewActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("url",url);
                intent.putExtras(bundle);
                startActivity(intent);
//                view.loadUrl(url);
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
