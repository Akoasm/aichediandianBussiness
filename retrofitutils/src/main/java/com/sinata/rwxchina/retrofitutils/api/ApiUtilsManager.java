package com.sinata.rwxchina.retrofitutils.api;


import com.sinata.rwxchina.retrofitutils.http.ApiUtils;
import com.sinata.rwxchina.retrofitutils.listener.OnNextCallBack;
import com.sinata.rwxchina.retrofitutils.progressutils.LoadDialog;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by HRR on 2017/9/15.
 */

public class ApiUtilsManager<T> extends BaseApi {
    private ApiUtils apiUtils;
    private OkHttpClient.Builder builder;

    public ApiUtilsManager() {
    }

    public OkHttpClient.Builder getBuilder() {
        return builder;
    }

    public void setBuilder(OkHttpClient.Builder builder) {
        this.builder = builder;
        this.apiUtils.setBuilder(getBuilder());
    }

    public ApiUtilsManager(RxAppCompatActivity appCompatActivity, OnNextCallBack onNextCallBack) {
        LoadDialog dialog=new LoadDialog(appCompatActivity);
        this.apiUtils = new ApiUtils(appCompatActivity,onNextCallBack);
    }

    protected Retrofit getRetrofit(){
        return apiUtils.getRetrofit(this);
    }
    protected void doHttpDeal(Observable observable){
        apiUtils.httpDeal(observable,this);
    }
    @Override
    public Observable getObservable(Retrofit retrofit) {
        return null;
    }
}
