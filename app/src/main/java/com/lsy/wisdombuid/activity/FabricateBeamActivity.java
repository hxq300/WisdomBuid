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
import com.lsy.wisdombuid.bean.FabricateEntity;
import com.lsy.wisdombuid.bean.GirderEntity;
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
 * describe :  TODO 制梁区
 */
public class FabricateBeamActivity extends MyBaseActivity implements View.OnClickListener {

    private static final String TAG = "FabricateBeamActivity";

    private int mId;
    private TextView tv_girder_name;
    private TextView tv_manufacture_pedestal;
    private TextView tv_state;
    private LinearLayout ll_top;
    private LinearLayout ll_bottom;
    private RecyclerView mRvTop;
    private RecyclerView mRvBottom;
    private Button btn;

    private List<String> mList;
    private List<String> mList2;
    private SaveBeamAdapter mTopAdapter;
    private SaveBeamAdapter mBottomAdapter;


    private String girder_name_one;
    private String state;
    private int stateId; //状态Id
    @Override
    protected void initTitle() {
        if (titleBar == null) {
        } else {
            titleBar.setTitle("制梁区");
            titleBar.hideLeftIv();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabricate_beam);

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
        ll_bottom = findViewById(R.id.ll_bottom);
        btn = findViewById(R.id.btn);
        mRvTop = findViewById(R.id.rv_top);
        mRvBottom = findViewById(R.id.rv_bottom);
        mRvTop.setLayoutManager(new GridLayoutManager(this, 4));
        mRvBottom.setLayoutManager(new GridLayoutManager(this, 4));

        mList = new ArrayList<>();
        mList.add("绑扎中");
        mList.add("养护中");

        mList2 = new ArrayList<>();

        mTopAdapter = new SaveBeamAdapter(mList);
        mBottomAdapter = new SaveBeamAdapter(mList2);

        mRvTop.setAdapter(mTopAdapter);
        mRvBottom.setAdapter(mBottomAdapter);

        mTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                state = mList.get(position);
              //  Log.d(TAG, "onItemClick: ========="+state);
            }
        });

        mBottomAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                girder_name_one = mList2.get(position);
              //  Log.d(TAG, "onItemClick: ========="+girder_name_one);
            }
        });


        ll_top.setOnClickListener(this);
        ll_bottom.setOnClickListener(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!"".equals(girder_name_one) && !"".equals(state))
                    updateManufactureGirder();
            }
        });

        Intent intent = getIntent();
        mId = intent.getIntExtra("id", 0);
        initTitle();
        initData();

    }

    private void initData() {
        findManufactureGirderById();
    }

    private void findManufactureGirderById() {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
        listcanshu.put("id", mId);
        okHttpClass.setPostCanShu(this, RequestURL.FindManufactureGirderById, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                  Log.d(TAG, "requestData: ============="+dataString);
                if (!"".equals(dataString)) {
                    Gson gson = new Gson();
                    FabricateEntity data = gson.fromJson(dataString, FabricateEntity.class);
                    if (null != data) {
                        tv_girder_name.setText(data.getGirder_name() + "");

                        tv_manufacture_pedestal.setText(data.getManufacture_pedestal() + "");
                        //  tv_state.setText(data.getState() + "");
                        //0:空闲,1:绑扎中,2:养护中  制梁区
                        String state = data.getState();
                        if (state.equals("0")){
                            tv_state.setText("空闲");
                        }
                        else if (state.equals("1")){
                            tv_state.setText("绑扎中");
                        }
                        else if(state.equals("2")){
                            tv_state.setText("养护中");
                        }
                    }
                }

                return dataString;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_top:
                if (mRvTop.getVisibility() == View.VISIBLE) {
                    mRvTop.setVisibility(View.GONE);
                } else {
                    mRvTop.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.ll_bottom:
                if (mRvBottom.getVisibility() == View.VISIBLE) {
                    mRvBottom.setVisibility(View.GONE);
                } else {
                    mRvBottom.setVisibility(View.VISIBLE);
                }
                if (mList2.size() == 0) {
                    findGirderByUptime();
                }

        }
    }

    private void updateManufactureGirder() {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
        listcanshu.put("id", mId);

        //传参:id(二维码返回id),girder_name(梁编号),state(状态)
        ////0:空闲,1:绑扎中,2:养护中  制梁区
        if (state.equals("绑扎中")) {
            listcanshu.put("state", 1);
        } else if(state.equals("养护中")){
            listcanshu.put("state", 2);
        }

        listcanshu.put("girder_name", girder_name_one);

        okHttpClass.setPostCanShu(this, RequestURL.UpdateManufactureGirder, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                Gson gson = new Gson();
                UpdateManufactureGirderEntity updateManufactureGirderEntity = gson.fromJson(dataString, UpdateManufactureGirderEntity.class);
                ToastUtils.showBottomToast(FabricateBeamActivity.this, updateManufactureGirderEntity.getMessage());
                return dataString;
            }
        });
    }

    private void findGirderByUptime() {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
        listcanshu.put("section_id", OKHttpClass.getToken(this));

        //  Log.d(TAG, "findGirderByUptime: =================" + OKHttpClass.getToken(this));
        okHttpClass.setPostCanShu(this, RequestURL.FindGirderByUptime, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                if (!"".equals(dataString)) {
                    Gson gson = new Gson();
                    List<GirderEntity> data = gson.fromJson(dataString, new TypeToken<List<GirderEntity>>() {
                    }.getType());
                    for (GirderEntity datum : data) {
                        mList2.add(datum.getGirder_name());
                    }
                    mBottomAdapter.notifyDataSetChanged();

                }

                return dataString;
            }
        });
    }

}
