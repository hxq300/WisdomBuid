package com.lsy.wisdombuid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.adapter.SaveBeamAdapter;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.GirderEntity;
import com.lsy.wisdombuid.bean.RebarEntity;
import com.lsy.wisdombuid.bean.UpdateManufactureGirderEntity;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hxq on 2021/4/27
 * describe :  TODO 钢筋困扎区
 */
public class RebarActivity extends MyBaseActivity implements View.OnClickListener {

    private static final String TAG = "RebarActivity";

    private int mId;
    private TextView tv_girder_name;
    private TextView tv_manufacture_pedestal;
    private TextView tv_state;
    private LinearLayout ll_top;
    private RecyclerView mRvTop;
    private Button btn;

    private List<String> mList;
    private SaveBeamAdapter mTopAdapter;


    private String girder_name;

    @Override
    protected void initTitle() {
        if (titleBar == null) {
        } else {
            titleBar.setTitle("钢筋困扎区");
            titleBar.hideLeftIv();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rebar);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }

        titleBar = findViewById(R.id.title_bar);
        tv_girder_name = findViewById(R.id.tv_girder_name);
        tv_manufacture_pedestal = findViewById(R.id.tv_manufacture_pedestal);
        tv_state = findViewById(R.id.tv_state);
        ll_top = findViewById(R.id.ll_top);
        btn = findViewById(R.id.btn);
        mRvTop = findViewById(R.id.rv_top);
        mRvTop.setLayoutManager(new GridLayoutManager(this,4));

        mList = new ArrayList<>();
        mTopAdapter = new SaveBeamAdapter(mList);
        mRvTop.setAdapter(mTopAdapter);

        mTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                girder_name = mList.get(position);
            }
        });



        ll_top.setOnClickListener(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!"".equals(girder_name))
                    updateState();
            }
        });

        Intent intent = getIntent();
        mId = intent.getIntExtra("id", 0);
        initTitle();
        initData();
    }

    private void initData() {
        FindRebarById();
    }

    private void FindRebarById() {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
        listcanshu.put("id", mId);

        Log.d(TAG, "FindRebarById: +++++======="+mId);

        okHttpClass.setPostCanShu(this, RequestURL.FindRebarById, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                Log.d(TAG, "FindRebarById: +++++======="+dataString);

                if (!"".equals(dataString)){
                    Gson gson = new Gson();
                    RebarEntity data = gson.fromJson(dataString, RebarEntity.class);
                    if (null != data){
                        tv_girder_name.setText(data.getRebar_name()+"");
                        tv_manufacture_pedestal.setText(data.getGirder_name()+"");
                       // tv_state.setText(data.getState()+"");

                        //0:空闲,1:钢筋绑扎中   钢筋捆扎区
                        String state = data.getState();
                        if (state.equals("0")){
                            tv_state.setText("空闲");
                        }
                        else if (state.equals("1")){
                            tv_state.setText("钢筋绑扎中");
                        }

                    }
                }

                return dataString;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_top:
                if (mRvTop.getVisibility() == View.VISIBLE){
                    mRvTop.setVisibility(View.GONE);
                }else {
                    mRvTop.setVisibility(View.VISIBLE);
                }
                if (mList.size() == 0){
                    findGirderByUptime();
                }
                break;

        }
    }

    private void updateState() {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
        listcanshu.put("id", mId);
        listcanshu.put("girder_name", girder_name);
        okHttpClass.setPostCanShu(this, RequestURL.UpdateState, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                Gson gson = new Gson();
                UpdateManufactureGirderEntity updateManufactureGirderEntity = gson.fromJson(dataString, UpdateManufactureGirderEntity.class);
                ToastUtils.showBottomToast(RebarActivity.this,updateManufactureGirderEntity.getMessage());
                return dataString;
            }
        });
    }

    private void findGirderByUptime() {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
        listcanshu.put("section_id", OKHttpClass.getToken(this));
        okHttpClass.setPostCanShu(this, RequestURL.FindGirderByUptime, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                if (!"".equals(dataString)){
                    Gson gson = new Gson();
                    List<GirderEntity> data = gson.fromJson(dataString, new TypeToken<List<GirderEntity>>(){}.getType());
                    for (GirderEntity datum : data) {
                        mList.add(datum.getGirder_name());
                    }
                    mTopAdapter.notifyDataSetChanged();

                }

                return dataString;
            }
        });
    }
}
