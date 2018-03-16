package com.rlzz.wms.net.base;

import android.util.Log;

import com.rlzz.wms.App;
import com.rlzz.wms.R;
import com.rlzz.wms.net.bean.ResultModel;
import com.rlzz.wms.net.exception.ResponseException;
import com.rlzz.wms.utils.NetWork;
import com.rlzz.wms.utils.ToastUtil;

import io.reactivex.observers.ResourceObserver;

/**
 * 封装网络请求观察者，在此处统一处理成功数据回调以及失败回调
 *
 * @author monty
 * @date 2017/9/13
 */

public abstract class BaseObserver<T> extends ResourceObserver<ResultModel<T>> {

    private static final String TAG = "BaseObserver";

    @Override
    public void onNext(ResultModel<T> value) {
        if (!NetWork.isConnected(App.getInstance())) {
            ToastUtil.show(R.string.toast_network_error);
            onFinish();
            return;
        }
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
            /** token 失效 code为2000，跳转到登录页面 */
            if ("2000".equals(((ResponseException) e).getCode())) {
//                AppManager.logout();
                // 退出登录，跳转到登录页面
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
        ToastUtil.show(ex.getMessage());
    }

    /**
     * 请求结束，onError(),onComplete()之后都会调用，常用于关闭Dialog
     */
    protected void onFinish() {
    }
}