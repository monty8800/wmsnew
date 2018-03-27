package com.rlzz.library.manager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import com.rlzz.library.RLApplication;


/**
 * @author monty
 * @date 2017/10/9
 */

public class AppManager {
    public interface Action {
        String LOGIN = "com.rlzz.uwinmes.action.LOGIN";
    }

    /**
     * 隐式启动登录页面
     */
    public static void startLoginActivity() {
        ActivityStackManager.getManager().finishAllActivity();
        Intent intent = new Intent();
        intent.setAction(Action.LOGIN);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        /**使用非Activity的Context启动Activity需要添加FLAG_ACTIVITY_NEW_TASK，
         * 否则会抛异常Calling startActivity() from outside of an Activity  context requires the FLAG_ACTIVITY_NEW_TASK flag.*/
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        RLApplication.getInstance().startActivity(intent);
    }

    /**
     * 登出
     */
   /* public static void logout() {
        MySelfInfo.getInstance().clearPassword();
        // 当前页面不是登陆页面
        if (!ActivityStackManager.getManager().peek().getClass().getName().equals(LoginActivity.class.getName())) {
            AppManager.startLoginActivity();
        }
    }*/

    public static void goToAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(localIntent);
    }

    public static void setServerHost(String serverHost) {
//        Config.SERVER_HOST_PRO = serverHost;
    }

    /**
     * 重启整个APP
     *
     * @param context
     * @param Delayed 延迟多少毫秒
     */
    public static void restartAPP(Context context, long Delayed) {

        /**开启一个新的服务，用来重启本APP*/
        Intent intent = new Intent(context, KillSelfService.class);
        intent.putExtra("PackageName", context.getPackageName());
        intent.putExtra("Delayed", Delayed);
        context.startService(intent);

        /**杀死整个进程**/
//        android.os.Process.killProcess(android.os.Process.myPid());
        ActivityStackManager.getManager().exitApp(context);
    }

    /***重启整个APP*/
    public static void restartAPP(Context context) {
        restartAPP(context, 2000);
    }

    public static void exitApp(){
        ActivityStackManager.getManager().exitApp(RLApplication.getInstance());
    }

}
