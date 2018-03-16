package com.rlzz.wms.utils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Printer;

/**
 * LogUtil
 *
 * @author monty
 * @date 2017/8/11
 */

public class LogUtil {

    public static void debugInit() {
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.addLogAdapter(new DiskLogAdapter());
    }

    public static void releaseInit() {
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.addLogAdapter(new DiskLogAdapter());
    }

    public static Printer tag(String tag) {
        return Logger.t(tag);
    }

    public static void d(String log, Object... args) {
        Logger.d(log, args);
    }

    public static void i(String log, Object... args) {
        Logger.i(log, args);
        String logStr = String.format(log, args);
    }

    public static void v(String log, Object... args) {
        Logger.v(log, args);
        String logStr = String.format(log, args);
    }

    public static void w(String log, Object... args) {
        Logger.w(log, args);
        String logStr = String.format(log, args);
    }

    public static void e(String log, Object... args) {
        Logger.e(log, args);
        String logStr = String.format(log, args);
    }

    public static void e(Throwable throwable, String log, Object... args) {
        Logger.e(throwable, log, args);
    }

    public static void json(String json) {
        Logger.json(json);
    }
}
