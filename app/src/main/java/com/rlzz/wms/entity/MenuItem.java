package com.rlzz.wms.entity;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.qihoo360.replugin.model.PluginInfo;

/**
 * @author monty
 * @package com.rlzz.wms.entity
 * @date 2018/3/19  下午4:48
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

public class MenuItem extends SectionEntity<PluginInfo> {

    public MenuItem(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MenuItem(PluginInfo pluginInfo) {
        super(pluginInfo);
    }
}
