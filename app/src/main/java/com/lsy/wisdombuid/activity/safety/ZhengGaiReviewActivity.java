package com.lsy.wisdombuid.activity.safety;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.adapter.RectNoticeAdapter;
import com.lsy.wisdombuid.adapter.ZhengGaiImgAdapter;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.IRecordData;
import com.lsy.wisdombuid.bean.RectifyEntity;
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
 * Created by lsy on 2020/3/30
 * todo : 整改复查
 */
public class ZhengGaiReviewActivity extends MyBaseActivity {

    //消息列表
    private RecyclerView idListRecycle;
    private RectNoticeAdapter listAdapter;
    private ZhengGaiImgAdapter zgListAdapter;
    //===标段
    private SharedUtils sharedUtils;
    private TextView tvSectionName, sGongxu, sUnits;

    private RectifyEntity.ItemsBean recordData;

    //
    private TextView iv_description;
    private TextView iv_uptime;
    private TextView iv_zg_description;
    private RecyclerView rv_zg_url;
    private TextView iv_plan_time;

    private EditText sRecord;

    private RadioGroup sRadiogroup;
    private int checkBtn = 1;//1、有效  2、无效

    private Button btnCom;
    private int type = 3;//3、安全  4、质量
    private String url;
    private List<String> mImgAdapterList = new ArrayList<>();
    private List<String> mZgImgAdapterList = new ArrayList<>();
    private ZhengGaiImgAdapter mZhengGaiImgAdapter;
    private RecyclerView rv_img;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhenggai_review);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        rv_img = findViewById(R.id.rv_img);

        Intent intent = getIntent();

        type = intent.getIntExtra("type", 1);
        url = intent.getStringExtra("url");
        recordData = (RectifyEntity.ItemsBean) intent.getSerializableExtra("data");

        initTitle();

        sharedUtils = new SharedUtils(ZhengGaiReviewActivity.this, SharedUtils.WISDOM);

        //初始化view
        initView();

        //初始化数据
        initData();
        initAdapter();

    }

    private void initAdapter() {
        String substring = url.substring(1, url.length() - 1);
        String[] split = substring.split(",");
        for (String s : split) {
            mImgAdapterList.add(s);
        }
        mZhengGaiImgAdapter = new ZhengGaiImgAdapter(mImgAdapterList, this);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);//设置为横向排列
        rv_img.setLayoutManager(layout);
        rv_img.setAdapter(mZhengGaiImgAdapter);

        String ss = recordData.getZg_url().substring(1, recordData.getZg_url().length() - 1);
        String[] strings = ss.split(",");
        for (String string : strings) {
            mZgImgAdapterList.add(string);
        }

        zgListAdapter = new ZhengGaiImgAdapter(mZgImgAdapterList,this);
        rv_zg_url.setAdapter(zgListAdapter);

        zgListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String s = mImgAdapterList.get(position);
                final Dialog dialog = new Dialog(ZhengGaiReviewActivity.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                ImageView imageView = new ImageView(ZhengGaiReviewActivity.this);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                Glide.with(ZhengGaiReviewActivity.this)
                        .load(RequestURL.OssUrl + s)
                        .into(imageView);
                dialog.setContentView(imageView);
                dialog.show();
            }
        });

        mZhengGaiImgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String s = mImgAdapterList.get(position);
                final Dialog dialog = new Dialog(ZhengGaiReviewActivity.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                ImageView imageView = new ImageView(ZhengGaiReviewActivity.this);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                Glide.with(ZhengGaiReviewActivity.this)
                        .load(RequestURL.OssUrl + s)
                        .into(imageView);
                dialog.setContentView(imageView);
                dialog.show();
            }
        });
    }

    private void initView() {

        iv_description = findViewById(R.id.iv_description);
        iv_uptime = findViewById(R.id.iv_uptime);
        iv_zg_description = findViewById(R.id.iv_zg_description);
        rv_zg_url = findViewById(R.id.rv_zg_url);
        iv_plan_time = findViewById(R.id.iv_plan_time);


        idListRecycle = (RecyclerView) findViewById(R.id.recycler_zhenggaitongzhidan);
        idListRecycle.setItemViewCacheSize(100);
        idListRecycle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        idListRecycle.setNestedScrollingEnabled(false);

        rv_zg_url = (RecyclerView) findViewById(R.id.rv_zg_url);
        rv_zg_url.setItemViewCacheSize(100);
        rv_zg_url.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rv_zg_url.setNestedScrollingEnabled(false);

        //====
        tvSectionName = findViewById(R.id.tv_section_name);
        tvSectionName.setText("" + sharedUtils.getData(SharedUtils.SECTION_NAME, ""));


        sGongxu = findViewById(R.id.zhenggai_gongxu);
        sUnits = findViewById(R.id.zhenggai_utils);

        sRecord = findViewById(R.id.zhenggai_record);
        sRadiogroup = findViewById(R.id.zhenggai_radiogroup);

        sRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.zhenggai_hege://合格
                        checkBtn = 1;
                        break;

                    case R.id.zhenggai_buhege://不合格
                        checkBtn = 2;
                        break;

                    default:
                        break;
                }
            }
        });

    }

    private void initData() {

//            recordData = gson.fromJson(sharedUtils.getData(SharedUtils.IRDETAILS, ""), RectifyEntity.ItemsBean.class);
        if (recordData != null){
            sUnits.setText("" + recordData.getSub_name());

            rv_zg_url = findViewById(R.id.rv_zg_url);

            iv_description.setText(recordData.getDescription());
            iv_uptime.setText(recordData.getUptime());
            iv_zg_description.setText(recordData.getZg_description());
            iv_plan_time.setText(recordData.getPlan_time());

        }

    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("" + getString(R.string.check_the_rectification));
        }
    }


    //整改复查提交
    public void zhenggaiCommit(View view) {
        if (GeneralMethod.isFastClick()) {

            if (type == 3) {
                updateStrtus();//安全
            } else {
                updateRecord();//质量
            }
        }
    }

    private void updateStrtus() {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
//        传参:id(隐患数据id,数据类型:int),status(合格/不合效,数据类型:String),fc_description(整改描述)
        listcanshu.put("id", recordData.getId());
        if (sRecord.getText().toString() != null) {
            listcanshu.put("fc_description", "" + sRecord.getText());
        } else {
            listcanshu.put("fc_description", "0");
        }

        if (checkBtn == 1) {
            listcanshu.put("status", "合格");
        } else {
            listcanshu.put("status", "不合效");
        }

        listcanshu.put("staff_id", "" + recordData.getStaff_id());

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(ZhengGaiReviewActivity.this, RequestURL.updateStrtus, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("record", "safetyAudit==" + dataString);

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

                    int code = jsonObject.getInt("code");
                    String data = jsonObject.getString("data");
                    String message = jsonObject.getString("message");

                    if (code == 200) {
                        ToastUtils.showBottomToast(ZhengGaiReviewActivity.this, "整改复查已提交");
                        finish();
                    } else {
                        ToastUtils.showBottomToast(ZhengGaiReviewActivity.this, "" + message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });

    }


    private void updateRecord() {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
//        传参:id(隐患数据id,数据类型:int),status(合格/不合效,数据类型:String),fc_description(整改描述)
        listcanshu.put("id", recordData.getId());
        if (sRecord.getText().toString() != null) {
            listcanshu.put("fc_description", "" + sRecord.getText());
        } else {
            listcanshu.put("fc_description", "0");
        }

        if (checkBtn == 1) {
            listcanshu.put("status", "合格");
        } else {
            listcanshu.put("status", "不合效");
        }

        listcanshu.put("staff_id", "" + recordData.getStaff_id());

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(ZhengGaiReviewActivity.this, RequestURL.updateStatus, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("record", "safetyAudit==" + dataString);

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

                    int code = jsonObject.getInt("code");
                    String data = jsonObject.getString("data");
                    String message = jsonObject.getString("message");

                    if (code == 200) {
                        ToastUtils.showBottomToast(ZhengGaiReviewActivity.this, "整改复查已提交");
                        finish();
                    } else {
                        ToastUtils.showBottomToast(ZhengGaiReviewActivity.this, "" + message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });

    }
}
