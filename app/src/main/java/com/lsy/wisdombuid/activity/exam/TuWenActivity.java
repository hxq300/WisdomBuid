package com.lsy.wisdombuid.activity.exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.util.StatusBarUtil;

/**
 * Created by lsy on 2020/4/1
 * todo : 培训课堂（视屏图文教程）
 */
public class TuWenActivity extends MyBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach_tuwen);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("" + "这是图文教程");
        }
    }

    //进入视屏
    public void toVedio(View view) {
        Intent record = new Intent(this, TeachingVideoActivity.class);
        startActivity(record);
    }
}
