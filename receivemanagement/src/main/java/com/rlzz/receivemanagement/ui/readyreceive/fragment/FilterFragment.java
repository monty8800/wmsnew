package com.rlzz.receivemanagement.ui.readyreceive.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rlzz.library.common.base.BaseFragment;
import com.rlzz.library.utils.LogUtil;
import com.rlzz.receivemanagement.R;
import com.rlzz.receivemanagement.entity.ReferenceBean;
import com.rlzz.receivemanagement.entity.ViewGroupBean;
import com.rlzz.receivemanagement.utils.CommonUtil;
import com.rlzz.receivemanagement.utils.ViewSelecter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lml on 2018/3/26.
 * 筛选fragment
 */

public class FilterFragment extends BaseFragment implements ReferenceFragment.OnSelectListener {
    View root;
    @BindView(R.id.iv_myself)
    ImageView ivMyself;
    @BindView(R.id.tv_myself)
    TextView tvMyself;
    @BindView(R.id.ll_myself)
    LinearLayout llMyself;
    @BindView(R.id.iv_everyOne)
    ImageView ivEveryOne;
    @BindView(R.id.tv_everyOne)
    TextView tvEveryOne;
    @BindView(R.id.ll_everyOne)
    LinearLayout llEveryOne;
    @BindView(R.id.iv_unhandle)
    ImageView ivUnhandle;
    @BindView(R.id.tv_unhandle)
    TextView tvUnhandle;
    @BindView(R.id.ll_unhandle)
    LinearLayout llUnhandle;
    @BindView(R.id.iv_entered)
    ImageView ivEntered;
    @BindView(R.id.tv_entered)
    TextView tvEntered;
    @BindView(R.id.ll_entered)
    LinearLayout llEntered;
    @BindView(R.id.iv_all)
    ImageView ivAll;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.ll_all)
    LinearLayout llAll;
    @BindView(R.id.tv_startTime)
    TextView tvStartTime;
    @BindView(R.id.tv_endTime)
    TextView tvEndTime;
    @BindView(R.id.tv_billNo)
    TextView tvBillNo;
    @BindView(R.id.iv_right_dep)
    ImageView ivRightDep;
    @BindView(R.id.tv_departName)
    TextView tvDepartName;
    @BindView(R.id.rl_depart)
    RelativeLayout rlDepart;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_maker)
    TextView tvMaker;
    @BindView(R.id.rl_maker)
    RelativeLayout rlMaker;
    @BindView(R.id.iv_purchaseIn)
    ImageView ivPurchaseIn;
    @BindView(R.id.tv_purchaseIn)
    TextView tvPurchaseIn;
    @BindView(R.id.ll_purchaseIn)
    LinearLayout llPurchaseIn;
    @BindView(R.id.iv_finishedIn)
    ImageView ivFinishedIn;
    @BindView(R.id.tv_finishedIn)
    TextView tvFinishedIn;
    @BindView(R.id.ll_finishedIn)
    LinearLayout llFinishedIn;
    @BindView(R.id.tv_reset)
    TextView tvReset;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    Unbinder unbinder;
    List<ViewGroupBean> handler;//处理人列表
    List<ViewGroupBean> status;//状态列表
    List<ViewGroupBean> businessTyps;//业务类型列表

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_drawerlayout_select;
    }

    public static FilterFragment newInstance() {
        FilterFragment filterFragment = new FilterFragment();
        return filterFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        initData();
        initView();
        LogUtil.i("************ onDestroyView() ************");
        return rootView;
    }

    private void initData() {
        handler = new ArrayList<>();
        status = new ArrayList<>();
        businessTyps = new ArrayList<>();
    }

    private void initView() {
        ViewGroupBean myself = new ViewGroupBean();
        myself.setSelected(true);
        myself.setLlWrap(llMyself);
        myself.setTvSelected(tvMyself);
        myself.setIvSelected(ivMyself);
        myself.setResId(R.id.ll_myself);
        handler.add(myself);
        ViewGroupBean everyOne = new ViewGroupBean();
        everyOne.setSelected(false);
        everyOne.setLlWrap(llEveryOne);
        everyOne.setTvSelected(tvEveryOne);
        everyOne.setIvSelected(ivEveryOne);
        everyOne.setResId(R.id.ll_everyOne);
        handler.add(everyOne);
        ViewSelecter.setSelectedView(handler, ViewSelecter.SELECTSINGLE, getActivity());

        ViewGroupBean unhandle = new ViewGroupBean();
        unhandle.setSelected(true);
        unhandle.setLlWrap(llUnhandle);
        unhandle.setTvSelected(tvUnhandle);
        unhandle.setIvSelected(ivUnhandle);
        unhandle.setResId(R.id.ll_unhandle);
        status.add(unhandle);
        ViewGroupBean entered = new ViewGroupBean();
        entered.setSelected(false);
        entered.setLlWrap(llEntered);
        entered.setTvSelected(tvEntered);
        entered.setIvSelected(ivEntered);
        entered.setResId(R.id.ll_entered);
        status.add(entered);
        ViewGroupBean all = new ViewGroupBean();
        all.setSelected(false);
        all.setLlWrap(llAll);
        all.setTvSelected(tvAll);
        all.setIvSelected(ivAll);
        all.setResId(R.id.ll_all);
        status.add(all);
        ViewSelecter.setSelectedView(status, ViewSelecter.SELECTSINGLE, getActivity());

        ViewGroupBean purchaseIn = new ViewGroupBean();
        purchaseIn.setSelected(true);
        purchaseIn.setLlWrap(llPurchaseIn);
        purchaseIn.setTvSelected(tvPurchaseIn);
        purchaseIn.setIvSelected(ivPurchaseIn);
        purchaseIn.setResId(R.id.ll_purchaseIn);
        businessTyps.add(purchaseIn);
        ViewGroupBean finishedIn = new ViewGroupBean();
        finishedIn.setSelected(false);
        finishedIn.setLlWrap(llFinishedIn);
        finishedIn.setTvSelected(tvFinishedIn);
        finishedIn.setIvSelected(ivFinishedIn);
        finishedIn.setResId(R.id.ll_finishedIn);
        businessTyps.add(finishedIn);
        ViewSelecter.setSelectedView(businessTyps, ViewSelecter.SELECTALL, getActivity());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.i("************ onDestroyView() ************");
        unbinder.unbind();
    }

    @OnClick({R.id.tv_startTime, R.id.tv_endTime, R.id.rl_depart, R.id.rl_maker, R.id.tv_reset, R.id.tv_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_startTime:
                CommonUtil.showDateSelectDialog(getActivity(), tvStartTime);
                break;
            case R.id.tv_endTime:
                CommonUtil.showDateSelectDialog(getActivity(), tvEndTime);
                break;
            case R.id.rl_depart:
                reference("部门", true);
                break;
            case R.id.rl_maker:
                reference("制单人", false);
                break;
            case R.id.tv_reset:
                break;
            case R.id.tv_ok:
                break;
        }
    }

    private void reference(String title, boolean hasAll) {
        FragmentManager fragmentManager = getFragmentManager();
        ReferenceFragment referenceFragment = ReferenceFragment.newInstance(this);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);//标题
        bundle.putString("url", "");//获取数据的路径
        bundle.putBoolean("hasAll", hasAll);//控制是否可以显示全部选择的条目
        referenceFragment.setArguments(bundle);
        transaction.addToBackStack(null);
        transaction.add(R.id.fl_drawerContent, referenceFragment).commitAllowingStateLoss();
    }


    @Override
    public void selectItem(boolean selectAll, ReferenceBean referenceBean) {
        if (selectAll) {
            tvDepartName.setText("全部");
        } else {
            tvDepartName.setText(referenceBean.getCommonInfoBean().getArcName());
        }
    }
}
