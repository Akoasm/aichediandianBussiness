package com.jnyilin.basiclib.utils.retrofitutils;



import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.utils.retrofitutils.interceptor.MyInterceptor;
import com.jnyilin.basiclib.utils.retrofitutils.service.ApiService;
import com.sinata.rwxchina.retrofitutils.api.ApiUtilsManager;
import com.sinata.rwxchina.retrofitutils.listener.OnNextCallBack;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * Created by HRR on 2017/9/15.
 */

public class ApiManager extends ApiUtilsManager {
    private ApiService apiService;

    public ApiManager(BaseActivity appCompatActivity, OnNextCallBack onNextCallBack){
        super(appCompatActivity,onNextCallBack);
        setBuilder(setOkHttpBuilder());
        setBaseUrl(HttpPath.BASEURL);
        apiService=getRetrofit().create(ApiService.class);
    }

    /**
     * 该构造方法可实现baseUrl的改变
     * @param baseUrl
     * @param appCompatActivity
     * @param onNextCallBack
     */
    public ApiManager(String baseUrl,BaseActivity appCompatActivity, OnNextCallBack onNextCallBack){
        super(appCompatActivity,onNextCallBack);
        setBuilder(setOkHttpBuilder());
        setBaseUrl(baseUrl);
        apiService=getRetrofit().create(ApiService.class);
    }


    /**
     * 设置网络请求builder
     * @return
     */
    private OkHttpClient.Builder setOkHttpBuilder(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.addInterceptor(new MyInterceptor());
        return builder;
    }

    public ApiService getApiService() {
        return apiService;
    }

    /**
     * get方法
     * 默认显示加载进度条
     * 默认支持缓存
     * 默认缓存地址为path地址
     * @param path
     * @param params
     */
    public void get(String path, Map<String,String> params){
        setCache(true);
        setMethod(path);
        doHttpDeal(apiService.get(path,params));
    }



    /**
     * get方法
     * 自定义是否显示加载进度条
     * 默认支持缓存
     * 默认缓存地址为path地址
     * @param path
     * @param isShow
     * @param params
     */
    public void get(String path,boolean isShow,Map<String,String> params){
        setShowProgress(isShow);
        setCache(true);
        setMethod(path);
        doHttpDeal(apiService.get(path,params));
    }

    /**
     * get方法
     * 默认显示加载进度条
     * 默认支持缓存
     * 缓存地址为method
     * @param path
     * @param method
     * @param params
     */
    public void get(String path,String method,Map<String,String> params){
        setCache(true);
        setMethod(method);
        doHttpDeal(apiService.get(path,params));
    }

    /**
     * post方法
     * 默认显示加载进度条
     * 支持缓存
     * 默认缓存地址为path地址
     * @param path
     * @param params
     */
    public void post(String path,Map<String,String> params){
        setCache(true);
        setMethod(path);
        doHttpDeal(apiService.post(path,params));
    }


    /**
     * post方法
     * 自定义显示加载进度条
     * 支持缓存
     * 默认缓存地址为path地址
     * @param path
     * @param params
     */
    public void post(String path,Map<String,String> params,boolean isShow){
        setShowProgress(isShow);
        setCache(true);
        setMethod(path);
        doHttpDeal(apiService.post(path,params));
    }

    /**
     * post文件方法
     * 默认显示加载进度条
     * 支持缓存
     * 默认缓存地址为path地址
     * @param path
     * @param params
     */
    public void postFile(String path, Map<String,String> params,MultipartBody.Part[] file){
        setCache(true);
        setMethod(path);
        doHttpDeal(apiService.postFile(path,params,file));
    }
    /**
     * post方法
     * 默认显示加载进度条
     * 默认支持缓存
     * 缓存地址为method
     * @param path
     * @param method
     * @param params
     */
    public void post(String path,String method,Map<String,String> params){
        setCache(true);
        setMethod(method);
        doHttpDeal(apiService.post(path,params));
    }

    /**
     * get方法
     * 自定义是否显示加载进度条
     * 默认支持缓存
     * 默认缓存地址为path地址
     * @param path
     * @param isShow
     * @param params
     */
    public void post(String path,boolean isShow,Map<String,String> params){
        setShowProgress(isShow);
        setCache(true);
        setMethod(path);
        doHttpDeal(apiService.post(path,params));
    }

    /**
     * app更新接口
     * @param params
     */
    public void appUpdate(String path,Map<String,String> params){
        doHttpDeal(apiService.post(path,params));
    }

    public void postJson(String path,String json){
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),json);
        doHttpDeal(apiService.postJson(path,body));
    }


}
