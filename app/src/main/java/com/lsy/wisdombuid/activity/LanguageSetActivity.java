package com.lsy.wisdombuid.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.util.ActivityManager;
import com.lsy.wisdombuid.util.LocalManageUtil;
import com.lsy.wisdombuid.util.SPUtil;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.ToastUtils;

/**
 * Created by lsy on 2020/3/18
 * todo : 语言设置
 */
public class LanguageSetActivity extends MyBaseActivity {

    private TextView laCh, laEn, laDefault, laZhrTW;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_setting);

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

        Drawable rightDrawable = getResources().getDrawable(R.mipmap.check_icon);
        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());

        laCh = findViewById(R.id.language_simple);
        laEn = findViewById(R.id.language_english);
        laDefault = findViewById(R.id.language_default);
        laZhrTW = findViewById(R.id.language_traditional);

        switch (SPUtil.getInstance(LanguageSetActivity.this).getSelectLanguage()) {
            case 0:
                laDefault.setCompoundDrawables(null, null, rightDrawable, null);
                oldView=laDefault;
                break;
            case 1:
                laCh.setCompoundDrawables(null, null, rightDrawable, null);
                oldView=laCh;
                break;
            case 2:
                laCh.setCompoundDrawables(null, null, rightDrawable, null);
                oldView=laCh;
                break;
            case 3:
                laEn.setCompoundDrawables(null, null, rightDrawable, null);
                oldView=laEn;
                break;
            default:
                laDefault.setCompoundDrawables(null, null, rightDrawable, null);
                oldView=laDefault;
                break;
        }

    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {
        } else {
            titleBar.setTitle("语言设置");
        }
    }

    private TextView tvView = null;
    private TextView oldView = null;

    //语言切换事件点击
    public void switchLanguage(View view) {

        tvView= (TextView) view;
        Drawable rightDrawable = getResources().getDrawable(R.mipmap.check_icon);
        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());



        switch (view.getId()) {

            case R.id.language_default://系统默认
                if (oldView != tvView) {
                    laDefault.setCompoundDrawables(null, null, rightDrawable, null);
                    ToastUtils.showBottomToast(LanguageSetActivity.this, ""+getString(R.string.wait_tip));
                    selectLanguage(0);
                    oldView.setCompoundDrawables(null,null,null,null);
                    oldView = tvView;
                } else {
                    ToastUtils.showBottomToast(LanguageSetActivity.this, getString(R.string.is_already) + tvView.getText().toString());
                }
                break;

            case R.id.language_simple://简体中文
                if (oldView != null && oldView != tvView) {
                    laCh.setCompoundDrawables(null, null, rightDrawable, null);
                    ToastUtils.showBottomToast(LanguageSetActivity.this, ""+getString(R.string.wait_tip));
                    selectLanguage(1);
                    oldView.setCompoundDrawables(null,null,null,null);
                    oldView = tvView;
                } else {
                    ToastUtils.showBottomToast(LanguageSetActivity.this, getString(R.string.is_already) + LocalManageUtil.getSelectLanguage(LanguageSetActivity.this));
                }
                break;

            case R.id.language_traditional://繁体中文
                if (oldView != null && oldView != tvView) {
                    laZhrTW.setCompoundDrawables(null, null, rightDrawable, null);
                    ToastUtils.showBottomToast(LanguageSetActivity.this, ""+getString(R.string.wait_tip));
                    selectLanguage(2);
                    oldView.setCompoundDrawables(null,null,null,null);
                    oldView = tvView;
                } else {
                    ToastUtils.showBottomToast(LanguageSetActivity.this, getString(R.string.is_already) + LocalManageUtil.getSelectLanguage(LanguageSetActivity.this));
                }
                break;

            case R.id.language_english://美式英语
                if (oldView != null && oldView != tvView) {
                    laEn.setCompoundDrawables(null, null, rightDrawable, null);
                    ToastUtils.showBottomToast(LanguageSetActivity.this, ""+getString(R.string.wait_tip));
                    selectLanguage(3);
                    oldView.setCompoundDrawables(null,null,null,null);
                    oldView = tvView;
                } else {
                    ToastUtils.showBottomToast(LanguageSetActivity.this, getString(R.string.is_already) + LocalManageUtil.getSelectLanguage(LanguageSetActivity.this));
                }
                break;
            default:
                break;
        }

    }

    private void selectLanguage(int select) {
        ToastUtils.showBottomToast(LanguageSetActivity.this, "正在初始化语言包");
        ActivityManager.getInstance().finishAllActivity();
        LocalManageUtil.saveSelectLanguage(this, select);
        FlashActivity.reStart(this);
    }
}
