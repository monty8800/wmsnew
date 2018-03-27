package com.rlzz.wms.observer;

import android.content.Context;

import com.rlzz.library.RLApplication;

import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author monty
 * @package com.rlzz.wms.observer
 * @date 2018/3/22  下午4:29
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

public class PluginTask<Param, Progress, Result> extends RxAsyncTask<Param, Progress, Result> {

    @Override
    protected Result call(Param[] params) {


        return null;
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
            is = RLApplication.getInstance().getAssets().open(assetFileName);
            fos = RLApplication.getInstance().openFileOutput(newFileName, Context.MODE_PRIVATE);
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
