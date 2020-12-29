package com.lsy.wisdombuid.activity.quality;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.safety.InsRecordDetailsActivity;
import com.lsy.wisdombuid.adapter.RectNoticeAdapter;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.IRecordData;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.GeneralMethod;
import com.lsy.wisdombuid.util.SharedUtils;
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
 * Created by lsy on 2020/4/10
 * todo : 审核检查记录
 */
public class QMSReviewActivity extends MyBaseActivity {


    //消息列表
    private RecyclerView idListRecycle;
    private RectNoticeAdapter listAdapter;

    //===标段
    private SharedUtils sharedUtils;
    private TextView tvSectionName;

    private IRecordData recordData;

    private TextView sPeople, sTime, sStation, sDesc, sZeren, sUnits;

    private EditText sJifen;

    private RadioGroup sRadiogroup;
    private int checkBtn = 1;//1、有效  2、无效

    private int type = 1;//1、检查记录  2、无效记录

    private LinearLayout jifenLine;

    private TextView wuxiao;
    private Button btnCom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_record_details);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);

        Intent intent = getIntent();

        type = intent.getIntExtra("type", 1);

        L.log("record", "type===" + type);

        initTitle();

        sharedUtils = new SharedUtils(QMSReviewActivity.this, SharedUtils.WISDOM);


        //初始化view
        initView();

        //初始化数据
        initData();

    }

    private void initView() {

        sPeople = findViewById(R.id.redetails_shangbaoren);
        sTime = findViewById(R.id.redetails_time);
        sStation = findViewById(R.id.redetails_zhandian);
        sDesc = findViewById(R.id.redetails_desc);
        sZeren = findViewById(R.id.redetails_zerenren);
        sUnits = findViewById(R.id.redetails_units);
        sJifen = findViewById(R.id.redetails_jifen);
        sRadiogroup = findViewById(R.id.redetails_radiogroup);
        wuxiao = findViewById(R.id.redetails_wuxiao);
        btnCom = findViewById(R.id.redetails_commit);
        jifenLine = findViewById(R.id.line_jifen);

        //===
        idListRecycle = (RecyclerView) findViewById(R.id.recycler_zhenggaitongzhidan);
        idListRecycle.setItemViewCacheSize(100);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RectificationNoticeActivity.this);
        idListRecycle.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        idListRecycle.setNestedScrollingEnabled(false);

        //====
        tvSectionName = findViewById(R.id.tv_section_name);
        tvSectionName.setText("" + sharedUtils.getData(SharedUtils.SECTION_NAME, ""));

        sRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rbtn_hege://有效
                        checkBtn = 1;
                        break;

                    case R.id.rbtn_buhege://无效
                        checkBtn = 2;
                        break;

                    default:
                        break;
                }
            }
        });


        if (type == 1) {
            wuxiao.setVisibility(View.GONE);

            sRadiogroup.setVisibility(View.VISIBLE);
            btnCom.setVisibility(View.VISIBLE);
            jifenLine.setVisibility(View.VISIBLE);

        } else {
            wuxiao.setVisibility(View.VISIBLE);

            sRadiogroup.setVisibility(View.GONE);
            btnCom.setVisibility(View.GONE);
            jifenLine.setVisibility(View.GONE);
        }

    }

    private void initData() {

        recordData = new IRecordData();
        Gson gson = new Gson();

        String data = sharedUtils.getData(SharedUtils.IRDETAILS, "");

        if (data != "" || !data.equals("")) {
            recordData = gson.fromJson(sharedUtils.getData(SharedUtils.IRDETAILS, ""), IRecordData.class);
            sPeople.setText("上报人： " + recordData.getStaff_name());
            sTime.setText("" + recordData.getUptime());
            sStation.setText("" + recordData.getStation_name());
            sDesc.setText("" + recordData.getDescription());
            sZeren.setText("负责人： " + recordData.getResponsible());
            sUnits.setText("分包单位： " + recordData.getSub_name());
        } else {
            ToastUtils.showBottomToast(QMSReviewActivity.this, "获取数据失败");
        }

        List<String> images = new ArrayList<>();

        L.log("record", "" + recordData.getUrl());
        if (recordData.getUrl() != null) {
            try {
                JSONArray jsonArray = new JSONArray("" + recordData.getUrl());

                for (int i = 0; i < 2; i++) {

                    images.add(jsonArray.get(i).toString());

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        listAdapter = new RectNoticeAdapter(QMSReviewActivity.this, images);
        idListRecycle.setAdapter(listAdapter);
    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            if (type == 1) {
                titleBar.setTitle("" + getString(R.string.inspection_record));
            } else {
                titleBar.setTitle("" + getString(R.string.invalid_records));
            }

        }
    }

    //提交审核
    private void upAudit() {

        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
//        传参:id(隐患数据id,数据类型:int),integral(积分,数据类型:String),state(有效/无效,数据类型:String),staff_id(员工id安全隐患记录返回的数据,数据类型:int)
        listcanshu.put("id", recordData.getId());
        if (sJifen.getText().toString() != null) {
            listcanshu.put("integral", "" + sJifen.getText());
        } else {
            listcanshu.put("integral", "0");
        }

        if (checkBtn == 1) {
            listcanshu.put("state", "有效");
        } else {
            listcanshu.put("state", "无效");
        }

        listcanshu.put("staff_id", "" + recordData.getStaff_id());

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(QMSReviewActivity.this, RequestURL.updateActive, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("record", "updateActive==" + dataString);

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

                    int code = jsonObject.getInt("code");
                    String data = jsonObject.getString("data");
                    String message = jsonObject.getString("message");

                    if (code == 200) {
                        ToastUtils.showBottomToast(QMSReviewActivity.this, "审核成功");
                        finish();
                    } else {
                        ToastUtils.showBottomToast(QMSReviewActivity.this, "" + message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });

    }

    //提交
    public void recordCommit(View view) {
        if (GeneralMethod.isFastClick()) {
            upAudit();
        }
    }
}
