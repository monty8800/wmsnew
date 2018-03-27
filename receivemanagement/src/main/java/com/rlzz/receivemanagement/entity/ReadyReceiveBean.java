package com.rlzz.receivemanagement.entity;

/**
 * Created by lml on 2018/3/21.
 */

public class ReadyReceiveBean {
    //位置
    private int position;
    //来源单据号
    private String srcDocNo;
    //来源单据行号
    private String srcDocNoNum;
    //单据时间
    private String docTime;
    //部门名称
    private String depName;
    //制单人
    private String maker;
    //业务类型
    private String businessType;
    //制单剩余时间
    private String restTime;
    //备注
    private String remark;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getSrcDocNo() {
        return srcDocNo;
    }

    public void setSrcDocNo(String srcDocNo) {
        this.srcDocNo = srcDocNo;
    }

    public String getSrcDocNoNum() {
        return srcDocNoNum;
    }

    public void setSrcDocNoNum(String srcDocNoNum) {
        this.srcDocNoNum = srcDocNoNum;
    }

    public String getDocTime() {
        return docTime;
    }

    public void setDocTime(String docTime) {
        this.docTime = docTime;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getRestTime() {
        return restTime;
    }

    public void setRestTime(String restTime) {
        this.restTime = restTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
