package com.lsy.wisdombuid.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.safety.TroubleReportActivity;
import com.lsy.wisdombuid.base.BaseActivity;
import com.lsy.wisdombuid.bean.UserData;
import com.lsy.wisdombuid.oss.OssService;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.GeneralMethod;
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
 * Created by lsy on 2020/3/17
 * todo : 个人资料
 */
public class PersionDataActivity extends BaseActivity implements OssService.OssCallback {

    private Dialog dialog;
    private SharedUtils sharedUtils;

    private int userId = 0;

    private ImageView headImg;
    private TextView pSex, pAge, pPhone, pBiaoduan, pZhandian, pDanwei;

    //更改头像部分
    //图片部分
    private File cameraSavePath;//拍照照片路径
    //    private List<String> picPahts = new ArrayList<>();
    private Uri uri;

    //====
    private EditText inputNick;
    private TextView tvName;
    private Button commit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persion_data);
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }

        sharedUtils = new SharedUtils(this, SharedUtils.WISDOM);

        userId = sharedUtils.getIntData(SharedUtils.USER_ID);

        initView();

        getDataPersion();

//        setUpdatePersionData("headImag/image_20200405154319", null);
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
                L.log("userInformation", "perInformation= 个人资料=" + dataString);
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
                            pPhone.setText("" + userData.getStaff_phone());
                        }

                        if (userData.getNikename() != null) {
                            tvName.setText(userData.getNikename());
                        } else if (userData.getStaff_name() != null) {
                            tvName.setText(userData.getStaff_name());
                        }

                        if (userData.getStaff_sex() != null) {
                            pSex.setText("" + userData.getStaff_sex());
                        }

                        if (userData.getStaff_age() > 0) {
                            pAge.setText("" + userData.getStaff_age());
                        }

                        if (userData.getSection_name() != null) {//标段
                            pBiaoduan.setText("" + userData.getSection_name());
                        }

                        if (userData.getStation_name() != null) {
                            pZhandian.setText("" + userData.getStation_name());
                        }

                        if (userData.getSub_name() != null) {//单位改成部门
                            pDanwei.setText("" + userData.getSub_name());
                        }


                        if (userData.getPicture() != null) {

                            Glide.with(PersionDataActivity.this).load(RequestURL.OssUrl + userData.getPicture())
                                    .error(R.mipmap.mine_head)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(headImg);

                        } else if (userData.getStaff_img() != null) {
                            Glide.with(PersionDataActivity.this).load(RequestURL.RequestImg + userData.getStaff_img())
                                    .error(R.mipmap.mine_head)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(headImg);
                        }


                    } else {
                        ToastUtils.showBottomToast(PersionDataActivity.this, "" + message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });
    }

    //修改个人资料
    private void setUpdatePersionData(final String picture, final String nikename) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        //{"id":2,"picture":"1.png","nikename":"asd"}
        listcanshu.put("id", userId);

        if (picture != null) {
            listcanshu.put("picture", "" + picture);
        }

        if (nikename != null) {
            listcanshu.put("nikename", nikename);
        }

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(this, RequestURL.updateInformation, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("userInformation", "perInformation= 个人资料=" + dataString);
                Gson gson = new Gson();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);
                    int code = jsonObject.getInt("code");
                    String data = jsonObject.getString("data");
                    String message = jsonObject.getString("message");

                    if (code == 200) {
                        if (picture != null) {
                            L.log("userInformation", "perInformation= 正在展示图片=" + RequestURL.OssUrl + picture);
                            Glide.with(PersionDataActivity.this).load(RequestURL.OssUrl + picture)
                                    .error(R.mipmap.mine_head)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(headImg);

                            sharedUtils.setData(sharedUtils.USER_IMAGE, picture);//
                        }

                        if (nikename != null) {
                            inputNick.setVisibility(View.GONE);
                            tvName.setVisibility(View.VISIBLE);
                            commit.setVisibility(View.GONE);
                            tvName.setText(nikename);

                            sharedUtils.setData(sharedUtils.USER_NICKNAME, nikename);//
                        }

                    } else {
                        ToastUtils.showBottomToast(PersionDataActivity.this, "" + message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });
    }

    private void initView() {

        headImg = findViewById(R.id.persion_head_img);
        pSex = findViewById(R.id.persion_sex);
        pAge = findViewById(R.id.persion_age);
        pPhone = findViewById(R.id.persion_phone);
        pBiaoduan = findViewById(R.id.persion_section);
        pZhandian = findViewById(R.id.persion_station);
        pDanwei = findViewById(R.id.persion_units);

        inputNick = findViewById(R.id.persion_input_nickname);
        tvName = findViewById(R.id.persion_text_nickname);
        commit = findViewById(R.id.persion_update_btn);

        if (SharedUtils.USER_IMAGE != null) {
            Glide.with(PersionDataActivity.this).load(RequestURL.OssUrl + sharedUtils.getData(SharedUtils.USER_IMAGE, ""))
                    .error(R.mipmap.mine_head)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(headImg);
        } else if (SharedUtils.USER_PIC != null) {
            Glide.with(PersionDataActivity.this).load(RequestURL.RequestImg + sharedUtils.getData(SharedUtils.USER_PIC, ""))
                    .error(R.mipmap.mine_head)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(headImg);
        }


    }

    public void back(View view) {
        finish();
    }

    //更改头像
    public void headImage(View view) {

        L.log("callback", "点击了头像");
        if (GeneralMethod.isFastClick()) {
            showBottomDialog();
        }

    }

    //自定义底部弹出框
    private void showBottomDialog() {
        dialog = new Dialog(this, R.style.DialogTheme);
        View view = View.inflate(this, R.layout.dialog_custom_layout, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.main_menu_animStyle);
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
        startActivityForResult(intent, 2);
    }

    //激活相机操作
    private void goCamera() {
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(PersionDataActivity.this, "com.lsy.wisdombuid.fileprovider", cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        PersionDataActivity.this.startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (dialog != null) {
            dialog.dismiss();
        }

        String photoPath;
        if (requestCode == 1 && resultCode == RESULT_OK) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                photoPath = String.valueOf(cameraSavePath);
            } else {
                photoPath = uri.getEncodedPath();
            }

            Log.d("拍照返回图片路径:", photoPath);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            // 设置图片名字
            String key = "headImag/image_" + sdf.format(new Date());

            //上传的文件名filename，上传的文件路径filePath
            updateOss(key, photoPath);
            setUpdatePersionData(key, null);

        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            if (null != data && data.getData() != null) {
                photoPath = RealPathFromUriUtils.getRealPathFromUri(this, data.getData());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                // 设置图片名字
                String key = "headImag/image_" + sdf.format(new Date());
                //上传的文件名filename，上传的文件路径filePath
                updateOss(key, photoPath);
                setUpdatePersionData(key, null);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //上传图片到oss
    private void updateOss(String filename, String filePath) {

        //初始化OssService类，参数分别是Content，accessKeyId，accessKeySecret，endpoint，bucketName（后4个参数是您自己阿里云Oss中参数）
        OssService ossService = new OssService(PersionDataActivity.this, "LTAI4Fjcn7J9c5aCVFTYabqE", "EuufkpKHommuLDd6EawJQac8togdPn", "http://oss-cn-shanghai.aliyuncs.com", "jjjt");
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
                L.log("上传进度：" + progress);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });


    }


    //
//    @Override
//    public void onProgressCallback(double progress) {
//        L.log("callback", "progress===" + progress);
//    }
//
    @Override
    public void sucess(String backUrl) {
        L.log("callback", "backUrl===" + backUrl);
//        ToastUtils.showBottomToast(this, "上传图片成功");
//        setUpdatePersionData(backUrl, null);
    }

    @Override
    public void failure(String message) {
        L.log("callback", "failure essage===" + message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (dialog != null) {
            dialog.dismiss();
        }
    }

    //编辑昵称
    public void editNickname(View view) {

        inputNick.setVisibility(View.VISIBLE);
        tvName.setVisibility(View.GONE);
        commit.setVisibility(View.VISIBLE);

    }

    //更改昵称按钮
    public void updateNick(View view) {
        if (inputNick.getText().toString().trim() != null) {
            setUpdatePersionData(null, inputNick.getText().toString());
        } else {
            ToastUtils.showBottomToast(this, "昵称不能为空，否则无法更改");
        }

    }


}
