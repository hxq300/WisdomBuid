package com.lsy.wisdombuid.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.util.ActivityManager;

/**
 * Created by lsy on 2020/3/16
 * todo :
 */
public class TitleBar extends RelativeLayout {

    private Context mContext;
    private ImageView mReturn_key;
    private TextView mCenter_Tv;
    private TextView mReight_Tv;
    private RelativeLayout title_line;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        initView();
        initId();
        Listener();
    }


    private void Listener() {
        mReturn_key.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 关闭当前页面
                ActivityManager.getInstance().popOneActivity((Activity) mContext);
//                ActivityMangerUtil.getInstance().finishActivity((Activity)mContext);
            }
        });

//        mReight_Tv.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mReight_Tv.getText().equals("修改")){
//
//                }
//            }
//        });
    }

    private void initId() {
        mCenter_Tv = findViewById(R.id.text_center);
        mReight_Tv = findViewById(R.id.text_reight);
        mReturn_key = (ImageView) findViewById(R.id.top_back_iv);
        title_line = (RelativeLayout) findViewById(R.id.title_line);
    }

    private void initView() {
        View.inflate(mContext, R.layout.ui_title_bar, this);
    }

    /**
     * 设置中间文本
     *
     * @param title
     */
    public void setTitle(String title) {
        mCenter_Tv.setText(title);
    }

    /**
     * 隐藏右边的文字
     */
    public void hideRightTv() {
        mReight_Tv.setVisibility(View.INVISIBLE);
    }

    /**
     * 隐藏左边的图片
     */
    public void hideLeftIv() {
        mReturn_key.setVisibility(View.INVISIBLE);
    }

    /**
     * 显示左边的图片
     */
    public void showLeftIv() {
        mReturn_key.setVisibility(View.VISIBLE);
    }
    /**
     * 显示右边的文字
     */
    public void showRightTv() {
        mReight_Tv.setVisibility(View.VISIBLE);
    }

    /**
     * 设置左边的图片
     *
     * @param resultId
     */
    public void setPhotoLeft(int resultId) {
        mReturn_key.setImageResource(resultId);
    }

    /**
     * 设置右边的文字
     * @param rText
     */
    public void setTextRight(String rText){
        mReight_Tv.setText(rText);
    }

    /**
     *
     * @param rText
     */
    public void setBg(String rText){
        title_line.setBackgroundColor(Color.parseColor(rText));
    }


}
