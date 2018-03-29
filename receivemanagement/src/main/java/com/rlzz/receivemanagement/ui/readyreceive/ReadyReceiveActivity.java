package com.rlzz.receivemanagement.ui.readyreceive;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rlzz.library.common.base.BaseActivity;
import com.rlzz.receivemanagement.R;
import com.rlzz.receivemanagement.adapter.ReadyReceiveAdapter;
import com.rlzz.receivemanagement.entity.ReadyReceiveBean;
import com.rlzz.receivemanagement.ui.readyreceive.fragment.FilterFragment;

import java.util.ArrayList;
import java.util.List;

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

    ReadyReceiveAdapter adapter;
    @BindView(R.id.rl_select)
    RelativeLayout rlSelect;

//    @Override
//    public int getContentLayoutId() {
//        return R.layout.activity_ready_receive;
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready_receive);
        initData();
        initView();
    }

    private void initData() {
        //模拟数据
        List<ReadyReceiveBean> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ReadyReceiveBean bean = new ReadyReceiveBean();
            bean.setPosition(i);
            bean.setBusinessType("采购入库");
            bean.setDepName("部门");
            bean.setDocTime("2018-03-21");
            bean.setMaker("制单人");
            bean.setRemark("这是备注");
            bean.setSrcDocNoNum("i");
            bean.setSrcDocNo("KR16861616");
            bean.setRestTime("2018-11-19");
            datas.add(bean);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvReadyList.setLayoutManager(linearLayoutManager);
        adapter = new ReadyReceiveAdapter(R.layout.item_ready_receive, datas);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ToastUtil.show("000");
                Toast.makeText(ReadyReceiveActivity.this,"hah",Toast.LENGTH_SHORT).show();
            }
        });
        //erlReadyList也可以自定义向下拉的View也可以自定义向上拉的底部
        erlReadyList.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            //上拉加载
            @Override
            public void onLoadMore() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<ReadyReceiveBean> data = new ArrayList<>();
                        for (int i = 0; i < 20; i++) {
                            ReadyReceiveBean bean = new ReadyReceiveBean();
                            bean.setPosition(i);
                            bean.setBusinessType("采购入库");
                            bean.setDepName("部门");
                            bean.setDocTime("2018-03-21");
                            bean.setMaker("制单人");
                            bean.setRemark("这是备注");
                            bean.setSrcDocNoNum("i");
                            bean.setSrcDocNo("KR16861616");
                            bean.setRestTime("2018-11-19");
                            data.add(bean);
                        }

                        erlReadyList.loadMoreComplete(new EasyRefreshLayout.Event() {
                            @Override
                            public void complete() {
                                adapter.getData().addAll(data);
                                adapter.notifyDataSetChanged();

                            }
                        }, 500);

                    }
                }, 2000);


            }

            //下拉刷新
            @Override
            public void onRefreshing() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<ReadyReceiveBean> list = new ArrayList<>();
                        for (int i = 0; i < 20; i++) {
                            ReadyReceiveBean bean = new ReadyReceiveBean();
                            bean.setPosition(i + 1);
                            bean.setBusinessType("采购入库");
                            bean.setDepName("部门");
                            bean.setDocTime("2018-03-21");
                            bean.setMaker("制单人");
                            bean.setRemark("这是备注");
                            bean.setSrcDocNoNum("i");
                            bean.setSrcDocNo("KRlmlmlmlmlml");
                            bean.setRestTime("2018-11-19");
                            list.add(bean);
                        }
                        //向下拉刷新最新数据
                        adapter.setNewData(list);
                        erlReadyList.refreshComplete();
                        Toast.makeText(getApplicationContext(), "refresh success", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);

            }
        });
        rvReadyList.setAdapter(adapter);
    }

    private void initView() {

    }

    @OnClick({R.id.iv_back, R.id.rl_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_select://筛选
                openFilter();
                break;
        }
    }

    private void openFilter() {
//        View view= LayoutInflater.from(this).inflate(R.layout.fragment_drawerlayout_select,null);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        FilterFragment filterFragment=FilterFragment.newInstance();
        transaction.add(filterFragment,"filterFragment");
        transaction.addToBackStack(null);
        transaction.replace(R.id.fl_drawerContent,filterFragment).commit();
//        flDrawerContent.addView(view);

        if (!relBarcodeDetail.isDrawerOpen(Gravity.RIGHT)) {
            relBarcodeDetail.openDrawer(Gravity.RIGHT);
        } else {
            relBarcodeDetail.closeDrawer(Gravity.RIGHT);
        }
    }

}
