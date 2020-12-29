package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/4/29
 * todo :
 */
public class RenyuanData {

//    "id":1,
//            "section_id":2,
//            "staff_name":"阿斯顿",
//            "train_name":"安全带体验",
//            "uptime":"2020-04-29"

    private int id;
    private int section_id;
    private String staff_name;
    private String train_name;
    private String uptime;
    private String staff_img;

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

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getTrain_name() {
        return train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getStaff_img() {
        return staff_img;
    }

    public void setStaff_img(String staff_img) {
        this.staff_img = staff_img;
    }
}
