package com.rlzz.library.net.utils;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rlzz.library.RLApplication;


/**
 * ToastUtil
 *
 * @author monty
 * @date 2017/8/16
 */

public class ToastUtil {
    private static Toast mToast;

    /**
     * 传入文字
     */
    public static void show(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(RLApplication.getInstance(), text, Toast.LENGTH_LONG);
        } else {
            //如果当前Toast没有消失， 直接显示内容，不需要重新设置
            mToast.setText(text);
        }
//        mToast.setGravity(Gravity.BOTTOM, 0, 0);
        mToast.show();
    }

    /**
     * 传入文本id
     */
    public static void show(int resId) {
        show(RLApplication.getInstance().getString(resId));
    }

    /**
     * 传入文字，带图片
     */
    public static void showImg(String text, int resImg) {

        if (mToast == null) {
            mToast = Toast.makeText(RLApplication.getInstance(), text, Toast.LENGTH_SHORT);
        } else {
            //如果当前Toast没有消失， 直接显示内容，不需要重新设置
            mToast.setText(text);
        }
        //添加图片的操作,这里没有设置图片和文字显示在一行的操作呢...
        LinearLayout view = (LinearLayout) mToast.getView();
        ImageView imageView = new ImageView(view.getContext());
        imageView.setImageResource(resImg);
        view.addView(imageView);

        mToast.show();
    }
}
