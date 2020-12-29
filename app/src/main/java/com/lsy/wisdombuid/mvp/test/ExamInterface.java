package com.lsy.wisdombuid.mvp.test;

import com.lsy.wisdombuid.bean.NewQuestionsData;
import com.lsy.wisdombuid.bean.PMData;
import com.lsy.wisdombuid.bean.QuestiondData;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.bean.SubjectData;

import java.util.List;

/**
 * Created by lsy on 2020/4/14
 * todo :
 */
public interface ExamInterface {

    interface Model {

        void getNewExam(String section_id);//section_id

        void getExamPaper(String examination_id);//获取试卷  examination_id（考试接口返回）

        void getSubject(String id);//查询题目  传参：id

        //        exam_id,staff_id,section_id ,exam_count
        void addExamRecord(int exam_id, int staff_id, int section_id, int exam_count);//提交考试

    }

    interface View {
        //setData方法是为了 Activity实现view接口以后，重写这个方法就可以得到数据，为View赋值
        void setNewExam(NewQuestionsData questionsData);//

        void setExamPaper(QuestiondData questiondData);//

        void setSubject(SubjectData subject);//

        void setSuccess();//
    }

    interface Presenter {
        void getNewExam(String section_id);//section_id

        void getExamPaper(String examination_id);//

        void getSubject(String id);//

        void addExamRecord(int exam_id, int staff_id, int section_id, int exam_count);//提交考试

        //获取数据以后回调
        void responseNewExam(NewQuestionsData questionsData);

        void responseExamPaper(QuestiondData questiondData);

        void responseSubject(SubjectData subjectData);

        void responseSuccess();

        void distory();
    }

}
