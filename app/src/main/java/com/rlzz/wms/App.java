package com.rlzz.wms;

import com.qihoo360.replugin.RePluginApplication;
import com.qihoo360.replugin.RePluginConfig;

/**
 * @author monty
 * @package com.rlzz.wms
 * @date 2018/3/8  下午2:02
 * @description
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */
public class App extends RePluginApplication {
    private static App application;

    public static App getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (application == null) {
            application = this;
        }
    }

    @Override
    protected RePluginConfig createConfig() {

        RePluginConfig c = new RePluginConfig();

        // 允许“插件使用宿主类”。默认为“关闭”
        c.setUseHostClassIfNotFound(true);

        // 打印详细日志
        c.setPrintDetailLog(true);

        // FIXME RePlugin默认会对安装的外置插件进行签名校验，这里先关掉，避免调试时出现签名错误
        c.setVerifySign(!BuildConfig.DEBUG);

        // FIXME 若宿主为Release，则此处应加上您认为"合法"的插件的签名，例如，可以写上"宿主"自己的。
        // RePlugin.addCertSignature("AAAAAAAAA");

        // 在Art上，优化第一次loadDex的速度
        c.setOptimizeArtLoadDex(true);
        return c;
    }
}
