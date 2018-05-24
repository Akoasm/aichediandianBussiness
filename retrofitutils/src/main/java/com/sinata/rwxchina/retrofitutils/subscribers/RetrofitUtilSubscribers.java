package com.sinata.rwxchina.retrofitutils.subscribers;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.sinata.rwxchina.retrofitutils.api.BaseApi;
import com.sinata.rwxchina.retrofitutils.cookie.CookieResulte;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;
import com.sinata.rwxchina.retrofitutils.exception.CodeException;
import com.sinata.rwxchina.retrofitutils.exception.HttpTimeException;
import com.sinata.rwxchina.retrofitutils.listener.OnNextCallBack;
import com.sinata.rwxchina.retrofitutils.progressutils.LoadDialog;
import com.sinata.rwxchina.retrofitutils.utils.AppUtil;
import com.sinata.rwxchina.retrofitutils.utils.CookieDbUtil;

import java.lang.ref.SoftReference;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by HRR on 2017/9/15.
 */

public class RetrofitUtilSubscribers<T> extends Subscriber<T> {
    /*是否弹框*/
    private boolean showPorgress = true;
    private Dialog pd;
    /**软引用反正内存泄露*/
    private SoftReference<Context> mActivity;
    private OnNextCallBack onNextCallBack;
    private BaseApi api;
    public RetrofitUtilSubscribers(Dialog dl,BaseApi api,SoftReference<Context> mActivity,OnNextCallBack onNextCallBack){
        this.pd=dl;
        this.api=api;
        this.mActivity = mActivity;
        this.onNextCallBack=onNextCallBack;
        setShowPorgress(api.isShowProgress());
        if (api.isShowProgress()){
            initProgress(api.isCancel());
        }
    }

    public boolean isShowPorgress() {
        return showPorgress;
    }

    public void setShowPorgress(boolean showPorgress) {
        this.showPorgress = showPorgress;
    }

    /**
     * 订阅开始
     */
    @Override
    public void onStart() {
        showProgress();
        /*需要缓存并且有网*/
        if (api.isCache() && AppUtil.isNetworkAvailable(mActivity.get().getApplicationContext())) {
             /*获取缓存数据*/
            CookieResulte cookieResulte = CookieDbUtil.getInstance(mActivity.get().getApplicationContext()).queryCookieBy(api.getUrl());
            if (cookieResulte != null) {
                long time = (System.currentTimeMillis() - cookieResulte.getTime()) / 1000;
                if (time < api.getCookieNetWorkTime()) {
                    if (onNextCallBack!= null) {
                        try {
                            onNextCallBack.onNext(cookieResulte.getResulte(), api.getMethod());
                        } catch (Exception e) {
                            Log.e("RetrofitUtilSubscribers","onStart异常:"+e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    onCompleted();
                    unsubscribe();
                }
            }
        }
    }

    /**
     * 初始化加载框
     */
    private void initProgress(boolean cancel){
        Context context = mActivity.get();
        if (!api.isShowProgress()){
            return;
        }
        if (pd==null){
            pd=new LoadDialog(context);
        }
        pd.setCancelable(cancel);
        if (cancel){
            pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    onCancelProgress();
                }
            });
        }
    }

    /**
     * 显示加载框
     */
    private void showProgress(){
        if (pd==null){
            return;
        }
        if (!pd.isShowing()){
            pd.show();
        }
    }

    /**
     * 取消加载框
     */
    private void dismissProgress(){
        if (pd==null){
            return;
        }
        if (pd.isShowing()){
            pd.dismiss();
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    @Override
    public void onCompleted() {
        Log.e("Tag","-------->onCompleted");
        dismissProgress();
    }

    @Override
    public void onError(Throwable e) {
        Log.e("Tag","-------->onError,e="+e.toString());
        if (onNextCallBack!=null){
            errorDo(e);
        }
        /*需要緩存并且本地有缓存才返回*/
        if (api.isCache()) {
            getCache();
        } else {

        }
        dismissProgress();


    }

    @Override
    public void onNext(T t) {
        Log.e("Tag","-------->onNext"+t.toString());
        /*缓存处理*/
        if (api.isCache()) {
            CookieResulte resulte = CookieDbUtil.getInstance(mActivity.get().getApplicationContext()).queryCookieBy(api.getUrl());
            long time = System.currentTimeMillis();
            /*保存和更新本地数据*/
            if (resulte == null) {
                resulte = new CookieResulte(api.getUrl(), t.toString(), time);
                CookieDbUtil.getInstance(mActivity.get().getApplicationContext()).saveCookie(resulte);
            } else {
                resulte.setResulte(t.toString());
                resulte.setTime(time);
                CookieDbUtil.getInstance(mActivity.get().getApplicationContext()).updateCookie(resulte);
            }
        }
//        dismissProgress();
        if (onNextCallBack!=null){
            try {
                onNextCallBack.onNext(t.toString(), api.getMethod());
            } catch (Exception e) {
                Log.e("RetrofitUtilSubscribers","onNext异常:"+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * 错误统一处理
     *
     * @param e
     */
    private void errorDo(Throwable e) {
        Context context = mActivity.get();
        if (context == null) {
            return;
        }
        OnNextCallBack httpOnNextListener = onNextCallBack;
        if (httpOnNextListener == null) {
            return;
        }
        if (e instanceof ApiException) {
            Log.e("Tag","-------->ApiException");
            httpOnNextListener.onError((ApiException) e,api.getMethod());
        } else if (e instanceof HttpTimeException) {
            Log.e("Tag","-------->HttpTimeException");
            HttpTimeException exception = (HttpTimeException) e;
            httpOnNextListener.onError(new ApiException(exception, CodeException.RUNTIME_ERROR, exception.getMessage()),api.getMethod());
        } else {
            Log.e("Tag","-------->else");
            httpOnNextListener.onError(new ApiException(e, CodeException.UNKNOWN_ERROR, e.getMessage()),api.getMethod());
        }
        /*可以在这里统一处理错误处理-可自由扩展*/
//        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取cache数据
     */
    private void getCache() {
        Observable.just(api.getUrl()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                errorDo(e);
            }

            @Override
            public void onNext(String s) {
                           /*获取缓存数据*/
                CookieResulte cookieResulte = CookieDbUtil.getInstance(mActivity.get().getApplicationContext()).queryCookieBy(s);
                if (cookieResulte == null) {
                    throw new HttpTimeException(HttpTimeException.NO_CHACHE_ERROR);
                }
                long time = (System.currentTimeMillis() - cookieResulte.getTime()) / 1000;
                if (time < api.getCookieNoNetWorkTime()) {
                    if (onNextCallBack != null) {
                        try {
                            onNextCallBack.onNext(cookieResulte.getResulte(), api.getMethod());
                        } catch (Exception e) {
                            Log.e("RetrofitUtilSubscribers","Cache.onNext异常:"+e.getMessage());
                            e.printStackTrace();
                        }
                    }
                } else {
                    CookieDbUtil.getInstance(mActivity.get().getApplicationContext()).deleteCookie(cookieResulte);
                    throw new HttpTimeException(HttpTimeException.CHACHE_TIMEOUT_ERROR);
                }
            }
        });
    }

}
