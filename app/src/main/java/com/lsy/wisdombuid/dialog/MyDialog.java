package com.lsy.wisdombuid.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by lsy on 2020/3/20
 * todo :
 */
public class MyDialog extends Dialog {

    private int layout;

    public MyDialog(Context context, int theme, int layout) {
        super(context, theme);
        this.layout = layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout);
    }
}
