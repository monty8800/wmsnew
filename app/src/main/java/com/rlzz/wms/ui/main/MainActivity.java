package com.rlzz.wms.ui.main;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qihoo360.replugin.model.PluginInfo;
import com.rlzz.wms.R;
import com.rlzz.wms.adapter.MenuAdapter;
import com.rlzz.wms.common.base.BaseActivity;
import com.rlzz.wms.core.RlPlugin;
import com.rlzz.wms.entity.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private List<MenuItem> menuItems;

    public final static String[] menu_headers = {"收发货","库内","查询&设置"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.recyclerView = findViewById(R.id.rcv_menu);
        this.recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        initMenu();

        MenuAdapter menuAdapter = new MenuAdapter(R.layout.layout_main_menu_item,R.layout.layout_main_menu_header_item,menuItems);
        menuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MenuItem menuItem = menuItems.get(position);
                if(!menuItem.isHeader){
                    Toast.makeText(MainActivity.this, menuItem.t.getAppLabel(), Toast.LENGTH_LONG).show();
                }
            }
        });
        this.recyclerView.setAdapter(menuAdapter);
    }

    private PluginInfo getSamplePluginInfo(){
        PluginInfo pluginInfo = null;
        List<PluginInfo> pluginInfoList = RlPlugin.getPluginInfoList();
        if(pluginInfoList!=null&&pluginInfoList.size()>=1){
            pluginInfo = pluginInfoList.get(0);
        }
        return pluginInfo;
    }

    private void initMenu() {
        PluginInfo samplePluginInfo = getSamplePluginInfo();
        menuItems = new ArrayList<>();
        for (int i = 0; i < menu_headers.length; i++) {
            MenuItem menuHeader = new MenuItem(true,menu_headers[i]);
            menuItems.add(menuHeader);
            for (int j = 0; j < 4; j++) {
                MenuItem menuItem = new MenuItem(samplePluginInfo);
                menuItems.add(menuItem);
            }
        }

    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
    }
}
