package com.lsy.wisdombuid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.bean.MessageData;
import com.lsy.wisdombuid.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy on 2020/3/17
 * todo :
 */
public class MessageAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<MessageData> messageList = new ArrayList();
    private LayoutInflater inflater;

    public MessageAdapter(Context context, List<MessageData> messageList) {
        this.context = context;
        this.messageList = messageList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(inflater.inflate(R.layout.view_message_list, parent, false));
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

        private TextView shenheTime;//审核时间
        private TextView content;//消息内容

        public ItemView(View itemView) {
            super(itemView);
//            desc = itemView.findViewById(R.id.text_tuijian_desc);
            shenheTime = itemView.findViewById(R.id.message_shenhe_time);
            content = itemView.findViewById(R.id.message_content);
        }

        public void bindData(Object item, final int position) {
            final MessageData datas = (MessageData) item;

            if (datas.getNoC() != null) {
                shenheTime.setText("" + TimeUtils.timeslashData(datas.getNoC()));
            }
            content.setText("您于" + datas.getUptime() + "上传的" + datas.getStation_name() + "隐患问题已经核实，感谢您的反馈！");

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


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent toAbout=new Intent(context, GoodsDetailsActivity.class);
//                    toAbout.putExtra("goodsId",""+datas.getGoodsId());
////                    toAbout.putExtra("articleId",datas.getArticleId());
//                    context.startActivity(toAbout);
//
//                }
//            });

//            photo.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    return false;
//                }
//            });
//
//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    relativeLayout.setVisibility(View.VISIBLE);
//                    return true;
//                }
//            });
        }
    }


}
