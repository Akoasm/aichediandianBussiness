package com.sina.rwxchina.aichediandianbussiness.my.presenter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;

import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.utils.retrofitutils.ApiManager;
import com.jnyilin.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.jnyilin.basiclib.utils.retrofitutils.entity.PageInfo;
import com.jnyilin.basiclib.utils.rxUtils.RxPresenter;
import com.sina.rwxchina.aichediandianbussiness.my.contract.WithdrawDetailContract;
import com.sina.rwxchina.aichediandianbussiness.my.entitiy.WithdrawDetailBean;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/12/1
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class WithdrawDetailPresenter extends RxPresenter<WithdrawDetailContract.View> implements WithdrawDetailContract.Presenter<WithdrawDetailContract.View> {
    private ApiManager apiManager;
    private BaseActivity baseActivity;

    public WithdrawDetailPresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void getData(@Nullable Map<String,String> params, final boolean isRefresh) {
      apiManager = new ApiManager(baseActivity, new StringCallBack() {
          @Override
          public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
              WithdrawDetailBean withdrawDetailBean =null;
                if (!TextUtils.isEmpty(result)){
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray list = jsonObject.getJSONArray("list");
                    if (list.length()==0) {
                        mView.showView(withdrawDetailBean,isRefresh);
                    }else{
                        withdrawDetailBean = new Gson().fromJson(result, WithdrawDetailBean.class);
                        mView.showView(withdrawDetailBean, isRefresh);
                        if (pageInfo != null)
                            mView.getPage(pageInfo);
                    }
                }
          }

          @Override
          public void onResultError(ApiException e, String method) {

          }
      });
        if (params==null)
            params = new HashMap<>();
        apiManager.get(HttpPath.WITHDRAWDETAILLIST,params);
    }

    @Override
    public void loadMore(Map<String,String> params) {
      apiManager = new ApiManager(baseActivity, new StringCallBack() {
          @Override
          public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                if (!TextUtils.isEmpty(result)){
                    WithdrawDetailBean withdrawDetailBean = new Gson().fromJson(result,WithdrawDetailBean.class);
                    mView.showLoadMore(withdrawDetailBean);
                    if (pageInfo!=null)
                        mView.getPage(pageInfo);
                }
          }

          @Override
          public void onResultError(ApiException e, String method) {

          }
      });
      apiManager.get(HttpPath.WITHDRAWDETAILLIST,params);
    }
}
