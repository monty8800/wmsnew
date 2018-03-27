package com.rlzz.receivemanagement.ui.readyreceive.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rlzz.receivemanagement.R;
import com.rlzz.receivemanagement.adapter.ReferenceAdapter;
import com.rlzz.receivemanagement.common.base.BaseFragment;
import com.rlzz.receivemanagement.entity.CommonInfoBean;
import com.rlzz.receivemanagement.entity.ReadyReceiveBean;
import com.rlzz.receivemanagement.entity.ReferenceBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lml on 2018/3/27.
 */

public class ReferenceFragment extends BaseFragment {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_referenceAll)
    TextView tvReferenceAll;
    @BindView(R.id.ll_selectAll)
    LinearLayout llSelectAll;
    @BindView(R.id.rv_referenceList)
    RecyclerView rvReferenceList;
    @BindView(R.id.erl_referenceList)
    EasyRefreshLayout erlReferenceList;
    Unbinder unbinder;

    ReferenceAdapter adapter;
    List<ReferenceBean> datas;
    String title;
    String url;
    boolean hasAll = false;

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_reference;
    }

    public ReferenceFragment() {
    }

    public static ReferenceFragment newInstance(OnSelectListener onSelectListener) {
        ReferenceFragment referenceFragment = new ReferenceFragment();
        referenceFragment.onSelectListener = onSelectListener;
        return referenceFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        initData();
        initView();
        return rootView;
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            title = bundle.getString("title");
            url = bundle.getString("url");
            hasAll = bundle.getBoolean("hasAll");
            //在正式环境中请求数据

        }
        //模拟数据
        datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CommonInfoBean bean = new CommonInfoBean();
            bean.setArcCode("123" + i);
            bean.setArcName("瑞联智造" + i);
            ReferenceBean referenceBean = new ReferenceBean();
            referenceBean.setCommonInfoBean(bean);
            referenceBean.setSelect(false);
            datas.add(referenceBean);
        }
    }

    private void initView() {
        tvTitle.setText(getActivity().getResources().getString(R.string.please_select) + title);
        if (hasAll) {
            llSelectAll.setVisibility(View.VISIBLE);
            tvReferenceAll.setText(getActivity().getResources().getString(R.string.all) + title);
        } else {
            llSelectAll.setVisibility(View.GONE);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvReferenceList.setLayoutManager(linearLayoutManager);
        adapter = new ReferenceAdapter(R.layout.item_reference, datas, getActivity());
        //erlReadyList也可以自定义向下拉的View也可以自定义向上拉的底部
        erlReferenceList.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            //上拉加载
            @Override
            public void onLoadMore() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<ReferenceBean> temp = new ArrayList<>();
                        for (int i = 0; i < 20; i++) {
                            CommonInfoBean bean = new CommonInfoBean();
                            bean.setArcCode("12345" + i);
                            bean.setArcName("上拉瑞联智造" + i);
                            ReferenceBean referenceBean = new ReferenceBean();
                            referenceBean.setCommonInfoBean(bean);
                            referenceBean.setSelect(false);
                            temp.add(referenceBean);
                        }

                        erlReferenceList.loadMoreComplete(new EasyRefreshLayout.Event() {
                            @Override
                            public void complete() {
                                adapter.getData().addAll(temp);
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
                        List<ReferenceBean> temp = new ArrayList<>();
                        for (int i = 0; i < 20; i++) {
                            CommonInfoBean bean = new CommonInfoBean();
                            bean.setArcCode("435" + i);
                            bean.setArcName("下拉瑞联智造" + i);
                            ReferenceBean referenceBean = new ReferenceBean();
                            referenceBean.setCommonInfoBean(bean);
                            referenceBean.setSelect(false);
                            temp.add(referenceBean);
                        }
                        //向下拉刷新最新数据
                        adapter.setNewData(temp);
                        erlReferenceList.refreshComplete();
//                        Toast.makeText(getApplicationContext(), "refresh success", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);

            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (onSelectListener != null) {
                    onSelectListener.selectItem(false, datas.get(position));
                    getFragmentManager().popBackStack();
//                    goBack();
                } else {
                    throw new RuntimeException("please setup the onSelectListener for ReferenceFragment");
                }
            }
        });
        rvReferenceList.setAdapter(adapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_back, R.id.ll_selectAll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                goBack();
                break;
            case R.id.ll_selectAll:
                if (onSelectListener != null) {
                    onSelectListener.selectItem(true, null);
//                    goBack();
                    getFragmentManager().popBackStack();
                } else {
                    throw new RuntimeException("please setup the onSelectListener for ReferenceFragment");
                }
                break;
        }
    }

    private void goBack() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        FilterFragment filterFragment = FilterFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.fl_drawerContent, filterFragment).commit();
    }


    private OnSelectListener onSelectListener;

    //选择之后的监听器
    public interface OnSelectListener {
        public void selectItem(boolean selectAll, ReferenceBean referenceBean);
    }
}
