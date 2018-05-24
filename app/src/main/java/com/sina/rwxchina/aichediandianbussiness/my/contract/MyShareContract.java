package com.sina.rwxchina.aichediandianbussiness.my.contract;

import com.jnyilin.basiclib.utils.rxUtils.BaseContract;

import java.util.Map;

/**
 * @author:zy
 * @detetime:2018/2/2
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface MyShareContract {
    interface View extends BaseContract.BaseView{
        void showView(String result);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getData();

    }
}
