package com.lsy.wisdombuid.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lsy.wisdombuid.R;

import java.util.List;

/**
 * Created by hxq on 2021/4/27
 * describe :  TODO
 */
public class SaveBeamAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public SaveBeamAdapter(@Nullable List<String> data) {
        super(R.layout.item_save, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.item_tv,item);
    }
}
