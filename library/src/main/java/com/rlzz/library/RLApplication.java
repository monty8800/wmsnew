package com.rlzz.library;

import android.app.Application;

/**
 * @author monty
 * @package com.rlzz.library
 * @date 2018/3/19  下午2:18
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

public class RLApplication extends Application {
    private static RLApplication application;

    public static RLApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (application == null) {
            application = this;
        }
    }
}
