package com.lsy.wisdombuid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.bean.TestHistory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy on 2020/3/19
 * todo : 考试记录
 */
public class TestGradeAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<TestHistory> messageList = new ArrayList();
    private LayoutInflater inflater;

    public TestGradeAdapter(Context context, List<TestHistory> messageList) {
        this.context = context;
        this.messageList = messageList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(inflater.inflate(R.layout.view_test_grade, parent, false));
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

        private TextView testIndex;
        private TextView testName;
        private TextView testGrade;

        private ImageView testIcon;

        public ItemView(View itemView) {
            super(itemView);
            testIndex = itemView.findViewById(R.id.test_grade_desc);
            testName = itemView.findViewById(R.id.test_grade_name);
            testGrade = itemView.findViewById(R.id.test_grade);
            testIcon = itemView.findViewById(R.id.test_grade_icon);
        }

        public void bindData(Object item, final int position) {
            final TestHistory datas = (TestHistory) item;

            testIndex.setText("" + (position + 1));
            testName.setText("" + datas.getExam_name());
            testGrade.setText("" + datas.getExam_count() + "分");

            if (datas.getExam_result().equals("不合格")) {
                testIcon.setImageResource(R.mipmap.grade_weotongguo);
            } else {
                testIcon.setImageResource(R.mipmap.grade_hege);
            }
        }
    }


}
