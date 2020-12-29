package com.lsy.wisdombuid.activity.safety;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.adapter.HorizontalImageAdapter;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.oss.OssService;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.RealPathFromUriUtils;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.ToastUtils;
import com.lsy.wisdombuid.widget.HorizontalListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy on 2020/3/27
 * todo : 隐患上报
 */
public class TroubleReportActivity extends MyBaseActivity implements HorizontalImageAdapter.OnClick, OssService.ProgressCallback {

    //
    private File cameraSavePath;//拍照照片路径
    private List<String> picPahts = new ArrayList<>();
    //    private List<String> filePahts = new ArrayList<>();
    private Uri uri;

    //====图片显示
    private HorizontalListView horListPhoto;
    private HorizontalImageAdapter horPhotoAdapter;
    private LinearLayout footPicture;

    private ImageView openFile;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trouble_report);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();

        initView();
    }

    private void initView() {


//        qiniuToken = getQiniuToken();
        footPicture = findViewById(R.id.ll_foot_picture);
//        pinglun_desc = findViewById(R.id.pinglun_desc);

//        boolean sdCardExist = Environment.getExternalStorageState()
//                .equals(android.os.Environment.MEDIA_MOUNTED);//判断sd卡是否存在
//        if (sdCardExist) {
//            Log.d( "拍照返SDCARD: ", "SD卡存在");
//        }else{
//            Log.d( "拍照返SDCARD: ", "SD卡不存在");
//        }
//
//        try {
//            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//                cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
//                if (!cameraSavePath.exists()) {
//                    cameraSavePath.mkdirs();
//                }
//            } else {
//                Toast.makeText(getActivity(), "R.string.sdcard_unmounted", Toast.LENGTH_SHORT).show();
//            }
//        } catch (Exception e) {
//            Log.d( "拍照返tip: ", ""+e.getMessage());
//        }
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
        horListPhoto = findViewById(R.id.two_listphoto);

        openFile = findViewById(R.id.image_open_file);

//        Glide.with(getActivity()).load("file:///storage/emulated/0/tencent/MicroMsg/WeiXin/mmexport1584891935024.jpg")
//                                    .into(openFile);

        openFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });
    }

    //自定义底部弹出框
    private void showBottomDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(TroubleReportActivity.this, R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(TroubleReportActivity.this, R.layout.dialog_custom_layout, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        //拍照
        dialog.findViewById(R.id.tv_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goCamera();
            }
        });

        //从相册中选择
        dialog.findViewById(R.id.tv_take_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goPhotoAlbum();
            }
        });

        //取消
        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }


    //激活相册操作
    private void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 234);
    }

    //激活相机操作
    private void goCamera() {
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(TroubleReportActivity.this, "com.lsy.wisdombuid.fileprovider", cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, 123);
    }


    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("" + getString(R.string.hidden_trouble_report));
        }
    }

    @Override
    public void deletedPic(int position) {
        picPahts.remove(position);
//        filePahts.remove(position);
        horPhotoAdapter = new HorizontalImageAdapter(TroubleReportActivity.this, picPahts);
        horPhotoAdapter.setOnClick(this);
        footPicture.setVisibility(View.VISIBLE);
        horListPhoto.setAdapter(horPhotoAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String photoPath;

        if (requestCode == 123) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                photoPath = String.valueOf(cameraSavePath);
            } else {
                photoPath = uri.getEncodedPath();
            }

            String time = "" + System.currentTimeMillis();

            Log.d("拍照返回图片路径:", photoPath);
            Log.d("拍照返真实:", RealPathFromUriUtils.compressImage(photoPath, TroubleReportActivity.this));
            picPahts.add(0, photoPath);

        } else if (requestCode == 234) {
            if (null != data && data.getData() != null) {
                photoPath = RealPathFromUriUtils.getRealPathFromUri(TroubleReportActivity.this, data.getData());

                Log.d("拍照返回图片路径:", photoPath);
                Log.d("拍照返真实:", RealPathFromUriUtils.compressImage(photoPath, TroubleReportActivity.this));
                //初始化OssService类，参数分别是Content，accessKeyId，accessKeySecret，endpoint，bucketName（后4个参数是您自己阿里云Oss中参数）
                OssService ossService = new OssService(TroubleReportActivity.this, "LTAI4Fjcn7J9c5aCVFTYabqE", "EuufkpKHommuLDd6EawJQac8togdPn", "http://oss-cn-shanghai.aliyuncs.com", "jjjt");
                //初始化OSSClient
                ossService.initOSSClient();
                String time = "" + System.currentTimeMillis();
                //开始上传，参数分别为content，上传的文件名filename，上传的文件路径filePath
                ossService.beginupload(this, time, photoPath);
                //上传的进度回调
                ossService.setProgressCallback(new OssService.ProgressCallback() {
                    @Override
                    public void onProgressCallback(final double progress) {
                        L.log("上传进度："+progress);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }
                });

                ossService.getProgressCallback();


                picPahts.add(0, photoPath);
            }

        }

        horPhotoAdapter = new HorizontalImageAdapter(TroubleReportActivity.this, picPahts);
        footPicture.setVisibility(View.VISIBLE);
        horPhotoAdapter.setOnClick(this);
        horListPhoto.setAdapter(horPhotoAdapter);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onProgressCallback(double progress) {

    }
}
