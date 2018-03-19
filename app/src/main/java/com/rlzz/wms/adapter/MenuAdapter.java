package com.rlzz.wms.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rlzz.wms.R;
import com.rlzz.wms.entity.MenuItem;

import java.util.List;

/**
 * @author monty
 * @package com.rlzz.wms.adapter
 * @date 2018/3/19  下午4:49
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

public class MenuAdapter extends BaseSectionQuickAdapter<MenuItem,BaseViewHolder> {

    public MenuAdapter(int layoutResId, int sectionHeadResId, List<MenuItem> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MenuItem item) {
        helper.setText(R.id.tv_item_header,item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, MenuItem item) {
        helper.setImageDrawable(R.id.img_appIcon,item.t.getIcon());
        helper.setText(R.id.tv_appName,item.t.getAppLabel());
    }
}
