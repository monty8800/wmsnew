package com.rlzz.wms.net.bean;

/**
 * 基础数据模型
 *
 * @author monty
 * @date 2017/7/18
 */

public class ResultModel<T> {
    public int status;
    public String msg;
    private T data;

    public boolean isSuccess() {
        return status == 200;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(T data) {
        this.data = data;
    }
}
