package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/4/5
 * todo : 我的消息实体
 */
public class MessageData {

//    "id":2,
//            "staff_id":8,
//            "station_id":2,
//            "station_name":"西藏北路站",
//            "uptime":"2020-04-04",
//            "nowtime":null
//    "noC":"20200405185322"

    private int id;
    private int staff_id;
    private int station_id;
    private String station_name;
    private String uptime;
    private String nowtime;
    private String noC;

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

    public int getStation_id() {
        return station_id;
    }

    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getNowtime() {
        return nowtime;
    }

    public void setNowtime(String nowtime) {
        this.nowtime = nowtime;
    }

    public String getNoC() {
        return noC;
    }

    public void setNoC(String noC) {
        this.noC = noC;
    }
}
