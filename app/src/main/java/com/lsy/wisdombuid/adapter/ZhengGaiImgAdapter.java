package com.lsy.wisdombuid.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.request.RequestURL;

import java.util.List;

/**
 * Created by hxq on 2021/1/6
 * describe :  TODO
 */
public class ZhengGaiImgAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Context context;
    public ZhengGaiImgAdapter(@Nullable List<String> data, Context context) {
        super(R.layout.img_itm, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(context)
                .load(RequestURL.OssUrl+item)
                .into((ImageView) helper.getView(R.id.iv_img));
    }
}
