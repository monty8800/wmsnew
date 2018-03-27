package com.rlzz.receivemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rlzz.receivemanagement.adapter.ReceiveAdapter;
import com.rlzz.receivemanagement.common.base.BaseActivity;
import com.rlzz.receivemanagement.entity.ReceiveBean;
import com.rlzz.receivemanagement.entity.ReceiveSection;
import com.rlzz.receivemanagement.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    /*@BindView(R.id.frameLayout)
    FrameLayout frameLayout*/;
    @BindView(R.id.rv_receive)
    RecyclerView rvReceive;

    List<ReceiveSection> datas;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        datas = new ArrayList<>();
        String json = "{\"body\":[{\n" +
                "\t\"header\":\"任务\",\n" +
                "\t\"content\":[{\n" +
                "\t\t\t\"position\":\"1\",\n" +
                "\t\t\t\"tips\":\"25\",\n" +
                "\t\t\t\"title\":\"收货1\",\n" +
                "\t\t\t\"flag\":\"ready_enter\",\n" +
                "\t\t\t\"activity\":\"\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"position\":\"\",\n" +
                "\t\t\t\"tips\":\"\",\n" +
                "\t\t\t\"title\":\"\",\n" +
                "\t\t\t\"flag\":\"ready_enter\",\n" +
                "\t\t\t\"activity\":\"\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"position\":\"1\",\n" +
                "\t\t\t\"tips\":\"25\",\n" +
                "\t\t\t\"title\":\"收货2\",\n" +
                "\t\t\t\"flag\":\"ready_enter\",\n" +
                "\t\t\t\"activity\":\"\"\n" +
                "\t\t}\n" +
                "\t]},{\"header\":\"制单\",\n" +
                "\t    \"content\":[{\n" +
                "\t\t\t\"position\":\"1\",\n" +
                "\t\t\t\"tips\":\"25\",\n" +
                "\t\t\t\"title\":\"收货3\",\n" +
                "\t\t\t\"flag\":\"ready_enter\",\n" +
                "\t\t\t\"activity\":\"\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"position\":\"1\",\n" +
                "\t\t\t\"tips\":\"25\",\n" +
                "\t\t\t\"title\":\"收货4\",\n" +
                "\t\t\t\"flag\":\"ready_enter\",\n" +
                "\t\t\t\"activity\":\"\"\n" +
                "\t\t}\n" +
                "\t]},{\"header\":\"ERP单据审核\",\n" +
                "\t    \"content\":[{\n" +
                "\t\t\t\"position\":\"1\",\n" +
                "\t\t\t\"tips\":\"25\",\n" +
                "\t\t\t\"title\":\"收货5\",\n" +
                "\t\t\t\"flag\":\"ready_enter\",\n" +
                "\t\t\t\"activity\":\"\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"position\":\"1\",\n" +
                "\t\t\t\"tips\":\"25\",\n" +
                "\t\t\t\"title\":\"收货6\",\n" +
                "\t\t\t\"flag\":\"ready_enter\",\n" +
                "\t\t\t\"activity\":\"\"\n" +
                "\t\t}\n" +
                "\t]}]\n" +
                "}\n";
        ReceiveBean receiveBean = (ReceiveBean) JsonUtil.stringToObject(json, ReceiveBean.class);
        List<ReceiveBean.BodyBean> bodyBeans = receiveBean.getBody();

        for (int j = 0; j < bodyBeans.size(); j++) {
            ReceiveSection header = new ReceiveSection(true, bodyBeans.get(j).getHeader());
            datas.add(header);
            for (int k = 0; k < bodyBeans.get(j).getContent().size(); k++) {
                ReceiveSection item = new ReceiveSection(bodyBeans.get(j).getContent().get(k));
                datas.add(item);
            }
        }
    }

    private void initView() {
        tvTitle.setText(getResources().getText(R.string.receive_title));
        //以下操作均是在获取网络数据之后进行的
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rvReceive.setLayoutManager(gridLayoutManager);
        ReceiveAdapter receiveAdapter = new ReceiveAdapter(R.layout.item_body, R.layout.item_header, datas);
        receiveAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //判断是否是头部
                if (!datas.get(position).isHeader && !datas.get(position).t.getTitle().equals("")){
                    Toast.makeText(MainActivity.this, datas.get(position).t.getTitle(), Toast.LENGTH_SHORT).show();
                    Intent readyReceiveIntent=new Intent();
                    readyReceiveIntent.setAction("ready_enter");
                    startActivity(readyReceiveIntent);
                }
            }
        });
        rvReceive.setAdapter(receiveAdapter);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
    }
}
