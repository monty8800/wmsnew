package com.rlzz.wms.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.qihoo360.replugin.model.PluginInfo;
import com.rlzz.library.common.base.BaseActivity;
import com.rlzz.library.utils.LogUtil;
import com.rlzz.library.utils.ToastUtil;
import com.rlzz.wms.App;
import com.rlzz.wms.R;
import com.rlzz.wms.core.RlPlugin;
import com.rlzz.wms.ui.login.LoginActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * SplashActivity 启动页
 * 在此页中进行如下任务:
 * 1.安装插件，预加载插件
 * 2.获取平台配置信息
 *
 * @author monty
 * @date 2018/03/22
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initPlugins();
    }

    private void initPlugins() {
        String bundlesDirPath = "external";
        String[] externalFile = null;
        try {
            externalFile = App.getInstance().getAssets().list(bundlesDirPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogUtil.d("externalFile -> " + (externalFile == null));
        progressBar.setMax(externalFile.length);
        String pluginFilePath = getFilesDir().getAbsolutePath() + File.separator;
        Observable
                .fromArray(externalFile)
                .filter(s -> s.endsWith(".apk"))
                .map(s -> {
                    String pluginName = s.substring(0, s.indexOf("."));
                    PluginInfo plugin = null;

                    if (!RlPlugin.isPluginInstalled(pluginName)) {
                        copyAssetsFileToAppFiles("external" + File.separator + s, s);
                        plugin = RlPlugin.install(pluginFilePath + s);
                    }

                    RlPlugin.preload(plugin);
                    return plugin;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PluginInfo>() {
                    private int index;

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PluginInfo pluginInfo) {
                        progressBar.setProgress(++index);
                        LogUtil.d("安装成功->" + pluginInfo.getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(e.toString());
                        ToastUtil.error(SplashActivity.this, "插件加载异常");

                    }

                    @Override
                    public void onComplete() {
                        ToastUtil.success(SplashActivity.this, "插件加载完成");
                        LoginActivity.GoToActivity(SplashActivity.this);
                        finish();
                    }
                });
    }

    /**
     * 从assets目录中复制某文件内容
     *
     * @param assetFileName assets目录下的Apk源文件路径
     * @param newFileName   复制到/data/data/package_name/files/目录下文件名
     */
    private void copyAssetsFileToAppFiles(String assetFileName, String newFileName) {
        InputStream is = null;
        FileOutputStream fos = null;
        int buffsize = 1024;

        try {
            is = this.getAssets().open(assetFileName);
            fos = this.openFileOutput(newFileName, Context.MODE_PRIVATE);
            int byteCount = 0;
            byte[] buffer = new byte[buffsize];
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_splash;
    }
}
