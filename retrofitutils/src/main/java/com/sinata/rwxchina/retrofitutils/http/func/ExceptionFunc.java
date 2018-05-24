package com.sinata.rwxchina.retrofitutils.http.func;

import android.util.Log;

import com.sinata.rwxchina.retrofitutils.exception.FactoryException;

import rx.Observable;
import rx.functions.Func1;

/**
 * 异常处理
 * Created by WZG on 2017/3/23.
 */

public class ExceptionFunc<String> implements Func1<Throwable, Observable<String>> {
    @Override
    public Observable<String> call(Throwable throwable) {
        Log.e("Tag","-------->"+throwable.getMessage());
        return Observable.error(FactoryException.analysisExcetpion(throwable));
    }
}
