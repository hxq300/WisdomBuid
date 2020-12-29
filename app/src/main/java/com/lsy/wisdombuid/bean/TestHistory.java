package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/4/16
 * todo :
 */
public class TestHistory {

//"id":1,
//        "exam_id":8,
//        "exam_name":"施工安全考核——管道工",
//        "staff_id":8,
//        "staff_name":"石胜伟",
//        "section_id":2,
//        "exam_count":28,
//        "exam_time":"2020-04-16",
//        "exam_result":"不合格",
//        "exam_timeC":1586966400000

    private int id;
    private int exam_id;
    private String exam_name;
    private int staff_id;
    private String staff_name;
    private int section_id;
    private int exam_count;
    private String exam_time;
    private String exam_result;
    private long exam_timeC;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

    public int getExam_count() {
        return exam_count;
    }

    public void setExam_count(int exam_count) {
        this.exam_count = exam_count;
    }

    public String getExam_time() {
        return exam_time;
    }

    public void setExam_time(String exam_time) {
        this.exam_time = exam_time;
    }

    public String getExam_result() {
        return exam_result;
    }

    public void setExam_result(String exam_result) {
        this.exam_result = exam_result;
    }

    public long getExam_timeC() {
        return exam_timeC;
    }

    public void setExam_timeC(long exam_timeC) {
        this.exam_timeC = exam_timeC;
    }
}
