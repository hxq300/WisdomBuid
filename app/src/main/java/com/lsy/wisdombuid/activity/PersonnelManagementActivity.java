package com.lsy.wisdombuid.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
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

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.persion.PersonalDetailsActivity;
import com.lsy.wisdombuid.activity.quality.QMSReviewActivity;
import com.lsy.wisdombuid.adapter.SubContractorAdapter;
import com.lsy.wisdombuid.adapter.ZhandianAdapter;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.PMData;
import com.lsy.wisdombuid.bean.PersonnelTypeData;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.bean.WorkTypeData;
import com.lsy.wisdombuid.mvp.SelectInterface;
import com.lsy.wisdombuid.mvp.SelectPresent;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.SharedUtils;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.ToastUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lsy on 2020/3/22
 * todo : 人员管理
 */
public class PersonnelManagementActivity extends MyBaseActivity implements SelectInterface.View, ZhandianAdapter.OnClick {

    @BindView(R.id.persional_num)
    TextView persionalNum;
    @BindView(R.id.persional_laowu_num)
    TextView persionalLaowuNum;
    @BindView(R.id.tv_section_name)
    TextView tvSectionName;


    private LinearLayout lineZhandain;
    private PopupWindow popupWindow;
    private List<String> zdDatas = new ArrayList<>();

    private TextView zhandianName;

    private SelectInterface.Presenter presenter;

    List<StationData> popuDatas = new ArrayList<>();

    private SharedUtils sharedUtils;


//    private Spinner mSpinner;

    //饼图1
    private PieChart mPieChart;

    //饼图1
    private PieChart mPieChart2;

    //分包单位分析
    //消息列表
    private RecyclerView idListRecycle;
    private SubContractorAdapter listAdapter;

    private int station_id = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persinal_management);
        ButterKnife.bind(this);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

        sharedUtils = new SharedUtils(PersonnelManagementActivity.this, SharedUtils.WISDOM);

        initView();

        presenter = new SelectPresent(this, PersonnelManagementActivity.this);

        presenter.getSelectStation("" + OKHttpClass.getToken(PersonnelManagementActivity.this));

//        getData();

    }

    private void initView() {

        zhandianName = findViewById(R.id.safety_zhandian_name);
        lineZhandain = findViewById(R.id.safety_line_zhandian);

//        mSpinner = findViewById(R.id.spinner_simple);
        //
        mPieChart = findViewById(R.id.pie_chart);
        mPieChart2 = findViewById(R.id.pie_chart_people);

        //===
        idListRecycle = (RecyclerView) findViewById(R.id.recycler_sub_contractor);
        idListRecycle.setItemViewCacheSize(100);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PersonnelManagementActivity.this);
        idListRecycle.setLayoutManager(linearLayoutManager);
        idListRecycle.setNestedScrollingEnabled(false);


        lineZhandain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (zdDatas != null) {
                    showPopupWindow(popuDatas);
                } else {
                    ToastUtils.showBottomToast(PersonnelManagementActivity.this, "当前暂无数据展示");
                }

            }
        });

        tvSectionName.setText("" + sharedUtils.getData(SharedUtils.SECTION_NAME, ""));
    }

    //图表1工种分析

    private int jobsNumber = 0;
    private static DecimalFormat df = new DecimalFormat("##.0");

    private List<PieEntry> getPieChartData(List<WorkTypeData> dataList) {
//        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            jobsNumber += dataList.get(i).getCount();
        }

        List<PieEntry> mPie = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {

            L.log("PieEntry=jobsNumber=", "" + jobsNumber + "-----" + toScale(dataList.get(i).getCount(), jobsNumber) + "%");
//            PieEntry pieEntry = new PieEntry((float) (dataList.get(i).getCount() / jobsNumber * 100),
//                    dataList.get(i).getWorktype_name(),
//                    getResources().getDrawable(R.mipmap.ic_launcher));
            PieEntry pieEntry = new PieEntry((float) toScale(dataList.get(i).getCount(), jobsNumber), "" + dataList.get(i).getWorktype_name() + "  " + dataList.get(i).getCount() + "人");
//            pieEntry.setX("float类型数字");
            mPie.add(pieEntry);
        }
        return mPie;
    }

    private int peopleNumber = 0;

    private List<PieEntry> getPieChartPeopleData(List<PersonnelTypeData> dataList) {
//        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            peopleNumber += dataList.get(i).getCount();
        }

        List<PieEntry> mPie = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {

            L.log("PieEntry=jobsNumber=", "" + peopleNumber + "-----" + toScale(dataList.get(i).getCount(), peopleNumber) + "%");
//            PieEntry pieEntry = new PieEntry((float) (dataList.get(i).getCount() / jobsNumber * 100),
//                    dataList.get(i).getWorktype_name(),
//                    getResources().getDrawable(R.mipmap.ic_launcher));
            PieEntry pieEntry = new PieEntry((float) toScale(dataList.get(i).getCount(), peopleNumber), "" + dataList.get(i).getType()+ dataList.get(i).getCount() + "人");
//            pieEntry.setX("float类型数字");
            mPie.add(pieEntry);
        }
        return mPie;
    }

    //换算百分比
    private double toScale(double v, int sum) {
        double temp = v / sum;
        double v1 = Double.parseDouble(df.format(v / sum * 100));
        return v1;
    }

    private void showPieChart(PieChart pieChart, List<PieEntry> pieList) {
        PieDataSet dataSet = new PieDataSet(pieList, "");

        // 设置颜色list，让不同的块显示不同颜色，下面是我觉得不错的颜色集合，比较亮
        ArrayList<Integer> colors = new ArrayList<Integer>();
        int[] MATERIAL_COLORS = {
//                Color.rgb(248, 181, 78)
                Color.rgb(92, 212, 255)
        };
        for (int c : MATERIAL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.LIBERTY_COLORS) {
            colors.add(c);
        }
        dataSet.setColors(colors);
        PieData pieData = new PieData(dataSet);

        // 设置描述，我设置了不显示，因为不好看，你也可以试试让它显示，真的不好看
        Description description = new Description();
        description.setEnabled(false);
        pieChart.setDescription(description);
        //设置半透明圆环的半径, 0为透明
        pieChart.setTransparentCircleRadius(0f);

        //设置初始旋转角度
        pieChart.setRotationAngle(-15);

        //数据连接线距图形片内部边界的距离，为百分数
        dataSet.setValueLinePart1OffsetPercentage(80f);

        //设置连接线的颜色
        dataSet.setValueLineColor(Color.GREEN);
        // 连接线在饼状图外面
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        // 设置饼块之间的间隔
        dataSet.setSliceSpace(1f);
        dataSet.setHighlightEnabled(true);
        // 不显示图例
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);
//        legend.setTextColor(Color.parseColor("#383838"));
//        legend.setTextSize(10);

        // 和四周相隔一段距离,显示数据
//        pieChart.setExtraOffsets(26, 5, 26, 5);
        pieChart.setExtraOffsets(16, 0, 16, 3);

        // 设置pieChart图表是否可以手动旋转
        pieChart.setRotationEnabled(true);
        // 设置piecahrt图表点击Item高亮是否可用
        pieChart.setHighlightPerTapEnabled(true);
        // 设置pieChart图表展示动画效果，动画运行1.4秒结束
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        //设置pieChart是否只显示饼图上百分比不显示文字
        pieChart.setDrawEntryLabels(true);
        //是否绘制PieChart内部中心文本
        pieChart.setDrawCenterText(false);

        // 绘制内容value，设置字体颜色大小
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.DKGRAY);

        pieChart.setEntryLabelColor(Color.parseColor("#666666"));
        pieChart.setData(pieData);
        // 更新 piechart 视图
        pieChart.postInvalidate();
    }


    private void showBottonChart(PieChart pieChart, List<PieEntry> pieList) {
        PieDataSet dataSet = new PieDataSet(pieList, "");

        // 设置颜色list，让不同的块显示不同颜色，下面是我觉得不错的颜色集合，比较亮
        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();
        int[] MATERIAL_COLORS = {
//                Color.rgb(248, 181, 78)
                Color.rgb(97, 223, 255),
                Color.rgb(87, 170, 230),
                Color.rgb(71, 218, 251)
        };
        for (int c : MATERIAL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(c);
        }
        dataSet.setColors(colors);
        PieData pieData = new PieData(dataSet);

        // 设置描述，我设置了不显示，因为不好看，你也可以试试让它显示，真的不好看
        Description description = new Description();
        description.setEnabled(false);
        pieChart.setDescription(description);
        //设置半透明圆环的半径, 0为透明
        pieChart.setTransparentCircleRadius(0f);

        //设置初始旋转角度
        pieChart.setRotationAngle(-15);

        //数据连接线距图形片内部边界的距离，为百分数
        dataSet.setValueLinePart1OffsetPercentage(100f);

        //设置连接线的颜色
        dataSet.setValueLineColor(Color.GREEN);
        // 连接线在饼状图外面
        dataSet.setYValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);

        dataSet.getValueLinePart2Length();
        // 设置饼块之间的间隔
        dataSet.setSliceSpace(1f);
        dataSet.setHighlightEnabled(true);
        // 不显示图例
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);
//        legend.setTextColor(Color.parseColor("#383838"));
//        legend.setTextSize(10);

        // 和四周相隔一段距离,显示数据
//        pieChart.setExtraOffsets(26, 5, 26, 5);
        pieChart.setExtraOffsets(13, 0, 13, 3);

        // 设置pieChart图表是否可以手动旋转
        pieChart.setRotationEnabled(true);
        // 设置piecahrt图表点击Item高亮是否可用
        pieChart.setHighlightPerTapEnabled(true);
        // 设置pieChart图表展示动画效果，动画运行1.4秒结束
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        //设置pieChart是否只显示饼图上百分比不显示文字
        pieChart.setDrawEntryLabels(true);
        //是否绘制PieChart内部中心文本
        pieChart.setDrawCenterText(false);

        pieChart.setHoleRadius(1f);

        // 绘制内容value，设置字体颜色大小
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.DKGRAY);

        pieChart.setEntryLabelColor(Color.parseColor("#666666"));
        pieChart.setData(pieData);
        // 更新 piechart 视图
        pieChart.postInvalidate();
    }


    @Override
    protected void initTitle() {
        if (titleBar == null) {
        } else {
            titleBar.setTitle("" + getString(R.string.personnel_management));
        }
    }

    @Override
    public void setSelect(List<StationData> dataList) {
//        ToastUtils.showBottomToast(PersonnelManagementActivity.this, "数据返回分类");
        popuDatas.clear();
        if (dataList != null && dataList.size() > 0) {
            popuDatas = dataList;
            if (dataList.get(0).getId() > 0) {
                zhandianName.setText("" + dataList.get(0).getStation_name());

//            mSpinner.setDropDownWidth(); //下拉宽度
//                mSpinner.setDropDownHorizontalOffset(100); //下拉的横向偏移
//                mSpinner.setDropDownVerticalOffset(100); //下拉的纵向偏移
//                //mSpinnerSimple.setBackgroundColor(AppUtil.getColor(instance,R.color.wx_bg_gray)); //下拉的背景色
//                //spinner mode ： dropdown or dialog , just edit in layout xml
//                //mSpinnerSimple.setPrompt("Spinner Title"); //弹出框标题，在dialog下有效
//
//                String[] spinnerItems = new String[dataList.size()];
//
//                for (int i = 0; i < dataList.size(); i++) {
//                    spinnerItems[i] = dataList.get(i).getStation_name().toString();
//                }
//
//                //自定义选择填充后的字体样式
//                //只能是textview样式，否则报错：ArrayAdapter requires the resource ID to be a TextView
//                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter(PersonnelManagementActivity.this,
//                        R.layout.item_spinner_select, spinnerItems);
//                //自定义下拉的字体样式
////            spinnerAdapter.setDropDownViewResource(R.layout.item_drop);
//                //这个在不同的Theme下，显示的效果是不同的
//                //spinnerAdapter.setDropDownViewTheme(Theme.LIGHT);
//                mSpinner.setAdapter(spinnerAdapter);

                presenter.getPersonCount("" + OKHttpClass.getToken(PersonnelManagementActivity.this), "" + dataList.get(0).getId());

            } else {
                ToastUtils.showBottomToast(PersonnelManagementActivity.this, "获取数据失败");
            }
        }

    }

    @Override
    public void setPersonCount(PMData pmData) {

        if (pmData.getData() != null && pmData.getData().size()!=0) {
            showPieChart(mPieChart, getPieChartData(pmData.getData()));
        }

        if (pmData.getData2() != null && pmData.getData2().size()!=0) {
            int allNum = 0;
            for (int i = 0; i < pmData.getData2().size(); i++) {
                allNum += pmData.getData2().get(i).getCount();
            }
            listAdapter = new SubContractorAdapter(PersonnelManagementActivity.this, pmData.getData2(), allNum);
            idListRecycle.setAdapter(listAdapter);
        }

        // 人员类型分析
        if (pmData.getData3() != null && pmData.getData3().size()!=0) {
            showBottonChart(mPieChart2, getPieChartPeopleData(pmData.getData3()));
        }

        if (pmData.getData4().get(0) != null && pmData.getData5().size()!=0) {
            persionalNum.setText("" + pmData.getData4().get(0).getCount() + "人");
            persionalLaowuNum.setText("" + pmData.getData5().get(0).getCount() + "人");

        }

//        ToastUtils.showBottomToast(PersonnelManagementActivity.this, "数据返回具体数据");
    }


    //=======
    private void showPopupWindow(List<StationData> datas) {

        View view = LayoutInflater.from(PersonnelManagementActivity.this).inflate(R.layout.view_popu_recyle, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.select);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PersonnelManagementActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        ZhandianAdapter scoreTeamAdapter = new ZhandianAdapter(PersonnelManagementActivity.this, datas);
        scoreTeamAdapter.setOnClick(this);
        recyclerView.setAdapter(scoreTeamAdapter);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(lineZhandain);
    }


    @SuppressLint("MissingSuperCall")
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
        presenter.getPersonCount("" + OKHttpClass.getToken(PersonnelManagementActivity.this), "" + sectionId);
        popupWindow.dismiss();
    }
}
