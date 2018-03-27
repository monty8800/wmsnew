package com.rlzz.wms.observer;

import android.support.annotation.Nullable;

/**
 * @author monty
 * @package com.rlzz.wms.observer
 * @date 2018/3/22  下午5:34
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

public interface ObserverListener<T> {
    void runOnUiThread(@Nullable T t);
}
