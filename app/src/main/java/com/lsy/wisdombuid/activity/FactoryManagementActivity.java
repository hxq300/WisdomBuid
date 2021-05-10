package com.lsy.wisdombuid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;


import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.lsy.wisdombuid.qr.QRActivity;
import com.lsy.wisdombuid.tools.L;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Author: a
 * @Date: 2021/5/9 18:12
 * @Description: TODO         //无用//    调用     调试
 */
public class FactoryManagementActivity extends Activity {
    private final static int REQUEST_CODE = 1002;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentIntegrator intentIntegrator = new IntentIntegrator(FactoryManagementActivity.this);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setCaptureActivity(QRActivity.class); // 设置自定义的activity是QRActivity
        intentIntegrator.setRequestCode(REQUEST_CODE);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1002) {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(resultCode, data);
            final String qrContent = scanResult.getContents();
            L.log("scan", "扫描结果" + qrContent);

            Gson gson = new Gson();

            if (qrContent != null) {
                try {
                    JSONObject jsonObject = new JSONObject(qrContent);
                    int type = jsonObject.getInt("type");
                    int id = jsonObject.getInt("id");
                    if (type == 1) { // 存梁区
                        Intent intent = new Intent(this, SaveBeamActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        finish();
                    } else if (type == 2) { //钢筋
                        Intent intent = new Intent(this, RebarActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        finish();
                    } else if (type == 3) {//制梁
                        Intent intent = new Intent(this, FabricateBeamActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
      //  System.exit(0);
        finish();
    }
}

 
