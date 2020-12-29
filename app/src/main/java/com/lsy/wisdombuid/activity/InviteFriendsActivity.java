package com.lsy.wisdombuid.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.util.QuanXian;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.ToastUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.Map;

/**
 * Created by lsy on 2020/3/19
 * todo : 邀请好友
 */
public class InviteFriendsActivity extends MyBaseActivity implements QuanXian.OnPermission {

    private UMShareAPI umShareAPI;
    private UMShareListener umShareListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friends);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

        //判断权限是否全部打开
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_LOGS) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.SET_DEBUG_APP) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.SYSTEM_ALERT_WINDOW) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_APN_SETTINGS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
        }


        initView();

    }

    private void initView() {

        umShareAPI = UMShareAPI.get(this);
//        umShareAPI.getPlatformInfo(this, SHARE_MEDIA.QQ, umShareListener);//QQ登录
//        umShareAPI.deleteOauth(this, SHARE_MEDIA.QQ, umShareListener);//撤销QQ授权
//
//        umShareListener = new UMAuthListener() {
//            @Override
//            public void onStart(SHARE_MEDIA platform) {}
//            @Override
//            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
//                // Logger.e("openid: " + data.get("uid"));
//                // Logger.e("昵称: " + data.get("name"));
//                // Logger.e("头像: " + data.get("iconurl"));
//                // Logger.e("性别: " + data.get("gender"));
//            }
//
//            @Override
//            public void onError(SHARE_MEDIA platform, int action, Throwable t) {
//
//            }
//
//            @Override
//            public void onCancel(SHARE_MEDIA platform, int action) {
//
//            }
//        };


        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                // 分享开始的回调
            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
                ToastUtils.showBottomToast(InviteFriendsActivity.this, platform + " 分享成功啦");
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                ToastUtils.showBottomToast(InviteFriendsActivity.this, platform + " 分享失败啦");
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                ToastUtils.showBottomToast(InviteFriendsActivity.this, platform + " 分享取消了");
            }
        };
    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {
        } else {
            titleBar.setTitle("" + getString(R.string.invite_friends));
        }
    }


    //分享事件
    public void share(View view) {
        UMImage image = new UMImage(this, R.mipmap.ic_launcher);//分享图标
        final UMWeb web = new UMWeb("https://www.baidu.com/"); //切记切记 这里分享的链接必须是http开头
        web.setTitle("上海智慧工地");//标题
        web.setThumb(image);  //缩略图
        web.setDescription("帮助建设单位、施工方或工地管理者快速搭建标准化或定制化管理平台，实现工地科技智慧管理，高效节能，构建建筑工地管理新生态。通过24 小时/7 天无限制的单个或多个项目同时管控，高质量数据并支持多种业务模式，实现大数据应用分析，更加有效、有针对性的管理。");//描述

        switch (view.getId()) {

            case R.id.invite_wiexin://微信
                new ShareAction(InviteFriendsActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
                        .withMedia(web)
                        .setCallback(umShareListener)
                        .share();
                break;
            case R.id.invite_qq://QQ
                ToastUtils.showBottomToast(InviteFriendsActivity.this, "QQ暂无法分享");
//                new ShareAction(InviteFriendsActivity.this).setPlatform(SHARE_MEDIA.QQ)
//                        .withMedia(web)
//                        .setCallback(umShareListener)
//                        .share();
                break;
            case R.id.invite_weibo://微博
                new ShareAction(InviteFriendsActivity.this).setPlatform(SHARE_MEDIA.SINA)
                        .withMedia(web)
                        .setCallback(umShareListener)
                        .share();
                break;

            default:
                break;
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void one_permission_isok(String permission_name) {

    }

    @Override
    public void one_permission_is_refuse(String permission_name) {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }

    @Override
    public void one_permission_is_refuse_no_prompt(String permission_name) {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_LOGS,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.GET_ACCOUNTS,
                    Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }


    /**
     * 请求权限
     */
    private void requestPermissions() {
        QuanXian quanXian = new QuanXian(InviteFriendsActivity.this, InviteFriendsActivity.this);
        quanXian.setOnPermission_isok(this);
        quanXian.requestPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_LOGS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.SET_DEBUG_APP,
                Manifest.permission.SYSTEM_ALERT_WINDOW,
                Manifest.permission.GET_ACCOUNTS,
                Manifest.permission.WRITE_APN_SETTINGS
        );
    }


}
