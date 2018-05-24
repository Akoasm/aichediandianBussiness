package com.jnyilin.basiclib.utils.rxUtils;

/**
 * @author:zy
 * @detetime:2017/10/17
 * @describe：类描述
 */

public interface BaseContract  {
    interface BasePresenter<T> {

        void attachView(T view);

        void detachView();
    }

    interface BaseView {

    }
}
