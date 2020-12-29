package com.lsy.wisdombuid.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lsy.wisdombuid.activity.MainActivity;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.bean.LaborData;
import com.lsy.wisdombuid.bean.UserData;
import com.lsy.wisdombuid.mvp.login.LoginInterface;
import com.lsy.wisdombuid.mvp.login.LoginPresent;
import com.lsy.wisdombuid.util.GeneralMethod;
import com.lsy.wisdombuid.util.SharedUtils;
import com.lsy.wisdombuid.util.StatusBarUtil;

public class LoginActivity extends AppCompatActivity implements LoginInterface.View {


    private EditText etPhone, etPassword;

    private LoginInterface.Presenter presenter;

    private RadioGroup loginRadiogroup;
    private int checkBtn = 1;//1、管理人员  2、劳务人员

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }

        initView();

        presenter = new LoginPresent(this, LoginActivity.this);
    }

    private void initView() {

        etPhone = findViewById(R.id.login_et_phone);
        etPassword = findViewById(R.id.login_et_pass);

        loginRadiogroup = findViewById(R.id.login_radiogroup);

        loginRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rbtn_manager://管理人员登录
                        checkBtn = 1;
                        break;

                    case R.id.rbtn_labor://劳务人员登录
                        checkBtn = 2;
                        break;

                    default:
                        break;
                }
            }
        });
    }


    //登录
    public void login(View view) {
        if (is_input() && GeneralMethod.isFastClick()) {
            if (checkBtn == 1) {//管理人员登录
                presenter.getManagerLogin("" + etPhone.getText().toString().trim(), "" + etPassword.getText().toString().trim());
            } else {//劳务人员登录
                presenter.getLaborLogin("" + etPhone.getText().toString().trim(), "" + etPassword.getText().toString().trim());
            }
        }
    }


    /**
     * 判断输入完成情况
     */
    private boolean is_input() {

        if (etPhone.getText().toString().trim().length() < 11) {
            Toast.makeText(LoginActivity.this, "请检查您的手机号", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etPassword.getText().toString().trim().length() < 1) {
            Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    //管理人员登录（数据返回）
    @Override
    public void setManagerLogin(UserData managerData) {
        saveUserMsg(managerData);
        Intent main = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(main);
        finish();
    }

    //劳务人员登录（数据返回）
    @Override
    public void setLaborLogin(LaborData.Labor laborLogin) {
        Intent main = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(main);
        finish();
    }

    //忘记密码
    public void updatePass(View view) {
        Intent updatePass = new Intent();
        updatePass.putExtra("title", "忘记密码");
        updatePass.setClass(LoginActivity.this, LostPasswordActivity.class);
        startActivity(updatePass);
    }


    /**
     * 保存用户id
     */
    private void saveUserMsg(UserData managerData) {
        SharedUtils sharedUtils = new SharedUtils(LoginActivity.this, SharedUtils.WISDOM);
        sharedUtils.setData(sharedUtils.TOKEN, managerData.getSection_id());//标段ID
        sharedUtils.setData(sharedUtils.JOB_ID, managerData.getPosition_id());//标段ID
        sharedUtils.setData(sharedUtils.SECTION_NAME, managerData.getSection_name());//标段名称
        sharedUtils.setData(sharedUtils.USER_PHONE, managerData.getStaff_phone());//
        sharedUtils.setData(sharedUtils.USER_PASS, managerData.getPassword());//
        if (managerData.getNikename() != null) {
            sharedUtils.setData(sharedUtils.USER_NICKNAME, managerData.getNikename());//
        } else if (managerData.getStaff_name() != null) {
            sharedUtils.setData(sharedUtils.USER_NAME, managerData.getStaff_name());//
        }

        if (managerData.getPicture() != null) {
            sharedUtils.setData(sharedUtils.USER_IMAGE, managerData.getPicture());//
        } else if (managerData.getStaff_img() != null) {
            sharedUtils.setData(sharedUtils.USER_PIC, managerData.getStaff_img());//
        }

        sharedUtils.setData(sharedUtils.USER_ID, managerData.getId());//
        sharedUtils.setData(sharedUtils.SUB_ID, managerData.getSub_id());//
        sharedUtils.setData(sharedUtils.STATION, managerData.getStation_id());//

//        sharedUtils.setBoolean(sharedUtils.COMSTATUS, data.isComStatus());
    }
}
