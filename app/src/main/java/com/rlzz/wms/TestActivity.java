package com.rlzz.wms;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;
import com.rlzz.base.common.base.BaseActivity;
import com.rlzz.wms.core.RlPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TestActivity extends BaseActivity {
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.img)
    ImageView img;

    public static void GoToActivity(Context context){
        Intent intent = new Intent(context,TestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        super.onPermissionsGranted(requestCode, perms);
        Toast.makeText(this, "申请成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        super.onPermissionsDenied(requestCode, perms);
        Toast.makeText(this, "申请失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_demo;
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
//        Toast.makeText(this,"click",Toast.LENGTH_LONG).show();
//        EasyPermissions.requestPermissions(this, "应用重启需要权限", 0, Manifest.permission.ACCESS_WIFI_STATE);
        initAllBundle();

    }

    @OnClick(R.id.button2)
    public void onButton2Clicked() {
        Intent receiveManagement = RePlugin.createIntent("ReceiveManagement", "com.rlzz.receivemanagement.MainActivity");

//        Intent intent = new Intent();
//        intent.setComponent(new ComponentName("com.rlzz.receivemanagement","com.rlzz.receivemanagement.TestActivity"));
        RlPlugin.startActivity(this, receiveManagement);
    }

    @OnClick(R.id.button3)
    public void onButton3Clicked() {
        List<PluginInfo> pluginInfoList = RePlugin.getPluginInfoList();

        Object[] objects = pluginInfoList.toArray();
        Observable.fromArray(objects).observeOn(Schedulers.newThread()).subscribeOn(Schedulers.newThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object pluginInfo) throws Exception {
                RlPlugin.uninstall(((PluginInfo) pluginInfo).getName());
            }
        });

    }

    @OnClick(R.id.button4)
    public void onButton4Clicked() {
//        Fragment receiveManagement = RlPlugin.getFragment("ReceiveManagement", "com.rlzz.receivemanagement.BlankFragment");
//        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, receiveManagement).commit();
        /*Intent intent = new Intent(this, com.rlzz.wms.ui.main.MainActivity.class);
        startActivity(intent);*/

    }

    private void initAllBundle() {
        List<String> externalBundles = getExternalBundles();
        String path = "external" + File.separator;
        String pluginFilePath = getFilesDir().getAbsolutePath() + File.separator;
        for (int i = 0; i < externalBundles.size(); i++) {
            String bundleName = externalBundles.get(i).substring(0, externalBundles.get(i).indexOf("."));
            if (!RePlugin.isPluginInstalled(bundleName)) {
                copyAssetsFileToAppFiles("external" + File.separator + externalBundles.get(i), externalBundles.get(i));
                PluginInfo bundleInfo = RlPlugin.install(pluginFilePath + externalBundles.get(i));
                RlPlugin.preload(bundleInfo);
                img.setImageDrawable(bundleInfo.getIcon());
                text.append("安装成功：" + bundleInfo.getName() + "\n");
                Log.d("monty", bundleInfo.toString());
            }
        }

        Log.d("monty", "已安装的插件->" + RePlugin.getPluginInfoList().toString());
        Toast.makeText(this, "已安装插件数：" + RePlugin.getPluginInfoList().size(), Toast.LENGTH_LONG).show();
    }

    public List<String> getExternalBundles() {
        String bundlesDirPath = "external";
        String[] externalFile = null;
        try {
            externalFile = App.getInstance().getAssets().list(bundlesDirPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> bundlePaths = new ArrayList<>();
        for (String bundlePath : externalFile) {
            if (bundlePath.endsWith(".apk")) {
                bundlePaths.add(bundlePath);
            }
        }
        return bundlePaths;
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
}
