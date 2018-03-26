package com.rlzz.wms.ui.login;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.rlzz.library.utils.ToastUtil;
import com.rlzz.wms.R;
import com.rlzz.wms.common.base.MVPActivity;
import com.rlzz.wms.utils.DatePickerUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author monty
 * @package com.rlzz.wms.ui.login
 * @date 2018/3/23  下午3:50
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */
public class LoginActivity extends MVPActivity<LoginContract.AbsPresenter> {

    @BindView(R.id.et_account)
    TextInputEditText etAccount;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.et_date)
    EditText etDate;
    @BindView(R.id.cb_rememberPwd)
    CheckBox cbRememberPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_serverSetting)
    TextView tvServerSetting;
    /*@BindView(R.id.btn_clearPwd)
    ImageButton btnClearPwd;
    @BindView(R.id.btn_showPwd)
    CheckBox btnShowPwd;*/

    @Override
    protected LoginContract.AbsPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        toolbar.setVisibility(View.GONE);

        Date time = Calendar.getInstance().getTime();
        String format = new SimpleDateFormat("yyyy-MM-dd").format(time);
        etDate.setText(format);

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        ToastUtil.success(this, "权限申请成功");
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        ToastUtil.warning(this, "权限申请失败");
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.et_date)
    public void onEtDateClicked() {
        DatePickerUtil.showDatePickerDialog(this, etDate.getText().toString(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                etDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        });
    }

    @OnClick(R.id.btn_login)
    public void onBtnLoginClicked() {
        if (checkAccountValid() && checkPassWordValid()) {
            String account = etAccount.getText().toString();
            String password = etPassword.getText().toString();
            mPresenter.login(account, password);
        }
    }

    private boolean checkPassWordValid() {
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            ToastUtil.error(this, "密码不允许为空");
            etPassword.requestFocus();
            return false;
        } else if (password.length() < 6) {
            ToastUtil.error(this, "密码长度最少为6位");
            etPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean checkAccountValid() {
        String account = etAccount.getText().toString();
        if (TextUtils.isEmpty(account)) {
            ToastUtil.error(this, "用户名不允许为空");
            etAccount.requestFocus();
            return false;
        } else if (account.length() < 6) {
            ToastUtil.error(this, "用户名长度最少为6位");
            etAccount.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    @OnClick(R.id.tv_serverSetting)
    public void onTvServerSettingClicked() {

    }

   /* @OnCheckedChanged(R.id.btn_showPwd)
    public void onBtnShowPwdClicked(boolean checked) {
        if (checked) {
            etPassword.setInputType(EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
        }
        etPassword.setSelection(etPassword.getText().length());
    }

    @OnClick({R.id.btn_clearPwd})
    public void onBtnClearPwdClicked() {
        etPassword.getText().clear();
        if (!etPassword.hasFocus()) {
            etPassword.requestFocus();
            ((View)etPassword.getParent()).requestFocus();
        }
    }*/
}

