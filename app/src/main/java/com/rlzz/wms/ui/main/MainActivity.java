package com.rlzz.wms.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qihoo360.replugin.model.PluginInfo;
import com.rlzz.library.common.base.BaseActivity;
import com.rlzz.library.manager.AppManager;
import com.rlzz.library.utils.ToastUtil;
import com.rlzz.library.utils.ToolBarUtil;
import com.rlzz.wms.R;
import com.rlzz.wms.adapter.MenuAdapter;
import com.rlzz.wms.core.RlPlugin;
import com.rlzz.wms.demo.TestActivity;
import com.rlzz.wms.entity.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private List<MenuItem> menuItems;

    private boolean isQuit = false; // 标记是否退出

    public static void GoToActivity(Context context){
        context.startActivity(new Intent(context,MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initToolbar();

        initMenu();

        this.recyclerView = findViewById(R.id.rcv_menu);
        this.recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        this.recyclerView.setAdapter(getMenuAdapter());

    }

    private void initToolbar() {
        ToolBarUtil.setToolBarTitle(toolbar, getString(R.string.main_title));
        toolbar.setOnClickListener(new View.OnClickListener() {
            // 点击首页toolbar进入Debug页面
            final static int COUNTS = 7;//点击次数
            final static long DURATION = 2 * 1000;//规定有效时间
            long[] mHits = new long[COUNTS];

            @Override
            public void onClick(View v) {
                System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
                mHits[mHits.length - 1] = SystemClock.uptimeMillis();
                if (mHits[0] >= (SystemClock.uptimeMillis() - DURATION)) {
                    TestActivity.GoToActivity(MainActivity.this);
                }
            }
        });
    }

    private MenuAdapter getMenuAdapter(/*int index,RecyclerView recyclerView*/) {
        MenuAdapter menuAdapter = new MenuAdapter(R.layout.layout_main_menu_item, R.layout.layout_main_menu_header_item, menuItems);
        menuAdapter.setOnItemClickListener((adapter, view, position) -> {
            MenuItem menuItem = menuItems.get(position);
            if (!menuItem.isHeader) {
                PluginInfo pl = menuItem.t;
                RlPlugin.launchPlugin(this, pl.getName());
                ToastUtil.info(this,menuItem.t.getAppLabel().toString());
            }
        });
        return menuAdapter;
    }

    private PluginInfo getSamplePluginInfo() {
        PluginInfo pluginInfo = null;
        List<PluginInfo> pluginInfoList = RlPlugin.getPluginInfoList();
        if (pluginInfoList != null && pluginInfoList.size() >= 1) {
            pluginInfo = pluginInfoList.get(0);
        }
        return pluginInfo;
    }

    // TODO: 2018/3/20  在插件Manifest中配置插件类型，按照"收发货"-0, "库内"-1, "查询&设置"-2 三种类型分组
    private void initMenu() {
        PluginInfo samplePluginInfo = getSamplePluginInfo();
        String[] menu_headers = getResources().getStringArray(R.array.menu_group);
        menuItems = new ArrayList<>();
        for (int i = 0; i < menu_headers.length; i++) {
            MenuItem menuHeader = new MenuItem(true, menu_headers[i]);
            menuItems.add(menuHeader);

            Random random = new Random();
            int ran = random.nextInt(6);

            int count = ran + 1;
            for (int j = 0; j < count; j++) {
                MenuItem menuItem = new MenuItem(samplePluginInfo);
                menuItems.add(menuItem);
            }
        }
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        if (!isQuit) {
            ToastUtil.info(this,"再按一次退出程序");
            isQuit = true;
            Observable.timer(2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
                @Override
                public void accept(Long aLong) throws Exception {
                    isQuit = false;
                }
            });
            return;
        } else {
            AppManager.exitApp();
        }
    }
}
