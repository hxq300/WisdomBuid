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
import com.lsy.wisdombuid.bean.SvaeEntity;
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
 * describe :  TODO 存梁区
 */
public class SaveBeamActivity extends MyBaseActivity implements View.OnClickListener {

    private static final String TAG = "SaveBeamActivity";

    private int mId;
    private TextView tv_girder_name, tv_girder_name_two;
    private TextView tv_manufacture_pedestal;
    private TextView tv_state;
    private LinearLayout ll_top;
    private LinearLayout ll_bottom;
    private RecyclerView mRvTop;
    private RecyclerView mRvBottom;
    private Button btn;

    private List<String> mList;
    private SaveBeamAdapter mTopAdapter;
    private SaveBeamAdapter mBottomAdapter;


    private String girder_name_one;
    private String girder_name_two;
    private int section_id;

    @Override
    protected void initTitle() {
        if (titleBar == null) {
        } else {
            titleBar.setTitle("存梁区");
            titleBar.hideLeftIv();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_bean);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }


        titleBar = findViewById(R.id.title_bar);
        tv_girder_name = findViewById(R.id.tv_girder_name);
        tv_girder_name_two = findViewById(R.id.tv_girder_name_two);
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

        mTopAdapter = new SaveBeamAdapter(mList);
        mBottomAdapter = new SaveBeamAdapter(mList);

        mRvTop.setAdapter(mTopAdapter);
        mRvBottom.setAdapter(mBottomAdapter);

        mTopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                girder_name_one = mList.get(position);
            }
        });

        mBottomAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                girder_name_two = mList.get(position);
            }
        });


        ll_top.setOnClickListener(this);
        ll_bottom.setOnClickListener(this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!"".equals(girder_name_one) && !"".equals(girder_name_two))
                    updateStorageGirder();
            }
        });

        Intent intent = getIntent();

        mId = intent.getIntExtra("id", 0);

        initTitle();
        initData();
    }

    private void initData() {
        FindStorageGirderById();
    }

    private void FindStorageGirderById() {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
        listcanshu.put("id", mId);
        okHttpClass.setPostCanShu(this, RequestURL.FindStorageGirderById, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                Log.d(TAG, "requestData: -------------" + dataString);
                if (!"".equals(dataString)) {
                    Gson gson = new Gson();
                    SvaeEntity data = gson.fromJson(dataString, SvaeEntity.class);
                    if (null != data) {
                        tv_girder_name.setText(data.getGirder_name_one() + "");
                        tv_girder_name_two.setText(data.getGirder_name_two() + "");
                        tv_manufacture_pedestal.setText(data.getStorage_pedestal() + "");

                        section_id = data.getSection_id();

                        //存梁区状态  0:空闲,1:单座占用,2:双座占用
                        String state = data.getState();
                        if (state.equals("0")){
                            tv_state.setText("空闲");
                        }
                        else if (state.equals("1")){
                            tv_state.setText("单座占用");
                        }
                        else if(state.equals("2")){
                            tv_state.setText("双座占用");
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
                if (mList.size() == 0) {
                    findGirderByUptime(1);
                }

                break;
            case R.id.ll_bottom:
                if (mRvBottom.getVisibility() == View.VISIBLE) {
                    mRvBottom.setVisibility(View.GONE);
                } else {
                    mRvBottom.setVisibility(View.VISIBLE);
                }
                if (mList.size() == 0) {
                    findGirderByUptime(1);
                }

        }
    }

    private void updateStorageGirder() {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
        listcanshu.put("id", mId);
        listcanshu.put("girder_name_one", girder_name_one);
        listcanshu.put("girder_name_two", girder_name_two);
        okHttpClass.setPostCanShu(this, RequestURL.UpdateStorageGirder, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                Gson gson = new Gson();
                UpdateManufactureGirderEntity updateManufactureGirderEntity = gson.fromJson(dataString, UpdateManufactureGirderEntity.class);
                ToastUtils.showBottomToast(SaveBeamActivity.this, updateManufactureGirderEntity.getMessage());
                return dataString;
            }
        });
    }

    private void findGirderByUptime(int type) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
        listcanshu.put("section_id", OKHttpClass.getToken(this));
        Log.d(TAG, "findGirderByUptime: =-------" + OKHttpClass.getToken(this));

        okHttpClass.setPostCanShu(this, RequestURL.FindGirderByUptime, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //  Log.d(TAG, "requestData: ---------------"+dataString);
                if (!"".equals(dataString)) {
                    Gson gson = new Gson();
                    List<GirderEntity> data = gson.fromJson(dataString, new TypeToken<List<GirderEntity>>() {
                    }.getType());
                    for (GirderEntity datum : data) {
                        mList.add(datum.getGirder_name());
                    }
                    mTopAdapter.notifyDataSetChanged();
                    mBottomAdapter.notifyDataSetChanged();

                }

                return dataString;
            }
        });
    }


}
