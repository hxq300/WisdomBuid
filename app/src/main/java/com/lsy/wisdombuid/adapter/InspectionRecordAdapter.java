package com.lsy.wisdombuid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.safety.InsRecordDetailsActivity;
import com.lsy.wisdombuid.activity.safety.ZhengGaiReviewActivity;
import com.lsy.wisdombuid.bean.IRecordData;
import com.lsy.wisdombuid.activity.login.LoginActivity;
import com.lsy.wisdombuid.bean.RectifyEntity;
import com.lsy.wisdombuid.util.GeneralMethod;
import com.lsy.wisdombuid.util.SharedUtils;
import com.lsy.wisdombuid.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy on 2020/3/30
 * todo : 安全检查记录
 */
public class InspectionRecordAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<RectifyEntity.ItemsBean> messageList = new ArrayList();
    private LayoutInflater inflater;
    private int type;

    private SharedUtils sharedUtils;

    public InspectionRecordAdapter(Context context, List<RectifyEntity.ItemsBean> messageList, int type) {
        this.context = context;
        this.messageList = messageList;
        this.type = type;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(inflater.inflate(R.layout.view_inspection_record_list, parent, false));
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

        private TextView title;
        private TextView upTime;

        public ItemView(View itemView) {
            super(itemView);
//            desc = itemView.findViewById(R.id.text_tuijian_desc);
            title = itemView.findViewById(R.id.in_record_title);
            upTime = itemView.findViewById(R.id.in_record_up_time);
        }

        public void bindData(Object item, final int position) {
            final RectifyEntity.ItemsBean datas = (RectifyEntity.ItemsBean) item;

            title.setText("" + datas.getTitle());
            upTime.setText("" + datas.getUptime());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (GeneralMethod.isFastClick()) {
                        sharedUtils = new SharedUtils(context, SharedUtils.WISDOM);

                        if (datas.toString() != null) {
                            sharedUtils.setData(sharedUtils.IRDETAILS, "" + messageList.get(position).toString());//存入对象


                            if (type == 3 || type == 4) {
                                Intent intent = new Intent();
                                intent.putExtra("type", type);
                                intent.putExtra("url",datas.getUrl());
                                intent.putExtra("data",messageList.get(position));
                                intent.setClass(context, ZhengGaiReviewActivity.class);

                                context.startActivity(intent);
                            } else {
                                Intent intent = new Intent();
                                intent.putExtra("type", type);
                                intent.putExtra("url",datas.getUrl());
                                intent.setClass(context, InsRecordDetailsActivity.class);

                                context.startActivity(intent);
                            }

                        } else {
                            ToastUtils.showBottomToast(context, "数据获取失败");
                        }

                    }

                }
            });

        }
    }


}
