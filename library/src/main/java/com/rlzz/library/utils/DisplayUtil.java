package com.rlzz.library.utils;


import com.rlzz.library.RLApplication;

/**
 * 尺寸单位转换
 *
 * @author monty
 * @date 2017/9/1
 */
public class DisplayUtil {

    public static int px2dp(float pxValue) {
        float density = RLApplication.getInstance().getResources().getDisplayMetrics().density;//得到设备的密度
        return (int) (pxValue / density + 0.5f);
    }

    public static int dp2px(float dpValue) {
        float density = RLApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    public static int px2sp(float pxValue) {
        float scaleDensity = RLApplication.getInstance().getResources().getDisplayMetrics().scaledDensity;//缩放密度
        return (int) (pxValue / scaleDensity + 0.5f);
    }

    public static int sp2px(float spValue) {
        float scaleDensity = RLApplication.getInstance().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scaleDensity + 0.5f);
    }


}
