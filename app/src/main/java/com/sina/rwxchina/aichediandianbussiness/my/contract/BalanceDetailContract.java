package com.sina.rwxchina.aichediandianbussiness.my.contract;


import com.jnyilin.basiclib.utils.retrofitutils.entity.PageInfo;
import com.jnyilin.basiclib.utils.rxUtils.BaseContract;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.BalanceDetailBean;

import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/11/23
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface BalanceDetailContract {
    interface View extends BaseContract.BaseView{
        void showView(List<BalanceDetailBean> balanceDetailBeen, boolean isRefresh);
        void getPage(PageInfo pageInfo);
        void showLoadMore(List<BalanceDetailBean> balanceDetailBeen);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getData(boolean isRefresh);
        void loadMore(Map<String, String> params);
    }
}
