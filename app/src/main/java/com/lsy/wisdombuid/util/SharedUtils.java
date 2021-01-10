package com.lsy.wisdombuid.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Create by lsy on 2019/12/04
 * MODO :
 */
public class SharedUtils {
    // 地图 token
    public static final String GPS = "gps";
    public static final String GPS_USER_ID = "gpsUserId";
    //文件名
    public static final String WISDOM = "WisdomDate";

    //
//    public static final String ONML = "section";
    public static final String TOKEN = "section";//标段ID
    public static final String JOB_ID = "position_id";//职务ID
    public static final String SECTION_NAME = "section_name";//标段名称
    public static final String USER_PHONE = "user_phone";//
    public static final String USER_PASS = "user_pass";//
    public static final String USER_NAME = "user_name";//
    public static final String USER_NICKNAME = "user_nickname";//
    public static final String USER_PIC = "user_pic";//
    public static final String USER_IMAGE = "user_image";//
    public static final String USER_ID = "user_id";//
    public static final String SUB_ID = "sub_id";//分包单位ID
    public static final String STATION = "station_id";//


    public static final String IRDETAILS = "ins_record_details";//检查记录详情


    private Context context;
    private SharedPreferences share;
    private SharedPreferences.Editor edit;

    public SharedUtils(Context context, String filename) {
        if (context == null) {
            return;
        }
        this.context = context;
        //指定操作的文件名称
        share = context.getSharedPreferences(filename, MODE_PRIVATE);
        //编辑文件
        edit = share.edit();
    }

    public void setData(String key, String value) {
        try {
            if (edit != null) {
                edit.putString(key, value);
                edit.commit();    //保存数据信息
            }
        } catch (Exception e) {

        }
    }

    public void setBoolean(String key, boolean value) {
        try {
            if (edit != null) {
                edit.putBoolean(key, value);
                edit.commit();    //保存数据信息
            }
        } catch (Exception e) {

        }
    }

    public void setData(String key, int value) {
        try {
            if (edit != null) {
                edit.putInt(key, value);
                edit.commit();    //保存数据信息
            }
        } catch (Exception e) {

        }
    }

    public String getData(String key) {
        String data = share.getString(key, "");
        return data;
    }

    public int getIntData(String key) {
        int data = share.getInt(key, 0);
        return data;
    }

    public String getData(String key, String moren) {
        String data = share.getString(key, moren);
        return data;
    }

    public boolean getBoolean(String key) {
        boolean data = share.getBoolean(key, false);
        return data;
    }

    public void remove_data() {
        share.edit().clear().commit();
        //用户推出以后，设置别名为空
//        setJpushBieMing("");
    }

    /**
     * 设置激光推送的别名
     */
//    private void setJpushBieMing(String user_id) {
//        //设置别名，一对一推送使用
//        BieMing bieMing = new BieMing(context);
//        bieMing.setAlias(user_id);
//    }

}
