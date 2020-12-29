package com.lsy.wisdombuid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.util.StatusBarUtil;

/**
 * Created by lsy on 2020/3/17
 * todo : 系统设置
 */
public class SysSettingActivity extends MyBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_setting);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();
    }


    public void Setting(View view) {

        switch (view.getId()) {

            case R.id.set_language://语言切换
                Intent toLanguage = new Intent(SysSettingActivity.this, LanguageSetActivity.class);
                startActivity(toLanguage);
                break;

            case R.id.set_privacy_agreement://隐私协议
                Intent privacyAgreement = new Intent(SysSettingActivity.this, PrivacyAgreementActivity.class);
                startActivity(privacyAgreement);
                break;

            case R.id.set_account_management://账号管理
                Intent itunes = new Intent(SysSettingActivity.this, ITunesActivity.class);
                startActivity(itunes);
                break;

            default:
                break;
        }

    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {
        } else {
            titleBar.setTitle("系统设置");
        }
    }
}
