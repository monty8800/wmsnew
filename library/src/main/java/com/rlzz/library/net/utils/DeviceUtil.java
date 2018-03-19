package com.rlzz.library.net.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author luomw
 * @ClassName: DeviceUtil
 * @Description: 设备的一些信息
 * @date 2015年8月27日 下午12:53:48
 */
public class DeviceUtil {

    WindowManager wm;
    DisplayMetrics displayMetrics;

    public DeviceUtil(Context context) {
        wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        displayMetrics = new DisplayMetrics();
    }

    public int getScreenWidth() {
        return wm.getDefaultDisplay().getWidth();
    }

    public int getScreenHeight() {
        return wm.getDefaultDisplay().getHeight();
    }

    /**
     * 获取手机型号
     */
    public static String getDeviceModel() {
        String model = Build.MODEL;

        if (model == null) {
            return "";
        } else {
            return model;
        }
    }

    /**
     * 获取手机品牌 + 型号
     *
     * @return
     */
    public static String getBrandModel() {
        return String.format("%1$s(%2$s)", Build.BRAND, Build.MODEL);
    }

    /**
     * 获取全局唯一标识
     * @return
     */
    public static String getIdentity() {
        String identity = PreferencesManager.getInstanceUser().getString("identity", null);
        if (identity == null) {
            identity = java.util.UUID.randomUUID().toString();
            PreferencesManager.getInstanceUser().putString("identity", identity);
        }
        return identity;
    }

    /**
     * 获取设备ID
     *
     * @param context
     * @return
     */
    @Deprecated
    public static String getDeviceInfo(Context context) {
        try {
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            String deviceId = null;
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                deviceId = tm.getDeviceId();
            }

            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

            String mac = wifi.getConnectionInfo().getMacAddress();

            if (TextUtils.isEmpty(deviceId)) {
                deviceId = mac;
            }

            if (TextUtils.isEmpty(deviceId)) {
                deviceId = android.provider.Settings.Secure.getString(
                        context.getContentResolver(),
                        android.provider.Settings.Secure.ANDROID_ID);
            }

            deviceId = deviceId.replaceAll(":", "");

            return deviceId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param mContext
     * @Title: resetProgram
     * @Description: TODO 重启软件
     */
    public static void resetProgram(Context mContext) {
        Intent i = ((ContextWrapper) mContext)
                .getBaseContext()
                .getPackageManager()
                .getLaunchIntentForPackage(((ContextWrapper) mContext).getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(i);
        ((Activity) mContext).finish();
        System.exit(0);
    }


    /**
     * @return 当前时间
     * @Title: getCurrentTime
     * @Description: TODO 获取当前时间 yyyy_MM_dd_HH_mm_ss
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")
                .format(new Date(System.currentTimeMillis()));
    }
}
