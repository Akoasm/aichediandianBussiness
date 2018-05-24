package com.jnyilin.basiclib.utils.webViewUtils.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.jnyilin.basiclib.R;
import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.base.BaseFragment;
import com.jnyilin.basiclib.utils.logUtils.LogUtils;
import com.jnyilin.basiclib.utils.retrofitutils.commonparametersutils.CommonParametersUtils;
import com.jnyilin.basiclib.utils.webViewUtils.DefaultWebChromeClient;
import com.jnyilin.basiclib.utils.webViewUtils.DefaultWebViewClient;
import com.just.agentweb.AgentWeb;

/**
 * @author HRR
 * @datetime 2017/11/27
 * @describe 默认fragment中webview
 * @modifyRecord
 */

public class DefaultWebViewFragment extends BaseFragment {
//    private LinearLayout linearLayout;
    private String url;
    private AgentWeb mAgentWeb;
    private String uid;
    private String token;
    private View toolbar;
    /**
     * 是否展示标题栏，默认展示
     */
    private boolean isTitle = false;
    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_defaultwebview;
    }

    @Override
    public void initView(View view,Bundle bundle) {
        Bundle bundle1=getArguments();
        url=bundle1.getString("url");
//        linearLayout=view.findViewById(R.id.default_webview);
        toolbar = view.findViewById(R.id.web_toolbar);


    }

    @Override
    public void setListener() {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        uid = CommonParametersUtils.getUid(getContext());
        token = CommonParametersUtils.getToken(getContext());
        //将uid和token拼接到请求地址中
        if (url.contains("?")){
            url=url+"&uid="+uid+"&token="+token;
        }else {
            url=url+"?uid="+uid+"&token="+token;
        }
        LogUtils.e("DefaultWebViewFragment","DefaultWebViewFragment:"+url);
        setmAgentWeb(view,url);
        toolbar.setVisibility(View.GONE);
    }

    public void setmAgentWeb(View view, String url){
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
        lp.weight=1f;
        mAgentWeb=AgentWeb.with(this)
                .setAgentWebParent((LinearLayout)view, lp)//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator()// 使用默认进度条
                .setWebChromeClient(new DefaultWebChromeClient(getActivity()))
                .setWebViewClient(new DefaultWebViewClient((BaseActivity) getActivity()))
                .createAgentWeb()//
                .ready()
                .go(url);
        mAgentWeb.getWebCreator().get().setOverScrollMode(WebView.OVER_SCROLL_NEVER);
    }


    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }


    @Override
    public boolean onFragmentKeyDown(int keyCode, KeyEvent event) {
        return mAgentWeb.handleKeyEvent(keyCode, event);
    }

    @Override
    public void onDestroyView() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroyView();
    }


}
