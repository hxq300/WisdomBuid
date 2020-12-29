package com.lsy.wisdombuid.adapter;

import android.content.Context;
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
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.util.GeneralMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by lsy on 2019/8/14
 * MODO : 整改通知
 */
public class RectNoticeAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> cateList = new ArrayList();
    private LayoutInflater inflater;

    public RectNoticeAdapter(Context context, List<String> cateList) {
        this.context = context;
        this.cateList = cateList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(inflater.inflate(R.layout.view_rectifition_notice, parent, false));
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

        //        private LinearLayout lineType;
//        private TextView idText;
        private ImageView image;

        public ItemView(View itemView) {
            super(itemView);
//            lineType = (LinearLayout) itemView.findViewById(R.id.type_lable);
//            idText = (TextView) itemView.findViewById(R.id.type_text);
            image = (ImageView) itemView.findViewById(R.id.rectification_list_photo);
        }

        public void bindData(Object item, final int position) {
            final String datas = (String) item;

//            idText.setText(datas.getTitle_name());
            Glide.with(context).load(RequestURL.OssUrl + datas).error(R.mipmap.rect_icon1).into(image);

//            lineType.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (GeneralMethod.isFastClick()) {
//                        if (onClick != null) {
//                            onClick.zixunNow(datas.getId());
//                        }
//                    }
//                }
//            });
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
