package com.sina.rwxchina.aichediandianbussiness.my.contract;



import com.jnyilin.basiclib.utils.rxUtils.BaseContract;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.WithDrawBean;

import java.util.List;

/**
 * @author:zy
 * @detetime:2017/11/30
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface BankCardContract {
    interface View extends BaseContract.BaseView{
        void showView(List<WithDrawBean.BankBean> bankCardBeen);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getData();
    }
}
