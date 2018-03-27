package com.rlzz.library.net.exception;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;
import com.lzy.okgo.exception.StorageException;
import com.rlzz.library.net.utils.JsonUtil;

import org.json.JSONException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;


/**
 * 错误/异常处理工具
 *
 * @author monty
 * @date 2017/9/21
 */
public class ExceptionHandle {

    /**
     * 自定义异常
     */
    public static final int UN_KNOWN_ERROR = 1000;//未知错误
    public static final int ANALYTIC_SERVER_DATA_ERROR = 1001;//解析数据错误
    public static final int CONNECT_ERROR = 1003;//网络连接错误
    public static final int TIME_OUT_ERROR = 1004;//网络连接超时

    public static Throwable handleException(Throwable e) throws IOException, NullPointerException {
        Log.e("monty", "Original Exception -> " + e.toString());
        ResponseException ex = new ResponseException(e, "服务异常");
        if (e instanceof HttpException) {
            HttpException httpExc = (HttpException) e;
            String exceptionJson = httpExc.response().errorBody().string();

            if (!TextUtils.isEmpty(exceptionJson) && JsonUtil.isJson(exceptionJson)) {
                Gson gson = new GsonBuilder().serializeNulls().create();
                ex = gson.fromJson(exceptionJson, ResponseException.class);
            } else {
                ex.setMessage("业务系统异常,请检查接口异常返回信息格式是否正确");
            }
            Log.d("monty", "exception message -> " + ex.getMessage());
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException
                || e instanceof MalformedJsonException) {  //解析数据错误
            ex = new ResponseException(e, ANALYTIC_SERVER_DATA_ERROR);
            ex.setMessage("解析异常");
            return ex;
        } else if (e instanceof UnknownHostException || e instanceof ConnectException) {//连接网络错误
            ex = new ResponseException(e, CONNECT_ERROR);
            ex.setMessage("网络连接失败,请检查网络是否可用");
            return ex;
        } else if (e instanceof SocketTimeoutException) {//网络超时
            ex = new ResponseException(e, TIME_OUT_ERROR);
            ex.setMessage("网络请求超时");
            return ex;
        } else if (e instanceof StorageException) {
            ex = new ResponseException(e, TIME_OUT_ERROR);
            ex.setMessage("sd卡不存在或没有权限");
            return ex;
        } else {  //未知错误
            /*ex = new ResponseException(e, UN_KNOWN_ERROR);
            ex.setMessage("未知错误");*/
            return e;
        }
    }

}
