package com.rlzz.library.net;

import android.util.ArrayMap;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rlzz.library.net.interceptor.RequestInterceptor;
import com.rlzz.library.net.interceptor.ResultInterceptor;
import com.rlzz.library.net.urlmanager.RetrofitUrlManager;
import com.rlzz.library.utils.LogUtil;
import com.rlzz.library.utils.JsonUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitHelper
 *
 * @author monty
 * @date 2017/8/14
 */

public class RetrofitHelper {
    public final static int CONNECT_TIMEOUT = 30;
    public final static int READ_TIMEOUT = 30;
    public final static int WRITE_TIMEOUT = 30;

    public final static String SERVER_HOST_PRO = "http://www.szrlzz.com";

    private ArrayMap<String, Object> services = new ArrayMap<>();

    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    private static class RetrofitHelperHolder {
        private static RetrofitHelper instance = new RetrofitHelper();
    }

    public static RetrofitHelper getInstance() {
        return RetrofitHelperHolder.instance;
    }

    private RetrofitHelper() {
        Log.d("monty", "RetrofitHelper ################### init");
        createOkHttpClient();
        createRetrofit();
    }

    public Retrofit createRetrofit() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_HOST_PRO)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用rxjava
                .addConverterFactory(GsonConverterFactory.create(buildGson()))//使用Gson
                .client(createOkHttpClient())
                .build();
        return retrofit;
    }

    private OkHttpClient createOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        this.okHttpClient = RetrofitUrlManager.getInstance().with(new OkHttpClient.Builder()) //RetrofitUrlManager 初始化
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new RequestInterceptor())
                .addNetworkInterceptor(new ResultInterceptor())
                .addNetworkInterceptor(loggingInterceptor)
                .build();
        return okHttpClient;
    }


    private static Gson buildGson() {
        return new GsonBuilder()
                .serializeNulls() // 保留null字段
                .create();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    private class HttpLogger implements HttpLoggingInterceptor.Logger {
        private StringBuilder mMessage = new StringBuilder();

        @Override
        public void log(String message) {
            // 请求或者响应开始
            if (message.startsWith("--> POST")) {
                mMessage.setLength(0);
            }
            // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
            if (JsonUtil.isJson(message)) {
                message = JsonUtil.formatJson(JsonUtil.decodeUnicode(message));
            }
            mMessage.append(message.concat("\n"));
            // 响应结束，打印整条日志
            if (message.startsWith("<-- END HTTP")) {
                LogUtil.d(mMessage.toString());
            }
        }
    }


    /**
     * 获取Service对象，如果已经创建过直接取出，否则新建一个，并缓存起来
     *
     * @param service
     * @param <T>
     * @return
     */

    public synchronized <T> T getService(final Class<T> service) {
        String serviceName = service.getName();
        if (null != services.get(serviceName)) {
            return (T) services.get(serviceName);
        }
        T ser = retrofit.create(service);
        services.put(serviceName, ser);
        return ser;
    }
}
