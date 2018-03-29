package com.rlzz.wms.ui.login;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.rlzz.library.common.base.MVPActivity;
import com.rlzz.library.utils.DatePickerUtil;
import com.rlzz.library.utils.ToastUtil;
import com.rlzz.wms.R;
import com.rlzz.wms.ui.main.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    public static void GoToActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }
    @Override
    protected LoginContract.AbsPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
//        toolbar.setVisibility(View.GONE);

        setDateInitialValue();

        setServerSetting();

    }

    private void setDateInitialValue() {
        Date time = Calendar.getInstance().getTime();
        String format = new SimpleDateFormat("yyyy-MM-dd").format(time);
        etDate.setText(format);
    }

    private void setServerSetting() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(getString(R.string.login_server_setting));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                ToastUtil.normal(LoginActivity.this, "进入服务器设置");
            }
        };
        spannableStringBuilder.setSpan(clickableSpan, 8, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.app_azure));
        spannableStringBuilder.setSpan(foregroundColorSpan, 8, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvServerSetting.setText(spannableStringBuilder);
        tvServerSetting.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        ToastUtil.success(this, "权限申请成功");
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        ToastUtil.warning(this, "权限申请失败");
    }

//    @Override
//    public int getContentLayoutId() {
//        return R.layout.activity_login;
//    }

    @OnClick(R.id.et_date)
    public void onEtDateClicked() {
        DatePickerUtil.showDatePickerDialog(this, etDate.getText().toString(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                etDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        });
    }

    private boolean isPassWordValid() {
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            ToastUtil.warning(this, "密码不允许为空");
            etPassword.requestFocus();
            return false;
        } else if (password.length() < 6) {
            ToastUtil.warning(this, "密码长度最少为6位");
            etPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean isAccountValid() {
        String account = etAccount.getText().toString();
        if (TextUtils.isEmpty(account)) {
            ToastUtil.warning(this, "用户名不允许为空");
            etAccount.requestFocus();
            return false;
        } else if (account.length() < 6) {
            ToastUtil.warning(this, "用户名长度最少为6位");
            etAccount.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    @OnClick(R.id.btn_login)
    public void onBtnLoginClicked() {
        if (isAccountValid() && isPassWordValid()) {
            String account = etAccount.getText().toString();
            String password = etPassword.getText().toString();
            boolean isRememberPwd = cbRememberPwd.isChecked();
//            mPresenter.login(account, password, isRememberPwd);
            MainActivity.GoToActivity(this);
        }
    }

    @OnClick(R.id.tv_serverSetting)
    public void onTvServerSettingClicked() {
        ServerSettingActivity.GoToActivity(this);
    }
}

