package com.lsy.wisdombuid.activity.progress;

import android.os.Bundle;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.util.StatusBarUtil;

/**
 * Created by lsy on 2020/4/17
 * todo :
 */
public class ProgressTrackingActivity extends MyBaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_progress_tracking);

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
            titleBar.setTitle("" + "进度跟踪");
        }
    }
}
