package com.rlzz.library.net.urlmanager;

import okhttp3.HttpUrl;

/**
 * @author monty
 * @package com.rlzz.library.net.urlmanager
 * @date 2018/3/27  下午5:01
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

class Utils {
    private Utils() {
        throw new IllegalStateException("do not instantiation me");
    }

    static HttpUrl checkUrl(String url) {
        HttpUrl parseUrl = HttpUrl.parse(url);
        if (null == parseUrl) {
            throw new InvalidUrlException(url);
        } else {
            return parseUrl;
        }
    }
}
