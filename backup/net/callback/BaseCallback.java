package com.rlzz.library.net.callback;

import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.rlzz.library.net.exception.ExceptionHandle;
import com.rlzz.library.net.exception.ResponseException;

import java.io.IOException;

/**
 * @author monty
 * @package com.rlzz.library.net.callback
 * @date 2018/3/27  下午2:41
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

public abstract class BaseCallback<T> extends AbsCallback<T> implements IFailureCallback {


    @Override
    public void onError(Response<T> response) {
        super.onError(response);

        Throwable exception = response.getException();

        if (null != exception) exception.printStackTrace();

        try {
            Throwable throwable = ExceptionHandle.handleException(exception);
            if (throwable instanceof ResponseException) { // 服务端业务报错
                ResponseException responseException = (ResponseException) throwable;
                /** token 失效 code为2000，跳转到登录页面 */
                if ("2000".equals(responseException.getCode())) {
                    // TODO: 2018/3/27 退出登录
                }
                onFailure(responseException.getCode(), responseException.getMessage(), throwable);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFailure(int errCode, String errMessage, Throwable throwable) {

    }
}
