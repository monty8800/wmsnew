package com.rlzz.library.net.base;

import android.util.Log;

import com.rlzz.library.R;
import com.rlzz.library.RLApplication;
import com.rlzz.library.net.bean.ResultModel;
import com.rlzz.library.net.exception.ResponseException;
import com.rlzz.library.utils.NetWork;
import com.rlzz.library.utils.ToastUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * 封装网络请求观察者，在此处统一处理成功数据回调以及失败回调
 *
 * @author monty
 * @date 2017/9/13
 */

public abstract class BaseSubscriber<T> implements Subscriber<ResultModel<T>> {

    private static final String TAG = "BaseObserver";

    @Override
    public void onSubscribe(Subscription s) {
        Log.d(TAG, "onSubscribe");
        if (!NetWork.isConnected(RLApplication.getInstance())) {
            s.cancel();
            ToastUtil.warning(RLApplication.getInstance(),RLApplication.getInstance().getString(R.string.toast_network_error));
            onFinish();
        }
    }

    @Override
    public void onNext(ResultModel<T> value) {
        Log.d(TAG, "onNext - result -> " + value.toString());
        T t = value.getData();
        onSuccess(t);
    }

   /**
     * 处理框架错误（包括okhttp的网络错误，转换错误,解析异常）
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "error:" + e.toString());
        if (e instanceof ResponseException) {
            // token 失效 code为2000，跳转到登录页面
            if ("2000".equals(((ResponseException) e).getCode())) {
//                AppManager.logout();
                // 退出登录，跳转到登录页面
                Log.e("monty","error");
            }
            onFailure(((ResponseException) e));
        } else {
            onFailure(new Exception(e));
        }
        onFinish();
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete");
        onFinish();
    }
    /**
     * 请求成功
     *
     * @param t
     */
    protected abstract void onSuccess(T t);

    /**
     * 请求失败
     *
     * @param ex
     */
    protected void onFailure(Exception ex) {
        ToastUtil.error(RLApplication.getInstance(),ex.getMessage());
    }

    /**
     * 请求结束，onError(),onComplete()之后都会调用，常用于关闭Dialog
     */
    protected void onFinish() {
    }
}