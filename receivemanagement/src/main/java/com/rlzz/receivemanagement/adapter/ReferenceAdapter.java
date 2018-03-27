package com.rlzz.receivemanagement.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rlzz.receivemanagement.R;
import com.rlzz.receivemanagement.entity.CommonInfoBean;
import com.rlzz.receivemanagement.entity.ReadyReceiveBean;
import com.rlzz.receivemanagement.entity.ReferenceBean;

import java.util.List;

/**
 * Created by lml on 2018/3/21.
 */

public class ReferenceAdapter extends BaseQuickAdapter<ReferenceBean,BaseViewHolder> {
    private Context context;

    public ReferenceAdapter(int layoutResId, @Nullable List<ReferenceBean> data,Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ReferenceBean item) {
        if(item.isSelect()){
            helper.setTextColor(R.id.tv_arcCode,context.getResources().getColor(R.color.title_bg));
            helper.setText(R.id.tv_arcCode,item.getCommonInfoBean().getArcCode());
            helper.setTextColor(R.id.tv_arcName,context.getResources().getColor(R.color.title_bg));
            helper.setText(R.id.tv_arcName,item.getCommonInfoBean().getArcName());
            helper.setVisible(R.id.iv_select,true);
        }else{
            helper.setTextColor(R.id.tv_arcCode,context.getResources().getColor(R.color.main_header));
            helper.setText(R.id.tv_arcCode,item.getCommonInfoBean().getArcCode());
            helper.setTextColor(R.id.tv_arcName,context.getResources().getColor(R.color.main_header));
            helper.setText(R.id.tv_arcName,item.getCommonInfoBean().getArcName());
            helper.setVisible(R.id.iv_select,false);
        }
    }
}
