package com.rlzz.library.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.rlzz.library.common.base.presenter.Presenter;


/**
 * MVPBaseActivity
 *
 * @author monty
 * @date 2017/8/16
 */

public abstract class MVPActivity<P extends Presenter> extends BaseActivity {
    protected String TAG = this.getClass().getSimpleName();
    protected P mPresenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        mPresenter.onViewStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
        mPresenter.onViewPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        mPresenter.onViewResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
        mPresenter.onViewStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        mPresenter.onViewDestroy();
        onDestroyPresenter();
    }

    public void onDestroyPresenter() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
