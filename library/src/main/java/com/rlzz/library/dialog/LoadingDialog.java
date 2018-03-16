package com.rlzz.library.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.rlzz.library.R;
import com.rlzz.library.dialog.base.BaseNiceDialog;
import com.rlzz.library.dialog.base.ViewHolder;
import com.rlzz.library.widget.LoadingView;


/**
 * Created by monty on 2017/9/7.
 */

public class LoadingDialog extends BaseNiceDialog {
    private String message = "";

    public static LoadingDialog init() {
        return init("正在加载...");
    }

    public static LoadingDialog init(String message) {
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        LoadingDialog loadingDialog = new LoadingDialog();
        loadingDialog.setArguments(bundle);
        return loadingDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        message = bundle.getString("message");
    }

    @Override
    public int intLayoutId() {
        return R.layout.dialog_loading_layout;
    }

    @Override
    public void convertView(ViewHolder holder, BaseNiceDialog dialog) {
        LoadingView loadingView = holder.getView(R.id.loadingView);
        loadingView.setSize(40);
        TextView tvMessage = holder.getView(R.id.tv_message);
        tvMessage.setText(message);
    }

   /* @Override
    public BaseNiceDialog show(FragmentManager manager) {
        super.show(manager,manager.getClass().toString());
        return null;
    }*/
}
