package com.lsy.wisdombuid.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.DaoJiShi;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lsy on 2020/3/26
 * todo : 忘记密码
 */
public class LostPasswordActivity extends MyBaseActivity {

    private DaoJiShi daoJiShi;
    private TextView getCode;

    private EditText etPhone;
    private EditText etCode;
    private EditText etNewPass;

    private String title = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_password);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

        initView();

    }

    private void initView() {
        getCode = findViewById(R.id.lost_get_code);
        etPhone = findViewById(R.id.lost_phone);
        etCode = findViewById(R.id.lost_code);
        etNewPass = findViewById(R.id.lost_new_password);
    }


    @Override
    protected void initTitle() {

        Intent intent = getIntent();

        title = intent.getStringExtra("title");

        if (titleBar == null) {

        } else {
            if (title != null) {
                titleBar.setTitle("" + title);
            } else {
                titleBar.setTitle("密码");
            }

        }
    }

    //发送验证码
    public void sendCode(View view) {
        if (etPhone.getText().toString().trim().length() == 11) {

            if (getCode.getText().equals("验证码发送中")) {
                ToastUtils.showBottomToast(LostPasswordActivity.this, "正在发送，请耐心等待");
            } else {
                getCode.setText("验证码发送中");
                getCodeNum();
            }
        } else {
            ToastUtils.showBottomToast(LostPasswordActivity.this, "请检查手机号是否正确");
        }

    }

    //发送验证码
    private void getCodeNum() {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

//        {"admin_phone":"17521223323"}
//        listcanshu.put("admin_phone", "" + etPhone.getText().toString().trim());
        listcanshu.put("staff_phone", "" + etPhone.getText().toString().trim());

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(LostPasswordActivity.this, RequestURL.getCode, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("login", "" + dataString);
                Gson gson = new Gson();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);
                    int code = jsonObject.getInt("code");
                    String data = jsonObject.getString("data");
                    String message = jsonObject.getString("message");

                    if (code == 200) {
                        daoJiShi = new DaoJiShi(LostPasswordActivity.this, getCode, "regist");
                        daoJiShi.Jishi();
//                        LaborData.Labor userData = gson.fromJson(data, LaborData.Labor.class);
//                        presenter.responseLabor(userData);

                    } else {
                        ToastUtils.showBottomToast(LostPasswordActivity.this, "" + message);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return dataString;
            }
        });
    }


    //更新密码
    public void lostUpdate(View view) {

        if (is_input()) {
            update();
        }
    }

    private void update() {

        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        //{"password":"960901","code":"123456","staff_phone":"13100000000"}
        listcanshu.put("password", "" + etNewPass.getText().toString().trim());
        listcanshu.put("code", Integer.parseInt(etCode.getText().toString().trim()));
        listcanshu.put("staff_phone", "" + etPhone.getText().toString().trim());

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(LostPasswordActivity.this, RequestURL.forgetPass, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("login", "" + dataString);
                Gson gson = new Gson();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);
                    int code = jsonObject.getInt("code");
                    String data = jsonObject.getString("data");
                    String message = jsonObject.getString("message");

                    if (code == 200) {
                        Intent update = new Intent(LostPasswordActivity.this, UpdateSuccessActivity.class);
                        startActivity(update);
                        finish();
                    } else {
                        ToastUtils.showBottomToast(LostPasswordActivity.this, "" + message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return dataString;
            }
        });
    }


    /**
     * 判断输入完成情况
     */
    private boolean is_input() {

        if (etPhone.getText().toString().trim().length() < 11) {
            Toast.makeText(LostPasswordActivity.this, "请检查您的手机号", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etCode.getText().toString().trim().length() < 6) {
            Toast.makeText(LostPasswordActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etNewPass.getText().toString().trim().length() < 1) {
            Toast.makeText(LostPasswordActivity.this, "请填写密码", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}
