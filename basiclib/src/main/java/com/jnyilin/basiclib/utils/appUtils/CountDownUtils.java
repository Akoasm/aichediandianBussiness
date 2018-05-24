package com.jnyilin.basiclib.utils.appUtils;

import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * @author HRR
 * @datetime 2017/11/20
 * @describe 倒计时工具类
 * @modifyRecord
 */

public class CountDownUtils {
    private static String TAG="CountDown";

    /**
     * 倒计时功能
     * @param view
     * @param count
     */
    public static void countDown(final TextView view, final int count){
        Observable.interval(0, 1, TimeUnit.SECONDS)//设置0延迟，每隔一秒发送一条数据
                .take(count) //设置循环count次
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return count-aLong; //
                    }
                })
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        view.setEnabled(false);//在发送数据的时候设置为不能点击
                    }
                })

                .observeOn(AndroidSchedulers.mainThread())//操作UI主要在UI线程
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                        view.setText("重新获取");//数据发送完后设置为原来的文字
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Long aLong) { //接受到一条就是会操作一次UI
                        Log.d(TAG, "onNext: "+aLong);
                        view.setText(aLong-1+"S");
                        view.setEnabled(true);
                    }
                });
    }
}
