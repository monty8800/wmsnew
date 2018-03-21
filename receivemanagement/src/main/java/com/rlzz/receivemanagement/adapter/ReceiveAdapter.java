package com.rlzz.receivemanagement.adapter;

import android.util.Log;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rlzz.receivemanagement.R;
import com.rlzz.receivemanagement.entity.ReceiveSection;

import java.util.List;

/**
 * Created by lml on 2018/3/19.
 */

public class ReceiveAdapter extends BaseSectionQuickAdapter<ReceiveSection,BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public ReceiveAdapter(int layoutResId, int sectionHeadResId, List<ReceiveSection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, ReceiveSection item) {
        helper.setText(R.id.tv_header,item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReceiveSection item) {
        helper.setText(R.id.tv_content,item.t.getTitle());
        Log.e(TAG, "convert: "+helper.getLayoutPosition());
        if(item.t.getTips().equals("") || item.t.getTips()==null){
            helper.setVisible(R.id.tv_tips,false);
        }else{
            helper.setVisible(R.id.tv_tips,true);
            helper.setText(R.id.tv_tips,item.t.getTips());
        }
    }
}
