package com.sina.rwxchina.aichediandianbussiness.my.contract;



import com.jnyilin.basiclib.utils.retrofitutils.entity.PageInfo;
import com.jnyilin.basiclib.utils.rxUtils.BaseContract;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.WithdrawDetailBean;

import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/1
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public interface WithdrawDetailContract {
    interface View extends BaseContract.BaseView{
        void showView(WithdrawDetailBean withdrawDetailBean, boolean isRefresh);
        void getPage(PageInfo pageInfo);
        void showLoadMore(WithdrawDetailBean withdrawDetailBean);
    }
    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getData(Map<String, String> params, boolean isRefresh);
        void loadMore(Map<String, String> params);
    }
}
