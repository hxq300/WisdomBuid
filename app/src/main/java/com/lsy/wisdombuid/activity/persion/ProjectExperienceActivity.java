package com.lsy.wisdombuid.activity.persion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.MainActivity;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.CoursesData;
import com.lsy.wisdombuid.bean.ProjectData;
import com.lsy.wisdombuid.qr.QRActivity;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.StatusBarUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lsy on 2020/4/29
 * todo : 培训项目安全体验
 */
public class ProjectExperienceActivity extends MyBaseActivity {


    @BindView(R.id.per_content)
    TextView perContent;
    @BindView(R.id.per_image)
    ImageView perImage;

    private int id = 1;

    private ProjectData projectData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_experience);
        ButterKnife.bind(this);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();


        Intent intent = getIntent();
        id = intent.getIntExtra("id", 1);

//        initView();

        getFindAnintegral();

    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("安全带体验");
        }
    }


    //查询积分明细
    private void getFindAnintegral() {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        //section_id
        listcanshu.put("id", id);//

        //培训课程分类列表
        okHttpClass.setPostCanShu(this, RequestURL.findTrainContent, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("peixun", "trainMenu=培训课程=" + dataString);
                Gson gson = new Gson();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

                    projectData = gson.fromJson(jsonObject.toString(), ProjectData.class);

                    if (projectData.getTrain_name() != null) {
                        titleBar.setTitle("" + projectData.getTrain_name());
                    }

                    if (projectData.getContent() != null) {
                        perContent.setText("" + projectData.getContent());
                    }

                    if (projectData.getImg_url() != null) {
                        Glide.with(ProjectExperienceActivity.this).load(RequestURL.OssUrl + projectData.getImg_url()).error(R.mipmap.good1).into(perImage);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });
    }

    private final static int REQUEST_CODE = 5006;

    //扫描识别
    public void toScan(View view) {
        //打开扫描界面
        IntentIntegrator intentIntegrator = new IntentIntegrator(ProjectExperienceActivity.this);
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
