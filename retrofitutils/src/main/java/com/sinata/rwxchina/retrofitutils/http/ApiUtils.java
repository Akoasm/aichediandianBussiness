package com.sinata.rwxchina.retrofitutils.http;

import android.app.Dialog;

import com.sinata.rwxchina.retrofitutils.api.BaseApi;
import com.sinata.rwxchina.retrofitutils.exception.RetryWhenNetworkException;
import com.sinata.rwxchina.retrofitutils.http.func.ExceptionFunc;
import com.sinata.rwxchina.retrofitutils.http.func.ResulteFunc;
import com.sinata.rwxchina.retrofitutils.interceptor.LoggingInterceptor;
import com.sinata.rwxchina.retrofitutils.listener.OnNextCallBack;
import com.sinata.rwxchina.retrofitutils.subscribers.RetrofitUtilSubscribers;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by HRR on 2017/9/13.
 */

public class ApiUtils{
    //使用软引用对象
    private OnNextCallBack onNextCallBack;
    private SoftReference<RxAppCompatActivity> appCompatActivity;
    private Dialog dialog;
    private OkHttpClient.Builder builder;
    public ApiUtils(RxAppCompatActivity appCompatActivity,OnNextCallBack onNextCallBack) {
        this.onNextCallBack = onNextCallBack;
        this.appCompatActivity = new SoftReference<>(appCompatActivity);
    }

    public OkHttpClient.Builder getBuilder() {
        return builder;
    }

    public void setBuilder(OkHttpClient.Builder builder) {
        this.builder = builder;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    /**
     * 获取retrofit实例
     * @return
     */
    public Retrofit getRetrofit(BaseApi api){
        if (builder==null){
            builder = new OkHttpClient.Builder();
        }
        //设置超时时间缓存等设置
        builder.connectTimeout(api.getConnectionTime(), TimeUnit.SECONDS);
        builder.addInterceptor(new LoggingInterceptor());
        /*创建retrofit对象*/
        final Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(api.getBaseUrl())
                .build();
        return retrofit;
    }
    public void httpDeal(Observable observable, BaseApi api){
        observable=observable
                /*请求失败后retry的设置*/
                .retryWhen(new RetryWhenNetworkException(api.getRetryCount(),api.getRetryDelay(),api.getRetryIncreaseDelay()))
                /*异常处理*/
                .onErrorResumeNext(new ExceptionFunc())
                 /*返回数据统一判断*/
                .map(new ResulteFunc())
                /*http请求线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                /*生命周期管理*/
                .compose(appCompatActivity.get().bindToLifecycle())
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread());
        //回调结果（弹出加载框）
        if (onNextCallBack!=null){
            RetrofitUtilSubscribers retrofitUtilSubscribers=new RetrofitUtilSubscribers(dialog,api,appCompatActivity,onNextCallBack);
            observable.subscribe(retrofitUtilSubscribers);
        }
    }
}
