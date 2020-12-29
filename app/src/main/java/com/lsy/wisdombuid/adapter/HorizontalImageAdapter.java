package com.lsy.wisdombuid.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.util.GeneralMethod;

import java.util.List;

/**
 * Create by lsy on 2019/12/20
 * MODO :
 */
public class HorizontalImageAdapter extends BaseAdapter {
    //    private int[] mIconIDs;
    private List<String> photos;
    private Context mContext;
    private LayoutInflater mInflater;
    Bitmap iconBitmap;
    private int selectIndex = -1;

    public HorizontalImageAdapter(Context context, List<String> photos){
        this.mContext = context;
        this.photos = photos;
        mInflater= LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return photos.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.view_horizon_image_item, null);
            holder.mPicture= convertView.findViewById(R.id.iv_list_photo);
            holder.deleted=(ImageView) convertView.findViewById(R.id.image_deleted);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        if(position == selectIndex){
            convertView.setSelected(true);
        }else{
            convertView.setSelected(false);
        }

        if (photos.get(position).length()<=0){

        }else{
            Glide.with(mContext).load(photos.get(position)).into(holder.mPicture);
        }

        holder.deleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GeneralMethod.isFastClick()){
                    if (onClick!=null){
                        onClick.deletedPic(position);
                    }
                }
            }
        });


        return convertView;
    }

    private static class ViewHolder {
        private ImageView mPicture ;
        private ImageView deleted;
    }

    public void setSelectIndex(int i){
        selectIndex = i;
    }



    public interface OnClick {
        void deletedPic(int position);
    }

    public OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }


}
