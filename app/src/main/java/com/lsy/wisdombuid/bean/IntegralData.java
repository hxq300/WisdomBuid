package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/4/8
 * todo : 积分明细实体
 */
public class IntegralData {

//    "id":12,
//            "content":"安全隐患上传",
//            "up_time":"2020-04-07",
//            "staff_id":8,
//            "integral":"25"

    private int id;
    private String content;
    private String up_time;
    private int staff_id;
    private int integral;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUp_time() {
        return up_time;
    }

    public void setUp_time(String up_time) {
        this.up_time = up_time;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }
}
