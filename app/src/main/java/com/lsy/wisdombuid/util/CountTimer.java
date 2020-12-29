package com.lsy.wisdombuid.util;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by lsy on 2020/4/16
 * todo :
 */
public class CountTimer {

    private TextView textView;

    private int times = 0;

    public CountTimer(TextView textView, int time) {
        this.textView = textView;
        this.times = time;

        if (times > 0) {

        } else {
            times = 45;
        }

        timer.start();
    }

    /**
     * 倒数计时器
     */
    private CountDownTimer timer = new CountDownTimer(times * 60 * 1000, 1000) {
        /**
         * 固定间隔被调用,就是每隔countDownInterval会回调一次方法onTick
         * @param millisUntilFinished
         */
        @Override
        public void onTick(long millisUntilFinished) {
            textView.setText(formatTime(millisUntilFinished));
        }

        /**
         * 倒计时完成时被调用
         */
        @Override
        public void onFinish() {
            textView.setText("00:00");
        }
    };

    /**
     * 将毫秒转化为 分钟：秒 的格式
     *
     * @param millisecond 毫秒
     * @return
     */
    public String formatTime(long millisecond) {
        int minute;//分钟
        int second;//秒数
        minute = (int) ((millisecond / 1000) / 60);
        second = (int) ((millisecond / 1000) % 60);
        if (minute < 10) {
            if (second < 10) {
                return "0" + minute + ":" + "0" + second;
            } else {
                return "0" + minute + ":" + second;
            }
        } else {
            if (second < 10) {
                return minute + ":" + "0" + second;
            } else {
                return minute + ":" + second;
            }
        }
    }

    /**
     * 取消倒计时
     */
    public void timerCancel() {
        timer.cancel();
    }

    /**
     * 开始倒计时
     */
    public void timerStart() {
        timer.start();
    }

}
