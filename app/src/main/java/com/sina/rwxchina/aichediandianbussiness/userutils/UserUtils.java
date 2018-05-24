package com.sina.rwxchina.aichediandianbussiness.userutils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.jnyilin.basiclib.utils.retrofitutils.commonparametersutils.CommonParametersUtils;
import com.jnyilin.basiclib.utils.toastUtils.ToastUtils;
import com.sina.rwxchina.aichediandianbussiness.login.activity.LoginActivity;


/**
 * @author HRR
 * @datetime 2018/1/17
 * @describe 用户工具类
 * @modifyRecord
 */

public class UserUtils {

    /**
     * 如果用户没有登录，则跳转到登录页面
     *
     * @param context
     */
    public static boolean isLogin(Context context) {
        if (TextUtils.isEmpty(CommonParametersUtils.getUid(context))) {
            ToastUtils.showShort("请登录后继续操作");
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            return false;
        } else {
            return true;
        }
    }
}
