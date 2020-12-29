package com.lsy.wisdombuid.activity.integral;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.MyMessageActivity;
import com.lsy.wisdombuid.adapter.IntegralSubsidiaryAdapter;
import com.lsy.wisdombuid.adapter.InteralShopAdapter;
import com.lsy.wisdombuid.adapter.MessageAdapter;
import com.lsy.wisdombuid.adapter.RecordAdapter;
import com.lsy.wisdombuid.base.BaseActivity;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.MessageData;
import com.lsy.wisdombuid.bean.RecordData;
import com.lsy.wisdombuid.dialog.IntegralDialog;
import com.lsy.wisdombuid.dialog.RecordDialog;
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
 * todo : 兑换记录
 */
public class ForRecordActivity extends MyBaseActivity implements RecordAdapter.OnClick {

    //消息列表
    private RecyclerView idListRecycle;
    private RecordAdapter listAdapter;

    private int pageNum = 1;

    private List<RecordData> dataList = new ArrayList<>();

    private SharedUtils sharedUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_record);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

        sharedUtils = new SharedUtils(ForRecordActivity.this, SharedUtils.WISDOM);

        //初始化view
        initView();

        //获取兑换记录
        getFindConversions();
    }

    private void initView() {
        //===
        idListRecycle = (RecyclerView) findViewById(R.id.recycler_for_record);
        idListRecycle.setItemViewCacheSize(100);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ForRecordActivity.this);
        idListRecycle.setLayoutManager(linearLayoutManager);
        idListRecycle.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("" + getString(R.string.for_record));
        }
    }


    //积分兑换商品记录
    private void getFindConversions() {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        //{"pageNo":1,"pageSize":10,"section_id":1}
        listcanshu.put("pageNo", pageNum);//页数
        listcanshu.put("pageSize", 10);//每页返回的条数
        listcanshu.put("section_id", OKHttpClass.getToken(ForRecordActivity.this));
        listcanshu.put("staff_id", sharedUtils.getIntData(SharedUtils.USER_ID));

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(this, RequestURL.findConversions, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("shop", "findConversions=兑换记录=" + dataString);
                Gson gson = new Gson();

                if (pageNum == 1) {
                    dataList.clear();
                }

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

                    JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("items").toString());

                    for (int i = 0; i < jsonArray.length(); i++) {
                        RecordData data = gson.fromJson(jsonArray.get(i).toString(), RecordData.class);
                        dataList.add(data);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                listAdapter = new RecordAdapter(ForRecordActivity.this, dataList);
                listAdapter.setOnClick(ForRecordActivity.this);
                idListRecycle.setAdapter(listAdapter);

                return dataString;
            }
        });
    }


    //生成二维码
    @Override
    public void GenerateQRCode(RecordData recordData) {

        RecordDialog dialog = new RecordDialog(ForRecordActivity.this);
        dialog.showDialog(recordData);
    }
}
