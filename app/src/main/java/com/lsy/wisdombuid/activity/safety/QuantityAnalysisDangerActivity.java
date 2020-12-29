package com.lsy.wisdombuid.activity.safety;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.SharedUtils;
import com.lsy.wisdombuid.util.StatusBarUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsy on 2020/3/30
 * todo : 综合隐患分析
 */
public class QuantityAnalysisDangerActivity extends MyBaseActivity {

    private Spinner mspinner;
    private List<String> zhandian = new ArrayList<>();

    //=====
    private BarChart chart;

    //===标段
    private SharedUtils sharedUtils;
    private TextView tvSectionName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yinhuan_analysis);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

        sharedUtils = new SharedUtils(QuantityAnalysisDangerActivity.this, SharedUtils.WISDOM);

        //初始化view
        initView();

        initData();

        initData1();

    }

    private void initData() {

        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
//        {"section_id":1,"station_id":1} //标段ID   站点ID
        listcanshu.put("section_id", OKHttpClass.getToken(QuantityAnalysisDangerActivity.this));

//        L.log("SafetySystem", "站点==" + section_id);

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(QuantityAnalysisDangerActivity.this, RequestURL.selectStation, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("SafetySystem", "站点==" + dataString);
                Gson gson = new Gson();

                List<StationData> dataList = new ArrayList<>();
                List<String> datas = new ArrayList<>();

                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(dataString);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        StationData stationData = gson.fromJson(jsonArray.get(i).toString(), StationData.class);
                        zhandian.add("" + stationData.getStation_name());
                        dataList.add(stationData);
                    }
//                    presenter.responseSelect(dataList);

                    initSpinner(zhandian);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });


    }

    private void initView() {
        mspinner = findViewById(R.id.mspinner);
        chart = findViewById(R.id.chart);

        tvSectionName = findViewById(R.id.tv_section_name);

        tvSectionName.setText("" + sharedUtils.getData(SharedUtils.SECTION_NAME, ""));
    }

    private void initSpinner(List<String> datas) {
        //======
        // 定义数组适配器，利用系统布局文件

        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datas);
        // 定义下拉框的样式
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        mspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                getFile(years[i]);//获取此类下数据
//                year = years[i];
                L.log("点击了" + zhandian.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mspinner.setAdapter(adapter);
    }


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

        int[] colors = new int[]{0xff8FC8F8, 0xff4BA2EC, 0xff1674C4};

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
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("" + getString(R.string.comprehensive_hidden_danger_analysis));
        }
    }


}
