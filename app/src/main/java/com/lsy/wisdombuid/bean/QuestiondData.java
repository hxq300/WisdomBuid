package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/4/14
 * todo : 试卷
 */
public class QuestiondData {
//    "id":2,
//            "section_id":2,
//            "specialty_id":1,
//            "specialty_name":"施工技术",
//            "examination_name":"施工安全考核",
//            "questions":"[1,2,3,4,5,6,7,8,9,10,15,16,17,18,19,25,23]",
//            "select_count":6,
//            "judge_count":5,
//            "gap_count":5,
//            "short_count":10,
//            "exam_time":45

    private int id;
    private int section_id;
    private int specialty_id;
    private String specialty_name;
    private String examination_name;
    private String questions;//题目数组
    private int select_count;//选择题分数
    private int judge_count;//判断题分数
    private int gap_count;//填空题分数
    private int short_count;//简答题分数
    private int exam_time;//考试时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

    public int getSpecialty_id() {
        return specialty_id;
    }

    public void setSpecialty_id(int specialty_id) {
        this.specialty_id = specialty_id;
    }

    public String getSpecialty_name() {
        return specialty_name;
    }

    public void setSpecialty_name(String specialty_name) {
        this.specialty_name = specialty_name;
    }

    public String getExamination_name() {
        return examination_name;
    }

    public void setExamination_name(String examination_name) {
        this.examination_name = examination_name;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public int getSelect_count() {
        return select_count;
    }

    public void setSelect_count(int select_count) {
        this.select_count = select_count;
    }

    public int getJudge_count() {
        return judge_count;
    }

    public void setJudge_count(int judge_count) {
        this.judge_count = judge_count;
    }

    public int getGap_count() {
        return gap_count;
    }

    public void setGap_count(int gap_count) {
        this.gap_count = gap_count;
    }

    public int getShort_count() {
        return short_count;
    }

    public void setShort_count(int short_count) {
        this.short_count = short_count;
    }

    public int getExam_time() {
        return exam_time;
    }

    public void setExam_time(int exam_time) {
        this.exam_time = exam_time;
    }
}
