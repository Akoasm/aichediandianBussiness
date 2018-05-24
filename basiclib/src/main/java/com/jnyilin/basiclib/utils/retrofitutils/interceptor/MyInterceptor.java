package com.jnyilin.basiclib.utils.retrofitutils.interceptor;


import com.jnyilin.basiclib.MyApplication;
import com.jnyilin.basiclib.utils.retrofitutils.commonparametersutils.CommonParametersUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author HRR
 * @datetime 2017/10/24
 * @describe 添加公共请求参数拦截器
 */

public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request=addParams(request);
        return chain.proceed(request);
    }

    /**
     * 添加公共请求参数
     *
     * @param request
     * @return
     */
    private Request addParams(Request request) {
        //添加公共参数
        HttpUrl httpUrl = request.url()
                .newBuilder()
                .addQueryParameter("uid", CommonParametersUtils.getUid(MyApplication.getContext()))
                .addQueryParameter("token", CommonParametersUtils.getToken(MyApplication.getContext()))
                .build();
        request = request.newBuilder().url(httpUrl).build();
        return request;
    }

}
