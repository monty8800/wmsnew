package com.rlzz.library.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.rlzz.library.R;
import com.rlzz.library.common.base.viewinterface.ILoading;
import com.rlzz.library.common.base.viewinterface.IView;
import com.rlzz.library.dialog.LoadingDialog;
import com.rlzz.library.dialog.NiceDialogFactory;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author monty
 * @date 2017/9/4
 */

public abstract class BaseFragment extends Fragment implements IView, ILoading {

    FrameLayout contentView;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_blank, container, false);
        contentView = rootView.findViewById(R.id.container);
        contentView.addView(inflater.inflate(getContentLayoutId(), null, false));
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private LoadingDialog loadingDialog;


    @Override
    public void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = NiceDialogFactory.createLoadingDialog();
            loadingDialog.setOutCancel(false);
        }
        loadingDialog.show(getChildFragmentManager());
    }

    @Override
    public void closeLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismissAllowingStateLoss();
            loadingDialog.onDestroy();
            loadingDialog = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
