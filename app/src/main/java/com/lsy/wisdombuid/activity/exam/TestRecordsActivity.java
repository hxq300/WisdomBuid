package com.lsy.wisdombuid.activity.exam;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.ITunesActivity;
import com.lsy.wisdombuid.adapter.AccountAdapter;
import com.lsy.wisdombuid.adapter.TestRecordsAdapter;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.SubjectData;
import com.lsy.wisdombuid.bean.TestHistory;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsy on 2020/3/25
 * todo : 考试记录
 */
public class TestRecordsActivity extends MyBaseActivity {

    //消息列表
    private RecyclerView idListRecycle;
    private TestRecordsAdapter listAdapter;

    private List<TestHistory> testHistories;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_records);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

        initView();

        //初始化数据
        initData();
    }

    private void initView() {

        //===
        idListRecycle = (RecyclerView) findViewById(R.id.recycler_records);
        idListRecycle.setItemViewCacheSize(100);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TestRecordsActivity.this);
        idListRecycle.setLayoutManager(linearLayoutManager);
        idListRecycle.setNestedScrollingEnabled(false);
    }


    private void initData() {

        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        listcanshu.put("staff_id", OKHttpClass.getUserId(TestRecordsActivity.this));

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(TestRecordsActivity.this, RequestURL.findExamRecordByStaffId, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("exam", "findExamRecordByStaffId==" + dataString);
                Gson gson = new Gson();


                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

//                    JSONObject dataObject = new JSONObject(jsonObject.get("data").toString());

                    JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));

                    testHistories = new ArrayList<>();
                    //历史记录
                    for (int i = 0; i < jsonArray.length(); i++) {
                        TestHistory testHistory = gson.fromJson(jsonArray.get(i).toString(), TestHistory.class);
                        testHistories.add(testHistory);
                    }

                    listAdapter = new TestRecordsAdapter(TestRecordsActivity.this, testHistories);
                    idListRecycle.setAdapter(listAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });


    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("" + getString(R.string.TestRecords));
        }
    }
}
