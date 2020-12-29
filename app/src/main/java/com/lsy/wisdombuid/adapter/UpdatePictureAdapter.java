package com.lsy.wisdombuid.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.util.GeneralMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by lsy on 2019/8/14
 * MODO : 图片上传适配器
 */
public class UpdatePictureAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Uri> cateList = new ArrayList();
    private LayoutInflater inflater;

    public UpdatePictureAdapter(Context context, List<Uri> cateList) {
        this.context = context;
        this.cateList = cateList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(inflater.inflate(R.layout.view_update_image, parent, false));
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
            final Uri datas = (Uri) item;

            if (cateList.size() - 1 == position) {
                image.setImageResource(R.mipmap.add_image);
            } else {
                Glide.with(context).load(datas).into(image);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (GeneralMethod.isFastClick()) {
                        if (onClick != null) {

                            if (cateList.size() - 1 == position) {
                                onClick.addPicture();
                            } else {
                                notifyDataSetChanged();
                            }
//                            onClick.zixunNow(datas.getId());
                        }
                    }
                }
            });
        }
    }

    public interface OnClick {
        void addPicture();
    }

    public OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }


}
