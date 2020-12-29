package com.lsy.wisdombuid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.login.LoginActivity;
import com.lsy.wisdombuid.activity.login.LostPasswordActivity;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.UserData;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.SharedUtils;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lsy on 2020/3/19
 * todo : 账号管理
 */
public class ITunesActivity extends MyBaseActivity {

    @BindView(R.id.account_head_img)
    ImageView accountHeadImg;
    @BindView(R.id.account_nick_name)
    TextView accountNickName;
    @BindView(R.id.account_phone)
    TextView accountPhone;

//    //消息列表
//    private RecyclerView idListRecycle;
//    private AccountAdapter listAdapter;

    private SharedUtils sharedUtils;

    private int userId = 0;
    private String phone = "";
    private String password = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_agreement);
        ButterKnife.bind(this);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();


        sharedUtils = new SharedUtils(this, SharedUtils.WISDOM);
        userId = sharedUtils.getIntData(SharedUtils.USER_ID);
        phone = sharedUtils.getData(SharedUtils.USER_PHONE);
        password = sharedUtils.getData(SharedUtils.USER_PASS);


        //初始化view
//        initView();
//
        //初始化数据
        getDataPersion();
    }


    //获取个人资料
    private void getDataPersion() {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        listcanshu.put("id", userId);

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(this, RequestURL.perInformation, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
//                L.log("userInformation", "perInformation= 个人资料=" + dataString);
                Gson gson = new Gson();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);
                    int code = jsonObject.getInt("code");
                    String data = jsonObject.getString("data");
                    String message = jsonObject.getString("message");

                    if (code == 200 && data != null) {
                        UserData userData = gson.fromJson(data, UserData.class);

//                        pSex, pAge, pPhone, pBiaoduan, pZhandian, pDanwei;

                        if (userData.getStaff_phone() != null) {
                            accountPhone.setText("" + userData.getStaff_phone());
                        }

                        if (userData.getNikename() != null) {
                            accountNickName.setText(userData.getNikename());
                        } else if (userData.getStaff_name() != null) {
                            accountNickName.setText(userData.getStaff_name());
                        }

                        if (userData.getPicture() != null) {

                            Glide.with(ITunesActivity.this).load(RequestURL.OssUrl + userData.getPicture())
                                    .error(R.mipmap.mine_head)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(accountHeadImg);

                        } else if (userData.getStaff_img() != null) {
                            Glide.with(ITunesActivity.this).load(RequestURL.RequestImg + userData.getStaff_img())
                                    .error(R.mipmap.mine_head)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(accountHeadImg);
                        }

                    } else {
                        ToastUtils.showBottomToast(ITunesActivity.this, "" + message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });
    }


//    private void initView() {
//
//        //===
//        idListRecycle = (RecyclerView) findViewById(R.id.recycler_account);
//        idListRecycle.setItemViewCacheSize(100);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ITunesActivity.this);
//        idListRecycle.setLayoutManager(linearLayoutManager);
//        idListRecycle.setNestedScrollingEnabled(false);
//
//    }

//    private void initData() {
//
//        List<String> messages=new ArrayList<>();
//
//        for (int i = 0; i < 4; i++) {
//            messages.add("1300000000"+i);
//        }
//
//        listAdapter = new AccountAdapter(ITunesActivity.this, messages);
//        idListRecycle.setAdapter(listAdapter);
//    }


    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("" + getString(R.string.account_agreement));
        }
    }


    //修改密码
    public void updatePass(View view) {
        Intent updatePass = new Intent();
        updatePass.putExtra("title", "修改密码");
        updatePass.setClass(ITunesActivity.this, LostPasswordActivity.class);
        startActivity(updatePass);
    }

    //重新登录
    public void reLogin(View view) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        listcanshu.put("staff_phone", "" + phone);
        listcanshu.put("password", "" + password);

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(ITunesActivity.this, RequestURL.login, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("login", "" + dataString);
                Gson gson = new Gson();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);
                    int code = jsonObject.getInt("code");
                    String data = jsonObject.getString("data");
                    String message = jsonObject.getString("message");

                    if (code == 200 && data != null) {
                        UserData userData = gson.fromJson(data, UserData.class);

                        saveUserMsg(userData);

                        finish();

//                        presenter.responseManager(userData);

                    } else {
                        ToastUtils.showBottomToast(ITunesActivity.this, "" + message);
                    }

//                            saveUserMsg(loginData);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return dataString;
            }
        });
    }


    /**
     * 保存用户id
     */
    private void saveUserMsg(UserData managerData) {
        SharedUtils sharedUtils = new SharedUtils(ITunesActivity.this, SharedUtils.WISDOM);
        sharedUtils.setData(sharedUtils.TOKEN, managerData.getSection_id());//标段ID
        sharedUtils.setData(sharedUtils.JOB_ID, managerData.getPosition_id());//标段ID
        sharedUtils.setData(sharedUtils.SECTION_NAME, managerData.getSection_name());//标段名称
        sharedUtils.setData(sharedUtils.USER_PHONE, managerData.getStaff_phone());//
        sharedUtils.setData(sharedUtils.USER_PASS, managerData.getPassword());//
        if (managerData.getNikename() != null) {
            sharedUtils.setData(sharedUtils.USER_NICKNAME, managerData.getNikename());//
        } else if (managerData.getStaff_name() != null) {
            sharedUtils.setData(sharedUtils.USER_NAME, managerData.getStaff_name());//
        }

        if (managerData.getPicture() != null) {
            sharedUtils.setData(sharedUtils.USER_IMAGE, managerData.getPicture());//
        } else if (managerData.getStaff_img() != null) {
            sharedUtils.setData(sharedUtils.USER_PIC, managerData.getStaff_img());//
        }

        sharedUtils.setData(sharedUtils.USER_ID, managerData.getId());//
        sharedUtils.setData(sharedUtils.SUB_ID, managerData.getSub_id());//
        sharedUtils.setData(sharedUtils.STATION, managerData.getStation_id());//

//        sharedUtils.setBoolean(sharedUtils.COMSTATUS, data.isComStatus());
    }

    //退出登录
    public void appExist(View view) {

        sharedUtils.remove_data();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
