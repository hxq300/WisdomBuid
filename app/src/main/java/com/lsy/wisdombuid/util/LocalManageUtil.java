package com.lsy.wisdombuid.util;

import android.content.Context;
import android.content.res.Configuration;

import com.github.jokar.multilanguages.library.MultiLanguage;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.tools.L;

import java.util.Locale;

/**
 * Created by lsy on 2020/3/18
 * todo : 语言切换工具
 */
public class LocalManageUtil {

    private static final String TAG = "LocalManageUtil";

    /**
     * 获取系统的locale
     *
     * @return Locale对象
     */
    public static Locale getSystemLocale(Context context) {
        return SPUtil.getInstance(context).getSystemCurrentLocal();
    }

    public static String getSelectLanguage(Context context) {
        switch (SPUtil.getInstance(context).getSelectLanguage()) {
            case 0:
                return context.getString(R.string.system_default);
            case 1:
                return context.getString(R.string.simplified_chinese);
            case 2:
                return context.getString(R.string.chinese_traditional);
            case 3:
                return context.getString(R.string.american_english);
            default:
                return context.getString(R.string.system_default);
        }
    }

    /**
     * 获取选择的语言设置
     *
     * @param context
     * @return
     */
    public static Locale getSetLanguageLocale(Context context) {

        if (SPUtil.getInstance(context).getSelectLanguage() == 0) {
            L.log("language", "language==getSystemLocale");
            return getSystemLocale(context);
        } else if (SPUtil.getInstance(context).getSelectLanguage() == 1) {
            L.log("language", "language==getSystemLocale");
            return Locale.CHINA;
        } else if (SPUtil.getInstance(context).getSelectLanguage() == 2) {
            return Locale.TAIWAN;
        } else if (SPUtil.getInstance(context).getSelectLanguage() == 3) {
            return Locale.ENGLISH;
        } else {
            return getSystemLocale(context);
        }

    }


    public static void saveSystemCurrentLanguage(Context context) {
        SPUtil.getInstance(context).setSystemCurrentLocal(MultiLanguage.getSystemLocal(context));
    }

    /**
     * 保存系统语言
     *
     * @param context
     * @param newConfig
     */
    public static void saveSystemCurrentLanguage(Context context, Configuration newConfig) {

        SPUtil.getInstance(context).setSystemCurrentLocal(MultiLanguage.getSystemLocal(newConfig));
    }

    public static void saveSelectLanguage(Context context, int select) {
        SPUtil.getInstance(context).saveLanguage(select);
        MultiLanguage.setApplicationLanguage(context);
    }
}

