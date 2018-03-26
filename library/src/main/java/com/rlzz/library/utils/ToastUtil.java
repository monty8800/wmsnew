package com.rlzz.library.utils;

import android.content.Context;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**
 * @author monty
 * @package com.rlzz.library.utils
 * @date 2018/3/26  上午10:53
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

public class ToastUtil {

    private static Toast toast;

    private static void cancel() {
        if (null != toast) {
            toast.cancel();
        }
    }

    private static void show() {
        if (null != toast) {
            toast.show();
        }
    }

    public static void error(Context context, CharSequence message) {
        cancel();
        toast = Toasty.error(context, message);
        show();
    }

    public static void info(Context context, CharSequence message) {
        cancel();
        toast = Toasty.info(context, message);
        show();
    }

    public static void normal(Context context, CharSequence message) {
        cancel();
        toast = Toasty.normal(context, message);
        show();
    }

    public static void success(Context context, CharSequence message) {
        cancel();
        toast = Toasty.success(context, message);
        show();
    }

    public static void warning(Context context, CharSequence message) {
        cancel();
        toast = Toasty.warning(context, message);
        show();
    }
}
