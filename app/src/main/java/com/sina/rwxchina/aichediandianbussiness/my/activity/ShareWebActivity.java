package com.sina.rwxchina.aichediandianbussiness.my.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.base.BaseActivity;
import com.sina.rwxchina.aichediandianbussiness.R;
import com.sina.rwxchina.aichediandianbussiness.my.contract.MyShareContract;
import com.sina.rwxchina.aichediandianbussiness.my.presenter.MySharepresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * @author:zy
 * @detetime:2018/1/15
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class ShareWebActivity extends BaseActivity implements PlatformActionListener, MyShareContract.View {
    private WebView webView;
    private WebSettings mWebSettings;
    private TextView title, cancel, right_Icon;
    private ImageView back;
    private String url, content, url_share, share_title, uid;
    private PopupWindow pop;
    private RelativeLayout mQQ, mWeixin, wechatmoments, qzone;
    private View fview;
    private MySharepresenter mySharepresenter;


    @Override
    public void initParms(Bundle params) {
        uid = params.getString("uid");
        url = HttpPath.WEB_MYSHARE+uid;
        mySharepresenter = new MySharepresenter(this);
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
        fview = view;
        webView = view.findViewById(R.id.couponRule_web);
        View titleView = view.findViewById(R.id.couponRule_title);
        title = titleView.findViewById(R.id.titleLayout_title_tv);
        back = titleView.findViewById(R.id.back);
        right_Icon = titleView.findViewById(R.id.rightIcon_tv);
        view = LayoutInflater.from(this).inflate(R.layout.pop_share, null);
        pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mQQ = view.findViewById(R.id.activity_share_dialog_qq);
        mWeixin = view.findViewById(R.id.activity_share_dialog_weixin);
        wechatmoments = view.findViewById(R.id.activity_share_dialog_weixinmoments);
        qzone = view.findViewById(R.id.activity_share_dialog_qqzone);
        cancel = view.findViewById(R.id.pop_cancel_tv);
        titleView.setVisibility(View.GONE);

    }

    /**
     * 0微信 1朋友圈
     *
     * @param flag
     */
    private void wechat(int flag) {
        Platform.ShareParams wechat = null;
        Platform weixin = null;
        if (flag == 0) {
            weixin = ShareSDK.getPlatform(Wechat.NAME);
            wechat = new Wechat.ShareParams();
        } else {
            weixin = ShareSDK.getPlatform(WechatMoments.NAME);
            wechat = new WechatMoments.ShareParams();
        }
        wechat.setTitle(share_title);
        wechat.setText(content);
        wechat.setUrl(url_share);
        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.drawable.acdd_log);
        wechat.setImageData(thumb);
        wechat.setShareType(Platform.SHARE_WEBPAGE);
        weixin.setPlatformActionListener(this);
        weixin.share(wechat);

    }

    /**
     * 0 qq 1 qqzone
     *
     * @param flag
     */
    private void qq(int flag) {
        Platform qq = null;
        Platform.ShareParams sp = null;
        if (flag == 0) {
            qq = ShareSDK.getPlatform(QQ.NAME);
            sp = new QQ.ShareParams();
        } else {
            qq = ShareSDK.getPlatform(QZone.NAME);
            sp = new QZone.ShareParams();
        }
        sp.setTitle(share_title);
        sp.setTitleUrl(url_share); // 标题的超链接
        sp.setText(content);
        sp.setSite(share_title);
        qq.setPlatformActionListener(this);
        qq.share(sp);
    }

    @Override
    public void setListener() {
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(ShareWebActivity.this, 1f);
            }
        });
        mQQ.setOnClickListener(this);
        mWeixin.setOnClickListener(this);
        wechatmoments.setOnClickListener(this);
        qzone.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        if (v.getId() == R.id.activity_share_dialog_qq) {
            qq(0);
        } else if (v.getId() == R.id.activity_share_dialog_weixin) {
            wechat(0);
        } else if (v.getId() == R.id.activity_share_dialog_weixinmoments) {
            wechat(1);
        } else if (v.getId() == R.id.activity_share_dialog_qqzone) {
            qq(1);
        } else if (v.getId() == R.id.pop_cancel_tv) {
            pop.dismiss();
        }
    }

    @Override
    public void doBusiness(Context mContext) {
        mySharepresenter.attachView(this);
        mySharepresenter.getData();
    }


    public void setWebView() {
        setTitle();
        mWebSettings = webView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebSettings.setSupportZoom(true);
        mWebSettings.setBuiltInZoomControls(false);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                ShareWebActivity.this.title.setText(title);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("ShowShare") || url.contains("Spread"))
                    showPop();
                else if (url.contains("#app"))
                    finish();
                else if (url.contains("#share"))
                    showPop();
                return true;
            }
        });
        webView.loadUrl(url);
    }

    private void showPop() {
        pop.setBackgroundDrawable(this.getResources().getDrawable(R.color.actionsheet_gray));
        pop.setOutsideTouchable(true);
        pop.setAnimationStyle(R.style.popbottom_anim_style);
        pop.showAtLocation(fview, Gravity.BOTTOM, 0, 0);
        backgroundAlpha(this, 0.3f);
    }

    public void onCancel(Platform platform, int action) {
        Toast.makeText(ShareWebActivity.this, "分享取消", Toast.LENGTH_LONG).show();
        pop.dismiss();
    }

    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> arg2) {
        Toast.makeText(ShareWebActivity.this, "分享成功", Toast.LENGTH_LONG).show();
        pop.dismiss();

    }

    @Override
    public void onError(Platform platform, int action, Throwable t) {
        Toast.makeText(ShareWebActivity.this, "分享失败", Toast.LENGTH_LONG).show();
        pop.dismiss();

    }


    private void setTitle() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack())
                    webView.goBack();
                else
                    finish();
            }
        });
    }

    @Override
    public void showView(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            share_title = jsonObject.optString("title");
            content = jsonObject.optString("content");
            url_share = jsonObject.optString("url") + "?uid="+uid;
            setWebView();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        mySharepresenter.detachView();
        super.onDestroy();
    }
}
