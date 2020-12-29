package com.lsy.wisdombuid.base;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.jokar.multilanguages.library.MultiLanguage;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.util.ActivityManager;
import com.lsy.wisdombuid.widget.TitleBar;

/**
 * Created by lsy on 2020/3/16
 * todo : 基础类
 */
public abstract class MyBaseActivity extends AppCompatActivity {
    protected TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_title_bar);
        ActivityManager.getInstance().pushOneActivity(this);

        titleBar = (TitleBar) findViewById(R.id.title_bar);
    }

    /**
     * 初始化头部
     */
    protected abstract void initTitle();

    @Override
    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(newBase);
        super.attachBaseContext(MultiLanguage.setLocal(newBase));
    }
}
