package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/3/23
 * todo : 人员类型分析
 */
public class PersonnelTypeData {

//    "id":0,
//            "staff_age":0,
//            "history_integral":0,
//            "end_integral":0,
//            "section_id":0,
//            "sub_id":0,
//            "worktype_id":0,
//            "station_id":0,
//            "count":5,
//            "type":"劳务人员"

    private int count;
    private String type;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
