package com.lsy.wisdombuid.activity.exam;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.adapter.CoursesAdapter;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.CoursesData;
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
 * Created by lsy on 2020/4/1
 * todo : 培训课堂
 */
public class TrainingCoursesActivity extends MyBaseActivity {


    private List<CoursesData> dataList = new ArrayList<>();

    //消息列表
    private RecyclerView idListRecycle;
    private CoursesAdapter listAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_courses);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

        initView();

        getFindAnintegral();

    }

    private void initView() {

        idListRecycle = findViewById(R.id.recycler_training_courses);
        idListRecycle.setItemViewCacheSize(100);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TrainingCoursesActivity.this);
        idListRecycle.setLayoutManager(linearLayoutManager);
        idListRecycle.setNestedScrollingEnabled(false);

    }

    @Override
    protected void initTitle() {
        if (null != titleBar)
            titleBar.setTitle("" + getString(R.string.the_training_class));
    }


    //查询积分明细
    private void getFindAnintegral() {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        //section_id
        listcanshu.put("section_id", OKHttpClass.getToken(TrainingCoursesActivity.this));//

        //培训课程分类列表
        okHttpClass.setPostCanShu(this, RequestURL.findTrainAll, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("teach", "trainMenu=培训课程=" + dataString);
                Gson gson = new Gson();

                JSONObject jsonObject = null;
                JSONArray jsonArray = null;
                try {
                    jsonObject = new JSONObject(dataString);

                    jsonArray = new JSONArray(jsonObject.getString("data"));

                    for (int i = 0; i < jsonArray.length(); i++) {
                        CoursesData data = gson.fromJson(jsonArray.get(i).toString(), CoursesData.class);
                        dataList.add(data);
                    }

                    listAdapter = new CoursesAdapter(TrainingCoursesActivity.this, dataList);
                    idListRecycle.setAdapter(listAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });
    }


}
