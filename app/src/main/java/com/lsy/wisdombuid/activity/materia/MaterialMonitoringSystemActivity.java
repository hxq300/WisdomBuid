package com.lsy.wisdombuid.activity.materia;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.PersonnelManagementActivity;
import com.lsy.wisdombuid.adapter.JianCeAdapter;
import com.lsy.wisdombuid.adapter.RateAdapter;
import com.lsy.wisdombuid.adapter.ZhandianAdapter;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.DeviceData;
import com.lsy.wisdombuid.bean.MaterialMonitoringSystemBean;
import com.lsy.wisdombuid.bean.MonitorgSysEntity;
import com.lsy.wisdombuid.bean.RebarEntity;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.bean.WeatherDataEntity;
import com.lsy.wisdombuid.mvp.jiancesys.MonitorgSysInterface;
import com.lsy.wisdombuid.mvp.jiancesys.MonitorgSysPresent;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.util.SharedUtils;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.ToastUtils;
import com.lsy.wisdombuid.util.net.OkHttpManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by lsy on 2020/4/2
 * todo : 物料监测系统
 */
public class MaterialMonitoringSystemActivity extends MyBaseActivity implements MonitorgSysInterface.View, ZhandianAdapter.OnClick {

    private MonitorgSysInterface.Presenter presenter;
    private SharedUtils sharedUtils;
    private LinearLayout lineZhandain;
    private PopupWindow popupWindow;
    private TextView zhandianName;
    private TextView tv_pm25, tv_pm10, tv_noise, tv_tem, tv_hum, tv_wp, tv_ws, tv_wd, tv_tsp, tv_atm, tv_date_week, tv_wea, tv_air_level, tv_air_tips, tv_win_speed, tv_name;
    List<StationData> popuDatas = new ArrayList<>();

    //=====
    private RecyclerView rateRecycle;
    private JianCeAdapter rateAdapter;

    private static final String TAG = "MaterialMonitoringSyste";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_monitoring);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

        //初始化view
        initView();

        presenter = new MonitorgSysPresent(this, MaterialMonitoringSystemActivity.this);

        presenter.getSelectStation("" + OKHttpClass.getToken(MaterialMonitoringSystemActivity.this));

        // 获取最近一周的天气数据
        getWeatherDate();
    }

    private void getWeatherDate() {
//        OkHttpClient okHttpClient = OkHttpManager.getInstance().getOkHttpClient();
//        //2、构建Request
//        Request.Builder builder = new Request.Builder();
//        Request request = builder.get().url(RequestURL.weatherUrl).build();
        int mId = 21043007;
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
        listcanshu.put("deviceId", mId);
        okHttpClass.setPostCanShu(this, RequestURL.materialMonitoringSystem, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
          //  {"id":0,  "pm25":"43",  "pm10":"54",  "noise":"66",  "tem":"23.1",
          //  "hum":"58.0",  "wp":"2",  "ws":"1.2", "wd":"东南风",  "tsp":"95"}
            @Override
            public String requestData(String dataString) {
                Log.d(TAG, "requestData:+++++======= "+dataString);
                if (!"".equals(dataString)){
                    Gson gson = new Gson();
                    MaterialMonitoringSystemBean requestGsonData = gson.fromJson(dataString, MaterialMonitoringSystemBean.class);
                    if (null!=requestGsonData){
                        if (requestGsonData.getCode()==200){
                            Log.d(TAG, "requestData: +++++====="+requestGsonData.getCode());
                            tv_pm25.setText(requestGsonData.getData().getPm25()+"");
                            tv_pm10.setText(requestGsonData.getData().getPm10()+"");
                            tv_noise.setText(requestGsonData.getData().getNoise()+"");
                            tv_tem .setText(requestGsonData.getData().getTem()+"");
                            tv_hum .setText(requestGsonData.getData().getHum()+"");
                            tv_wp .setText(requestGsonData.getData().getWp()+"");
                            tv_ws .setText(requestGsonData.getData().getWs()+"");
                            tv_wd .setText(requestGsonData.getData().getWd()+"");
                            tv_tsp .setText(requestGsonData.getData().getTsp()+"");
                        }
                    }
                }
                return dataString;
            }
        });

    }

    private void initView() {
        sharedUtils = new SharedUtils(MaterialMonitoringSystemActivity.this, SharedUtils.WISDOM);
        zhandianName = findViewById(R.id.safety_zhandian_name);
        lineZhandain = findViewById(R.id.safety_line_zhandian);
        tv_pm25 = findViewById(R.id.tv_pm25);
        tv_pm10 = findViewById(R.id.tv_pm10);
        tv_noise = findViewById(R.id.tv_noise);
        tv_tem = findViewById(R.id.tv_tem);
        tv_hum = findViewById(R.id.tv_hum);
        tv_wp = findViewById(R.id.tv_wp);
        tv_ws = findViewById(R.id.tv_ws);
        tv_wd = findViewById(R.id.tv_wd);
        tv_tsp = findViewById(R.id.tv_tsp);
        tv_atm = findViewById(R.id.tv_atm);

        tv_date_week = findViewById(R.id.tv_date_week);
        tv_wea = findViewById(R.id.tv_wea);
        tv_air_level = findViewById(R.id.tv_air_level);
        tv_air_tips = findViewById(R.id.tv_air_tips);
        tv_win_speed = findViewById(R.id.tv_win_speed);

        tv_name = findViewById(R.id.tv_name);
        tv_name.setText("" + sharedUtils.getData(SharedUtils.SECTION_NAME, ""));

        lineZhandain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popuDatas != null) {
                    showPopupWindow(popuDatas);
                } else {
                    ToastUtils.showBottomToast(MaterialMonitoringSystemActivity.this, "当前暂无数据展示");
                }

            }
        });


        rateRecycle = (RecyclerView) findViewById(R.id.id_material_recycler);
        rateRecycle.setItemViewCacheSize(100);
        rateRecycle.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        rateRecycle.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("" + "物料监测系统");
        }
    }

    @Override
    public void setSelect(List<StationData> dataList) {

        popuDatas.clear();
        if (dataList != null && dataList.size() > 0) {
            popuDatas = dataList;
            if (popuDatas.get(0).getId() > 0) {
                zhandianName.setText("" + popuDatas.get(0).getStation_name());
                presenter.getDeviceData(dataList.get(0).getGroupId());
            }
        }

    }

    @Override
    public void setDeviceData(List<DeviceData.RealData> deviceData) {
//        ToastUtils.showBottomToast(MaterialMonitoringSystemActivity.this, "数据返回成功" + deviceData.size());
        rateAdapter = new JianCeAdapter(MaterialMonitoringSystemActivity.this, deviceData);
        rateRecycle.setAdapter(rateAdapter);
    }

    /**
     * 物料检测数据 回调成功
     *
     * @param dataBeans
     */
    @Override
    public void setMonotorgSysEntity(MonitorgSysEntity.DataBean dataBeans) {
        if (dataBeans != null) {
            tv_pm25.setText(dataBeans.getPm25());
            tv_pm10.setText(dataBeans.getPm10());
            tv_noise.setText(dataBeans.getNoise());
            tv_tem.setText(dataBeans.getTem());
            tv_hum.setText(dataBeans.getHum());
            tv_wp.setText(dataBeans.getWd());
            tv_ws.setText(dataBeans.getWs());
            tv_wd.setText(dataBeans.getWd());
            tv_tsp.setText(dataBeans.getTsp());
            tv_atm.setText(dataBeans.getAtm());
        }
    }

    @Override
    public void setWeatherData(WeatherDataEntity.DataBean weatherData) {
        tv_date_week.setText(weatherData.getDate() + "(" + weatherData.getWeek() + ")");
        tv_wea.setText(weatherData.getWea());
        tv_air_level.setText(weatherData.getAir_level());
        tv_win_speed.setText(weatherData.getWin_speed());
        tv_air_tips.setText(weatherData.getAir_tips());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.distory();

        if (popupWindow != null) {
            popupWindow.dismiss();
        }

    }

    //=======
    private void showPopupWindow(List<StationData> datas) {
        View view = LayoutInflater.from(MaterialMonitoringSystemActivity.this).inflate(R.layout.view_popu_recyle, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.select);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MaterialMonitoringSystemActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        ZhandianAdapter scoreTeamAdapter = new ZhandianAdapter(MaterialMonitoringSystemActivity.this, datas);
        scoreTeamAdapter.setOnClick(this);
        recyclerView.setAdapter(scoreTeamAdapter);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(lineZhandain);
    }

    @Override
    public void checkZhandian(int position, int sectionId, String name) {
        zhandianName.setText("" + name);
        popupWindow.dismiss();
        if (popuDatas.get(position) != null) {
            presenter.getDeviceData(popuDatas.get(position).getGroupId());
        }
    }
}
