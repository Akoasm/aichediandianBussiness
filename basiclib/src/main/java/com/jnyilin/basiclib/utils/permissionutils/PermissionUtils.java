package com.jnyilin.basiclib.utils.permissionutils;

import android.app.Activity;

import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;

import rx.functions.Action1;

/**
 * @author HRR
 * @datetime 2017/12/25
 * @describe 权限控制工具类
 * @modifyRecord
 */

public class PermissionUtils {

    /**
     * 单独权限申请
     * @param activity
     * @param permission
     */
    public static void permissionOne(Activity activity, String permission){
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(permission)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            //用户同意使用write权限
                        }else {
                            //用户拒绝使用write权限
                        }
                    }
                });
    }
    /**
     * 单独权限申请，可添加申请回调
     * @param activity
     * @param permission
     * @param call 申请回调
     */
    public static void permissionOne(Activity activity, String permission, final PermissionCall call){
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(permission)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            call.success();
                            //用户同意使用write权限
                        }else {
                            call.fail();
                            //用户拒绝使用write权限
                        }
                    }
                });
    }

    /**
     * 同时申请多个权限
     * @param activity
     * @param permissions
     */
    public static void permissonMoreAll(Activity activity,String... permissions){
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(permissions)//这里填写所需要的权限
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            //当所有权限都允许之后，返回true
                        } else {
                            //只要有一个权限禁止，返回false，
                            //下一次申请只申请没通过申请的权限
                        }
                    }
                });
    }


    /**
     * 同时申请多个权限,可添加申请回调
     * @param activity
     * @param permissions
     * @param call
     */
    public static void permissonMoreAll(Activity activity, final PermissionCall call,String... permissions){
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(permissions)//这里填写所需要的权限
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            //当所有权限都允许之后，返回true
                            call.success();
                        } else {
                            //只要有一个权限禁止，返回false，
                            //下一次申请只申请没通过申请的权限
                            call.fail();
                        }
                    }
                });
    }



    /**
     * 分别申请多个申请
     * @param activity
     * @param permissions
     */
    public static void permissonMoreOne(Activity activity,String... permissions){
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.requestEach(permissions)
                .subscribe(new Action1<Permission>() {
                    @Override
                    public void call(Permission permission) {
                    }
                });
    }

    /**
     * 分别申请多个申请,可添加申请回调
     * @param activity
     * @param call
     * @param permissions
     */
    public static void permissonMoreOne(Activity activity, final PermissionCall call,String... permissions){
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.requestEach(permissions)
                .subscribe(new Action1<Permission>() {
                    @Override
                    public void call(Permission permission) {
                        call.next(permission);
                    }
                });
    }
}
