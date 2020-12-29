package com.lsy.wisdombuid.qr;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.View;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.BaseActivity;
import com.lsy.wisdombuid.util.StatusBarUtil;

/**
 * Created by lsy on 2020/3/20
 * todo :
 */
public class QRActivity extends BaseActivity {

    private DecoratedBarcodeView mDBV;
    private CaptureManager captureManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }

        mDBV = (DecoratedBarcodeView) findViewById(R.id.dbv);

        captureManager = new CaptureManager(this, mDBV);
        captureManager.initializeFromIntent(getIntent(), savedInstanceState);
        captureManager.decode();
    }

    private View.OnClickListener BackListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        captureManager.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mDBV.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }

    public void back(View view) {
        finish();
    }
}