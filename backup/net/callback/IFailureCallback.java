package com.rlzz.library.net.callback;

/**
 * @author monty
 * @package com.rlzz.library.net.callback
 * @date 2018/3/27  下午2:51
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

public interface IFailureCallback {
    void onFailure(int errCode,String errMessage,Throwable throwable);
}
