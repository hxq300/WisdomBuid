package com.lsy.wisdombuid.activity.integral;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.MyMessageActivity;
import com.lsy.wisdombuid.adapter.IntegralSubsidiaryAdapter;
import com.lsy.wisdombuid.adapter.MessageAdapter;
import com.lsy.wisdombuid.base.BaseActivity;
import com.lsy.wisdombuid.bean.IntegralData;
import com.lsy.wisdombuid.bean.MessageData;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.SharedUtils;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsy on 2020/3/20
 * todo : 我的积分
 */
public class MyIntegralActivity extends BaseActivity {

    //消息列表
    private RecyclerView idListRecycle;
    private IntegralSubsidiaryAdapter listAdapter;

    private int pageNum = 1;

    private SharedUtils sharedUtils;

    private List<IntegralData> dataList = new ArrayList<>();

    private TextView intergral_all;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_integral);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }

        sharedUtils = new SharedUtils(this, SharedUtils.WISDOM);
        //初始化view
        initView();

        getFindAnintegral();
    }

    private void initView() {

        intergral_all = findViewById(R.id.intergral_all);

        //===
        idListRecycle = (RecyclerView) findViewById(R.id.recycler_mingxi);
        idListRecycle.setItemViewCacheSize(100);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyIntegralActivity.this);
        idListRecycle.setLayoutManager(linearLayoutManager);
        idListRecycle.setNestedScrollingEnabled(false);
    }

    //返回按钮
    public void back(View view) {
        finish();
    }

    //刷新
    public void refresh(View view) {
        ToastUtils.showBottomToast(MyIntegralActivity.this, "数据正在刷新");
        getFindAnintegral();
    }


    //查询积分明细
    private void getFindAnintegral() {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        //{"pageNo":1,"pageSize":10,"staff_id":3}
        listcanshu.put("pageNo", pageNum);//页数
        listcanshu.put("pageSize", 10);//每页返回的条数
        listcanshu.put("staff_id", sharedUtils.getIntData(SharedUtils.USER_ID));//用户ID

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(this, RequestURL.findAnintegral, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("shop", "findAnintegral=积分明细=" + dataString);
                Gson gson = new Gson();

                if (pageNum == 1) {
                    dataList.clear();
                }

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

                    int jifen = jsonObject.getInt("end_integral");

                    intergral_all.setText("" + jifen);

                    JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("items").toString());

                    for (int i = 0; i < jsonArray.length(); i++) {
                        IntegralData data = gson.fromJson(jsonArray.get(i).toString(), IntegralData.class);
                        dataList.add(data);
                    }

                    listAdapter = new IntegralSubsidiaryAdapter(MyIntegralActivity.this, dataList);
                    idListRecycle.setAdapter(listAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });
    }


}
