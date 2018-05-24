package com.jnyilin.basiclib.utils.appUtils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

/**
 * @author HRR
 * @datetime 2017/11/29
 * @describe 拨打电话工具类
 * @modifyRecord
 */

public class CallPhoneUtils{
    /**
     * 调用拨号界面
     * @param phone 电话号码
     */
    public static void call(Context context, String phone) {
        if (TextUtils.isEmpty(phone)){
            return;
        }
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 调用拨号功能
     * @param phone 电话号码
     */
    public static void callPhone(Context context, String phone) {
        if (TextUtils.isEmpty(phone)){
            return;
        }
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(intent);
    }
}
