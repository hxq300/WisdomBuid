package com.lsy.wisdombuid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.BaseActivity;
import com.lsy.wisdombuid.activity.login.LoginActivity;
import com.lsy.wisdombuid.bean.ImmediatelyGpsLoginEntity;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.SPUtil;
import com.lsy.wisdombuid.util.SharedUtils;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.net.OkHttpManager;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by lsy on 2020/3/16
 * todo : 闪屏页
 */
public class FlashActivity extends BaseActivity {

    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }

        handler = new Handler();

        initView();
        // 立即定位登录
        loginImmediatelyGps();
    }

    private void loginImmediatelyGps() {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        //设置请求类型、地址和参数
        okHttpClass.setPostNoToken("http://www.gpsnow.net/user/login.do?name=pxgs2020&password=a12345", listcanshu);

        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                if (dataString.length() <100)
                    return dataString;
                try {
                    Gson gson = new Gson();
                    ImmediatelyGpsLoginEntity immediatelyGpsLoginEntity = gson.fromJson(dataString, ImmediatelyGpsLoginEntity.class);
                    SharedUtils sharedUtils = new SharedUtils(FlashActivity.this, SharedUtils.WISDOM);
                    sharedUtils.setData(sharedUtils.GPS, immediatelyGpsLoginEntity.getData().getToken());//标段ID
                    sharedUtils.setData(sharedUtils.GPS_USER_ID, immediatelyGpsLoginEntity.getData().getUserId());//标段ID
                } catch (Exception e) {
                }


                return dataString;
            }
        });


    }

    private void initView() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (OKHttpClass.getToken(FlashActivity.this) == 0) {
                    //如果用户是第一次启动
                    Intent toLogin = new Intent(FlashActivity.this, LoginActivity.class);
                    startActivity(toLogin);
                    finish();
                } else {
                    Intent tomain = new Intent(FlashActivity.this, MainActivity.class);
                    startActivity(tomain);
                    finish();
                }
            }
        }, 2000);

    }

    public static void reStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
