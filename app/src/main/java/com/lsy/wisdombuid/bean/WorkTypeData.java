package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/3/23
 * todo : 工种分析
 */
public class WorkTypeData {
//    "id":0,
//            "staff_age":0,
//            "history_integral":0,
//            "end_integral":0,
//            "section_id":0,
//            "sub_id":0,
//            "worktype_id":1,
//            "station_id":0,
//            "count":7,
//            "worktype_name":"电工"

    private int count;
    private String worktype_name;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getWorktype_name() {
        return worktype_name;
    }

    public void setWorktype_name(String worktype_name) {
        this.worktype_name = worktype_name;
    }
}
