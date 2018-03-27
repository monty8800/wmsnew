package com.rlzz.library.net.urlmanager.parser;

import okhttp3.HttpUrl;

/**
 * @author monty
 * @package com.rlzz.library.net.urlmanager.parser
 * @date 2018/3/27  下午4:58
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

public interface UrlParser {
    /**
     * 将 {@link RetrofitUrlManager#mDomainNameHub} 中映射的 Url 解析成完整的{@link HttpUrl}
     * 用来替换 @{@link Request#url} 达到动态切换 Url
     *
     * @param domainUrl
     * @return
     */
    HttpUrl parseUrl(HttpUrl domainUrl, HttpUrl url);
}
