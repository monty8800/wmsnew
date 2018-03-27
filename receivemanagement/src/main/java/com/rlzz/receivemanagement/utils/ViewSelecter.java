package com.rlzz.receivemanagement.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.rlzz.receivemanagement.R;
import com.rlzz.receivemanagement.entity.ViewGroupBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lml on 2018/3/26.
 */

public class ViewSelecter {
    public static final String SELECTALL="selectAll";//多选
    public static final String SELECTSINGLE="selectSingle";//单选


    /**
     * 返回选择的条目
     * @param viewGroupBeans
     * @param flag
     * @return
     */
    public static List<ViewGroupBean> setSelectedView(List<ViewGroupBean> viewGroupBeans, String flag, Context context){
        List<ViewGroupBean> selectItems=new ArrayList<>();
        if(flag.equals(SELECTALL)){
            selectItems=selectMost(viewGroupBeans,context);
        }else{
            selectItems=selectSingle(viewGroupBeans,context);
        }
        return selectItems;
    }

    /**
     * 单项选择
     * @param viewGroupBeans
     * @param context
     * @return
     */
    private static List<ViewGroupBean> selectSingle(List<ViewGroupBean> viewGroupBeans, Context context) {
        int size=viewGroupBeans.size();
        for(int i=0;i<size;i++){
            ViewGroupBean bean=viewGroupBeans.get(i);
            final int position=i;
            bean.getLlWrap().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!bean.isSelected()){
                        bean.getLlWrap().setBackground(context.getResources().getDrawable(R.drawable.select_shape));
                        bean.getIvSelected().setVisibility(View.VISIBLE);
                        bean.getTvSelected().setTextColor(context.getResources().getColor(R.color.title_bg));
                        bean.setSelected(true);
                        for(int j=0;j<size;j++){
                            if(position!=j){
                                ViewGroupBean bean=viewGroupBeans.get(j);
                                bean.getLlWrap().setBackground(context.getResources().getDrawable(R.drawable.unselect_shape));
                                bean.getIvSelected().setVisibility(View.GONE);
                                bean.getTvSelected().setTextColor(context.getResources().getColor(R.color.dark_gray));
                                bean.setSelected(false);
                            }
                        }
                    }
                }
            });
        }

        List<ViewGroupBean> tempBeans=new ArrayList<>();
        for(int k=0;k<size;k++){
            ViewGroupBean tempBean=viewGroupBeans.get(k);
            if(tempBean.isSelected()){
                tempBeans.add(tempBean);
            }
        }
        //返回选择的项
        return tempBeans;
    }

    /**
     * 多项选择
     * @param viewGroupBeans
     * @param context
     * @return
     */
    private static List<ViewGroupBean> selectMost(List<ViewGroupBean> viewGroupBeans,Context context) {
        int size=viewGroupBeans.size();
        for(int i=0;i<size;i++){
            ViewGroupBean bean=viewGroupBeans.get(i);
            bean.getLlWrap().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(bean.isSelected()){
                        bean.getLlWrap().setBackground(context.getResources().getDrawable(R.drawable.unselect_shape));
                        bean.getIvSelected().setVisibility(View.GONE);
                        bean.getTvSelected().setTextColor(context.getResources().getColor(R.color.dark_gray));
                        bean.setSelected(false);
                    }else{
                        bean.getLlWrap().setBackground(context.getResources().getDrawable(R.drawable.select_shape));
                        bean.getIvSelected().setVisibility(View.VISIBLE);
                        bean.getTvSelected().setTextColor(context.getResources().getColor(R.color.title_bg));
                        bean.setSelected(true);
                    }
                }
            });
        }
        List<ViewGroupBean> tempBeans=new ArrayList<>();
        for(int k=0;k<size;k++){
            ViewGroupBean tempBean=viewGroupBeans.get(k);
            if(tempBean.isSelected()){
                tempBeans.add(tempBean);
            }
        }
        //返回选择的项
        return tempBeans;
    }
}
