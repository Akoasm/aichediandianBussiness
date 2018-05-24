package com.sina.rwxchina.aichediandianbussiness.my.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jnyilin.basiclib.base.BaseActivity;
import com.sina.rwxchina.aichediandianbussiness.R;


/**
 * @author:zy
 * @detetime:2018/2/2
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class BusinessAllianceActivity extends BaseActivity{
    private WebView webView;
    private WebSettings mWebSettings;
    private TextView title,right_Icon;
    private ImageView back;
    private String url,uid;
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
        webView = view.findViewById(R.id.couponRule_web);
        View titleView = view.findViewById(R.id.couponRule_title);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        back = titleView.findViewById(R.id.back);
        right_Icon = titleView.findViewById(R.id.rightIcon_tv);
        titleView.setVisibility(View.GONE);
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
}
