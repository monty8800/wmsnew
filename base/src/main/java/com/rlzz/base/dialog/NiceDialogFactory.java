package com.rlzz.base.dialog;


/**
 * Created by monty on 2017/9/7.
 */

public class NiceDialogFactory {

    public static LoadingDialog createLoadingDialog() {
        return (LoadingDialog) LoadingDialog.init().setWidth(100).setHeight(100).setDimAmount(0.5f);
    }
    public static LoadingDialog createLoadingDialog(String message) {
        return (LoadingDialog) LoadingDialog.init(message).setWidth(100).setHeight(100).setDimAmount(0.5f);
    }

    public static ConfirmDialog createConfirmDialog() {
        return (ConfirmDialog) ConfirmDialog.init().setMargin(50);
    }
    public static ConfirmDialog createConfirmDialog(String title) {
        return (ConfirmDialog) ConfirmDialog.init(title).setWidth(600);
    }

}
