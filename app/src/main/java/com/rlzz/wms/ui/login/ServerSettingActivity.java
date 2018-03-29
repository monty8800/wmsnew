package com.rlzz.wms.ui.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.rlzz.library.utils.PreferencesManager;
import com.rlzz.library.utils.RegexUtils;
import com.rlzz.library.utils.ToastUtil;
import com.rlzz.library.utils.ToolBarUtil;
import com.rlzz.wms.R;
import com.rlzz.wms.base.BaseToolBarActivity;
import com.rlzz.wms.entity.ServerData;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * @author monty
 * @package com.rlzz.wms.ui.login
 * @date 2018/3/26  下午3:50
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */
public class ServerSettingActivity extends BaseToolBarActivity {

    @BindView(R.id.et_protocol)
    EditText etProtocol;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_port)
    EditText etPort;
    @BindView(R.id.et_catalog)
    EditText etCatalog;
    @BindView(R.id.et_accountBook)
    EditText etAccountBook;
    @BindView(R.id.btn_save)
    Button btnSave;

    private ServerData serverData;

    private CharSequence[] protocols = {"http", "https"};

    public static void GoToActivity(Context context) {
        context.startActivity(new Intent(context, ServerSettingActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ToolBarUtil.setToolBarTitle(toolbar,"服务器设置");

        if (savedInstanceState == null) {
            serverData = new ServerData();
            serverData.protocol = PreferencesManager.getInstanceDefault().getInt("protocol", 0);
            serverData.ipAddress = PreferencesManager.getInstanceDefault().getString("ipAddress");
            serverData.port = PreferencesManager.getInstanceDefault().getString("port");
            serverData.catalog = PreferencesManager.getInstanceDefault().getString("catalog");
            serverData.accountBook = PreferencesManager.getInstanceDefault().getString("accountBook");
        } else {
            serverData = savedInstanceState.getParcelable("serverData");
        }

        etProtocol.setText(protocols[serverData.protocol]);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("serverData", serverData);
        super.onSaveInstanceState(outState);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_server_setting;
    }

    @OnClick(R.id.et_protocol)
    public void onEtProtocolClicked() {

        String protocol = etProtocol.getText().toString();

        int selectIndex = Arrays.binarySearch(protocols, 0, protocols.length, protocol);

        new AlertDialog.Builder(this).setSingleChoiceItems(protocols, selectIndex, (dialog, which) -> {
            serverData.protocol = which;
            etProtocol.setText(protocols[serverData.protocol]);
        }).setTitle("选择接口协议").show();
    }

    @OnClick(R.id.et_accountBook)
    public void onAccountBookClicked() {
        // TODO: 2018/3/26 请求帐套
        String protocol = etProtocol.getText().toString();
        String port = etPort.getText().toString();
        String ipAddress = etAddress.getText().toString();
        String catalog = etCatalog.getText().toString();

    }

    @OnClick(R.id.btn_save)
    public void onBtnSaveClicked() {
        if (isIpAddressValid() && isPortValid() && isAccountBookValid()) {
            saveConfig();
            showLoading();
            Observable.timer(3, TimeUnit.SECONDS).subscribe(aLong -> closeLoading());
        }
    }

    private void saveConfig() {
        PreferencesManager.getInstanceDefault().putInt("protocol", serverData.protocol);
        PreferencesManager.getInstanceDefault().putString("ipAddress", serverData.ipAddress);
        PreferencesManager.getInstanceDefault().putString("port", serverData.port);
        PreferencesManager.getInstanceDefault().putString("catalog", serverData.catalog);
        PreferencesManager.getInstanceDefault().putString("accountBook", serverData.accountBook);
    }

    private boolean isAccountBookValid() {
        String accountBook = etAccountBook.getText().toString();
        if (TextUtils.isEmpty(accountBook)) {
            ToastUtil.warning(this, "帐套号不允许为空");
            return false;
        } else {
            serverData.accountBook = accountBook;
        }
        return true;
    }

    private boolean isPortValid() {
        String port = etPort.getText().toString();
        if (TextUtils.isEmpty(port)) {
            ToastUtil.warning(this, "端口号不允许为空");
            return false;
        } else {
            serverData.port = port;
            return true;
        }
    }

    private boolean isIpAddressValid() {
        String ipAddress = etAddress.getText().toString();
        if (!RegexUtils.isIP(ipAddress)) {
            ToastUtil.warning(this, "IP地址格式不正确");
            return false;
        } else {
            serverData.ipAddress = ipAddress;
            return true;
        }

    }


}
