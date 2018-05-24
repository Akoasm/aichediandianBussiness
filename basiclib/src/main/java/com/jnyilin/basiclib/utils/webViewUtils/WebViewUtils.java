package com.jnyilin.basiclib.utils.webViewUtils;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.ChromeClientCallbackManager;
import com.just.agentweb.MiddleWareWebChromeBase;
import com.just.agentweb.MiddleWareWebClientBase;
import com.just.agentweb.WebDefaultSettingsManager;


/**
 * Created by HRR on 2017/9/25.
 * AgentWeb封装类，WebView的使用
 */

public class WebViewUtils{
    private AgentWeb mAgentWeb;
    private ViewGroup mViewGroup;
    private WebChromeClient mWebChromeClient;
    private WebViewClient mWebViewClient;
    public WebViewUtils(ViewGroup mViewGroup, WebChromeClient mWebChromeClient, WebViewClient mWebViewClient) {
        this.mViewGroup = mViewGroup;
        this.mWebChromeClient = mWebChromeClient;
        this.mWebViewClient = mWebViewClient;
    }
    public WebViewUtils(ViewGroup mViewGroup, WebChromeClient mWebChromeClient) {
        this.mViewGroup = mViewGroup;
        this.mWebChromeClient = mWebChromeClient;
        this.mWebViewClient = new WebViewClient();
    }
    public WebViewUtils(ViewGroup mViewGroup, WebViewClient mWebViewClient) {
        this.mViewGroup = mViewGroup;
        this.mWebChromeClient = new WebChromeClient();
        this.mWebViewClient = mWebViewClient;
    }
    public WebViewUtils(ViewGroup mViewGroup) {
        this.mViewGroup = mViewGroup;
        this.mWebChromeClient = new WebChromeClient();
        this.mWebViewClient = new WebViewClient();
    }

    public ViewGroup getmViewGroup() {
        return mViewGroup;
    }

    public void setmViewGroup(ViewGroup mViewGroup) {
        this.mViewGroup = mViewGroup;
    }

    public WebChromeClient getmWebChromeClient() {
        return mWebChromeClient;
    }

    public void setmWebChromeClient(WebChromeClient mWebChromeClient) {
        this.mWebChromeClient = mWebChromeClient;
    }

    public WebViewClient getmWebViewClient() {
        return mWebViewClient;
    }

    public void setmWebViewClient(WebViewClient mWebViewClient) {
        this.mWebViewClient = mWebViewClient;
    }

    /**
     * 设置webview，activity环境下
     * @param url
     * @param activity
     */
    public void setmAgentWeb(String url, Activity activity){
        mAgentWeb=AgentWeb.with(activity)
                .setAgentWebParent(this.mViewGroup, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .setAgentWebSettings(WebDefaultSettingsManager.getInstance())
                .setWebChromeClient(this.mWebChromeClient)
                .setWebViewClient(this.mWebViewClient)
                .createAgentWeb()//
                .ready()
                .go(url);
    }

    /**
     * 设置webview，fragment环境下
     * @param url
     * @param fragment
     */
    public void setmAgentWeb(String url,Fragment fragment){
        mAgentWeb=AgentWeb.with(fragment)
                .setAgentWebParent(this.mViewGroup, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator()// 使用默认进度条
                .setWebChromeClient(this.mWebChromeClient)
                .setWebViewClient(this.mWebViewClient)
                .createAgentWeb()//
                .ready()
                .go(url);
    }

    /**
     * 设置带标题webview
     * @param url
     * @param activity
     * @param receivedTitleCallback
     */
    public void setTitleWeb(String url, Activity activity,ChromeClientCallbackManager.ReceivedTitleCallback receivedTitleCallback){
        mAgentWeb=AgentWeb.with(activity)
                .setAgentWebParent(this.mViewGroup, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .setAgentWebSettings(WebDefaultSettingsManager.getInstance())
                .setReceivedTitleCallback(receivedTitleCallback)
                .setWebChromeClient(this.mWebChromeClient)
                .setWebViewClient(this.mWebViewClient)
                .createAgentWeb()//
                .ready()
                .go(url);
    }

    /**
     * 设置带标题webview
     * @param url
     * @param fragment
     * @param receivedTitleCallback
     */
    public void setTitleWeb(String url, Fragment fragment,ChromeClientCallbackManager.ReceivedTitleCallback receivedTitleCallback){
        mAgentWeb=AgentWeb.with(fragment)
                .setAgentWebParent(this.mViewGroup, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator()// 使用默认进度条
                .setReceivedTitleCallback(receivedTitleCallback)
                .setWebChromeClient(this.mWebChromeClient)
                .setWebViewClient(this.mWebViewClient)
                .createAgentWeb()//
                .ready()
                .go(url);
    }



    /**
     * 获取webview
     * @return
     */
    public WebView getWebView(){
        if (mAgentWeb!=null){
            return mAgentWeb.getWebCreator().get();
        }else {
            return null;
        }
    }

    /**
     * 获取AgentWeb
     * @return
     */
    public AgentWeb getmAgentWeb(){
        if (mAgentWeb!=null){
            return mAgentWeb;
        }else {
            return null;
        }
    }

    //TODO 其他功能仍需完善

}
