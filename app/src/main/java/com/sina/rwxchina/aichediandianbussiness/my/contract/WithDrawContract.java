package com.sina.rwxchina.aichediandianbussiness.my.contract;


import com.jnyilin.basiclib.utils.rxUtils.BaseContract;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.WithDrawBean;

import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/11/29
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface WithDrawContract {
    interface View extends BaseContract.BaseView{
        void showView(WithDrawBean withDrawBean);
        void showVerificationCode(String msg);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getData();
        void commitData(Map<String,String> map);
        void getVerificationCode(Map<String,String> map);
    }
}
