package com.rlzz.wms.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rlzz.library.R;
import com.rlzz.library.common.base.BaseActivity;
import com.rlzz.library.common.base.viewinterface.IView;
import com.rlzz.library.utils.ToolBarUtil;

import butterknife.ButterKnife;


/**
 * 基础Activity
 * 处理LoadingDialog、ToolBar
 *
 * @author monty
 * @date 2017/8/11
 */

public abstract class BaseToolBarActivity extends BaseActivity implements IView {

    public static final String TAG = BaseToolBarActivity.class.getSimpleName();

    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        setupView(findViewById(R.id.container));

        ButterKnife.bind(this);
        toolbar = findViewById(R.id.toolbar);
        initToolBar();

    }

    protected void initToolBar() {
        ToolBarUtil.setStatusBarColor(this, getResources().getColor(R.color.app_azure));
        ToolBarUtil.setStatusBarFits(toolbar);
        ToolBarUtil.setToolbarNavigation(toolbar, R.drawable.ic_icon_chevron_left, R.string.desc_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setupView(ViewGroup containerView) {
        int contentLayoutId = getContentLayoutId();
        View view = LayoutInflater.from(this).inflate(contentLayoutId, null, false);
        containerView.addView(view);
    }
}
