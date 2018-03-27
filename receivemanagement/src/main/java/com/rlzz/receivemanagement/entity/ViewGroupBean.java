package com.rlzz.receivemanagement.entity;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by lml on 2018/3/26.
 * 筛选的条件中的多选或者单选的Bean
 */

public class ViewGroupBean {
    private LinearLayout llWrap;
    private ImageView ivSelected;
    private TextView tvSelected;
    private boolean isSelected;
    private int resId;//每个布局的ID

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public LinearLayout getLlWrap() {
        return llWrap;
    }

    public void setLlWrap(LinearLayout llWrap) {
        this.llWrap = llWrap;
    }

    public ImageView getIvSelected() {
        return ivSelected;
    }

    public void setIvSelected(ImageView ivSelected) {
        this.ivSelected = ivSelected;
    }

    public TextView getTvSelected() {
        return tvSelected;
    }

    public void setTvSelected(TextView tvSelected) {
        this.tvSelected = tvSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
