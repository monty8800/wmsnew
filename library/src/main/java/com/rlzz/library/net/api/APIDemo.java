package com.rlzz.library.net.api;

import com.rlzz.library.net.bean.ResultModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author monty
 * @package com.rlzz.wms.net.api
 * @date 2018/3/8  下午3:57
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

public interface APIDemo {

        /**
         * 测试服务器地址是否可用
         *
         * @return
         */
        @GET("/article/list/{page}/json")
        Observable<ResultModel<String>> getBlog(@Path("page") int page);
}
