package com.sina.rwxchina.aichediandianbussiness.my.contract;



import com.jnyilin.basiclib.utils.rxUtils.BaseContract;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.WithdrawStatusBean;

import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/5
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface WithdrawStatusContract {
    interface View extends BaseContract.BaseView{
        void showView(WithdrawStatusBean withdrawStatusBean);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getData(Map<String, String> map);
    }
}
