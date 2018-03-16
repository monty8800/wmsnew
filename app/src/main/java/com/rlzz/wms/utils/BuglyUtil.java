/*
package com.rlzz.wms.utils;

import android.app.Application;
import android.util.Log;

import com.rlzz.uwinmes.BuildConfig;
import com.rlzz.uwinmes.mvp.login.MySelfInfo;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.CrashReport.CrashHandleCallback;
import com.tencent.bugly.crashreport.CrashReport.UserStrategy;

import java.util.HashMap;
import java.util.Map;

*/
/**
 * Bugly - 异常崩溃自动上传工具
 *
 * @author monty
 * @date 2017/9/4
 *//*


public class BuglyUtil {
    public static final String APP_CONTEXT_TAG = "rlzz";
    private static final String BUGLY_APP_ID = "21cc9c4212";

    */
/**
     * 腾讯Bugly，处理异常上报初始化
     *//*

    public static void init(Application application) {
        UserStrategy strategy = new UserStrategy(application); //App的策略Bean
        strategy.setAppChannel(application.getPackageName())   //设置渠道
                .setAppVersion(Version.getVersionName(application))      //App的版本
                .setAppReportDelay(1000)  //设置SDK处理延时，毫秒
                .setDeviceID(DeviceUtil.getIdentity())
                .setCrashHandleCallback(new AppCrashHandleCallback())
                .setBuglyLogUpload(true)
                .setEnableANRCrashMonitor(true);
        CrashReport.setIsDevelopmentDevice(application, BuildConfig.DEBUG);

        CrashReport.setUserId(MySelfInfo.getInstance().getAccount());
        //自定义策略生效，必须在初始化SDK前调用
//        CrashReport.initCrashReport(application, BUGLY_APP_ID, BuildConfig.DEBUG, strategy);
        Bugly.init(application, BUGLY_APP_ID, BuildConfig.DEBUG, strategy);
    }

    private static class AppCrashHandleCallback extends CrashHandleCallback //bugly回调
    {
        @Override
        public synchronized Map<String, String> onCrashHandleStart(int crashType, String errorType, String errorMessage, String errorStack) {
            String crashTypeName = null;
            switch (crashType) {
                case CrashHandleCallback.CRASHTYPE_JAVA_CATCH:
                    crashTypeName = "JAVA_CATCH";
                    break;
                case CrashHandleCallback.CRASHTYPE_JAVA_CRASH:
                    crashTypeName = "JAVA_CRASH";
                    break;
                case CrashHandleCallback.CRASHTYPE_NATIVE:
                    crashTypeName = "JAVA_NATIVE";
                    break;
                case CrashHandleCallback.CRASHTYPE_U3D:
                    crashTypeName = "JAVA_U3D";
                    break;
                default: {
                    crashTypeName = "unknown";
                }
            }

            Log.e(APP_CONTEXT_TAG, "Crash Happen Type:" + crashType + " TypeName:" + crashTypeName);
            Log.e(APP_CONTEXT_TAG, "errorType:" + errorType);
            Log.e(APP_CONTEXT_TAG, "errorMessage:" + errorMessage);
            Log.e(APP_CONTEXT_TAG, "errorStack:" + errorStack);

            Map<String, String> userDatas = super.onCrashHandleStart(crashType, errorType, errorMessage, errorStack);
            if (userDatas == null) {
                userDatas = new HashMap<>();
            }

            userDatas.put("DEBUG", "TRUE");
            return userDatas;
        }

    }
}
*/
