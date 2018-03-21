package com.rlzz.receivemanagement.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by lml on 2018/3/19.
 */

public class ReceiveSection extends SectionEntity<ReceiveBean.BodyBean.ContentBean> {

    public ReceiveSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public ReceiveSection(ReceiveBean.BodyBean.ContentBean s) {
        super(s);
    }
}
