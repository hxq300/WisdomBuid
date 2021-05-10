package com.lsy.wisdombuid.activity;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.exam.TrainingCheckActivity;
import com.lsy.wisdombuid.activity.persion.PersonalDetailsActivity;
import com.lsy.wisdombuid.activity.persion.ProjectExperienceActivity;
import com.lsy.wisdombuid.activity.safety.SafetyManagementActivity;
import com.lsy.wisdombuid.base.BaseActivity;
import com.lsy.wisdombuid.fragment.MyFragment;
import com.lsy.wisdombuid.fragment.index.HomePageFragment;
import com.lsy.wisdombuid.fragment.index.MineFragment;
import com.lsy.wisdombuid.fragment.index.MoreFragment;
import com.lsy.wisdombuid.qr.QRActivity;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.QuanXian;
import com.lsy.wisdombuid.util.StatusBarUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener, QuanXian.OnPermission {


    private ArrayList<MyFragment> fragments;//主页碎片

    private LinearLayout lineOne, lineTwo, lineThree;
    private ImageView imgOne, imgTwo, imgThree;
    private TextView texOne, texTwo, texThree;

    private int open = 0;

    private final static int REQUEST_CODE = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }

        //判断权限是否全部打开
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_LOGS) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.SET_DEBUG_APP) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.SYSTEM_ALERT_WINDOW) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_APN_SETTINGS) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.CHANGE_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
        }

        initView();


    }

    private void initView() {

//        lineOne, lineTwo, lineThree;
        lineOne = findViewById(R.id.id_one_tab);
        lineTwo = findViewById(R.id.id_two_tab);
        lineThree = findViewById(R.id.id_three_tab);

//        imgOne, imgTwo, imgThree;
        imgOne = findViewById(R.id.id_one_img);
        imgTwo = findViewById(R.id.id_two_img);
        imgThree = findViewById(R.id.id_three_img);

//        texOne, texTwo, texThree;
        texOne = findViewById(R.id.id_one_tex);
        texTwo = findViewById(R.id.id_two_tex);
        texThree = findViewById(R.id.id_three_text);

        lineOne.setOnClickListener(this);
        lineTwo.setOnClickListener(this);
        lineThree.setOnClickListener(this);

        fragments = getFragments();
//        setDefaultFragment();
        setFragmentNum(open);


    }

    /**
     * 设置Fragment
     *
     * @return
     */
    private ArrayList<MyFragment> getFragments() {
        ArrayList<MyFragment> fragments = new ArrayList<>();
//        HomePageFragment homePageFragment = HomePageFragment.newInstance();
//        homePageFragment.setIntentIntegrator(new IntentIntegrator(MainActivity.this));
//        fragments.add(homePageFragment);
        fragments.add(HomePageFragment.newInstance());
        fragments.add(MoreFragment.newInstance());
        fragments.add(MineFragment.newInstance());
        return fragments;
    }

    /**
     * 设置选中的Fragment
     */
    private MyFragment now_fragment;

    private void setFragmentNum(int num) {
        FragmentManager fm = getSupportFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        now_fragment = null;
        switch (num) {
            case 0:
                now_fragment = fragments.get(0);

                texOne.setTextColor(getResources().getColor(R.color.tab_focuse));
                texTwo.setTextColor(getResources().getColor(R.color.three));
                texThree.setTextColor(getResources().getColor(R.color.three));

                imgOne.setImageResource(R.mipmap.tab_home_blue);
//                imgTwo.setImageResource(R.mipmap.tab_more);
                imgThree.setImageResource(R.mipmap.tab_mine);

                break;

            case 1:
                now_fragment = fragments.get(1);
                texOne.setTextColor(getResources().getColor(R.color.three));
                texTwo.setTextColor(getResources().getColor(R.color.tab_focuse));
                texThree.setTextColor(getResources().getColor(R.color.three));

                imgOne.setImageResource(R.mipmap.tab_home);
//                imgTwo.setImageResource(R.mipmap.tab_more_blue);
                imgThree.setImageResource(R.mipmap.tab_mine);
                break;

            case 2:
                now_fragment = fragments.get(2);
                texOne.setTextColor(getResources().getColor(R.color.three));
                texTwo.setTextColor(getResources().getColor(R.color.three));
                texThree.setTextColor(getResources().getColor(R.color.tab_focuse));

                imgOne.setImageResource(R.mipmap.tab_home);
//                imgTwo.setImageResource(R.mipmap.tab_more);
                imgThree.setImageResource(R.mipmap.tab_mine_blue);
                break;

            default:
                break;
        }
        // 使用当前Fragment的布局替代id_content的控件
        transaction.replace(R.id.layFrame, now_fragment);
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_one_tab:
                //第一个页面
                setFragmentNum(0);
                break;

            case R.id.id_two_tab:
                setFragmentNum(1);
                break;

            case R.id.id_three_tab:
                //第三个页面
                setFragmentNum(2);
                break;

            default:
                break;
        }
    }

    //我的事件
    public void persionData(View view) {
        switch (view.getId()) {

            case R.id.mine_persion_data://个人资料
                Intent persionData = new Intent(MainActivity.this, PersionDataActivity.class);
                startActivity(persionData);
                break;

            case R.id.mine_persion_data_line://个人资料
                Intent pData = new Intent(MainActivity.this, PersionDataActivity.class);
                startActivity(pData);
                break;

            case R.id.mine_my_message://我的消息
                Intent myMessage = new Intent(MainActivity.this, MyMessageActivity.class);
                startActivity(myMessage);
                break;

            case R.id.mine_clean_cache://清除缓存
                Intent cleanCache = new Intent(MainActivity.this, ClearCacheActivity.class);
                startActivity(cleanCache);
                break;

            case R.id.mine_system_setting://系统设置
                Intent sysSetting = new Intent(MainActivity.this, SysSettingActivity.class);
                startActivity(sysSetting);
                break;

            case R.id.mine_about_us://关于我们
                Intent aboutUs = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(aboutUs);
                break;

            case R.id.mine_invite_friends://邀请好友
                Intent inFriends = new Intent(MainActivity.this, InviteFriendsActivity.class);
                startActivity(inFriends);
                break;

            default:
                break;
        }
    }

    public static void reStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    // TODO 首页点击事件
    public void indexBtn(View view) {
        switch (view.getId()) {

            case R.id.index_scan://二维码扫描
                //打开扫描界面
                IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setCaptureActivity(QRActivity.class); // 设置自定义的activity是QRActivity
                intentIntegrator.setRequestCode(REQUEST_CODE);
                intentIntegrator.initiateScan();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(resultCode, data);
            final String qrContent = scanResult.getContents();
            L.log("scan", "扫描结果" + qrContent);

            //{"type":1,"id":1}
            Gson gson = new Gson();

            if (qrContent != null) {
                try {
                    JSONObject jsonObject = new JSONObject(qrContent);

                    int type = jsonObject.getInt("type");
                    int id = jsonObject.getInt("id");


                    if (type == 1) {
                        Intent peixun = new Intent();
                        peixun.putExtra("id", id);
                        peixun.setClass(this, ProjectExperienceActivity.class);
                        startActivity(peixun);
                    } else if (type == 2) {
                        int staff_id = jsonObject.getInt("staff_id");
                        Intent peixun = new Intent();
                        peixun.putExtra("staff_id", staff_id);
                        peixun.setClass(this, PersonalDetailsActivity.class);
                        startActivity(peixun);
                    } else {
                        Toast.makeText(MainActivity.this, "扫描结果:" + qrContent, Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }
        else if (requestCode == 1002) {
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
                    } else if (type == 2) { //钢筋
                        Intent intent = new Intent(this, RebarActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    } else if (type == 3) {//制梁
                        Intent intent = new Intent(this, FabricateBeamActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    /**
     * 请求权限
     */
    private void requestPermissions() {
        QuanXian quanXian = new QuanXian(MainActivity.this, MainActivity.this);
        quanXian.setOnPermission_isok(this);
        quanXian.requestPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        );
    }

    @Override
    public void one_permission_isok(String permission_name) {

    }

    @Override
    public void one_permission_is_refuse(String permission_name) {

    }

    @Override
    public void one_permission_is_refuse_no_prompt(String permission_name) {

    }

    //首页点击事件
    public void homeClick(View view) {
        switch (view.getId()) {

            case R.id.home_renyuanguanli://人员管理
                Intent renyuan = new Intent(MainActivity.this, PersonnelManagementActivity.class);
                startActivity(renyuan);
                break;

            case R.id.home_anquanguanlixitong://安全管理系统
                Intent safety = new Intent(MainActivity.this, SafetyManagementActivity.class);
                startActivity(safety);
                break;

            case R.id.home_peixunkaohe://培训考核
                Intent kaohe = new Intent(MainActivity.this, TrainingCheckActivity.class);
                startActivity(kaohe);
                break;

            default:
                break;
        }
    }
}
