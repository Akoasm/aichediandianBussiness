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
import com.sina.rwxchina.aichediandianbussiness.my.contract.BalanceDetailContract;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.BalanceDetailBean;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/11/23
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class BalanceDetailPresenter extends RxPresenter<BalanceDetailContract.View> implements BalanceDetailContract.Presenter<BalanceDetailContract.View> {
    private ApiManager apiManager;
    private BaseActivity baseActivity;

    public BalanceDetailPresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }
    @Override
    public void getData(final boolean isRefresh) {
         apiManager = new ApiManager(baseActivity, new StringCallBack() {
             @Override
             public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                 List<BalanceDetailBean> list = null;
                 if (!TextUtils.isEmpty(result)){
                     JSONArray jsonArray = new JSONArray(result);
                     if (jsonArray.length()==0){
                         mView.showView(list,isRefresh);
                     }else {
                         list = new Gson().fromJson(result,new TypeToken<List<BalanceDetailBean>>(){}.getType());
                         mView.showView(list,isRefresh);
                         if (pageInfo!=null)
                             mView.getPage(pageInfo);
                     }
                 }
             }
             @Override
             public void onResultError(ApiException e, String method) {

             }
         });
        Map<String,String> map = new HashMap<>();
        apiManager.get(HttpPath.BALANCEDETAIL,map);
    }


    @Override
    public void loadMore(Map<String,String> params) {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                if (!TextUtils.isEmpty(result)){
                    List<BalanceDetailBean> list = new Gson().fromJson(result,new TypeToken<List<BalanceDetailBean>>(){}.getType());
                    mView.showLoadMore(list);
                    if (pageInfo!=null){
                        mView.getPage(pageInfo);
                    }
                }
            }
            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        apiManager.get(HttpPath.BALANCEDETAIL,params);
    }
}
