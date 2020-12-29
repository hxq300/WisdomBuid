package com.lsy.wisdombuid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.BaseActivity;
import com.lsy.wisdombuid.activity.login.LoginActivity;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.util.StatusBarUtil;

/**
 * Created by lsy on 2020/3/16
 * todo : 闪屏页
 */
public class FlashActivity extends BaseActivity {

    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }

        handler = new Handler();

        initView();
    }

    private void initView() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (OKHttpClass.getToken(FlashActivity.this) == 0) {
                    //如果用户是第一次启动
                    Intent toLogin = new Intent(FlashActivity.this, LoginActivity.class);
                    startActivity(toLogin);
                    finish();
                } else {
                    Intent tomain = new Intent(FlashActivity.this, MainActivity.class);
                    startActivity(tomain);
                    finish();
                }
            }
        }, 2000);
    }

    public static void reStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
