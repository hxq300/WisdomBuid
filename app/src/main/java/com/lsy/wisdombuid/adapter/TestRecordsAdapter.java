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
import com.lsy.wisdombuid.bean.TestHistory;
import com.lsy.wisdombuid.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy on 2020/3/19
 * todo : 考试记录
 */
public class TestRecordsAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<TestHistory> messageList = new ArrayList();
    private LayoutInflater inflater;

    public TestRecordsAdapter(Context context, List<TestHistory> messageList) {
        this.context = context;
        this.messageList = messageList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(inflater.inflate(R.layout.view_test_records, parent, false));
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

        private TextView testDesc;
        private TextView testName;
        private TextView testTime;

        public ItemView(View itemView) {
            super(itemView);
            testDesc = itemView.findViewById(R.id.test_record_desc);
            testName = itemView.findViewById(R.id.test_name);
            testTime = itemView.findViewById(R.id.test_time);
        }

        public void bindData(Object item, final int position) {
            final TestHistory datas = (TestHistory) item;

            testDesc.setText("" + (position + 1));
            testName.setText("" + datas.getExam_name());
            testTime.setText("" + datas.getExam_time());
        }
    }


}
