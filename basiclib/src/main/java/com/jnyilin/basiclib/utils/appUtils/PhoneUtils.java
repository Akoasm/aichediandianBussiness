package com.jnyilin.basiclib.utils.appUtils;

import android.os.Build;

/**
 * Created by HRR on 2017/10/11.
 * 手机工具类
 */

public class PhoneUtils {

    /**
     * 获取手机厂商
     * @return
     */
    public static String getPhoneManufacturer(){
        return Build.MANUFACTURER;
    }

    /**
     * 获取手机型号
     * @return
     */
    public static String getPhoneModel(){
        return Build.MODEL;
    }

    /**
     * 获取系统型号
     * @return
     */
    public static String getSystemType(){
        return Build.VERSION.RELEASE;
    }
}
