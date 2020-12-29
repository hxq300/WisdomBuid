package com.lsy.wisdombuid.mvp.test;

import android.content.Context;

import com.lsy.wisdombuid.bean.NewQuestionsData;
import com.lsy.wisdombuid.bean.QuestiondData;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.bean.SubjectData;

import java.util.List;

/**
 * Created by lsy on 2020/4/14
 * todo :
 */
public class ExamPresent implements ExamInterface.Presenter {

    private ExamInterface.View view;
    private ExamInterface.Model model;

    private Context context;

    public ExamPresent(ExamInterface.View view, Context context) {
        this.view = view;
        this.context = context;
        this.model = new ExamModel(this, context);
    }

    @Override
    public void getNewExam(String section_id) {
        model.getNewExam(section_id);
    }

    @Override
    public void getExamPaper(String examination_id) {
        model.getExamPaper(examination_id);
    }

    @Override
    public void getSubject(String id) {
        model.getSubject(id);
    }

    @Override
    public void addExamRecord(int exam_id, int staff_id, int section_id, int exam_count) {
        model.addExamRecord(exam_id, staff_id, section_id, exam_count);
    }

    @Override
    public void responseNewExam(NewQuestionsData questionsData) {
        view.setNewExam(questionsData);
    }

    @Override
    public void responseExamPaper(QuestiondData questiondData) {
        view.setExamPaper(questiondData);
    }

    @Override
    public void responseSubject(SubjectData subjectData) {
        view.setSubject(subjectData);
    }

    @Override
    public void responseSuccess() {
        view.setSuccess();
    }

    @Override
    public void distory() {
        view = null;
    }
}
