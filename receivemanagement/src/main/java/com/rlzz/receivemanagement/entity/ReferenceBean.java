package com.rlzz.receivemanagement.entity;

/**
 * Created by lml on 2018/3/27.
 */

public class ReferenceBean {
    private CommonInfoBean commonInfoBean;
    private boolean isSelect;

    public CommonInfoBean getCommonInfoBean() {
        return commonInfoBean;
    }

    public void setCommonInfoBean(CommonInfoBean commonInfoBean) {
        this.commonInfoBean = commonInfoBean;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
