package com.lsy.wisdombuid.activity.progress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.BaseActivity;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.ToastUtils;

/**
 * Created by lsy on 2020/4/1
 * todo :
 */
public class  ScheduleControlActivity extends BaseActivity {

    private PopupWindow popupWindow;

    private ImageView sPopu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_control);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }

        //初始化view
        initView();

    }

    private void initView() {

        sPopu = findViewById(R.id.process_spopu);

    }

    public void back(View view) {
        finish();
    }

    //显示弹窗
    public void showPopu(View view) {

        View layout = LayoutInflater.from(ScheduleControlActivity.this).inflate(R.layout.view_schedule_popu, null);

        TextView shangbao = layout.findViewById(R.id.process_shangbao);
        TextView genzong = layout.findViewById(R.id.process_genzong);

        shangbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showBottomToast(ScheduleControlActivity.this, "点击了进度上报");
            }
        });


        genzong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showBottomToast(ScheduleControlActivity.this, "点击了进度追踪");
            }
        });

//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.select);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SafetyManagementActivity.this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        ZhandianAdapter scoreTeamAdapter = new ZhandianAdapter(SafetyManagementActivity.this, datas);
//        recyclerView.setAdapter(scoreTeamAdapter);
        popupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.setContentView(layout);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(sPopu);

    }
}
