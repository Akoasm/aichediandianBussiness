package com.sinata.rwxchina.retrofitutils.http.func;

import com.sinata.rwxchina.retrofitutils.exception.HttpTimeException;

import rx.functions.Func1;

/**
 * Created by HRR on 2017/9/18.
 * 服务器返回数据判断
 */

public class ResulteFunc implements Func1<Object,Object> {
    @Override
    public Object call(Object o) {
        if (o == null || "".equals(o.toString())) {
            throw new HttpTimeException("数据错误");
        }
        return o;
    }
}
