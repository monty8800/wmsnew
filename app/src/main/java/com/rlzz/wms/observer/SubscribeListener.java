package com.rlzz.wms.observer;

/**
 * @author monty
 * @package com.rlzz.wms.observer
 * @date 2018/3/22  下午5:33
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

public interface SubscribeListener<T> {
    T runOnSubThread();
}
