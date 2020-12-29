package com.lsy.wisdombuid.activity.login;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lsy.wisdombuid.R;

/**
 * Created by lsy on 2020/3/16
 * todo : 注册
 */
public class RegisterActivity extends AppCompatActivity {
//
//    private DaoJiShi daoJiShi;
//
//    private TextView getCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regerst);

        initView();

    }

    private void initView() {
//        getCode=findViewById(R.id.regist_get_code);

    }

    //注册
    public void regerst(View view) {
        finish();
    }

//    //发送验证码
//    public void sendCode(View view) {
//        daoJiShi = new DaoJiShi(RegisterActivity.this, getCode, "regist");
//        daoJiShi.Jishi();
//    }
}
