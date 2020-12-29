package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/4/14
 * todo :
 */
public class SubjectData {

//    "id":1,
//            "section_id":2,
//            "knowledge_id":2,
//            "specialty_id":1,
//            "questions_type":"选择题",
//            "knowledge_name":"预应力技术",
//            "specialty_name":"施工技术",
//            "questions_content":"钢筋笼存在下垫宜使用",
//            "select_A":"工字钢",
//            "select_B":"方木",
//            "select_C":"铁架",
//            "select_D":"泥土地",
//            "select_answer":"B"

//            "questions_type":"判断题",
//            "judge_answer":true

//            "questions_type":"填空题",
//            "gap_answer":"[φ1000/800,φ1000]"

//            "questions_type":"简答题",
//            "short_answer":"[危险源，存在时间，作业时间，措施，责任人]"


    private int id;
    private int section_id;
    private int knowledge_id;
    private int specialty_id;
    private String questions_type;
    private String knowledge_name;
    private String specialty_name;
    private String questions_content;
    private String select_A;
    private String select_B;
    private String select_C;
    private String select_D;
    private String select_answer;
    private boolean judge_answer;
    private String short_answer;

    public String getShort_answer() {
        return short_answer;
    }

    public void setShort_answer(String short_answer) {
        this.short_answer = short_answer;
    }

    private String gap_answer;

    public String getGap_answer() {
        return gap_answer;
    }

    public void setGap_answer(String gap_answer) {
        this.gap_answer = gap_answer;
    }

    public boolean isJudge_answer() {
        return judge_answer;
    }

    public void setJudge_answer(boolean judge_answer) {
        this.judge_answer = judge_answer;
    }

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

    public int getKnowledge_id() {
        return knowledge_id;
    }

    public void setKnowledge_id(int knowledge_id) {
        this.knowledge_id = knowledge_id;
    }

    public int getSpecialty_id() {
        return specialty_id;
    }

    public void setSpecialty_id(int specialty_id) {
        this.specialty_id = specialty_id;
    }

    public String getQuestions_type() {
        return questions_type;
    }

    public void setQuestions_type(String questions_type) {
        this.questions_type = questions_type;
    }

    public String getKnowledge_name() {
        return knowledge_name;
    }

    public void setKnowledge_name(String knowledge_name) {
        this.knowledge_name = knowledge_name;
    }

    public String getSpecialty_name() {
        return specialty_name;
    }

    public void setSpecialty_name(String specialty_name) {
        this.specialty_name = specialty_name;
    }

    public String getQuestions_content() {
        return questions_content;
    }

    public void setQuestions_content(String questions_content) {
        this.questions_content = questions_content;
    }

    public String getSelect_A() {
        return select_A;
    }

    public void setSelect_A(String select_A) {
        this.select_A = select_A;
    }

    public String getSelect_B() {
        return select_B;
    }

    public void setSelect_B(String select_B) {
        this.select_B = select_B;
    }

    public String getSelect_C() {
        return select_C;
    }

    public void setSelect_C(String select_C) {
        this.select_C = select_C;
    }

    public String getSelect_D() {
        return select_D;
    }

    public void setSelect_D(String select_D) {
        this.select_D = select_D;
    }

    public String getSelect_answer() {
        return select_answer;
    }

    public void setSelect_answer(String select_answer) {
        this.select_answer = select_answer;
    }
}
