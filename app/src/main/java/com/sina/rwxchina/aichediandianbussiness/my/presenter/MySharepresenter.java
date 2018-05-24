package com.sina.rwxchina.aichediandianbussiness.my.presenter;

import android.text.TextUtils;

import com.jnyilin.basiclib.HttpPath;
import com.jnyilin.basiclib.base.BaseActivity;
import com.jnyilin.basiclib.utils.retrofitutils.ApiManager;
import com.jnyilin.basiclib.utils.retrofitutils.callback.StringCallBack;
import com.jnyilin.basiclib.utils.retrofitutils.entity.PageInfo;
import com.jnyilin.basiclib.utils.rxUtils.RxPresenter;
import com.sina.rwxchina.aichediandianbussiness.my.contract.MyShareContract;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:zy
 * @detetime:2018/2/2
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class MySharepresenter extends RxPresenter<MyShareContract.View> implements MyShareContract.Presenter<MyShareContract.View>{
    private ApiManager apiManager;
    private BaseActivity baseActivity;

    public MySharepresenter(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void getData() {
        apiManager = new ApiManager(baseActivity, new StringCallBack() {
            @Override
            public void onResultNext(String result, String method, int code, String msg, PageInfo pageInfo) throws Exception {
                if (!TextUtils.isEmpty(result)){
                    mView.showView(result);
                }
            }

            @Override
            public void onResultError(ApiException e, String method) {

            }
        });
        Map<String,String> map = new HashMap<>();
        apiManager.get(HttpPath.MYSHARE,map);
    }
}
