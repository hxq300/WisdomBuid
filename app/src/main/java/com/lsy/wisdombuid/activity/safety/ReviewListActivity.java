package com.lsy.wisdombuid.activity.safety;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.adapter.InspectionRecordAdapter;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.IRecordData;
import com.lsy.wisdombuid.bean.RectifyEntity;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsy on 2020/4/12
 * todo : 整改复查列表
 */
public class ReviewListActivity extends MyBaseActivity {

    //消息列表
    private RecyclerView idListRecycle;
    private InspectionRecordAdapter listAdapter;

    private LinearLayout noData;

    private int station_id = 0;//
    private int intId = 1;//1\查询记录  2、无效数据a
    private int pageNo = 1;

    private List<RectifyEntity.ItemsBean> dataList = new ArrayList<>();

    private SmartRefreshLayout smartRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_record);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);

        Intent intent = getIntent();

        station_id = intent.getIntExtra("stationnId", 0);

        initTitle();


        L.log("record", "intId===" + intId);

        //初始化view
        initView();

        getRecord();

    }

    //检查记录
    private void getRecord() {

        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

//        {"pageNo":1,"pageSize":10,"section_id":2,"station_id":1}
        listcanshu.put("pageNo", pageNo);
        listcanshu.put("pageSize", 15);
        listcanshu.put("section_id", OKHttpClass.getToken(ReviewListActivity.this));//标段ID登录返回
        listcanshu.put("station_id", station_id);//站点ID(下拉框里面选的)

        L.log("record", "station_id===" + OKHttpClass.getToken(ReviewListActivity.this));

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(ReviewListActivity.this, RequestURL.findDataZgUrl, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadmore();
                Gson gson = new Gson();
                RectifyEntity rectifyEntity = gson.fromJson(dataString, RectifyEntity.class);
                dataList.addAll(rectifyEntity.getItems());

                    if (dataList != null && dataList.size() > 0) {
                        if (listAdapter == null){
                            listAdapter = new InspectionRecordAdapter(ReviewListActivity.this, dataList, 3);
                            idListRecycle.setAdapter(listAdapter);
                            noData.setVisibility(View.GONE);
                        }else {
                            listAdapter.notifyDataSetChanged();
                        }

                    } else {
                        noData.setVisibility(View.VISIBLE);
                    }

                return dataString;
            }
        });

    }

    private void initView() {
        noData = findViewById(R.id.line_no_data);

        //===
        idListRecycle = (RecyclerView) findViewById(R.id.recycler_inspection_record);
        idListRecycle.setItemViewCacheSize(100);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ReviewListActivity.this);
        idListRecycle.setLayoutManager(linearLayoutManager);
        idListRecycle.setNestedScrollingEnabled(false);


        // 下拉刷新
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(com.scwang.smartrefresh.layout.api.RefreshLayout refreshlayout) {
                dataList.clear();
                pageNo = 1;
                getRecord();
            }
        });


        // 上拉加载更多
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(com.scwang.smartrefresh.layout.api.RefreshLayout refreshlayout) {
                // 加载回调
                pageNo++;
                getRecord();
            }
        });
    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("" + getString(R.string.check_the_rectification));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        getRecord();
    }
}
