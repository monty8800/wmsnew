package com.rlzz.wms.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;
import com.qihoo360.replugin.packages.PluginRunningList;

import java.util.List;

/**
 * @author monty
 * @package com.rlzz.wms.core
 * @date 2018/3/16  上午11:30
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

public class RlPlugin {

    /**
     * 安装插件
     * @param path 插件绝对路径
     * @return
     */
    public static PluginInfo install(String path) {
        return RePlugin.install(path);
    }

    /**
     * 插件是否已安装
     * @param pluginName 插件名称
     * @return
     */
    public static boolean isPluginInstalled(String pluginName) {
        return RePlugin.isPluginInstalled(pluginName);
    }

    /**
     * 插件是否正在运行
     * @param pluginName 插件名称
     * @return
     */
    public static boolean isPluginRunning(String pluginName) {
        return RePlugin.isPluginRunning(pluginName);
    }

    /**
     * 卸载插件
     * @param pluginName 插件名称
     * @return
     */
    public static boolean uninstall(String pluginName) {
        return RePlugin.uninstall(pluginName);
    }

    /**
     * 获取正在运行的插件列表
     * @return
     */
    public static PluginRunningList getRunningPlugins() {
        return RePlugin.getRunningPlugins();
    }

    /**
     * 获取所有已经安装的插件
     * @return
     */
    public static List<PluginInfo> getPluginInfoList() {
        return RePlugin.getPluginInfoList();
    }

    /**
     * 根据插件名称查找插件信息
     * @param pluginName 插件名称
     * @return
     */
    public static PluginInfo getPluginInfo(String pluginName) {
        return RePlugin.getPluginInfo(pluginName);
    }

    /**
     * 获取插件版本
     * @param pluginName
     * @return
     */
    public static int getPluginVersion(String pluginName) {
        return RePlugin.getPluginVersion(pluginName);
    }

    /**
     * 判断插件是否使用过，只要释放过Dex、Native的，就认为是“使用过”的
     * @param pluginName
     * @return
     */
    public static boolean isPluginUsed(String pluginName) {
        return RePlugin.isPluginUsed(pluginName);
    }

    /**
     * 预加载插件，此方法会立即释放优化后的Dex和Native库，但不会运行插件代码。
     * @param pi 要加载的插件信息
     * @return
     */
    public static boolean preload(PluginInfo pi) {
        return RePlugin.preload(pi);
    }

    /**
     * 预加载插件，此方法会立即释放优化后的Dex和Native库，担不回运行插件代码。
     * @param pluginName 要加载的插件名称
     * @return
     */
    public static boolean preload(String pluginName) {
        return RePlugin.preload(pluginName);
    }

    /**
     * 开启一个插件的Activity
     * 其中Intent的ComponentName的Key应为插件名（而不是包名），可使用createIntent方法来创建Intent对象
     * @param context Context对象
     * @param intent  要打开Activity的Intent，其中ComponentName的Key必须为插件名
     * @return
     */
    public static boolean startActivity(Context context, Intent intent) {
        return RePlugin.startActivity(context, intent);
    }

    /**
     * 开启一个插件的Activity，无需调用createIntent或设置ComponentName来修改Intent
     * @param context    Context对象
     * @param intent     要打开Activity的Intent，其中ComponentName的Key必须为插件名
     * @param pluginName 插件名。稍后会填充到Intent中
     * @param activity   插件的Activity。稍后会填充到Intent中
     * @return
     */
    public static boolean startActivity(Context context, Intent intent, String pluginName, String activity) {
        return RePlugin.startActivity(context, intent, pluginName, activity);
    }

    /**
     * 通过 forResult 方式启动一个插件的 Activity
     * @param activity    源 Activity
     * @param intent      要打开 Activity 的 Intent，其中 ComponentName 的 Key 必须为插件名
     * @param requestCode 请求码
     * @return
     */
    public static boolean startActivityForResult(Activity activity, Intent intent, int requestCode) {
        return RePlugin.startActivityForResult(activity, intent, requestCode);
    }

    /**
     * 通过 forResult 方式启动一个插件的 Activity
     * @param activity    源 Activity
     * @param intent      要打开 Activity 的 Intent，其中 ComponentName 的 Key 必须为插件名
     * @param requestCode 请求码
     * @param options     附加的数据
     * @return
     */
    public static boolean startActivityForResult(Activity activity, Intent intent, int requestCode, Bundle options) {
        return RePlugin.startActivityForResult(activity, intent, requestCode, options);
    }

    /**
     * 获取插件中的Fragment
     * @param pluginName    插件名称
     * @param fragmentClass 插件中的fragment
     * @return
     */
    public static Fragment getFragment(String pluginName, String fragmentClass) {
        if (TextUtils.isEmpty(pluginName) || TextUtils.isEmpty(fragmentClass)) {
            return null;
        }
        //代码使用插件Fragment
        //获取插件的ClassLoader
        ClassLoader d1ClassLoader = RePlugin.fetchClassLoader(pluginName);
        Fragment fragment = null;
        try {
            //使用插件的Classloader获取指定Fragment实例
            fragment = d1ClassLoader.loadClass(fragmentClass).asSubclass(Fragment.class).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return fragment;
    }

    /**
     * 获取插件中的View
     * @param pluginName    插件名称
     * @param viewClass     插件中的view
     * @return
     */
    public static View getView(String pluginName, String viewClass) {
        if (TextUtils.isEmpty(pluginName) || TextUtils.isEmpty(viewClass)) {
            return null;
        }
        //代码使用插件Fragment
        //获取插件的ClassLoader
        ClassLoader d1ClassLoader = RePlugin.fetchClassLoader(pluginName);
        View view = null;
        try {
            //使用插件的Classloader获取指定Fragment实例
            view = d1ClassLoader.loadClass(viewClass).asSubclass(View.class).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return view;
    }
}
