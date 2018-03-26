package com.rlzz.wms.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * @author monty
 * @package com.rlzz.wms.widget
 * @date 2018/3/24  下午2:12
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

public class PassWordEditText extends AppCompatEditText {
    public PassWordEditText(Context context) {
        this(context,null);
    }

    public PassWordEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PassWordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }
}
