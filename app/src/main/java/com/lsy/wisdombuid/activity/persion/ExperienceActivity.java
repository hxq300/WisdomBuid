package com.lsy.wisdombuid.activity.persion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.BehaviorRecord;
import com.lsy.wisdombuid.bean.ProjectData;
import com.lsy.wisdombuid.bean.RenyuanData;
import com.lsy.wisdombuid.qr.QRActivity;
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
 * todo : 体验具体项目
 */
public class ExperienceActivity extends MyBaseActivity {


    @BindView(R.id.per_content)
    TextView perContent;
    @BindView(R.id.per_image)
    ImageView perImage;


    @BindView(R.id.id_renyuan_recycler)
    RecyclerView idRenyuanRecycler;

    private String pData = "";

    private ProjectData projectData;

    private List<RenyuanData> renyuanDataList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        ButterKnife.bind(this);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();


        Intent intent = getIntent();
        pData = intent.getStringExtra("projectData");

        initView();

        getData();

    }

    private void initView() {

        Gson gson = new Gson();

        if (pData != null) {
            projectData = gson.fromJson(pData, ProjectData.class);

            if (projectData.getTrain_name() != null) {
                titleBar.setTitle("" + projectData.getTrain_name());
            }

            if (projectData.getContent() != null) {
                perContent.setText("" + projectData.getContent());
            }

            if (projectData.getImg_url() != null) {
                Glide.with(ExperienceActivity.this).load(RequestURL.OssUrl + projectData.getImg_url()).into(perImage);
            }

        }

        idRenyuanRecycler.setItemViewCacheSize(100);
        idRenyuanRecycler.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        idRenyuanRecycler.setNestedScrollingEnabled(false);

    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("安全带体验");
        }
    }


    //查询积分明细
    private void getData() {

        renyuanDataList = new ArrayList<>();

        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        //"train_name":"安全带体验","section_id":2
        listcanshu.put("train_name", projectData.getTrain_name());//
        listcanshu.put("section_id", OKHttpClass.getToken(ExperienceActivity.this));//

        //培训课程分类列表
        okHttpClass.setPostCanShu(this, RequestURL.findTrainRecordByTrainName, listcanshu);
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

                        RenyuanData renyuanData = gson.fromJson(jsonArray.get(i).toString(), RenyuanData.class);

                        renyuanDataList.add(renyuanData);

                        initRecycle();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });


        idRenyuanRecycler.setAdapter(new CommonAdapter<RenyuanData>(this, R.layout.item_renyuan_view, renyuanDataList) {
            @Override
            protected void convert(ViewHolder holder, RenyuanData renyuanData, int position) {
                ImageView imageView = holder.itemView.findViewById(R.id.reyuan_image);
                holder.setText(R.id.renyuan_name, "" + renyuanData.getStaff_name());

                if (renyuanData.getStaff_img() != null) {
                    Glide.with(ExperienceActivity.this).load(RequestURL.RequestImg + renyuanData.getStaff_img())
                            .error(R.mipmap.people_renyuan)
//                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                            .into(imageView);
                }
            }

        });

    }

    private void initRecycle() {

        idRenyuanRecycler.setAdapter(new CommonAdapter<RenyuanData>(this, R.layout.item_renyuan_view, renyuanDataList) {
            @Override
            protected void convert(ViewHolder holder, RenyuanData renyuanData, int position) {
                ImageView imageView = holder.itemView.findViewById(R.id.reyuan_image);
                holder.setText(R.id.renyuan_name, "" + renyuanData.getStaff_name());

                if (renyuanData.getStaff_img() != null) {
                    Glide.with(ExperienceActivity.this).load(RequestURL.RequestImg + renyuanData.getStaff_img())
                            .error(R.mipmap.people_renyuan)
//                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                            .into(imageView);
                }
            }

        });

    }

    private final static int REQUEST_CODE = 5006;

    //扫描识别
    public void toScan(View view) {
        //打开扫描界面
        IntentIntegrator intentIntegrator = new IntentIntegrator(ExperienceActivity.this);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setCaptureActivity(QRActivity.class); // 设置自定义的activity是QRActivity
        intentIntegrator.setRequestCode(REQUEST_CODE);
        intentIntegrator.initiateScan();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(resultCode, data);
            final String qrContent = scanResult.getContents();
            L.log("scan", "扫描结果" + qrContent);
//            Toast.makeText(ProjectExperienceActivity.this, "扫描结果:" + qrContent, Toast.LENGTH_SHORT).show();

            //{"id":8,"staff_id":8,"type":2}
            Gson gson = new Gson();

            if (qrContent != null) {
                try {
                    JSONObject jsonObject = new JSONObject(qrContent);

                    int type = jsonObject.getInt("type");
                    int id = jsonObject.getInt("id");
                    int staff_id = jsonObject.getInt("staff_id");

                    if (type > 0) {
                        Intent peixun = new Intent();
                        peixun.putExtra("train_name", projectData.getTrain_name());
                        peixun.putExtra("staff_id", staff_id);
                        peixun.setClass(this, EmployeeInformationActivity.class);
                        startActivity(peixun);

                        finish();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
