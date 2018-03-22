package com.rlzz.receivemanagement.ui.readyreceive;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.rlzz.receivemanagement.R;
import com.rlzz.receivemanagement.common.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lml on 2018/3/21.
 */

public class ReadyReceiveActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_select)
    TextView tvSelect;
    @BindView(R.id.rv_readyList)
    RecyclerView rvReadyList;
    @BindView(R.id.erl_readyList)
    EasyRefreshLayout erlReadyList;
    @BindView(R.id.fl_drawerContent)
    FrameLayout flDrawerContent;
    @BindView(R.id.rel_barcode_detail)
    DrawerLayout relBarcodeDetail;

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_ready_receive;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initData() {

    }

    private void initView() {

    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
