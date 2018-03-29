package com.rlzz.base.common.base;

import java.util.List;

/**
 * 动态申请权限回调
 *
 * @author monty
 * @date 2017/11/3
 */

public interface PermissionListener {
    /**
     * 权限申请成功
     */
    void onGranted();

    /**
     * 拒绝权限
     *
     * @param deniedPermissions
     */
    void onDenied(List<String> deniedPermissions);
}