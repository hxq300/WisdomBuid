//package com.lsy.wisdombuid.activity;
//
//import android.os.Bundle;
//
//import androidx.annotation.Nullable;
//
//import com.lsy.wisdombuid.R;
//import com.lsy.wisdombuid.base.MyBaseActivity;
//import com.lsy.wisdombuid.util.StatusBarUtil;
//
///**
// * Created by lsy on 2020/3/18
// * todo : 账号管理
// */
//public class AccountManagementActivity extends MyBaseActivity {
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_system_setting);
//
//        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
//        StatusBarUtil.setTranslucentStatus(this);
//        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
//            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
//        }
//        titleBar = findViewById(R.id.title_bar);
//        initTitle();
//    }
//
//    @Override
//    protected void initTitle() {
//        if (titleBar == null) {
//        } else {
//            titleBar.setTitle("账号管理");
//        }
//    }
//
//}
