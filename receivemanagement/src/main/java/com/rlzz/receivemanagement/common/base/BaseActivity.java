package com.rlzz.receivemanagement.common.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.rlzz.base.dialog.LoadingDialog;
import com.rlzz.base.dialog.NiceDialogFactory;
import com.rlzz.receivemanagement.R;
import com.rlzz.receivemanagement.common.base.viewinterface.ILoading;
import com.rlzz.receivemanagement.common.base.viewinterface.IView;
import com.rlzz.receivemanagement.manager.ActivityStackManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 基础Activity
 * 处理LoadingDialog、ToolBar
 *
 * @author monty
 * @date 2017/8/11
 */

public abstract class BaseActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks,IView, ILoading {

    public static final String TAG = BaseActivity.class.getSimpleName();

    private static PermissionListener mPermissionListener;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ActivityStackManager.getManager().push(this);
        setContentView(R.layout.activity_base);
        setupView(findViewById(R.id.container));
        ButterKnife.bind(this);
    }

    private void setupView(ViewGroup containerView) {
        int contentLayoutId = getContentLayoutId();
        View view = LayoutInflater.from(this).inflate(contentLayoutId, null, false);
        containerView.addView(view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
        mPermissionListener = null;
        ActivityStackManager.getManager().remove(this);
    }

    private LoadingDialog loadingDialog;


    @Override
    public void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = NiceDialogFactory.createLoadingDialog();
            loadingDialog.setOutCancel(false);
        } else {
            loadingDialog.dismiss();
        }
        loadingDialog.show(getSupportFragmentManager());
    }

    @Override
    public void closeLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    /**
     * 处理权限请求结果
     * 请求权限使用：EasyPermissions.requestPermissions(this, "拍照需要摄像头权限",RC_CAMERA_AND_WIFI, perms);
     * 检查权限使用：EasyPermissions.hasPermissions(this, perms)
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }

    /**
     * 请求权限已被授权
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    /**
     * 请求权限授权被拒
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}
