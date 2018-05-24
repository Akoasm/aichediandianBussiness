package com.sina.rwxchina.aichediandianbussiness.my.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;

import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.utils.logUtils.LogUtils;
import com.jnyilin.basiclib.utils.retrofitutils.ApiManager;
import com.jnyilin.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.jnyilin.basiclib.utils.retrofitutils.entity.PageInfo;
import com.jnyilin.basiclib.utils.rxUtils.RxPresenter;
import com.jnyilin.basiclib.utils.toastUtils.ToastUtils;
import com.sina.rwxchina.aichediandianbussiness.my.contract.WithDrawContract;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.WithDrawBean;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/11/29
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class WithDrawPresenter extends RxPresenter<WithDrawContract.View> implements WithDrawContract.Presenter<WithDrawContract.View> {
    private ApiManager apiManager;
    private BaseActivity baseActivity;

    public WithDrawPresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void getData() {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                if (!TextUtils.isEmpty(result)) {
                    WithDrawBean withDrawBean = new Gson().fromJson(result, WithDrawBean.class);
                    mView.showView(withDrawBean);
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {
                LogUtils.e(e.toString());
            }
        });
        Map<String,String> map = new HashMap<>();
        apiManager.get(HttpPath.WITHDRAWDATA,map);
    }

    @Override
    public void commitData(Map<String,String> params) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                ToastUtils.showShort(msg);
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.WITHDRAW,params);
    }

    @Override
    public void getVerificationCode(Map<String, String> map) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
               mView.showVerificationCode(msg);
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.post(HttpPath.GETVERIFICATIONCODE,map);
    }
}
