package com.lsy.wisdombuid.tools;

import android.util.Log;

public class L {
    public static boolean isDebug = true;

    public static void log(String title, String msg) {
        if (isDebug) {
            Log.e(title, msg);
        }
    }

    public static void log(String msg) {
        if (isDebug) {
            Log.e("打印数值：", msg);
        }

    }
}
