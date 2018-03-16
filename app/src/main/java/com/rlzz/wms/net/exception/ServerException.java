package com.rlzz.wms.net.exception;

/**
 * 自定义服务器错误
 *
 * @author monty
 * @date 2017/9/21
 */
@Deprecated
public class ServerException extends RuntimeException {
    private int code;
    private String msg;

    public ServerException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
