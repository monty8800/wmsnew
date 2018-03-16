package com.rlzz.library.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.rlzz.library.R;
import com.rlzz.library.dialog.base.BaseNiceDialog;
import com.rlzz.library.dialog.base.ViewHolder;

/**
 * Created by monty on 2017/9/7.
 */

public class ConfirmDialog extends BaseNiceDialog {

    private OnConfirmClickListener listener;
    private String title;
    private String message;

    public static ConfirmDialog init() {
        return init("提示");
    }

    public static ConfirmDialog init(String title) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setArguments(bundle);
        return confirmDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        title = bundle.getString("title");
    }

    @Override
    public int intLayoutId() {
        return R.layout.dialog_confirm_layout;
    }

    @Override
    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
        holder.setText(R.id.title, title);
        holder.setText(R.id.message, message);
        holder.setOnClickListener(R.id.cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        holder.setOnClickListener(R.id.ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onOkClick(dialog);
                }
            }
        });
    }

    public ConfirmDialog setMessageText(String messageText) {
        this.message = messageText;
        return this;
    }

    public ConfirmDialog setOnConfirmClickListener(OnConfirmClickListener listener) {
        this.listener = listener;
        return this;
    }

    public interface OnConfirmClickListener {
        void onOkClick(BaseNiceDialog dialog);
    }
}
