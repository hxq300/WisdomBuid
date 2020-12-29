package com.lsy.wisdombuid.activity.exam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.BaseActivity;
import com.lsy.wisdombuid.bean.TeachVData;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.ToastUtils;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by lsy on 2020/4/1
 * todo : 教学视屏
 */
public class TeachingVideoActivity extends BaseActivity implements View.OnClickListener {

    private TeachVData teachVData;
    private TextView vTitle, vType,tv_img_msg;
    private StandardGSYVideoPlayer gsy_player;
    private OrientationUtils orientationUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_teaching_video);
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }

        //初始化布局
        initView();
        //拼接视频 / 图片地址
        jointUrl();
    }

    private void jointUrl() {
        // 获取intent传递数据
        Intent intent = getIntent();
        int getId = intent.getIntExtra("id", 0);
        String video_url = intent.getStringExtra("video_url");
        String content = intent.getStringExtra("content");
        //拼接 地址
        String videoUrl = RequestURL.OssUrl+video_url;
        String imgUrl = RequestURL.OssUrl + content;
        //初始化GSY视频播放器
        initVideo(imgUrl,videoUrl);
        //获取培训内容（视频教程）
        getFindTrainByIdX(getId);
    }

    /**
     * 初始化布局
     */
    private void initView() {
        vTitle = findViewById(R.id.video_title);
        vType = findViewById(R.id.video_type);
        tv_img_msg = findViewById(R.id.tv_img_msg);
        tv_img_msg.setOnClickListener(this);
        gsy_player = findViewById(R.id.gsy_videoPlayer);
    }

    /**
     * 初始化视频部分
     */
    private void initVideo(String imgUrl,String videoUrl) {
        gsy_player.setUp(videoUrl,true,"");
        //是否可以滑动调整
        gsy_player.setIsTouchWiget(true);
        gsy_player.setAutoFullWithSize(true);
        orientationUtils = new OrientationUtils(this, gsy_player);
        // 返回按钮点击事件
        gsy_player.getBackButton().setOnClickListener(view -> onBackPressed());
        // 全屏按钮点击事件
        gsy_player.getFullscreenButton().setOnClickListener(v -> orientationUtils.resolveByClick());
        // 播放视频
        gsy_player.startPlayLogic();

    }

    /**
     * 获取培训内容（视频教程）
     */
    @SuppressLint("SetTextI18n")
    private void getFindTrainByIdX(int videoId) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        //section_id
        listcanshu.put("id", videoId);

        //培训课程分类列表
        okHttpClass.setPostCanShu(this, RequestURL.findTrainByIdX, listcanshu);
        okHttpClass.setGetIntenetData(dataString -> {
            Gson gson = new Gson();
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(dataString);
                String message = jsonObject.getString("message");
                int code = jsonObject.getInt("code");
                if (code == 200) {
                    teachVData = gson.fromJson(dataString, TeachVData.class);
                } else {
                    ToastUtils.showBottomToast(TeachingVideoActivity.this, "" + message);
                }

                vTitle.setText("" + teachVData.getData().getTrain_name());
                vType.setText("" + teachVData.getData().getType_name());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return dataString;
        });
    }

    /**
     * 点击事件
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_img_msg: //跳转图文教程页面
                startActivity(new Intent(TeachingVideoActivity.this, TuWenActivity.class));
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gsy_player.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gsy_player.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }


}
