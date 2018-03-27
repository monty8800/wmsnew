package com.rlzz.receivemanagement.adapter;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rlzz.receivemanagement.R;
import com.rlzz.receivemanagement.entity.ReadyReceiveBean;

import java.util.List;

/**
 * Created by lml on 2018/3/21.
 */

public class ReadyReceiveAdapter extends BaseQuickAdapter<ReadyReceiveBean,BaseViewHolder> {
    public ReadyReceiveAdapter(int layoutResId, @Nullable List<ReadyReceiveBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReadyReceiveBean item) {
        helper.setText(R.id.tv_rowNum,item.getPosition()+"");
        helper.setText(R.id.tv_srcDocNo,item.getSrcDocNo());
        Log.e(TAG, "convert: 11111");
    }
}
