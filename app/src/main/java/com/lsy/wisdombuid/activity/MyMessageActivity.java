package com.lsy.wisdombuid.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.adapter.MessageAdapter;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.MessageData;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.SharedUtils;
import com.lsy.wisdombuid.util.StatusBarUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsy on 2020/3/17
 * todo : 我的消息
 */
public class MyMessageActivity extends MyBaseActivity {

    //消息列表
    private RecyclerView idListRecycle;
    private MessageAdapter listAdapter;

    private int pageNum = 1;//后台从1开始
    private SharedUtils sharedUtils;

    private int userId = 0;

    private List<MessageData> dataList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

        sharedUtils = new SharedUtils(this, SharedUtils.WISDOM);

        userId = sharedUtils.getIntData(SharedUtils.USER_ID);

        initView();

        getMyMessage(pageNum);

    }


    private void initView() {

        //===
        idListRecycle = (RecyclerView) findViewById(R.id.recycler_message);
        idListRecycle.setItemViewCacheSize(100);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyMessageActivity.this);
        idListRecycle.setLayoutManager(linearLayoutManager);
        idListRecycle.setNestedScrollingEnabled(false);

    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {
        } else {
            titleBar.setTitle("我的消息");
        }
    }


    //获取系统通知消息
    private void getMyMessage(final int pageNum) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        //{"pageNo":1,"pageSize":10,"staff_id":3}
        listcanshu.put("pageNo", pageNum);//页数
        listcanshu.put("pageSize", 10);//每页返回的条数
        listcanshu.put("staff_id", userId);//用户ID

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(this, RequestURL.myMessage, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("userInformation", "myMessage=" + dataString);
                Gson gson = new Gson();

                if (pageNum == 1) {
                    dataList.clear();
                }

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

                    JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("items").toString());

                    for (int i = 0; i < jsonArray.length(); i++) {
                        MessageData data = gson.fromJson(jsonArray.get(i).toString(), MessageData.class);
                        dataList.add(data);
                    }

                    listAdapter = new MessageAdapter(MyMessageActivity.this, dataList);
                    idListRecycle.setAdapter(listAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });
    }


}
