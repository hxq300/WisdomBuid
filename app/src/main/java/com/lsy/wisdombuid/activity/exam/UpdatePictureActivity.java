package com.lsy.wisdombuid.activity.exam;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.adapter.UpdatePictureAdapter;
import com.lsy.wisdombuid.base.BaseActivity;
import com.lsy.wisdombuid.util.RealPathFromUriUtils;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy on 2020/4/1
 * todo : 照片上传
 */
public class UpdatePictureActivity extends BaseActivity implements UpdatePictureAdapter.OnClick {

    //消息列表
    private RecyclerView idListRecycle;
    private UpdatePictureAdapter listAdapter;

    //==========
    private File cameraSavePath;//拍照照片路径
    private List<Uri> picPahts = new ArrayList<>();
    private List<String> filePahts = new ArrayList<>();
    private Uri uri;

    private ImageView openFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_picture);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }

        //初始化view
        initView();
    }

    private void initView() {

        picPahts.add(0, uri);

        //===
        idListRecycle = (RecyclerView) findViewById(R.id.recycler_add_picture);
        idListRecycle.setItemViewCacheSize(100);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RectificationNoticeActivity.this);
        idListRecycle.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        idListRecycle.setNestedScrollingEnabled(false);

        //=======
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");

        listAdapter = new UpdatePictureAdapter(UpdatePictureActivity.this, picPahts);
        listAdapter.setOnClick(this);
        idListRecycle.setAdapter(listAdapter);
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
            Log.d("拍照返真实:", RealPathFromUriUtils.compressImage(photoPath, UpdatePictureActivity.this));
//            picPahts.add(0, photoPath);

        } else if (requestCode == 234) {
            if (null != data && data.getData() != null) {
                photoPath = RealPathFromUriUtils.getRealPathFromUri(UpdatePictureActivity.this, data.getData());
                uri = data.getData();

//                Log.d("拍照返回图片路径:", photoPath);
//                Log.d("拍照返真实:", RealPathFromUriUtils.compressImage(photoPath, UpdatePictureActivity.this));

                picPahts.add(0, uri);

                filePahts.add(0, photoPath);

                Gson g = new Gson();
                String jsonString = g.toJson(filePahts);

                Log.d("拍照返回图片路径:", "=====" + jsonString);

                listAdapter.notifyDataSetChanged();
            }

        }


        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void addPicture() {
        ToastUtils.showBottomToast(this, "正在打开相册");
        goPhotoAlbum();
    }

    //激活相册操作
    private void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 234);
    }

    public void back(View view) {
        finish();
    }
}
