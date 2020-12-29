package com.lsy.wisdombuid.activity.integral;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.adapter.RecordAdapter;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.util.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy on 2020/3/20
 * todo : 积分充值
 */
public class PayIntragleActivity extends MyBaseActivity {

    private TextView realPrice1, realPrice2, realPrice3;
    private RelativeLayout rLayout1, rLayout2, rLayout3;

    private CheckBox typeWeixin, typeAlipay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_intragle);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

        //初始化view
        initView();
    }

    private void initView() {
        realPrice1 = findViewById(R.id.pay_real_price1);
        realPrice2 = findViewById(R.id.pay_real_price2);
        realPrice3 = findViewById(R.id.pay_real_price3);

        rLayout1 = findViewById(R.id.pay_money_one);
        rLayout2 = findViewById(R.id.pay_money_two);
        rLayout3 = findViewById(R.id.pay_money_three);

        typeWeixin = findViewById(R.id.check_type_weixin);
        typeAlipay = findViewById(R.id.check_type_alipay);

        realPrice1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        realPrice2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        realPrice3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        realPrice3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

    }


    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("" + getString(R.string.for_record));
        }
    }

    public void payMoney(View view) {
        switch (view.getId()) {
            case R.id.pay_money_one:
                rLayout1.setBackgroundResource(R.drawable.pay_bg_blue);
                rLayout2.setBackgroundResource(R.drawable.pay_bg_hui);
                rLayout3.setBackgroundResource(R.drawable.pay_bg_hui);
                break;
            case R.id.pay_money_two:
                rLayout2.setBackgroundResource(R.drawable.pay_bg_blue);
                rLayout1.setBackgroundResource(R.drawable.pay_bg_hui);
                rLayout3.setBackgroundResource(R.drawable.pay_bg_hui);
                break;
            case R.id.pay_money_three:
                rLayout3.setBackgroundResource(R.drawable.pay_bg_blue);
                rLayout2.setBackgroundResource(R.drawable.pay_bg_hui);
                rLayout1.setBackgroundResource(R.drawable.pay_bg_hui);
                break;

            default:
                break;

        }
    }

    //支付方式
    public void payType(View view) {
        switch (view.getId()) {

            case R.id.pay_type1:
                typeWeixin.setChecked(true);
                typeAlipay.setChecked(false);
                break;

            case R.id.pay_type2:
                typeWeixin.setChecked(false);
                typeAlipay.setChecked(true);
                break;

            default:
                break;
        }
    }
}
