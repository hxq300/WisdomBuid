package com.lsy.wisdombuid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.bean.RecordData;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.util.GeneralMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy on 2020/3/17
 * todo :
 */
public class RecordAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<RecordData> messageList = new ArrayList();
    private LayoutInflater inflater;

    public RecordAdapter(Context context, List<RecordData> messageList) {
        this.context = context;
        this.messageList = messageList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(inflater.inflate(R.layout.view_for_record, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ItemView) holder).bindData(messageList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }


    class ItemView extends RecyclerView.ViewHolder {

        private ImageView image;

        private TextView title;

        public ItemView(View itemView) {
            super(itemView);
//            desc = itemView.findViewById(R.id.text_tuijian_desc);
            image = itemView.findViewById(R.id.record_image);
            title = itemView.findViewById(R.id.record_title);
        }

        public void bindData(Object item, final int position) {
            final RecordData datas = (RecordData) item;

            Glide.with(context).load(RequestURL.OssUrl + datas.getCommodity_img()).error(R.mipmap.good1).into(image);


            title.setText("" + datas.getCommodity_name());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (GeneralMethod.isFastClick()) {
                        if (onClick != null) {
                            onClick.GenerateQRCode(datas);
                        }
                    }
                }
            });

        }
    }


    public interface OnClick {
        void GenerateQRCode(RecordData recordData);//生成兑换商品二维码
    }

    public OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }


}
