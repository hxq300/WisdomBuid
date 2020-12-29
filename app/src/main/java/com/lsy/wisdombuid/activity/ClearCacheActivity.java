package com.lsy.wisdombuid.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.util.DataCleanManager;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lsy on 2020/3/17
 * todo : 清除缓存
 */
public class ClearCacheActivity extends MyBaseActivity {

    @BindView(R.id.cache_data)
    TextView cacheData;
    @BindView(R.id.clean_btn)
    Button cleanBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_cache);
        ButterKnife.bind(this);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

        bindData();
    }

    private void bindData() {

        try {
            cacheData.setText("" + DataCleanManager.getTotalCacheSize(ClearCacheActivity.this));
        } catch (Exception e) {
            e.printStackTrace();
        }

        cleanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataCleanManager.clearAllCache(ClearCacheActivity.this);
                ToastUtils.showBottomToast(ClearCacheActivity.this, "清除成功");
                try {
                    cacheData.setText("" + DataCleanManager.getTotalCacheSize(ClearCacheActivity.this));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {
        } else {
            titleBar.setTitle("清除缓存");
        }
    }
}
