package com.jnyilin.basiclib.utils.permissionutils;

import com.tbruyelle.rxpermissions.Permission;

/**
 * @author HRR
 * @datetime 2017/12/25
 * @describe
 * @modifyRecord
 */

public interface PermissionCall {
    /**权限申请成功*/
    void success();
    /**权限申请失败*/
    void fail();
    /**分别申请多个权限*/
    void next(Permission permission);
}
