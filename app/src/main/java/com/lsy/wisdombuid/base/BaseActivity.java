package com.lsy.wisdombuid.base;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.github.jokar.multilanguages.library.MultiLanguage;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(newBase);
        super.attachBaseContext(MultiLanguage.setLocal(newBase));
    }
}
