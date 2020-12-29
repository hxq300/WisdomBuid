package com.lsy.wisdombuid.activity.exam;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.base.BaseActivity;
import com.lsy.wisdombuid.bean.NewQuestionsData;
import com.lsy.wisdombuid.bean.QuestiondData;
import com.lsy.wisdombuid.bean.SubjectData;
import com.lsy.wisdombuid.mvp.test.ExamInterface;
import com.lsy.wisdombuid.mvp.test.ExamPresent;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.SharedUtils;
import com.lsy.wisdombuid.util.StatusBarUtil;
import com.lsy.wisdombuid.util.TimeUtils;
import com.lsy.wisdombuid.util.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lsy on 2020/4/14
 * todo : 考试试题
 */
public class QuestionsActivity extends BaseActivity implements ExamInterface.View {

    @BindView(R.id.line_just_one)
    LinearLayout lineJustOne;
    private ExamInterface.Presenter presenter;

    private int examination_id = 0;
    private int exam_id = 0;

    private List<Integer> dataIndex;
    private int index = 0;

    //=====选择题（choice question）
    private LinearLayout xuanzeti, pandaunti, tiankongti;
    private TextView choiceIndex, choiceType, choiceContent, choiceA, choiceB, choiceC, choiceD;

    private TextView pageNo;

    private EditText jianda;
    private EditText editOne, editTwo;

    private QuestiondData questionds;

    private int grade = 0;
    private int qType = 1;

    private SharedUtils sharedUtils;

    private TextView countDown;

    /**
     * 倒数计时器
     */
    private CountDownTimer timer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_questions);
        ButterKnife.bind(this);

        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            StatusBarUtil.setStatusBarColor(this, 0xFFA400);
        }

        Intent intent = getIntent();

        examination_id = intent.getIntExtra("examination_id", 0);
        exam_id = intent.getIntExtra("exam_id", 0);

        sharedUtils = new SharedUtils(QuestionsActivity.this, SharedUtils.WISDOM);

        presenter = new ExamPresent(this, QuestionsActivity.this);

        presenter.getExamPaper("" + examination_id);


        initView();


    }

    //初始化布局
    private void initView() {

        xuanzeti = findViewById(R.id.line_xuanzeti);
        pandaunti = findViewById(R.id.line_panduanti);
        tiankongti = findViewById(R.id.line_tiankongti);
        jianda = findViewById(R.id.edit_jianda);
        editOne = findViewById(R.id.questions_edit_one);
        editTwo = findViewById(R.id.questions_edit_two);
        choiceIndex = findViewById(R.id.quest_choice_index);
        choiceType = findViewById(R.id.quest_choice_type);
        choiceContent = findViewById(R.id.quest_choice_content);
        choiceA = findViewById(R.id.quest_choice_a);
        choiceB = findViewById(R.id.quest_choice_b);
        choiceC = findViewById(R.id.quest_choice_c);
        choiceD = findViewById(R.id.quest_choice_d);
        pageNo = findViewById(R.id.quest_page_no);
        countDown = findViewById(R.id.quest_count_down);
        tvChoice = choiceA;

    }

    @Override
    public void setNewExam(NewQuestionsData questionsData) {

    }

    @Override
    public void setExamPaper(QuestiondData questiondData) {


        this.questionds = questiondData;

        if (questionds.getExam_time() > 0) {
            startTimer(questionds.getExam_time());
            timer.start();
        } else {
            startTimer(45);
            timer.start();
        }
        dataIndex = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(questiondData.getQuestions());
            for (int i = 0; i < jsonArray.length(); i++) {
                dataIndex.add(i, jsonArray.getInt(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (dataIndex.size() > 0) {
            presenter.getSubject("" + dataIndex.get(index));

            if (index == 0) {
                pageNo.setText("1/" + dataIndex.size());
            }

        }

    }

    private void startTimer(int time) {
        timer = new CountDownTimer(time * 60 * 1000, 1000) {
            /**
             * 固定间隔被调用,就是每隔countDownInterval会回调一次方法onTick
             * @param millisUntilFinished
             */
            @Override
            public void onTick(long millisUntilFinished) {

                if (millisUntilFinished == 10000) {
                    ToastUtils.showBottomToast(QuestionsActivity.this, "考试时间还有十秒，请尽快提交试卷，考试时间结束将自动提交试卷");
                }

                countDown.setText(TimeUtils.formatTime(millisUntilFinished));
            }

            /**
             * 倒计时完成时被调用
             */
            @Override
            public void onFinish() {
                presenter.addExamRecord(exam_id, sharedUtils.getIntData(SharedUtils.USER_ID), OKHttpClass.getToken(QuestionsActivity.this), grade);
                countDown.setText("00:00");
            }
        };

    }

    private String selectAnswer;//选择题答案
    private boolean isJudge = false;//判断题答案

    private List<String> editAnswer;
    private List<String> editJianda;

    @Override
    public void setSubject(SubjectData subject) {

        choiceIndex.setText(index + 1 + ".");
        choiceType.setText("" + subject.getQuestions_type());
        choiceContent.setText("\u3000\u3000\u3000\u3000\u3000" + subject.getQuestions_content());
        if (tvChoice != null) {
            tvChoice.setBackgroundColor(Color.parseColor("#F6F6F6"));
        }

        selectAnswer = "";

        isJudge = false;
        if (subject.getQuestions_type().equals("选择题")) {
            qType = 1;
            xuanzeti.setVisibility(View.VISIBLE);
            pandaunti.setVisibility(View.GONE);
            tiankongti.setVisibility(View.GONE);
            jianda.setVisibility(View.GONE);
            selectAnswer = subject.getSelect_answer();

            choiceA.setText("A．" + subject.getSelect_A());
            choiceB.setText("B．" + subject.getSelect_B());
            choiceC.setText("C．" + subject.getSelect_C());
            choiceD.setText("D．" + subject.getSelect_D());


        } else if (subject.getQuestions_type().equals("判断题")) {
            isJudge = subject.isJudge_answer();
            qType = 2;
            xuanzeti.setVisibility(View.GONE);
            pandaunti.setVisibility(View.VISIBLE);
            tiankongti.setVisibility(View.GONE);
            jianda.setVisibility(View.GONE);
        } else if (subject.getQuestions_type().equals("填空题")) {
            qType = 3;
            xuanzeti.setVisibility(View.GONE);
            pandaunti.setVisibility(View.GONE);
            tiankongti.setVisibility(View.VISIBLE);
            jianda.setVisibility(View.GONE);

            editAnswer = new ArrayList<>();
            try {
                L.log("exam", "editAnswer==" + subject.getGap_answer());
                JSONArray jsonArray = new JSONArray(subject.getGap_answer());
                for (int i = 0; i < jsonArray.length(); i++) {
//                    L.log("exam", "editAnswer==" + jsonArray.get(i).toString());
                    editAnswer.add(i, jsonArray.getString(i));
                }

                if (editAnswer.size() == 1) {
                    editTwo.setVisibility(View.GONE);
                    lineJustOne.setVisibility(View.GONE);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (subject.getQuestions_type().equals("简答题")) {
            qType = 4;
            xuanzeti.setVisibility(View.GONE);
            pandaunti.setVisibility(View.GONE);
            tiankongti.setVisibility(View.GONE);
            jianda.setVisibility(View.VISIBLE);

            editJianda = new ArrayList<>();
            jianda.setText("答：");
            try {
//                [危险源,存在时间,作业时间,措施,责任人]
                L.log("exam", "editJianda==" + subject.getShort_answer());
                JSONArray jsonArray = new JSONArray(subject.getShort_answer());
                for (int i = 0; i < jsonArray.length(); i++) {
//                    L.log("exam", "editAnswer==" + jsonArray.get(i).toString());
                    editJianda.add(i, jsonArray.getString(i));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    //交卷成功
    @Override
    public void setSuccess() {

        if (timer != null) {
            timer.cancel();
        }

        Intent intent = new Intent(QuestionsActivity.this, ExamSuccessActivity.class);
        startActivity(intent);
        finish();
    }


    private TextView tvChoice;
    private String answer = "O";

    private boolean isAnswer = false;


    //选择题
    public void choiceSelect(View view) {

        tvChoice.setBackgroundColor(Color.parseColor("#F6F6F6"));
        view.setBackgroundColor(Color.parseColor("#b8e1fb"));
        tvChoice = (TextView) view;


        switch (view.getId()) {

            case R.id.quest_choice_a:
                answer = "A";

                if (selectAnswer.equals(answer)) {
                    isAnswer = true;
                } else {
                    isAnswer = false;
                }

                break;

            case R.id.quest_choice_b:
                answer = "B";
                if (selectAnswer.equals(answer)) {
                    isAnswer = true;
                } else {
                    isAnswer = false;
                }
                break;

            case R.id.quest_choice_c:
                answer = "C";
                if (selectAnswer.equals(answer)) {
                    isAnswer = true;
                } else {
                    isAnswer = false;
                }
                break;

            case R.id.quest_choice_d:
                answer = "D";
                if (selectAnswer.equals(answer)) {
                    isAnswer = true;
                } else {
                    isAnswer = false;
                }
                break;

            default:
                break;

        }

    }

    //上一题  下一题
    public void questionsNext(View view) {


        switch (view.getId()) {

//            case R.id.quest_shang:
//                if (index > 1) {
//                    --index;
//                    pageNo.setText(index - 1 + "/" + dataIndex.size());
//                    presenter.getSubject("" + dataIndex.get(index - 1));
//                } else {
////                    ToastUtils.showBottomToast(QuestionsActivity.this, "已经是第一题了");
//                }
//
//                break;

            case R.id.quest_xia:
                if (isCheck()) {
                    if (index < dataIndex.size()-1) {
                        ++index;
                        pageNo.setText(index + 1 + "/" + dataIndex.size());
                        presenter.getSubject("" + dataIndex.get(index));
                    } else {
                        ToastUtils.showBottomToast(QuestionsActivity.this, "已经是最后一题了了");
                    }
                }

                break;

            default:
                break;


        }

        answer = "O";
        judgeS = 0;

    }

    private int lack = 0;

    private boolean isCheck() {

        if (qType == 1) {//选择题
            if (answer.equals("O")) {
                ToastUtils.showBottomToast(QuestionsActivity.this, "未选择答案");
                return false;
            } else {
                if (isAnswer) {
                    grade += questionds.getSelect_count();
//                    ToastUtils.showBottomToast(QuestionsActivity.this, "选对啦！当前分数" + grade);
                }
                return true;
            }
        } else if (qType == 2) {//判断题
            if (judgeS < 1) {
                ToastUtils.showBottomToast(QuestionsActivity.this, "未选择答案");
                return false;
            } else {

                if (isTrue) {
                    grade += questionds.getJudge_count();
                }
                return true;
            }
        } else if (qType == 3) {//填空题

            if (editAnswer.size() >= 2) {
                if (editOne.getText().toString().equals(editAnswer.get(0)) && editTwo.getText().toString().equals(editAnswer.get(1))) {
                    grade += questionds.getGap_count();
                }
            } else if (editAnswer.size() == 1) {
                if (editOne.getText().toString().equals(editAnswer.get(0))) {
                    grade += questionds.getGap_count();
                }
            }
            editOne.setText("");
            editTwo.setText("");

            return true;
        } else if (qType == 4) {//填空题

            lack = 0;

            for (int i = 0; i < editJianda.size(); i++) {
                if (jianda.getText().toString().contains(editJianda.get(i))) {

                } else {
                    lack++;
                }
            }

            L.log("exam", "lack===" + lack);
            if (lack == 0) {
                grade += questionds.getShort_count();
            } else if (lack == editJianda.size()) {
            } else {
                grade += (questionds.getGap_count() - lack);
            }

            return true;
        }

        return true;
    }

    public void back(View view) {
        finish();
    }

    private int judgeS = 0;
    private boolean isTrue = false;

    //判断题
    public void judgeSelect(View view) {

        tvChoice.setBackgroundColor(Color.parseColor("#F6F6F6"));
        view.setBackgroundColor(Color.parseColor("#b8e1fb"));
        tvChoice = (TextView) view;

        switch (view.getId()) {

            case R.id.quest_judge_true:
                judgeS = 1;

                if (isJudge) {
                    isTrue = true;
                } else {
                    isTrue = false;
                }

                break;

            case R.id.quest_judge_false:
                judgeS = 2;

                if (isJudge) {
                    isTrue = false;
                } else {
                    isTrue = true;
                }
                break;
            default:
                break;
        }
    }

    //提交试卷
    public void submitPaper(View view) {

        presenter.addExamRecord(exam_id, sharedUtils.getIntData(SharedUtils.USER_ID), OKHttpClass.getToken(QuestionsActivity.this), grade);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (timer != null) {
            timer.cancel();
        }
    }

}
