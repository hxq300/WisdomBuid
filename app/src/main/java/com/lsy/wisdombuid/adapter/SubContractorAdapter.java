package com.lsy.wisdombuid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.bean.SubContractorData;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.widget.EasySeekBar;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy on 2020/3/19
 * todo : 分包单位条形统计图
 */
public class SubContractorAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<SubContractorData> messageList = new ArrayList();
    private int allPeople = 0;
    private LayoutInflater inflater;

    public SubContractorAdapter(Context context, List<SubContractorData> messageList, int allPeople) {
        this.context = context;
        this.messageList = messageList;
        this.allPeople = allPeople;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(inflater.inflate(R.layout.view_sub_danwei, parent, false));
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

        private TextView pNum;
        private TextView pName;
        private EasySeekBar seekBar;

        public ItemView(View itemView) {
            super(itemView);
//            desc = itemView.findViewById(R.id.text_tuijian_desc);
            pNum = itemView.findViewById(R.id.sub_people_number);
            pName = itemView.findViewById(R.id.sub_name);
            seekBar = itemView.findViewById(R.id.sub_people_seek);
        }

        public void bindData(Object item, final int position) {
            final SubContractorData datas = (SubContractorData) item;

            pNum.setText(datas.getCount()+"");
            pName.setText("" + datas.getSub_name());

            seekBar.setValue(toScale(datas.getCount(), allPeople));
            L.log("seekbar", "------" + toScale(datas.getCount(), allPeople));
        }
    }


    //换算百分比
    private int toScale(int v, int sum) {
        double temp = v / sum;
        int v1 = Integer.parseInt("" + (v * 100 / sum));
        return v1;
    }
}
