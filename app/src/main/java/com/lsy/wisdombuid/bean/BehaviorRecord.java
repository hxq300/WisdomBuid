package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/4/29
 * todo : 行为记录
 */
public class BehaviorRecord {

//    "id":3,
//            "staff_id":8,
//            "behavior_id":6,
//            "section_id":2,
//            "station_id":1,
//            "uptime":"2020-03-22",
//            "remark":"9点半才到",
//            "staff_name":"石胜伟",
//            "behavior_name":"迟到",
//            "section_name":"苏州轨道交通S1线TG06标",
//            "station_name":"鹿城路站"

    private int id;
    private int staff_id;
    private int behavior_id;
    private int section_id;
    private int station_id;
    private String uptime;
    private String remark;
    private String staff_name;
    private String behavior_name;
    private String section_name;
    private String station_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public int getBehavior_id() {
        return behavior_id;
    }

    public void setBehavior_id(int behavior_id) {
        this.behavior_id = behavior_id;
    }

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

    public int getStation_id() {
        return station_id;
    }

    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getBehavior_name() {
        return behavior_name;
    }

    public void setBehavior_name(String behavior_name) {
        this.behavior_name = behavior_name;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }
}
