package com.rlzz.wms.net;

import android.util.ArrayMap;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rlzz.wms.Config;
import com.rlzz.wms.net.interceptor.RequestInterceptor;
import com.rlzz.wms.net.interceptor.ResultInterceptor;
import com.rlzz.wms.utils.JsonUtil;
import com.rlzz.wms.utils.LogUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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

    private ArrayMap<String, Object> services = new ArrayMap<>();

    private Retrofit retrofit;

    private static class RetrofitHelperHolder {
        private static RetrofitHelper instance = new RetrofitHelper();
    }

    public static RetrofitHelper getInstance() {
        return RetrofitHelperHolder.instance;
    }

    private RetrofitHelper() {
        Log.d("monty", "RetrofitHelper ################### init");
        retrofit = createRetrofit(Config.SERVER_HOST_PRO);
    }

    public Retrofit createRetrofit(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .client(createOkHttpClient())
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(buildGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    private OkHttpClient createOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new RequestInterceptor())
                .addNetworkInterceptor(new ResultInterceptor())
                .addNetworkInterceptor(loggingInterceptor)
                .build();
        return client;
    }


    private static Gson buildGson() {
        return new GsonBuilder()
                .serializeNulls() // 保留null字段
                .create();
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

//    public synchronized API getAPIService() {
//        return getService(API.class);
//    }
}
