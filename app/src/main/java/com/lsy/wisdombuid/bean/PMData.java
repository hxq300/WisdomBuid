package com.lsy.wisdombuid.bean;

import java.util.List;

/**
 * Created by lsy on 2020/3/23
 * todo :
 */
public class PMData {

    private List<WorkTypeData> data;
    private List<SubContractorData> data2;
    private List<PersonnelTypeData> data3;
    private List<AllData> data4;
    private List<AllData> data5;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<WorkTypeData> getData() {
        return data;
    }

    public void setData(List<WorkTypeData> data) {
        this.data = data;
    }

    public List<SubContractorData> getData2() {
        return data2;
    }

    public void setData2(List<SubContractorData> data2) {
        this.data2 = data2;
    }

    public List<PersonnelTypeData> getData3() {
        return data3;
    }

    public void setData3(List<PersonnelTypeData> data3) {
        this.data3 = data3;
    }

    public List<AllData> getData4() {
        return data4;
    }

    public void setData4(List<AllData> data4) {
        this.data4 = data4;
    }

    public List<AllData> getData5() {
        return data5;
    }

    public void setData5(List<AllData> data5) {
        this.data5 = data5;
    }

    public class AllData {
//        "id":0,
//                "staff_age":0,
//                "history_integral":0,
//                "end_integral":0,
//                "section_id":0,
//                "sub_id":0,
//                "worktype_id":0,
//                "station_id":0,
//                "count":11

        private int count;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }


}
