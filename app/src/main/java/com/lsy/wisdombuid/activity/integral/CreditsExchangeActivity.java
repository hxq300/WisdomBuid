package com.lsy.wisdombuid.activity.integral;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.adapter.InteralShopAdapter;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.ShopGoodsData;
import com.lsy.wisdombuid.dialog.IntegralDialog;
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
 * todo : 积分兑换
 */
public class CreditsExchangeActivity extends MyBaseActivity implements InteralShopAdapter.OnClick {

    //消息列表
    private RecyclerView idListRecycle;
    private InteralShopAdapter listAdapter;

    private List<ShopGoodsData> dataList = new ArrayList<>();
    private int pageNum = 1;

    private SharedUtils sharedUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits_exchange);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

        sharedUtils = new SharedUtils(CreditsExchangeActivity.this, SharedUtils.WISDOM);

        //初始化view
        initView();

        //获取商品数据
        getFindCommodity();
    }

    private void initView() {
        //===
        idListRecycle = (RecyclerView) findViewById(R.id.recycler_shop);
        idListRecycle.setItemViewCacheSize(100);
        idListRecycle.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        idListRecycle.setNestedScrollingEnabled(false);


    }

    //分页模糊查询商品id
    private void getFindCommodity() {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        //{"pageNo":1,"pageSize":10,"section_id":1}
        listcanshu.put("pageNo", pageNum);//页数
        listcanshu.put("pageSize", 10);//每页返回的条数
        listcanshu.put("section_id", "" + OKHttpClass.getToken(CreditsExchangeActivity.this));//用户ID

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(this, RequestURL.findCommodity, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("shop", "findCommodity=" + dataString);
                Gson gson = new Gson();

                if (pageNum == 1) {
                    dataList.clear();
                }

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

                    JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("items").toString());

                    for (int i = 0; i < jsonArray.length(); i++) {
                        ShopGoodsData data = gson.fromJson(jsonArray.get(i).toString(), ShopGoodsData.class);
                        dataList.add(data);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                listAdapter = new InteralShopAdapter(CreditsExchangeActivity.this, dataList);
                listAdapter.setOnClick(CreditsExchangeActivity.this);
                idListRecycle.setAdapter(listAdapter);

                return dataString;
            }
        });


    }

    //增加兑换记录
    private void addInsertConversions(String commodity_id, int commodity_price) {

        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        //{"id":3,"commodity_id":1,"section_id":1,"station_id":1,"sub_id":1,"commodity_price":10}
        listcanshu.put("id", sharedUtils.getIntData(SharedUtils.USER_ID));//
        listcanshu.put("commodity_id", Integer.parseInt(commodity_id));//
        listcanshu.put("section_id", OKHttpClass.getToken(CreditsExchangeActivity.this));//用户ID
        listcanshu.put("station_id", sharedUtils.getIntData(SharedUtils.STATION));//
        listcanshu.put("sub_id", sharedUtils.getIntData(SharedUtils.SUB_ID));//
        listcanshu.put("commodity_price", commodity_price);//

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(this, RequestURL.insertConversions, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("shop", "findCommodity=增加兑换记录=" + dataString);
                Gson gson = new Gson();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);
                    int code = jsonObject.getInt("code");
                    String data = jsonObject.getString("data");
                    String message = jsonObject.getString("message");

                    if (code == 200 && data != null) {

                        //兑换成功弹窗提示
                        IntegralDialog dialog = new IntegralDialog(CreditsExchangeActivity.this);
                        dialog.showDialog(true);
                    } else {

                        IntegralDialog dialog = new IntegralDialog(CreditsExchangeActivity.this);
                        dialog.showDialog(false);
                        ToastUtils.showBottomToast(CreditsExchangeActivity.this, "" + message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return dataString;
            }
        });
    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("" + getString(R.string.shop_integral));
        }
    }

    //事件点击
    public void total(View view) {
        switch (view.getId()) {

            case R.id.total_shop://积分商城
                Intent shop = new Intent(CreditsExchangeActivity.this, IntegralShopActivity.class);
                startActivity(shop);
                break;

            case R.id.total_my://我的积分
                Intent my = new Intent(CreditsExchangeActivity.this, MyIntegralActivity.class);
                startActivity(my);
                break;

            case R.id.total_record://兑换记录
                Intent record = new Intent(CreditsExchangeActivity.this, ForRecordActivity.class);
                startActivity(record);

                break;
            default:
                break;
        }
    }

    //商品点击兑换回调
    @Override
    public void toConvert(String commodity_id, int commodity_price) {
        addInsertConversions(commodity_id, commodity_price);
    }
}
