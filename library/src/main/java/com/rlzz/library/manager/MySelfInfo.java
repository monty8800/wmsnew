package com.rlzz.wms.manager;

import android.text.TextUtils;

import com.rlzz.wms.common.Constants;
import com.rlzz.wms.utils.PreferencesManager;

import java.util.List;

/**
 * 用户信息管理类
 * Created by monty on 2017/9/28.
 */

public class MySelfInfo {
    private String account;
    private String nickName;
    private String token;
    private List<String> roles;

    /**
     * 数量精度
     */
    private int qtyScale = -1;


    public static MySelfInfo getInstance() {
        return MySelfInfoHolder.INSTANCE;
    }

    private static final class MySelfInfoHolder {
        private static final MySelfInfo INSTANCE = new MySelfInfo();
    }

    /*public String getAccount() {
        if (BuildConfig.DEBUG && TextUtils.isEmpty(account)) {
            account = "rlzz";
        }
        return account;
    }*/

    public void setAccount(String account) {
        this.account = account;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getRoles() {
        return this.roles;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取Token
     * 判断内存缓存是否有token，如果没有就取配置项中的token
     *
     * @return
     */
    public String getToken() {
        if (TextUtils.isEmpty(token)) {
            return PreferencesManager.getInstanceUser().getString(Constants.KEY_TOKEN);
        }
        return token;
    }

    public boolean saveToken2cache(String token) {
        boolean saveResult = PreferencesManager.getInstanceUser().putString(Constants.KEY_TOKEN, token);
        if (saveResult) {
            this.token = token;
        }
        return saveResult;
    }

    public int getQtyScale() {
        if (qtyScale == -1) {
            qtyScale = PreferencesManager.getInstanceUser().getInt(Constants.KEY_QTY_SCALE, 2);
        }
        return qtyScale;
    }

    /**
     * 设置精度
     *
     * @param qtyScale
     * @return
     */
    public boolean setQtyScale(int qtyScale) {
        if (PreferencesManager.getInstanceUser().putInt(Constants.KEY_QTY_SCALE, qtyScale)) {
            this.qtyScale = qtyScale;
            return true;
        }
        return false;
    }

    /**
     * 清除密码
     */
    public void clearPassword() {
        PreferencesManager.getInstanceUser().putString(Constants.KEY_PASSWORD, "");
    }
}
