package com.rlzz.wms.utils;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileUtil
 *
 * @author monty
 * @date 2017/8/11
 */

public class FileUtil {

    private FileUtil() {
        throw new UnsupportedOperationException("can't instantiate ...");
    }

    public static File getExternalCacheDir(Context context) {
        return context.getExternalCacheDir();
    }

    public static File getExternalPicturesCacheDir(Context context) {
        File externalCacheDir = getExternalCacheDir(context);
        File picturesDir = new File(externalCacheDir.getAbsoluteFile() + File.separator + "Pictures");
        if (!picturesDir.exists()) {
            picturesDir.mkdirs();
        }
        return picturesDir;
    }

    public static File getExternalGlideCacheDir(Context context) {
        File externalCacheDir = getExternalCacheDir(context);
        File picturesDir = new File(externalCacheDir.getAbsoluteFile() + File.separator + "Glide_Cache");
        if (!picturesDir.exists()) {
            picturesDir.mkdirs();
        }
        return picturesDir;
    }

    public static File getExternalGalleryCacheDir(Context context) {
        File externalCacheDir = getExternalCacheDir(context);
        File picturesDir = new File(externalCacheDir.getAbsoluteFile() + File.separator + "Gallery");
        if (!picturesDir.exists()) {
            picturesDir.mkdirs();
        }
        return picturesDir;
    }

    public static String getExternalCacheDirAbsolutePath(Context context) {
        return getExternalCacheDir(context).getAbsolutePath();
    }

    public static String assetFile2String(String fileName, Context context) {
        StringBuilder result = new StringBuilder();
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        try {
            inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            bufReader = new BufferedReader(inputReader);
            String line;

            while ((line = bufReader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeIO(inputReader, bufReader);

        return result.toString();
    }


    /**
     * 关闭IO
     *
     * @param closeables closeable
     */
    public static void closeIO(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        try {
            for (Closeable closeable : closeables) {
                if (closeable != null) {
                    closeable.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] file2byte(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) file.length());
        BufferedInputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = bufferedInputStream.read(buffer, 0, buf_size))) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            byteArrayOutputStream.close();
        }
        return null;
    }
}