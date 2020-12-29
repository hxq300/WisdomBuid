package com.lsy.wisdombuid.activity.safety;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.PersonnelManagementActivity;
import com.lsy.wisdombuid.adapter.SubContractorAdapter;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.SubContractorData;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy on 2020/3/31
 * todo : 分包单位隐患分析
 */
public class FenBaoYinghuanFenxiActivity extends MyBaseActivity {


    //消息列表
    private RecyclerView idListRecycle;
    private SubContractorAdapter listAdapter;
    private List<SubContractorData> messageLists = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_fenbao);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

        //初始化view
        initView();

        initData();
    }

    int allNum = 0;

    private void initData() {

        for (int i = 0; i < 5; i++) {
            SubContractorData data = new SubContractorData((int) (1 + Math.random() * (100 - 1 + 1)), "上海交接交通" + i);
            allNum += data.getCount();
            messageLists.add(data);
        }

        listAdapter = new SubContractorAdapter(FenBaoYinghuanFenxiActivity.this, messageLists, allNum);
        idListRecycle.setAdapter(listAdapter);

    }

    private void initView() {

        //===
        idListRecycle = (RecyclerView) findViewById(R.id.recycler_sub_contractor);
        idListRecycle.setItemViewCacheSize(100);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FenBaoYinghuanFenxiActivity.this);
        idListRecycle.setLayoutManager(linearLayoutManager);
        idListRecycle.setNestedScrollingEnabled(false);

    }


    @Override
    protected void initTitle() {
        if (titleBar == null) {
        } else {
            titleBar.setTitle("" + getString(R.string.comprehensive_hidden_danger_analysis));
        }
    }


}
