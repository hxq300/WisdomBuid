package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/4/16
 * todo :
 */
public class ExamData {

//    "id":6,
//            "exam_name":"施工安全考核——钢筋工",
//            "section_id":2,
//            "specialty_id":2,
//            "specialty_name":"施工安全",
//            "examination_id":2,
//            "examination_name":"施工安全考核",
//            "worktype_id":3,
//            "worktype_name":"钢筋工",
//            "exam_time":45,
//            "start_time":"2020-04-01",
//            "end_time":"2020-05-01",
//            "up_time":"2020-04-12 15:19:16",
//            "start_timeC":1586620800000,
//            "end_timeC":1586620800000


    private int id;
    private String exam_name;
    private int section_id;
    private int specialty_id;
    private String specialty_name;
    private int examination_id;
    private String examination_name;
    private int worktype_id;
    private String worktype_name;
    private int exam_time;
    private String start_time;
    private String end_time;
    private String up_time;
    private long start_timeC;
    private long end_timeC;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
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

    public int getExamination_id() {
        return examination_id;
    }

    public void setExamination_id(int examination_id) {
        this.examination_id = examination_id;
    }

    public String getExamination_name() {
        return examination_name;
    }

    public void setExamination_name(String examination_name) {
        this.examination_name = examination_name;
    }

    public int getWorktype_id() {
        return worktype_id;
    }

    public void setWorktype_id(int worktype_id) {
        this.worktype_id = worktype_id;
    }

    public String getWorktype_name() {
        return worktype_name;
    }

    public void setWorktype_name(String worktype_name) {
        this.worktype_name = worktype_name;
    }

    public int getExam_time() {
        return exam_time;
    }

    public void setExam_time(int exam_time) {
        this.exam_time = exam_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getUp_time() {
        return up_time;
    }

    public void setUp_time(String up_time) {
        this.up_time = up_time;
    }

    public long getStart_timeC() {
        return start_timeC;
    }

    public void setStart_timeC(long start_timeC) {
        this.start_timeC = start_timeC;
    }

    public long getEnd_timeC() {
        return end_timeC;
    }

    public void setEnd_timeC(long end_timeC) {
        this.end_timeC = end_timeC;
    }
}
