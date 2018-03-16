package com.rlzz.wms.manager;

import android.text.TextUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 用户菜单查看权限
 * 用于控制首页7个菜单项是否显示
 *
 * @author monty
 * @date 2018/1/18
 */

public class Roles {

    /**
     * 完工待检任务列表
     */
    public static String DEFAULT_TASK_READ = "dld:quarantinetask-default-list:list";

    /**
     * 来料待检任务列表
     */
    public static String TODO_TASK_READ = "dld:quarantinetask-002-list:list";

    /**
     * 完工检验单列表
     */
    public static String DEFAULT_TESTORDER_READ = "dld:testorder-default-list:list";

    /**
     * 来料检验单列表
     */
    public static String TODO_TESTORDER_READ = "dld:testorder-002-list:list";

    /**
     * 月统计
     */
    public static String ARRIVAL_TESTORDER_STATISTICAL_FOR_MONTH_TOTAL = "dld:arrivalTestOrderStatisticalForMonth-list:list";

    /**
     * 周统计
     */
    public static String ARRIVAL_TESTORDER_STATISTICAL_FOR_WEEK_TOTAL = "dld:arrivalTestOrderStatisticalForWeek-list:list";

    /**
     * 供应商统计
     */
    public static String ARRIVAL_TESTORDER_STATISTICAL_FOR_SUPPLIER_TOTAL = "dld:arrivalTestOrderStatisticalForSupplier-list:list";

    public static boolean hasRole(String role) {
        List<String> roles = MySelfInfo.getInstance().getRoles();
        return roles != null && !TextUtils.isEmpty(role) && roles.contains(role);
    }

    public static boolean hasRole(String... checkRoles) {
        List<String> roles = MySelfInfo.getInstance().getRoles();
        return roles.containsAll(Arrays.asList(checkRoles));
    }
}
