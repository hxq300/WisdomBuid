package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/3/23
 * todo : 分包单位
 */
public class SubContractorData {
//    "id":0,
//            "staff_age":0,
//            "history_integral":0,
//            "end_integral":0,
//            "section_id":0,
//            "sub_id":1,
//            "worktype_id":0,
//            "station_id":0,
//            "count":7,
//            "sub_name":"上海五保公司"


    private int count;
    private String sub_name;

    public SubContractorData(int count, String sub_name) {
        this.count = count;
        this.sub_name = sub_name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }
}
