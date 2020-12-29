package com.lsy.wisdombuid.activity.quality;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

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
import com.lsy.wisdombuid.activity.safety.FenBaoYinghuanFenxiActivity;
import com.lsy.wisdombuid.activity.safety.InspectionRecordActivity;
import com.lsy.wisdombuid.activity.safety.QuantityAnalysisDangerActivity;
import com.lsy.wisdombuid.adapter.ZhandianAdapter;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.SafetyIndexData;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.mvp.safety.SafetySystemInterface;
import com.lsy.wisdombuid.mvp.safety.SafetySystemPresent;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.SharedUtils;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.ToastUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy on 2020/4/10
 * todo : 质量管理系统
 */
public class QMSActivity extends MyBaseActivity implements SafetySystemInterface.View, ZhandianAdapter.OnClick {

    private LinearLayout lineZhandain;
    private PopupWindow popupWindow;
    private TextView zhandianName;

    //======

    private SafetySystemInterface.Presenter presenter;

    List<StationData> popuDatas = new ArrayList<>();

    //====图表
    //=====
    private BarChart chart;

    //===标段
    private SharedUtils sharedUtils;
    private TextView tvSectionName;

    private TextView sNumber, sZhenggailv, sYizhenggai, sWeizhenggai, sCaoqi;

    private int station_id = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qms);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

        sharedUtils = new SharedUtils(QMSActivity.this, SharedUtils.WISDOM);

        initView();

        presenter = new SafetySystemPresent(this, QMSActivity.this);

        presenter.getSelectStation("" + OKHttpClass.getToken(QMSActivity.this));

        initData1();

    }

    private void initView() {

        zhandianName = findViewById(R.id.safety_zhandian_name);
        lineZhandain = findViewById(R.id.safety_line_zhandian);
        tvSectionName = findViewById(R.id.tv_section_name);
        chart = findViewById(R.id.chart);

        sNumber = findViewById(R.id.safety_number);
        sZhenggailv = findViewById(R.id.safety_zhenggailv);
        sYizhenggai = findViewById(R.id.safety_yizhenggai);
        sWeizhenggai = findViewById(R.id.safety_weizhenggai);
        sCaoqi = findViewById(R.id.safety_caoqi);

        tvSectionName.setText("" + sharedUtils.getData(SharedUtils.SECTION_NAME, ""));

        lineZhandain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popuDatas != null) {
                    showPopupWindow(popuDatas);
                } else {
                    ToastUtils.showBottomToast(QMSActivity.this, "当前暂无数据展示");
                }

            }
        });

    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("" + getString(R.string.quality_control_system));
        }
    }

    public void safetyManagent(View view) {

        switch (view.getId()) {

            case R.id.safety_daizhenggai://待整改
                Intent daizhenggai = new Intent();
                daizhenggai.putExtra("stationnId", station_id);
                daizhenggai.setClass(QMSActivity.this, WaitReActivity.class);

                startActivity(daizhenggai);
                break;

            case R.id.safety_jianchajilu://检查记录

                Intent jilu = new Intent();
                jilu.putExtra("stationnId", station_id);
                jilu.putExtra("title", "检查记录");
                jilu.putExtra("intId", 1);
                jilu.setClass(this, QMSRecordActivity.class);
                startActivity(jilu);

                break;

            case R.id.safety_wuxiaoshuju://无效数据（隐患上报）

                Intent wuxiao = new Intent();
                wuxiao.putExtra("stationnId", station_id);
                wuxiao.putExtra("title", "无效数据");
                wuxiao.putExtra("intId", 2);
                wuxiao.setClass(this, InspectionRecordActivity.class);
                startActivity(wuxiao);

                break;

            case R.id.safety_zhenggaireviw://整改复查
                Intent review = new Intent();
                review.putExtra("stationnId", station_id);
                review.putExtra("title", "整改复查");
                review.putExtra("intId", 3);
                review.setClass(this, QMSReviewListActivity.class);
                startActivity(review);
                break;

            case R.id.safety_danger_type://隐患类型分析
                Intent analysis = new Intent(QMSActivity.this, QuantityAnalysisDangerActivity.class);
                startActivity(analysis);
                break;

            case R.id.safety_yinhuan_fenxi://分包单位隐患分析
                Intent fenbao = new Intent(QMSActivity.this, FenBaoYinghuanFenxiActivity.class);
                startActivity(fenbao);
                break;

            default:
                break;
        }

    }

    //=======
    private void showPopupWindow(List<StationData> datas) {

        View view = LayoutInflater.from(QMSActivity.this).inflate(R.layout.view_popu_recyle, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.select);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(QMSActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        ZhandianAdapter scoreTeamAdapter = new ZhandianAdapter(QMSActivity.this, datas);
        scoreTeamAdapter.setOnClick(this);
        recyclerView.setAdapter(scoreTeamAdapter);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(lineZhandain);
    }

    //获取下拉站点
    @Override
    public void setSelect(List<StationData> dataList) {
        popuDatas.clear();
        if (dataList != null && dataList.size() > 0) {
            popuDatas = dataList;
            if (popuDatas.get(0).getId() > 0) {
                this.station_id = dataList.get(0).getId();
                zhandianName.setText("" + popuDatas.get(0).getStation_name());
                presenter.getQMS("" + OKHttpClass.getToken(QMSActivity.this), "" + popuDatas.get(0).getId());
            }
        }
    }


    @Override
    public void setSafetyZGL(SafetyIndexData safetyZGL) {
//        sNumber, sZhenggailv, sYizhenggai, sWeizhenggai, sCaoqi

        sNumber.setText("" + safetyZGL.getCountAll());
        DecimalFormat decimalFormat = new DecimalFormat("0.0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        if (safetyZGL.getCountZGL() > 0.0) {
            String zgl = decimalFormat.format(safetyZGL.getCountZGL()) + "%";
            sZhenggailv.setText("" + zgl);
        } else {
            sZhenggailv.setText("0.0");
        }


        sYizhenggai.setText("" + safetyZGL.getCountYZG());
        sWeizhenggai.setText("" + safetyZGL.getCountWZG());
        sCaoqi.setText("" + safetyZGL.getCountCQWZG());


    }

    //====
    //======================
    private void initData1() {
        //返回标签设置的文本颜色
        chart.getDescription().setEnabled(true);
        /*设置仅在活动图表上可见的最大绘制值的数量
         *当启用setDrawValues()时
         */
        chart.setMaxVisibleValueCount(100);
        //缩放
        chart.setPinchZoom(false);
        chart.setDrawBarShadow(false);
        //将此设置为true以绘制网格背景，否则为false
        chart.setDrawGridBackground(false);

        //
        chart.setNoDataText("暂时没有数据");

        chart.animateY(1500);
        chart.getLegend().setEnabled(false);

//        IAxisValueFormatter xAxisFormatter = new DefaultAxisValueFormatter(chart);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(3);

//        xAxis.setValueFormatter(xAxisFormatter);

        ArrayList<BarEntry> values = new ArrayList<>();

        int[] colors = new int[]{0xffF699FF, 0xff9A8AE8, 0xffD05EF5};

        //Sum就是代码有几个条形
        int sum = 3;
        for (int i = 0; i < sum; i++) {
            float multi = (i + 1);
            float val = (float) (int) (1 + Math.random() * (100 - 1 + 1));
//            float val = (float) (int) ((Math.random() * multi) + multi / 2);
            values.add(new BarEntry(i, val));
        }

        BarDataSet set1;
        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {

            set1 = new BarDataSet(values, "data");
//            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            set1.setColors(colors);
            set1.setDrawValues(true);
            set1.setValueTextSize(16f);
            set1.setValueTextColor(colors[1]);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            chart.setData(data);
            chart.setFitBars(true);

            final String[] months = new String[]{
//                    "高家闸建筑用砂矿",
//                    "镇北堡2号建筑用砂矿",
//                    "套门沟老采区3号建筑石料用灰岩矿",
//                    "套门沟5号建筑石料用灰岩矿",
//                    "套门沟老采区1号建筑石料用灰岩矿",
//                    "套门沟老采区2号建筑石料用灰岩矿",
//                    "套门沟石灰石矿山"
                    "一般隐患",
                    "紧要隐患",
                    "严重隐患"
            };


            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {

                    int index = 0;
                    if ((int) value >= 0) {

                        if (index > (int) value) {
                            return "";
                        } else {
                            L.log("chart", "value" + value);
                            index = (int) value;
                            return months[(int) value % months.length];
                        }

//                        L.log("chart","value=="+value );

                    } else {
                        return "";
                    }
                }
            });
        }
        //这里的作用是将X轴上的文字旋转80度
//        chart.getXAxis().setLabelRotationAngle(-80);
        chart.getXAxis().setLabelRotationAngle(0);
        chart.getAxisRight().setEnabled(false);
        chart.setScaleYEnabled(false);
        chart.setScaleXEnabled(false);
        chart.invalidate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.distory();
        if (popupWindow != null) {
            popupWindow.dismiss();
        }

    }

    @Override
    public void checkZhandian(int position, int sectionId, String name) {
        this.station_id = sectionId;
        zhandianName.setText("" + name);
        presenter.getQMS("" + OKHttpClass.getToken(QMSActivity.this), "" + sectionId);
        popupWindow.dismiss();
    }
}
