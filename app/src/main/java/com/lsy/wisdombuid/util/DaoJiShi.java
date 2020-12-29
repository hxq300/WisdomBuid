package com.lsy.wisdombuid.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Create by lsy on 2019/12/04
 * MODO : 倒计时工具类
 */
public class DaoJiShi {
    private TextView textView;
    private Context context;

    private SharedPreferences shared;
    private String Key;

    public DaoJiShi(Context context, TextView textView, String key) {
        this.textView = textView;
        this.context = context;
        Key = key;
        shared = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        getTime();
    }

    public void Jishi() {
        SaveTime();
        Jishi("");
    }

    private Timer timer;

    public void Jishi(String n) {
        if (time > 60) {
            time = 60L;
        }
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Message message = new Message();
                    handler.sendMessage(message);
                }
            }, 0, 1000);
        }
    }


    private long time = 60L;

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time--;
            textView.setText(time + "s");
//            SaveTime();
            if (time == 0L) {
                textView.setText("获取验证码");
                if (timer != null) {
                    timer.cancel();
                    timer.purge();
                    timer = null;
                }
                time = 60L;
            }
        }
    };


    /**
     * 获取剩余时间
     *
     * @return
     */
    public void getTime() {
        long stime = 0;
        long now_time = System.currentTimeMillis() / 1000;
        stime = shared.getLong(Key, 0);
        if (now_time - stime > 60) {
            time = 60;
        } else {
            time = 60 - (now_time - stime);
            Jishi("");
        }
    }

    private void SaveTime() {
        SharedPreferences.Editor edit = shared.edit(); //编辑文件
        edit.putLong(Key, System.currentTimeMillis() / 1000);
        edit.commit();    //保存数据信息
    }

}

