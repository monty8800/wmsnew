package com.rlzz.library.net;

import com.rlzz.library.net.exception.ExceptionHandle;
import com.rlzz.library.net.utils.LogUtil;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author monty
 * @date 2017/9/13
 */

public class RxSchedulers {
    private static final String TAG = "RxSchedulers";

    /**
     * 在此处处理在IO线程进行请求，在主线程进行回调，进行统一异常处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> compose() {
        return observable -> {
            // 拦截异常进行统一转换处理
            return observable
                    .onErrorResumeNext(throwable -> {
                        LogUtil.d(TAG + " -> throwable" + throwable.toString());
                        return Observable.error(ExceptionHandle.handleException(throwable));
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        };
    }

    /**
     * 在此处处理在IO线程进行请求，在主线程进行回调，进行统一异常处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> io_main() {
        return upstream -> upstream
                .onErrorResumeNext(new Function<Throwable, Publisher<? extends T>>() {
                    @Override
                    public Publisher<? extends T> apply(Throwable throwable) throws Exception {
                        return Flowable.error(ExceptionHandle.handleException(throwable));
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}