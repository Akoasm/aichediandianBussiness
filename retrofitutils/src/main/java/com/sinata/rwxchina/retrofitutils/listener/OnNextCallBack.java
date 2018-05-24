package com.sinata.rwxchina.retrofitutils.listener;


import com.sinata.rwxchina.retrofitutils.exception.ApiException;

/**
 * Created by HRR on 2017/9/15.
 */
public abstract class OnNextCallBack{

    /**
     * 回调成功
     */
     public abstract void onNext(String result, String method) throws Exception;

    /**
     * 回调失败
     */
    public abstract void onError(ApiException e, String method);
}
