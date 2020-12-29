package com.lsy.wisdombuid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.MainActivity;
import com.lsy.wisdombuid.bean.IntegralData;
import com.lsy.wisdombuid.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy on 2020/3/19
 * todo : 积分明细适配器
 */
public class IntegralSubsidiaryAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<IntegralData> messageList = new ArrayList();
    private LayoutInflater inflater;

    public IntegralSubsidiaryAdapter(Context context, List<IntegralData> messageList) {
        this.context = context;
        this.messageList = messageList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(inflater.inflate(R.layout.view_integral_subsidiary, parent, false));
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

        private TextView jifen;
        private TextView typeName;
        private TextView createTime;

        public ItemView(View itemView) {
            super(itemView);
//            desc = itemView.findViewById(R.id.text_tuijian_desc);
            jifen = itemView.findViewById(R.id.item_add_jifen);
            typeName = itemView.findViewById(R.id.item_type_name);
            createTime = itemView.findViewById(R.id.item_create_time);
        }

        public void bindData(Object item, final int position) {
            final IntegralData datas = (IntegralData) item;

            jifen.setText("" + datas.getIntegral());
            typeName.setText("" + datas.getContent());
            createTime.setText("" + datas.getUp_time());

        }
    }

}
