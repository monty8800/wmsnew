package com.rlzz.library.net.interceptor;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rlzz.library.net.utils.JsonUtil;
import com.rlzz.library.net.utils.LogUtil;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 拦截请求结果，封装成ResultMode
 * 因为Retrofit定义接口的时候，要么使用String，要么使用json，
 * 在Okhttp设置的GsonConverterFactory只能接受json，否则直接报错，
 * 所以在响应的时候拦截一下统一封装成ReslutModel
 *
 * @author monty
 * @date 2017/9/29
 */

public class ResultInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        ResponseBody responseBody = response.body();

        if (HttpHeaders.hasBody(response) && response.code() == 200) {
            BufferedSource source = responseBody.source();

            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();

            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(Charset.forName("UTF-8"));
            }

            String result = "";
            if (isPlaintext(buffer)) {
                result = buffer.clone().readString(charset);
            }

            JsonObject jsonObject = new JsonObject();
            if (JsonUtil.isJson(result)) {
                try {
                    JsonObject dataObj = (JsonObject) new JsonParser().parse(result);
                    jsonObject.add("data", dataObj);
                } catch (ClassCastException e) {
                    JsonArray dataArr = (JsonArray) new JsonParser().parse(result);
                    jsonObject.add("data", dataArr);
                }
            } else {
                jsonObject.addProperty("data", result);
            }
            jsonObject.addProperty("status", 200);
            jsonObject.addProperty("msg", "");

            String json = jsonObject.toString();
            LogUtil.json(json);

            return response.newBuilder().body(ResponseBody.create(contentType, json)).build();
        }
        return response;
    }

    private static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }
}
