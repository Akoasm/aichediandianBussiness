package com.sina.rwxchina.aichediandianbussiness.my.presenter;


import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.utils.retrofitutils.ApiManager;
import com.jnyilin.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.jnyilin.basiclib.utils.retrofitutils.entity.PageInfo;
import com.jnyilin.basiclib.utils.rxUtils.RxPresenter;
import com.sina.rwxchina.aichediandianbussiness.my.contract.AddBankCardContract;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.Map;

/**
 * @author:zy
 * @detetime:2017/11/23
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class AddBankCardPresenter extends RxPresenter<AddBankCardContract.View> implements AddBankCardContract.Presenter<AddBankCardContract.View> {
    private BaseActivity baseActivity;
    private ApiManager apiManager;
    private Params params;
    public interface Params{
        Map<String,String> addParams();
    }

    public AddBankCardPresenter(BaseActivity baseActivity,Params params) {
        this.baseActivity = baseActivity;
        this.params = params;
    }

    @Override
    public void commitData() {
      apiManager = new ApiManager(baseActivity, new StringCallBack() {
          @Override
          public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
              mView.showView();
          }

          @Override
          public void onResultError(ApiException e, String method) {

          }
      });
        apiManager.post(HttpPath.ADDBANKCARD,params.addParams());
    }
}
