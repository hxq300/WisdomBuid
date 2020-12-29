package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/4/13
 * todo :
 */
public class GongXuData {

//        "id":1,
//            "section_id":2,
//            "station_id":1,
//            "process_name":"左侧进洞"

    private int id;
    private int section_id;
    private int station_id;
    private String process_name;

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

    public int getStation_id() {
        return station_id;
    }

    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }

    public String getProcess_name() {
        return process_name;
    }

    public void setProcess_name(String process_name) {
        this.process_name = process_name;
    }
}
