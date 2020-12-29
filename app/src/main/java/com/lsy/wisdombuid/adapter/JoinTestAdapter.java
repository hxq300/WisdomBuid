package com.lsy.wisdombuid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.exam.QuestionsActivity;
import com.lsy.wisdombuid.activity.exam.TrainingCheckActivity;
import com.lsy.wisdombuid.bean.ExamData;
import com.lsy.wisdombuid.bean.QuestiondData;
import com.lsy.wisdombuid.util.GeneralMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy on 2020/3/19
 * todo : 考试记录
 */
public class JoinTestAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ExamData> messageList = new ArrayList();
    private LayoutInflater inflater;

    public JoinTestAdapter(Context context, List<ExamData> messageList) {
        this.context = context;
        this.messageList = messageList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(inflater.inflate(R.layout.view_join_test, parent, false));
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
        private TextView joinName;
        private TextView joinTime;

        public ItemView(View itemView) {
            super(itemView);
            testIndex = itemView.findViewById(R.id.test_grade_desc);
            joinName = itemView.findViewById(R.id.test_join_name);
            joinTime = itemView.findViewById(R.id.test_join_time);
        }

        public void bindData(Object item, final int position) {
            final ExamData datas = (ExamData) item;

            testIndex.setText("" + (position + 1));
            joinName.setText("" + datas.getExamination_name());
            joinTime.setText(datas.getExam_time() + "分钟");


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (GeneralMethod.isFastClick()) {
                        Intent testNow = new Intent();

                        testNow.putExtra("examination_id", datas.getExamination_id());
                        testNow.putExtra("exam_id", datas.getId());
                        testNow.setClass(context, QuestionsActivity.class);
                        context.startActivity(testNow);
                    }
                }
            });


        }
    }


}
