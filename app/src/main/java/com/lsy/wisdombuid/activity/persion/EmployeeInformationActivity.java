package com.lsy.wisdombuid.activity.persion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.PersionDataActivity;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.PerImformationData;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lsy on 2020/4/29
 * todo : 员工信息
 */
public class EmployeeInformationActivity extends MyBaseActivity {


    @BindView(R.id.employee_image)
    ImageView employeeImage;
    @BindView(R.id.employee_name)
    TextView employeeName;
    @BindView(R.id.employee_gongzhong)
    TextView employeeGongzhong;
    @BindView(R.id.employee_bumen)
    TextView employeeBumen;
    @BindView(R.id.employee_gongsi)
    TextView employeeGongsi;

    private PerImformationData imformationData;
    private int staff_id = 0;
    private String train_name = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_information);
        ButterKnife.bind(this);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();


        Intent intent = getIntent();

        staff_id = intent.getIntExtra("staff_id", 1);
        train_name = intent.getStringExtra("train_name");
//        initView();

        getFindAnintegral();

    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("员工信息");
        }
    }


    //个人所有信息查询
    private void getFindAnintegral() {
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
                try {
                    jsonObject = new JSONObject(dataString);

                    String data = jsonObject.getString("data");

                    imformationData = gson.fromJson(data, PerImformationData.class);

                    if (imformationData.getStaff_img() != null) {
                        Glide.with(EmployeeInformationActivity.this).load(RequestURL.RequestImg + imformationData.getStaff_img())
                                .error(R.mipmap.mine_head)
                                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                .into(employeeImage);
                    }

                    if (imformationData.getStaff_name() != null) {
                        employeeName.setText("" + imformationData.getStaff_name());
                    }

                    if (imformationData.getType() != null) {
                        employeeGongzhong.setText("" + imformationData.getType());
                    }

                    if (imformationData.getDepartment_name() != null) {
                        employeeBumen.setText("" + imformationData.getDepartment_name());
                    }

                    if (imformationData.getSub_name() != null) {
                        employeeGongsi.setText("" + imformationData.getSub_name());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });
    }

    //个人所有信息查询
    private void addCommit() {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        //section_id
        listcanshu.put("staff_name", imformationData.getStaff_name());//
        listcanshu.put("train_name", train_name);//
        listcanshu.put("staff_img", imformationData.getStaff_img());//
        listcanshu.put("section_id", OKHttpClass.getToken(EmployeeInformationActivity.this));//

        //培训课程分类列表
        okHttpClass.setPostCanShu(this, RequestURL.insertTrainRecord, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("peixun", "trainMenu=人员信息=" + dataString);
//                Gson gson = new Gson();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);
                    int code = jsonObject.getInt("code");
                    String data = jsonObject.getString("data");
                    String message = jsonObject.getString("message");

                    if (code == 200 && data != null) {
                        ToastUtils.showBottomToast(EmployeeInformationActivity.this, "提交成功");
                        finish();
                    } else {
                        ToastUtils.showBottomToast(EmployeeInformationActivity.this, "" + message);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return dataString;
            }
        });
    }

    public void btnCommit(View view) {

        addCommit();

    }
}
