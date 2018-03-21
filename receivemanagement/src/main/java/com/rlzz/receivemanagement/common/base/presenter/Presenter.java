package com.rlzz.receivemanagement.common.base.presenter;

import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Base ReportPresenter
 *
 * @author monty
 * @date 2017/8/11
 */

public abstract class Presenter<V> {
    protected String TAG = this.getClass().getSimpleName();
    public V mvpView;

    public V getView() {
        return mvpView;
    }

    private CompositeDisposable compositeDisposable;

    protected void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void attachView(V view) {
        Log.i(TAG, "attachView - " + view.getClass().getSimpleName());
        this.mvpView = view;
    }

    public void detachView() {
        Log.i(TAG, "detachView - " + mvpView.getClass().getSimpleName());
        this.mvpView = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public void onViewCreate() {
        Log.i(TAG, "onViewCreate");
    }

    public void onViewPause() {
        Log.i(TAG, "onViewPause");
    }

    public void onViewResume() {
        Log.i(TAG, "onViewResume");
    }

    public void onViewStart() {
        Log.i(TAG, "onViewStart");
    }

    public void onViewStop() {
        Log.i(TAG, "onViewStop");
    }

    public void onViewDestroy() {
        Log.i(TAG, "onViewDestroy");
    }


}
