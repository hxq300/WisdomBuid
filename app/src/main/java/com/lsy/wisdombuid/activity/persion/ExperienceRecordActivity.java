package com.lsy.wisdombuid.activity.persion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.BehaviorRecord;
import com.lsy.wisdombuid.bean.ProjectData;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.ToastUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lsy on 2020/4/29
 * todo : 体验记录
 */
public class ExperienceRecordActivity extends MyBaseActivity {


    @BindView(R.id.id_tiyan_recycler)
    RecyclerView idTiyanRecycler;

    private List<ProjectData> projectDataList = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_record);
        ButterKnife.bind(this);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

        initView();

        getData();

    }

    private void initView() {

        idTiyanRecycler.setItemViewCacheSize(100);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ExperienceRecordActivity.this);
        idTiyanRecycler.setLayoutManager(linearLayoutManager);
        idTiyanRecycler.setNestedScrollingEnabled(false);

    }

    //获取项目体验记录
    private void getData() {

        projectDataList = new ArrayList<>();

        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

//        {"pageNo":1,"pageSize":10,"section_id"}
        //section_id
        listcanshu.put("id", 1);//
//        listcanshu.put("pageSize", 30);//
//        listcanshu.put("section_id", OKHttpClass.getToken(ExperienceRecordActivity.this));//

        //培训课程分类列表
        okHttpClass.setPostCanShu(this, RequestURL.findTrainContentAll, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("peixun", "trainMenu=培训课程=" + dataString);
                Gson gson = new Gson();

                JSONArray jsonArray = null;

                try {

                    jsonArray = new JSONArray(dataString);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        ProjectData projectData = gson.fromJson(jsonArray.get(i).toString(), ProjectData.class);

                        projectDataList.add(projectData);

                    }

                    initRecycle();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                return dataString;
            }
        });


        idTiyanRecycler.setAdapter(new CommonAdapter<ProjectData>(this, R.layout.item_tiyan_view, projectDataList) {
            @Override
            protected void convert(ViewHolder holder, ProjectData projectData, int position) {

                holder.setText(R.id.tiyan_name, "" + projectData.getTrain_name());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent experience = new Intent();
                        experience.putExtra("projectData", projectData.toString());
                        experience.setClass(ExperienceRecordActivity.this, ExperienceActivity.class);
                        startActivity(experience);

                    }
                });

            }

        });

    }

    private void initRecycle() {
        idTiyanRecycler.setAdapter(new CommonAdapter<ProjectData>(this, R.layout.item_tiyan_view, projectDataList) {
            @Override
            protected void convert(ViewHolder holder, ProjectData projectData, int position) {

                holder.setText(R.id.tiyan_name, "" + projectData.getTrain_name());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent experience = new Intent();
                        experience.putExtra("projectData", projectData.toString());
                        experience.setClass(ExperienceRecordActivity.this, ExperienceActivity.class);
                        startActivity(experience);

                    }
                });

            }

        });
    }


    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("体验记录");
        }
    }
}
