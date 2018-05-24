package com.sina.rwxchina.aichediandianbussiness.my.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;

import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.utils.retrofitutils.ApiManager;
import com.jnyilin.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.jnyilin.basiclib.utils.retrofitutils.entity.PageInfo;
import com.jnyilin.basiclib.utils.rxUtils.RxPresenter;
import com.sina.rwxchina.aichediandianbussiness.my.contract.WithdrawStatusContract;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.WithdrawStatusBean;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/5
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class WithdrawStatusPresenter extends RxPresenter<WithdrawStatusContract.View> implements WithdrawStatusContract.Presenter<WithdrawStatusContract.View>{
    private BaseActivity baseActivity;
    private ApiManager apiManager;

    public WithdrawStatusPresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void getData(Map<String, String> map) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                    if (!TextUtils.isEmpty(result)){
                        WithdrawStatusBean withdrawStatusBean = new Gson().fromJson(result,WithdrawStatusBean.class);
                        mView.showView(withdrawStatusBean);
                    }
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.get(HttpPath.WITHDRAWSTATUS,map);
    }
}
