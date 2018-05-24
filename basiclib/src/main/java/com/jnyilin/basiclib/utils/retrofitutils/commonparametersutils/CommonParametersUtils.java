package com.jnyilin.basiclib.utils.retrofitutils.commonparametersutils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author HRR
 * @datetime 2017/10/25
 * @describe 请求公共参数工具类
 */

public class CommonParametersUtils {
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static final String SHARENAME="commonParameters";
    /**
     * 获取uid
     * @param context
     * @return
     */
    public static String getUid(Context context){
        preferences =context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE);
        return preferences.getString("uid","");
    }

    /**
     * 保存uid
     * @param context
     * @param uid
     */
    public static void saveUid(Context context,String uid){
        editor=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE).edit();
        editor.putString("uid",uid);
        editor.commit();
    }

    /**
     * 获取token
     * @param context
     * @return
     */
    public static String getToken(Context context){
        preferences=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE);
        return preferences.getString("token","");
    }

    /**
     * 保存phone
     * @param context
     * @param phone
     */
    public static void savePhone(Context context,String phone){
        editor=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE).edit();
        editor.putString("phone",phone);
        editor.commit();
    }
    /**
     * 获取phone
     * @param context
     * @return
     */
    public static String getPhone(Context context){
        preferences=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE);
        return preferences.getString("phone","");
    }

    /**
     * 保存pwd
     * @param context
     * @param pwd
     */
    public static void savePwd(Context context,String pwd){
        editor=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE).edit();
        editor.putString("pwd",pwd);
        editor.commit();
    }
    /**
     * 获取pwd
     * @param context
     * @return
     */
    public static String getPwd(Context context){
        preferences=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE);
        return preferences.getString("pwd","");
    }

    /**
     * 保存token
     * @param context
     * @param token
     */
    public static void saveToken(Context context,String token){
        editor=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE).edit();
        editor.putString("token",token);
        editor.commit();
    }


    /**
     * 获取身份
     * @param context
     * @return
     */
     public static boolean getIdentity(Context context){
         preferences=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE);
         return preferences.getBoolean("isAgent",false);
     }
    /**
     * 保存身份
     * @param context
     * @param isAgent
     */
    public static void saveIdentity(Context context,boolean isAgent){
        editor=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE).edit();
        editor.putBoolean("isAgent",isAgent);
        editor.commit();
    }

    /**
     * 清空登陆数据
     * @param context
     */
    public static void clearParams(Context context){
        editor=context.getSharedPreferences(SHARENAME,Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }
}
