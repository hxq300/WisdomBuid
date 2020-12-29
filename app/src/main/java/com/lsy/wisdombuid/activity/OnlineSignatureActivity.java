package com.lsy.wisdombuid.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.util.QuanXian;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.widget.SignatureView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lsy on 2020/5/11
 * todo : 在线签名
 */
public class OnlineSignatureActivity extends MyBaseActivity implements QuanXian.OnPermission {

    @BindView(R.id.signature_area)
    TextView signatureArea;
    @BindView(R.id.signature_clean)
    TextView signatureClean;
    @BindView(R.id.signature_pad)
    SignatureView signaturePad;
    @BindView(R.id.signature_commit)
    Button signatureCommit;
    @BindView(R.id.signature_iamge)
    ImageView signatureIamge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_signature);
        ButterKnife.bind(this);

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
        //判断权限是否全部打开
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
        }

        signaturePad.setOnSignedListener(new SignatureView.OnSignedListener() {
            @Override
            public void onSigned() {
                signatureCommit.setEnabled(true);
                signatureClean.setEnabled(true);
            }

            @Override
            public void onClear() {
                signatureCommit.setEnabled(false);
                signatureClean.setEnabled(false);
            }
        });

        signatureClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signaturePad.clear();
            }
        });
        signatureCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap signatureBitmap = signaturePad.getSignatureBitmap();
                Bitmap bitmap = compressScale(signatureBitmap);
                if (addSignatureToGallery(bitmap)) {
                    Toast.makeText(OnlineSignatureActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OnlineSignatureActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void requestPermissions() {
        QuanXian quanXian = new QuanXian(OnlineSignatureActivity.this, OnlineSignatureActivity.this);
        quanXian.setOnPermission_isok(this);
        quanXian.requestPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        );
    }


    public File getAlbumStorageDir(String albumName) {
        File file = new File(Environment.getExternalStorageDirectory(), albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }

    public boolean addSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            final File photo = new File(getAlbumStorageDir("draw"), String.format("qianming.png", System.currentTimeMillis()));
            saveBitmapToJPG(signature, photo);
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(photo);
            mediaScanIntent.setData(contentUri);
            OnlineSignatureActivity.this.sendBroadcast(mediaScanIntent);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                photoPath = String.valueOf(cameraSavePath);
                Glide.with(OnlineSignatureActivity.this).load(photo).into(signatureIamge);
            } else {
//                photoPath = uri.getEncodedPath();
                Glide.with(OnlineSignatureActivity.this).load(contentUri.getEncodedPath()).into(signatureIamge);
            }

            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *  
     *  * 图片按比例大小压缩方法 
     *  * 
     *  * @param image （根据Bitmap图片压缩） 
     *  * @return 
     *  
     */
    public static Bitmap compressScale(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //newOpts.inPreferredConfig = Bitmap.Config.RGB_565;//降低图片从ARGB888到RGB565
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        // return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
        return bitmap;
    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {
        } else {
            titleBar.setTitle(getString(R.string.online_signature));
        }
    }

    @Override
    public void one_permission_isok(String permission_name) {

    }

    @Override
    public void one_permission_is_refuse(String permission_name) {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }

    @Override
    public void one_permission_is_refuse_no_prompt(String permission_name) {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }

}
