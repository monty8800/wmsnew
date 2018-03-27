package com.rlzz.receivemanagement.entity;

/**
 * Created by lml on 2018/3/27.
 * 用于部门，仓库，业务类型，收发类别，供应商信息的参照
 */

public class CommonInfoBean {
    private String id;
    private String arcCode;
    private String arcName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArcCode() {
        return arcCode;
    }

    public void setArcCode(String arcCode) {
        this.arcCode = arcCode;
    }

    public String getArcName() {
        return arcName;
    }

    public void setArcName(String arcName) {
        this.arcName = arcName;
    }
}
