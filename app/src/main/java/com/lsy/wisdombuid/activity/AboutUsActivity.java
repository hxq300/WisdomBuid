package com.lsy.wisdombuid.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.util.APKVersionCodeUtils;
import com.lsy.wisdombuid.util.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lsy on 2020/3/17
 * todo : 关于我们
 */
public class AboutUsActivity extends MyBaseActivity {

    @BindView(R.id.version_name)
    TextView versionName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

        bandingdata();
    }

    private void bandingdata() {
        versionName.setText("V" + APKVersionCodeUtils.getVerName(AboutUsActivity.this));
    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {
        } else {
            titleBar.setTitle("" + getString(R.string.about_us));
        }
    }
}
