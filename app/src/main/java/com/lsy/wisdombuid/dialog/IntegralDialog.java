package com.lsy.wisdombuid.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.lsy.wisdombuid.R;

/**
 * Created by lsy on 2020/3/20
 * todo : 积分兑换弹窗
 */
public class IntegralDialog {
    private Context context;
    private MyDialog dialog;

    public IntegralDialog(Context context) {
        this.context = context;
    }

    public void showDialog(boolean isSuccess) {
        dialog = new MyDialog(context, R.style.WoDeDialog, R.layout.dialog_integral);
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);

        ImageView imageClose = dialog.findViewById(R.id.dialog_image);

        if (isSuccess)
            imageClose.setImageResource(R.mipmap.duihuan_success);
        else
            imageClose.setImageResource(R.mipmap.duihuan_failue);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


    }
}