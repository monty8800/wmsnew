package com.rlzz.library.net.interceptor;


import com.rlzz.library.net.RetrofitHelper;
import com.rlzz.library.utils.DeviceUtil;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Header拦截器，为Okhttp添加统一Header
 *
 * @author monty
 * @date 2017/9/29
 */

public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
//        String token = MySelfInfo.getInstance().getToken();

//        if (TextUtils.isEmpty(token)) {
//            token = "";
//        }

        Request.Builder requestBuilder = chain.request().newBuilder()
                .addHeader("x-user-agent", "mobile-web-app")
                .addHeader("Source", "Android")
                .addHeader("Device", DeviceUtil.getBrandModel())
                .addHeader("DeviceId", DeviceUtil.getIdentity())
                .addHeader("Accept", "application/json")
                .addHeader("x-user-token", "")
                .addHeader("x-access-token", "");

        HttpUrl url = chain.request().url();
        HttpUrl globalHost = HttpUrl.parse(RetrofitHelper.SERVER_HOST_PRO);

        /**
         * 此处判断是否开发包，由于开发环境服务端有多个地址，其中业务模块的地址需要拦截换成后台开发人员本地地址（拦截策略：拦截地址中带有"qc"的地址就认为是业务模块地址），
         * */
//        if ("develop".equals(BuildConfig.FLAVOR) && !url.pathSegments().contains("qc")) {
//            globalHost = HttpUrl.parse(Config.SERVER_HOST);
//        }

        requestBuilder.url(url.newBuilder()
                .scheme(globalHost.scheme())
                .host(globalHost.host())
                .port(globalHost.port())
                .build());

        return chain.proceed(requestBuilder.build());
    }
}
