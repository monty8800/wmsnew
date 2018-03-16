package com.rlzz.wms.net.exception;

/**
 * api接口错误/异常统一处理类
 *
 * @author monty
 * @date 2017/9/21
 * <p>
 * "timestamp": "2017-09-29T10:24:29.222+0800",
 * "status": 500,
 * "error": "Internal Server Error",
 * "exception": "java.lang.RuntimeException",
 * "message": "testing exception",
 * "path": "/api/login"
 */
public class ResponseException extends Exception {
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 服务错误码
     */
    private int status;
    /**
     * 业务错误码
     */
    private String code;
    /**
     * 错误描述
     */
    private String error;
    /**
     * 异常类型
     */
    private String exception;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 接口路径
     */
    private String path;

    public ResponseException(Throwable throwable, int status) {
        super(throwable);
        this.status = status;
    }

    public ResponseException(Throwable throwable, String message) {
        super(throwable);
        this.message = message;
    }

    public ResponseException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
