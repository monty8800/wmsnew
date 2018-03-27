package com.rlzz.library.net.urlmanager;

import android.text.TextUtils;

/**
 * @author monty
 * @package com.rlzz.library.net.urlmanager
 * @date 2018/3/27  下午5:00
 * @description url无效异常
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

public class InvalidUrlException extends RuntimeException {
    public InvalidUrlException(String url) {
        super("You've configured an invalid url : " + (TextUtils.isEmpty(url) ? "EMPTY_OR_NULL_URL" : url));
    }
}
