package com.sina.rwxchina.aichediandianbussiness.my.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.utils.retrofitutils.ApiManager;
import com.jnyilin.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.jnyilin.basiclib.utils.retrofitutils.entity.PageInfo;
import com.jnyilin.basiclib.utils.rxUtils.RxPresenter;
import com.sina.rwxchina.aichediandianbussiness.my.contract.BankCardContract;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.BankCardBean;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.WithDrawBean;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/11/30
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class BankCardPresenter extends RxPresenter<BankCardContract.View> implements BankCardContract.Presenter<BankCardContract.View>{
    private ApiManager apiManager;
    private BaseActivity baseActivity;

    public BankCardPresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void getData() {
         apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                if (!TextUtils.isEmpty(result)){
                    List<WithDrawBean.BankBean> list = new Gson().fromJson(result,new TypeToken<List<WithDrawBean.BankBean>>(){}.getType());
                    mView.showView(list);
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        Map<String,String> map = new HashMap<>();
        apiManager.get(HttpPath.BANKCARDLIST,map);
    }
}
