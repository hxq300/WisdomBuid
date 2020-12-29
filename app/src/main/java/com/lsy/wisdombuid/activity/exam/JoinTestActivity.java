package com.lsy.wisdombuid.activity.exam;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.adapter.JoinTestAdapter;
import com.lsy.wisdombuid.adapter.TestRecordsAdapter;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.ExamData;
import com.lsy.wisdombuid.bean.QuestiondData;
import com.lsy.wisdombuid.bean.TestHistory;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.StatusBarUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsy on 2020/3/25
 * todo : 参加考试
 */
public class JoinTestActivity extends MyBaseActivity {

    //消息列表
    private RecyclerView idListRecycle;
    private JoinTestAdapter listAdapter;

    private int pageNo = 1;

    private List<ExamData> pageLists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_test);

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
        idListRecycle = (RecyclerView) findViewById(R.id.recycler_join_test);
        idListRecycle.setItemViewCacheSize(100);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(JoinTestActivity.this);
        idListRecycle.setLayoutManager(linearLayoutManager);
        idListRecycle.setNestedScrollingEnabled(false);
    }


    private void initData() {

        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

//        {"pageNo":1,"pageSize":10,"section_id":2}
        listcanshu.put("pageNo", pageNo);
        listcanshu.put("pageSize", 20);
        listcanshu.put("section_id", OKHttpClass.getToken(JoinTestActivity.this));

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(JoinTestActivity.this, RequestURL.findExamination, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("exam", "findExamRecordByStaffId==" + dataString);
                Gson gson = new Gson();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

                    pageLists = new ArrayList<>();

                    String data = jsonObject.getString("items");

                    JSONArray jsonArray = new JSONArray(data);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        ExamData questionsData = gson.fromJson(jsonArray.get(i).toString(), ExamData.class);

                        pageLists.add(questionsData);
                    }

                    listAdapter = new JoinTestAdapter(JoinTestActivity.this, pageLists);
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
            titleBar.setTitle("" + getString(R.string.join_test));
        }
    }
}
