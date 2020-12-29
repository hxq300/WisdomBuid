package com.lsy.wisdombuid.activity.persion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.BaseActivity;
import com.lsy.wisdombuid.bean.BehaviorRecord;
import com.lsy.wisdombuid.bean.JobLogData;
import com.lsy.wisdombuid.bean.PerImformationData;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.StatusBarUtil;
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
 * todo : 个人信息
 */
public class PersonalDetailsActivity extends BaseActivity {

    @BindView(R.id.persional_image)
    ImageView persionalImage;
    @BindView(R.id.persional_type)
    TextView persionalType;
    @BindView(R.id.persional_phone)
    TextView persionalPhone;
    @BindView(R.id.persional_jifen)
    TextView persionalJifen;
    @BindView(R.id.persional_xiuangmu)
    TextView persionalXiuangmu;
    @BindView(R.id.persional_is_peixun)
    TextView persionalIsPeixun;
    @BindView(R.id.persional_is_jiaodi)
    TextView persionalIsJiaodi;
    @BindView(R.id.persional_is_qianding)
    TextView persionalIsQianding;
    @BindView(R.id.persional_is_baogao)
    TextView persionalIsBaogao;
    @BindView(R.id.persional_is_yingjichuzhi)
    TextView persionalIsYingjichuzhi;
    @BindView(R.id.persional_name)
    TextView persionalName;


    @BindView(R.id.id_xingwei_recycler)
    RecyclerView idXingweiRecycler;
    @BindView(R.id.id_gongzuo_recycler)
    RecyclerView idGongzuoRecycler;


    private PerImformationData imformationData;
    private int staff_id = 0;

    private List<BehaviorRecord> behaviorRecords;
    private List<JobLogData> logDataList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persinal_detail);
        ButterKnife.bind(this);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }

        Intent intent = getIntent();
        staff_id = intent.getIntExtra("staff_id", 0);

        getFindAnintegral();

        initView();

    }

    private void initView() {

        idXingweiRecycler.setItemViewCacheSize(100);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PersonalDetailsActivity.this);
        idXingweiRecycler.setLayoutManager(linearLayoutManager);
        idXingweiRecycler.setNestedScrollingEnabled(false);

        idGongzuoRecycler.setItemViewCacheSize(100);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(PersonalDetailsActivity.this);
        idGongzuoRecycler.setLayoutManager(linearLayoutManager1);
        idGongzuoRecycler.setNestedScrollingEnabled(false);

    }


    //个人所有信息查询
    private void getFindAnintegral() {

        behaviorRecords = new ArrayList<>();
        logDataList = new ArrayList<>();

        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        //section_id
        listcanshu.put("staff_id", staff_id);//

        //培训课程分类列表
        okHttpClass.setPostCanShu(this, RequestURL.findALL, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("peixun", "trainMenu=人员信息=" + dataString);
                Gson gson = new Gson();

                JSONObject jsonObject = null;

                JSONArray jsonArray2 = null;
                JSONArray jsonArray3 = null;

                try {
                    jsonObject = new JSONObject(dataString);

                    String data = jsonObject.getString("data");
                    String data2 = jsonObject.getString("data2");
                    String data3 = jsonObject.getString("data3");

                    jsonArray2 = new JSONArray(data2);
                    jsonArray3 = new JSONArray(data3);

                    for (int i = 0; i < jsonArray2.length(); i++) {
                        BehaviorRecord record = gson.fromJson(jsonArray2.get(i).toString(), BehaviorRecord.class);
                        behaviorRecords.add(record);
                        initXingwei();
                    }

                    for (int i = 0; i < jsonArray2.length(); i++) {
                        JobLogData logData = gson.fromJson(jsonArray3.get(i).toString(), JobLogData.class);
                        logDataList.add(logData);
                        initJobLog();
                    }


                    imformationData = gson.fromJson(data, PerImformationData.class);

                    if (imformationData.getStaff_img() != null) {
                        Glide.with(PersonalDetailsActivity.this).load(RequestURL.RequestImg + imformationData.getStaff_img())
                                .error(R.mipmap.mine_head)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(persionalImage);
                    }

                    if (imformationData.getStaff_name() != null) {
                        persionalName.setText("" + imformationData.getStaff_name());
                    }

                    if (imformationData.getType() != null) {
                        persionalType.setText("" + imformationData.getType());
                    }

                    if (imformationData.getDepartment_name() != null) {
                        persionalPhone.setText("" + imformationData.getDepartment_name());
                    }

                    persionalJifen.setText("" + imformationData.getEnd_integral());


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });

        idXingweiRecycler.setAdapter(new CommonAdapter<BehaviorRecord>(this, R.layout.item_xingwei_view, behaviorRecords) {
            @Override
            protected void convert(ViewHolder holder, BehaviorRecord behaviorRecord, int position) {
                holder.setText(R.id.xingwei_remark, "" + behaviorRecord.getRemark());
                holder.setText(R.id.xingwei_time, "时间：" + behaviorRecord.getUptime());
            }

        });

        idGongzuoRecycler.setAdapter(new CommonAdapter<JobLogData>(this, R.layout.item_xingwei_view, logDataList) {
            @Override
            protected void convert(ViewHolder holder, JobLogData logData, int position) {
                holder.setText(R.id.xingwei_remark, "" + logData.getExam_name());
                holder.setText(R.id.xingwei_time, "时间：" + logData.getExam_time());
            }

        });

    }

    private void initJobLog() {
        idGongzuoRecycler.setAdapter(new CommonAdapter<JobLogData>(this, R.layout.item_xingwei_view, logDataList) {
            @Override
            protected void convert(ViewHolder holder, JobLogData logData, int position) {
                holder.setText(R.id.xingwei_remark, "" + logData.getExam_name());
                holder.setText(R.id.xingwei_time, "时间：" + logData.getExam_time());
            }

        });
    }

    private void initXingwei() {
        idXingweiRecycler.setAdapter(new CommonAdapter<BehaviorRecord>(this, R.layout.item_xingwei_view, behaviorRecords) {
            @Override
            protected void convert(ViewHolder holder, BehaviorRecord behaviorRecord, int position) {
                holder.setText(R.id.xingwei_remark, "" + behaviorRecord.getRemark());
                holder.setText(R.id.xingwei_time, "时间：" + behaviorRecord.getUptime());
            }

        });
    }

    public void back(View view) {
        finish();
    }
}
