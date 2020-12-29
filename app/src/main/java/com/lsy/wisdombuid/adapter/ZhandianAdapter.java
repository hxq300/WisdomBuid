package com.lsy.wisdombuid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.MainActivity;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.util.GeneralMethod;
import com.lsy.wisdombuid.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy on 2020/3/19
 * todo : 站点下拉框适配器
 */
public class ZhandianAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<StationData> messageList = new ArrayList();
    private LayoutInflater inflater;

    public ZhandianAdapter(Context context, List<StationData> messageList) {
        this.context = context;
        this.messageList = messageList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(inflater.inflate(R.layout.view_zhandian_item, parent, false));
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

        private TextView name;

        public ItemView(View itemView) {
            super(itemView);
//            desc = itemView.findViewById(R.id.text_tuijian_desc);
            name = itemView.findViewById(R.id.item_zhandain_name);
        }

        public void bindData(Object item, final int position) {
            final StationData datas = (StationData) item;

            name.setText("" + datas.getStation_name());

//            desc.setText(""+datas.getIntroduce());
//
//            if (datas.getLogo().get(0)!=null){
//                Glide.with(context).load("http://a2.sayyin.com/community" + datas.getLogo().get(0))
//                        .error(R.mipmap.shangping) //异常时候显示的图片
//                        .placeholder(R.mipmap.shangping) //加载成功前显示的图片
//                        .fallback(R.mipmap.shangping) //url为空的时候,显示的图片
////                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
//                        .into(headImage);
//            }


            itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            itemView.setBackgroundColor(0xffb8e1fb);
                            break;
//                        case MotionEvent.ACTION_MOVE:
//                            LogUtil.log("action_move");
//                            break;
                        case MotionEvent.ACTION_UP:
                            itemView.setBackgroundColor(0xffFFFFFF);
                            if (GeneralMethod.isFastClick()) {
                                if (onClick != null) {
                                    onClick.checkZhandian(position, datas.getId(), datas.getStation_name());
                                }
                            }
//                            ToastUtils.showBottomToast(context, "选择了" + datas.getStation_name());
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });

        }
    }

    public interface OnClick {
        void checkZhandian(int position, int sectionId, String name);
    }

    public OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }


}
