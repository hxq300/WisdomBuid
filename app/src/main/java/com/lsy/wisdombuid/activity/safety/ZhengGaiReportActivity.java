package com.lsy.wisdombuid.activity.safety;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.adapter.UpdatePictureAdapter;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.IRecordData;
import com.lsy.wisdombuid.activity.login.LostPasswordActivity;
import com.lsy.wisdombuid.oss.OssService;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.RealPathFromUriUtils;
import com.lsy.wisdombuid.util.SharedUtils;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsy on 2020/3/30
 * todo : 整改上报
 */
public class ZhengGaiReportActivity extends MyBaseActivity implements UpdatePictureAdapter.OnClick, OssService.OssCallback {

    //消息列表
//    private RecyclerView idListRecycle;
//    private RectNoticeAdapter listAdapter;

    //===标段
    private SharedUtils sharedUtils;
    private IRecordData recordData;

    private TextView rTime, rStation, rName;

    private EditText rContent;//隐患内容

    //=========图片部分

    //消息列表
    private RecyclerView idListRecycle;
    private UpdatePictureAdapter listAdapter;

    //==========
    private File cameraSavePath;//拍照照片路径
    private List<Uri> picPahts = new ArrayList<>();
    //    private List<String> filePahts = new ArrayList<>();
    private Uri uri;

    private List<String> imageList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhenggai_shangbao);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

        sharedUtils = new SharedUtils(ZhengGaiReportActivity.this, SharedUtils.WISDOM);

        //初始化view
        initView();

        //初始化数据
        initData();

    }

    private void initView() {
        rTime = findViewById(R.id.report_time);
        rStation = findViewById(R.id.report_station);
        rName = findViewById(R.id.report_name);
        rContent = findViewById(R.id.report_content);

//        //===
        picPahts.add(0, uri);

        //===添加图片部分
        idListRecycle = (RecyclerView) findViewById(R.id.recycler_add_picture);
        idListRecycle.setItemViewCacheSize(100);
        idListRecycle.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        idListRecycle.setNestedScrollingEnabled(false);

        //=======
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");

        listAdapter = new UpdatePictureAdapter(ZhengGaiReportActivity.this, picPahts);
        listAdapter.setOnClick(this);
        idListRecycle.setAdapter(listAdapter);
    }

    private void initData() {

        recordData = new IRecordData();
        Gson gson = new Gson();

        String data = sharedUtils.getData(SharedUtils.IRDETAILS, "");

        if (data != "" || !data.equals("")) {
            recordData = gson.fromJson(sharedUtils.getData(SharedUtils.IRDETAILS, ""), IRecordData.class);
            rTime.setText(" " + recordData.getUptime());
            rStation.setText("" + recordData.getStation_name());
            rName.setText("" + recordData.getRisk_name());
        } else {
            ToastUtils.showBottomToast(ZhengGaiReportActivity.this, "获取数据失败");
        }

    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("" + getString(R.string.the_rectification_report));
        }
    }


    //上报
    private void commitReported() {

        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

//        id(安全隐患id),zg_url1(地址数组),zg_description(整改说明)
        listcanshu.put("id", recordData.getId());
        listcanshu.put("zg_url1", "" + imageList.toString());
        listcanshu.put("section_id", OKHttpClass.getToken(ZhengGaiReportActivity.this));//标段ID登录返回
        listcanshu.put("zg_description", rContent.getText().toString());//(整改说明)

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(ZhengGaiReportActivity.this, RequestURL.safetyRectificationReport, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("safetyInspectionRecord", "record=待整改=" + dataString);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

                    int code = jsonObject.getInt("code");
                    String data = jsonObject.getString("data");
                    String message = jsonObject.getString("message");

                    if (code == 200) {
                        ToastUtils.showBottomToast(ZhengGaiReportActivity.this, "提交成功");
                        finish();
                    } else {
                        ToastUtils.showBottomToast(ZhengGaiReportActivity.this, "" + message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });

    }


    //提交按钮
    public void btnCommit(View view) {
        if (is_input()) {
            commitReported();
        }

    }

    /**
     * 判断输入完成情况
     */
    private boolean is_input() {

        if (rContent.getText().toString().length() < 1) {
            Toast.makeText(ZhengGaiReportActivity.this, "请填写整改内容", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (imageList.size() < 1) {
            Toast.makeText(ZhengGaiReportActivity.this, "请上传现场整改图片", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        String photoPath;

        if (requestCode == 123) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                photoPath = String.valueOf(cameraSavePath);
            } else {
                photoPath = uri.getEncodedPath();
            }

            Log.d("拍照返回图片路径:", photoPath);
            Log.d("拍照返真实:", RealPathFromUriUtils.compressImage(photoPath, ZhengGaiReportActivity.this));
//            picPahts.add(0, photoPath);

        } else if (requestCode == 234) {
            if (null != data && data.getData() != null) {
                photoPath = RealPathFromUriUtils.getRealPathFromUri(ZhengGaiReportActivity.this, data.getData());
                uri = data.getData();

//                Log.d("拍照返回图片路径:", photoPath);
//                Log.d("拍照返真实:", RealPathFromUriUtils.compressImage(photoPath, UpdatePictureActivity.this));

                picPahts.add(0, uri);

//                filePahts.add(0, photoPath);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                // 设置图片名字
                String key = "report/" + sdf.format(new Date());

                listAdapter.notifyDataSetChanged();
                //上传的文件名filename，上传的文件路径filePath
                updateOss(key, photoPath);

//                Gson g = new Gson();
//                String jsonString = g.toJson(filePahts);
//
//                Log.d("拍照返回图片路径:", "=====" + jsonString);


            }


        }


//        horPhotoAdapter = new HorizontalImageAdapter(getActivity(), picPahts);
//        footPicture.setVisibility(View.VISIBLE);
//        horPhotoAdapter.setOnClick(this);
//        horListPhoto.setAdapter(horPhotoAdapter);
        super.onActivityResult(requestCode, resultCode, data);
    }


    //添加照片
    @Override
    public void addPicture() {
        if (picPahts.size() <= 9) {
            ToastUtils.showBottomToast(this, "正在打开相册");
            goPhotoAlbum();
        } else {
            ToastUtils.showBottomToast(this, "图片过多，请选择主要图片上传");
        }

    }

    //激活相册操作
    private void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 234);
    }


    //上传图片到oss
    private void updateOss(String filename, String filePath) {

        //初始化OssService类，参数分别是Content，accessKeyId，accessKeySecret，endpoint，bucketName（后4个参数是您自己阿里云Oss中参数）
        OssService ossService = new OssService(ZhengGaiReportActivity.this, "LTAI4Fjcn7J9c5aCVFTYabqE", "EuufkpKHommuLDd6EawJQac8togdPn", "http://oss-cn-shanghai.aliyuncs.com", "jjjt");
        //初始化OSSClient
        ossService.initOSSClient();

        ossService.setCallback(this);

//        ossService.setCallback(new OssService.OssCallback() {
//            @Override
//            public void sucess(String backUrl) {
//                L.log("callback", "backUrl===" + backUrl);
//            }
//
//            @Override
//            public void failure(String message) {
//                L.log("callback", "message===" + message);
//            }
//        });
        ossService.getProgressCallback();
        String time = "" + System.currentTimeMillis();
        //开始上传，参数分别为content，上传的文件名filename，上传的文件路径filePath
        ossService.beginupload(this, filename, filePath);
        //上传的进度回调
        ossService.setProgressCallback(new OssService.ProgressCallback() {
            @Override
            public void onProgressCallback(final double progress) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        L.log("上传进度：" + progress);
                    }
                });
            }
        });


    }

    //图片上传成功
    @Override
    public void sucess(String backUrl) {
        imageList.add(0, backUrl);
        listAdapter.notifyDataSetChanged();

        L.log("report", "imageList==" + imageList.toString());
    }

    //图片上传失败
    @Override
    public void failure(String message) {
        ToastUtils.showBottomToast(this, "图片上传失败");
    }
}
