package com.lsy.wisdombuid.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.bean.RecordData;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.util.QRCodeUtil;

/**
 * Created by lsy on 2020/3/20
 * todo : 积分兑换记录
 */
public class RecordDialog {
    private Context context;
    private MyDialog dialog;

    public RecordDialog(Context context) {
        this.context = context;
    }

    public void showDialog(RecordData recordData) {
        dialog = new MyDialog(context, R.style.WoDeDialog, R.layout.dialog_integral_record);
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);


        TextView title = dialog.findViewById(R.id.record_dialog_title);
        TextView time = dialog.findViewById(R.id.record_dialog_time);
        ImageView image = dialog.findViewById(R.id.record_dialog_goods_image);
        ImageView zxingImage = dialog.findViewById(R.id.record_dialog_zxing);

        title.setText(recordData.getCommodity_name());
        time.setText("兑换日期：" + recordData.getConversion_time());

        Glide.with(context).load(RequestURL.OssUrl + recordData.getCommodity_img()).error(R.mipmap.good1).into(image);

        Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap("" + recordData.getId(), 680, 680);
        zxingImage.setImageBitmap(mBitmap);

    }
}