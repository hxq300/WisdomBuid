package com.lsy.wisdombuid.activity.login;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.util.StatusBarUtil;

/**
 * Created by lsy on 2020/3/26
 * todo : 密码重置成功
 */
public class UpdateSuccessActivity extends MyBaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_success);

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

    }


    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("" + getString(R.string.update_pass_success));
        }
    }
}
