package com.lsy.wisdombuid.activity.safety;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.lsy.wisdombuid.R;

import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.CarGpsMessageEntity;
import com.lsy.wisdombuid.bean.CarIdEntity;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.util.SharedUtils;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.widget.TitleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hxq on 2021/1/10
 * describe :  TODO
 */
public class GPSActivity extends MyBaseActivity {


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    getCarIds();
                    break;
            }
        }
    };
    private MapView mapView;
    private BaiduMap mBaiduMap;
    private Timer mTimer;
    private TitleBar titleBar;

    private String carIds = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        initView();
        initTitle();
    }

    private void initView() {
        mapView = findViewById(R.id.mapView);
        titleBar = findViewById(R.id.title_bar);
        mBaiduMap = mapView.getMap();
        // 缩放
        MapStatusUpdate msu_ = MapStatusUpdateFactory.zoomTo(18.0f);
        mBaiduMap.setMapStatus(msu_);

        MapStatusUpdate msu = MapStatusUpdateFactory
                .newLatLng(new LatLng(33.337904, 115.181893));
        mBaiduMap.setMapStatus(msu);

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                mHandler.sendMessage(message);
            }
        }, 0, 1000 * 30);


    }

    /**
     * 获取 car id
     */

    private void  getCarIds(){
        SharedUtils sharedUtils = new SharedUtils(this, SharedUtils.WISDOM);
        String token = sharedUtils.getData(SharedUtils.GPS);
        if (null != token && !"".equals(token)) {
            Map<String, Object> listcanshu = new HashMap<>();
            OKHttpClass okHttpClass = new OKHttpClass();
            //设置请求类型、地址和参数
            okHttpClass.setPostYesToken(this, "http://www.gpsnow.net/car/getByUserId.do?", token, listcanshu,carIds);

            okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
                @Override
                public String requestData(String dataString) {
                    Gson gson = new Gson();
                    CarIdEntity carIdEntity = gson.fromJson(dataString, CarIdEntity.class);
                    for (int i = 0; i < carIdEntity.getData().size(); i++) {
                        if (i == carIdEntity.getData().size() - 1){
                            carIds = carIds+carIdEntity.getData().get(i).getCarId();
                        }else {
                            carIds = carIds+carIdEntity.getData().get(i).getCarId()+",";
                        }
                    }
                    Log.d("testTest", "requestData: " + carIds);
                    getGPSMessage(carIds);
                    return dataString;
                }
            });
        }
    }

    // 根据 车辆carId 获取 车辆的坐标
    private void getGPSMessage(String id) {
        SharedUtils sharedUtils = new SharedUtils(this, SharedUtils.WISDOM);
        String token = sharedUtils.getData(SharedUtils.GPS);
        int userId = sharedUtils.getIntData(SharedUtils.GPS_USER_ID);
        if (null != token && !"".equals(token)) {
            Map<String, Object> listcanshu = new HashMap<>();
            listcanshu.put("userId",userId+"");
            listcanshu.put("carIds",id);
            OKHttpClass okHttpClass = new OKHttpClass();
            //设置请求类型、地址和参数
            okHttpClass.setPostYesToken(this, "http://www.gpsnow.net/carStatus/getByCarIds.do?", token, listcanshu,carIds);

            okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
                @Override
                public String requestData(String dataString) {
                    if (dataString.length() < 100)
                        return dataString;
                    try {
                        Gson gson = new Gson();
                        CarGpsMessageEntity carGpsMessageEntity = gson.fromJson(dataString, CarGpsMessageEntity.class);
                        List<CarGpsMessageEntity.DataBean> data = carGpsMessageEntity.getData();
                        mBaiduMap.clear();
                        Resources res = getResources();
                        BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
                        Bitmap bitmap = BitmapFactory
                                .decodeResource(res, R.drawable.car_icon);

                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        // 计算缩放比例
                        float scaleWidth = ((float) width * 3) / width;
                        float scaleHeight = ((float) height * 3) / height;
                        // 取得想要缩放的matrix参数
                        Matrix matrix = new Matrix();
                        matrix.postScale(scaleWidth, scaleHeight);
                        // 得到新的图片
                        Bitmap newbm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
                        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(newbm);

                        ArrayList<OverlayOptions> overlayOptions = new ArrayList<>();
                        for (CarGpsMessageEntity.DataBean datum : data) {
                            overlayOptions.add(
                                    new MarkerOptions().position(new LatLng(datum.getLatc(), datum.getLonc()))
                                            .icon(bitmapDescriptor)
                            );
                        }
                        mBaiduMap.addOverlays(overlayOptions);
                    } catch (Exception e) {

                    }

                    return dataString;
                }
            });
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null)
            mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mapView != null)
            mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        if (mapView != null)
            mapView.onDestroy();
    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {
        } else {
            titleBar.setTitle("车辆定位");
        }
    }
}
