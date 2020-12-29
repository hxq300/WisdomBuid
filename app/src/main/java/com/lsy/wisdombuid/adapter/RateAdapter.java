package com.lsy.wisdombuid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.bean.HomeBtnData;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.util.GeneralMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by lsy on 2019/8/14
 * MODO :
 */
public class RateAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<HomeBtnData.BtnData> cateList = new ArrayList();
    private LayoutInflater inflater;

    public RateAdapter(Context context, List<HomeBtnData.BtnData> cateList) {
        this.context = context;
        this.cateList = cateList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(inflater.inflate(R.layout.view_type, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ItemView) holder).bindData(cateList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return cateList.size();
    }


    class ItemView extends RecyclerView.ViewHolder {

        private LinearLayout lineType;
        private TextView idText;
        private ImageView icon;

        public ItemView(View itemView) {
            super(itemView);
            lineType = (LinearLayout) itemView.findViewById(R.id.type_lable);
            idText = (TextView) itemView.findViewById(R.id.type_text);
            icon = (ImageView) itemView.findViewById(R.id.type_icon);
        }

        public void bindData(Object item, final int position) {
            final HomeBtnData.BtnData datas = (HomeBtnData.BtnData) item;

            idText.setText(datas.getTitle_name());
            Glide.with(context).load(RequestURL.RequestImg + datas.getTitle_img()).into(icon);

            lineType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (GeneralMethod.isFastClick()) {
                        if (onClick != null) {
                            onClick.zixunNow(datas.getId());
                        }
                    }
                }
            });
        }
    }

    public interface OnClick {
        void zixunNow(int position);
    }

    public OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }


}
