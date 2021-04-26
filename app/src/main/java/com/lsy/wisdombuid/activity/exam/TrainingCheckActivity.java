package com.lsy.wisdombuid.activity.exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.MyBaseActivity;
import com.lsy.wisdombuid.bean.NewQuestionsData;
import com.lsy.wisdombuid.bean.QuestiondData;
import com.lsy.wisdombuid.bean.SubjectData;
import com.lsy.wisdombuid.mvp.test.ExamInterface;
import com.lsy.wisdombuid.mvp.test.ExamPresent;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.TimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lsy on 2020/3/25
 * todo : 培训考核
 */
public class TrainingCheckActivity extends MyBaseActivity implements ExamInterface.View {

    private LinearLayout lineExam;

    private ExamInterface.Presenter presenter;

    private TextView trainNotes, pagerName, testTime, nowTime;

    private int examination_id = 0;
    private int exam_id = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_check);
        ButterKnife.bind(this);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }
        titleBar = findViewById(R.id.title_bar);
        initTitle();


        initView();

        presenter = new ExamPresent(this, TrainingCheckActivity.this);

        presenter.getNewExam("" + OKHttpClass.getToken(TrainingCheckActivity.this));


    }

    private void initView() {

        trainNotes = findViewById(R.id.training_notes);
        pagerName = findViewById(R.id.training_pager_name);
        testTime = findViewById(R.id.training_test_time);
        nowTime = findViewById(R.id.training_now_time);

        lineExam = findViewById(R.id.line_exam);

        long nowT = System.currentTimeMillis();

        nowTime.setText("" + TimeUtils.toDate("" + nowT));

        lineExam.getBackground().mutate().setAlpha(23);
    }

    @Override
    protected void initTitle() {
        if (titleBar == null) {

        } else {
            titleBar.setTitle("" + getString(R.string.training_and_check));
        }
    }

    public void testInto(View view) {

        switch (view.getId()) {

            case R.id.record_cv://考试记录
                Intent record = new Intent(TrainingCheckActivity.this, TestRecordsActivity.class);
                startActivity(record);
                break;

            case R.id.test_test_now://参加考试(直接进入试题)
                Intent testNow = new Intent();
                testNow.putExtra("examination_id", examination_id);
                testNow.putExtra("exam_id", exam_id);
                testNow.setClass(TrainingCheckActivity.this, QuestionsActivity.class);
                startActivity(testNow);
                break;

            case R.id.exam_cv://参加考试
                Intent joinTest = new Intent(TrainingCheckActivity.this, JoinTestActivity.class);
                startActivity(joinTest);
                break;

            case R.id.result_cv://我的成绩
                Intent myGrade = new Intent(TrainingCheckActivity.this, MyGradeActivity.class);
                startActivity(myGrade);
                break;

            case R.id.train_cv://培训课程
                Intent courses = new Intent(TrainingCheckActivity.this, TrainingCoursesActivity.class);
                startActivity(courses);
                break;


            default:
                break;
        }

    }

    @Override
    public void setNewExam(NewQuestionsData questionsData) {
        if (questionsData != null) {
            presenter.getExamPaper("" + questionsData.getExamination_id());
            examination_id = questionsData.getExamination_id();
            exam_id = questionsData.getId();
            trainNotes.setText("" + questionsData.getExam_name());
            pagerName.setText("" + questionsData.getExamination_name());
            testTime.setText(questionsData.getExam_time() + "分钟");
        } else {
            lineExam.setVisibility(View.GONE);
        }


    }

    @Override
    public void setExamPaper(QuestiondData questiondData) {

    }

    @Override
    public void setSubject(SubjectData subject) {

    }

    @Override
    public void setSuccess() {

    }


}
