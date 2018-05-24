package com.jnyilin.basiclib.utils.webViewUtils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.utils.logUtils.LogUtils;
import com.just.agentweb.AgentWeb;

import org.greenrobot.eventbus.EventBus;


/**
 * @author HRR
 * @datetime 2018/1/30
 * @describe
 * @modifyRecord
 */

public class AndroidAgentInterface implements View.OnClickListener{
    private Handler deliver = new Handler(Looper.getMainLooper());
    private AgentWeb agent;
    private Context context;
    private View popView;
    private PopupWindow pop;
    private RelativeLayout mQQ, mWeixin, wechatmoments, qzone;
    private TextView cancel;
    public AndroidAgentInterface(AgentWeb agent, Context context) {
        this.agent = agent;
        this.context = context;
    }



    @JavascriptInterface
    public void callAndroid(final String msg) {
        LogUtils.e("AndroidAgentInterface","js调用android："+msg);
        switch (msg){
            case "#app":
                ((BaseActivity)context).finish();
                break;
            case "#share":
//                initPopView();
                break;
            case "#infomationUpdateSuccess":
                EventBus.getDefault().post("infomationUpdateSuccess");
                break;
            default:
                break;
        }
    }

//    private void initPopView(){
//        popView = LayoutInflater.from(context).inflate(R.layout.pop_share, null);
//        pop = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//        mQQ = popView.findViewById(R.id.activity_share_dialog_qq);
//        mWeixin = popView.findViewById(R.id.activity_share_dialog_weixin);
//        wechatmoments = popView.findViewById(R.id.activity_share_dialog_weixinmoments);
//        qzone = popView.findViewById(R.id.activity_share_dialog_qqzone);
//        cancel = popView.findViewById(R.id.pop_cancel_tv);
//        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                backgroundAlpha((BaseActivity)context, 1f);
//            }
//        });
//        mQQ.setOnClickListener(this);
//        mWeixin.setOnClickListener(this);
//        wechatmoments.setOnClickListener(this);
//        qzone.setOnClickListener(this);
//        cancel.setOnClickListener(this);
//        showPop();
//    }
//
    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.activity_share_dialog_qq) {
//            qq(0);
//        } else if (v.getId() == R.id.activity_share_dialog_weixin) {
//            wechat(0);
//        } else if (v.getId() == R.id.activity_share_dialog_weixinmoments) {
//            wechat(1);
//        } else if (v.getId() == R.id.activity_share_dialog_qqzone) {
//            qq(1);
//        } else if (v.getId() == R.id.pop_cancel_tv) {
//            pop.dismiss();
//        }
    }
//
//    /**
//     * 0微信 1朋友圈
//     *
//     * @param flag
//     */
//    private void wechat(int flag) {
//        Platform.ShareParams wechat = null;
//        Platform weixin = null;
//        if (flag == 0) {
//            weixin = ShareSDK.getPlatform(Wechat.NAME);
//            wechat = new Wechat.ShareParams();
//        } else {
//            weixin = ShareSDK.getPlatform(WechatMoments.NAME);
//            wechat = new WechatMoments.ShareParams();
//        }
//        wechat.setTitle("");
//        wechat.setText("");
//        wechat.setUrl("");
//        Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.drawable.share_logo);
//        wechat.setImageData(thumb);
//        wechat.setShareType(Platform.SHARE_WEBPAGE);
//        weixin.setPlatformActionListener(this);
//        weixin.share(wechat);
//
//    }
//
//    /**
//     * 0 qq 1 qqzone
//     *
//     * @param flag
//     */
//    private void qq(int flag) {
//        Platform qq = null;
//        Platform.ShareParams sp = null;
//        if (flag == 0) {
//            qq = ShareSDK.getPlatform(QQ.NAME);
//            sp = new QQ.ShareParams();
//        } else {
//            qq = ShareSDK.getPlatform(QZone.NAME);
//            sp = new QZone.ShareParams();
//        }
//        sp.setTitle("");
//        sp.setTitleUrl(""); // 标题的超链接
//        sp.setText("");
//        sp.setSite("");
//        qq.setPlatformActionListener(this);
//        qq.share(sp);
//    }
//
//    private void showPop() {
//        pop.setBackgroundDrawable(context.getResources().getDrawable(R.color.actionsheet_gray));
//        pop.setOutsideTouchable(true);
//        pop.setAnimationStyle(R.style.popbottom_anim_style);
//        pop.showAtLocation(popView, Gravity.BOTTOM, 0, 0);
//        backgroundAlpha((BaseActivity)context, 0.3f);
//    }
//
//    @Override
//    public void onCancel(Platform platform, int action) {
//        Toast.makeText(context, "分享取消", Toast.LENGTH_LONG).show();
//        pop.dismiss();
//
//    }
//
//    @Override
//    public void onComplete(Platform platform, int action, HashMap<String, Object> arg2) {
//        Toast.makeText(context, "分享成功", Toast.LENGTH_LONG).show();
//        pop.dismiss();
//
//    }
//
//    @Override
//    public void onError(Platform platform, int action, Throwable t) {
//        Toast.makeText(context, "分享失败", Toast.LENGTH_LONG).show();
//        pop.dismiss();
//
//    }
//
//    /**
//     * 改变View透明度
//     * @param alpha
//     */
//    public void backgroundAlpha(Activity activity, float alpha){
//        WindowManager.LayoutParams lp =activity.getWindow().getAttributes();
//        lp.alpha =alpha;
//        ((BaseActivity)context).getWindow().setAttributes(lp);
//    }
}
