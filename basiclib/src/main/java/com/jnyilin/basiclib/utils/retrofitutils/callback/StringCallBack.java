package com.jnyilin.basiclib.utils.retrofitutils.callback;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jnyilin.basiclib.MyApplication;
import com.jnyilin.basiclib.utils.retrofitutils.entity.PageInfo;
import com.sinata.rwxchina.retrofitutils.exception.ApiException;
import com.sinata.rwxchina.retrofitutils.listener.OnNextCallBack;

import org.json.JSONObject;


/**
 * Created by HRR on 2017/9/18.
 */

public abstract class StringCallBack extends OnNextCallBack{
    public Context mC;
    public StringCallBack(){}
    public StringCallBack(Context context){
        this.mC=context;
    }
    @Override
    public void onNext(String result, String method) throws Exception{
//        Log.e("StringCallBack","result="+result);
        JSONObject jsonObject=null;
        String data=null;
        jsonObject=new JSONObject(result);
        int code=jsonObject.optInt("code");
        String msg=jsonObject.optString("msg");
        JSONObject pageinfo=jsonObject.optJSONObject("pageinfo");
        PageInfo info=null;
        if (pageinfo!=null){
            info=new Gson().fromJson(pageinfo.toString(),PageInfo.class);
        }
        switch (code){
            case 0:
                data= jsonObject.get("data").toString();
                onResultNext(data,method,code,msg,info);
                break;
            case 1:
                Toast.makeText(MyApplication.getContext(),msg,Toast.LENGTH_SHORT).show();
                break;
            case 2:
                //需要重新登录，需要传入上下文对象
//                if (mC!=null){
//                    UserUtils.isLogin(mC);
//                }
            default:
                if (!msg.equals("token解析错误")){
                    Toast.makeText(MyApplication.getContext(),msg,Toast.LENGTH_SHORT).show();
                }
                break;
        }


    }



    @Override
    public void onError(ApiException e, String method) {
        Log.e("StringCallBack","e="+e.getDisplayMessage());
        Toast.makeText(MyApplication.getContext(),e.getDisplayMessage(),Toast.LENGTH_SHORT).show();
        onResultError(e,method);
    }

    public abstract void onResultNext(String result, String method,int code,String msg,PageInfo pageInfo) throws Exception;
    public abstract void onResultError(ApiException e, String method);

}
