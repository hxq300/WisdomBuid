package com.lsy.wisdombuid.bean;

import java.util.List;

/**
 * Created by hxq on 2021/1/8
 * describe :  TODO
 */
public class SafetyTypeEntity {

    /**
     * message : null
     * data : [{"id":0,"risk_id":0,"section_id":0,"station_id":0,"risk_name":"施工设备隐患","sub_id":0,"count":"1","process_id":0,"staff_id":0,"awarda":0},{"id":0,"risk_id":0,"section_id":0,"station_id":0,"risk_name":"宿舍隐患","sub_id":0,"count":"1","process_id":0,"staff_id":0,"awarda":0},{"id":0,"risk_id":0,"section_id":0,"station_id":0,"risk_name":"现场环境隐患","sub_id":0,"count":"7","process_id":0,"staff_id":0,"awarda":0},{"id":0,"risk_id":0,"section_id":0,"station_id":0,"risk_name":"场地设备","sub_id":0,"count":"1","process_id":0,"staff_id":0,"awarda":0}]
     * data2 : null
     * data3 : null
     * data4 : null
     * data5 : null
     * code : null
     */

    private Object message;
    private Object data2;
    private Object data3;
    private Object data4;
    private Object data5;
    private Object code;
    private List<DataBean> data;

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getData2() {
        return data2;
    }

    public void setData2(Object data2) {
        this.data2 = data2;
    }

    public Object getData3() {
        return data3;
    }

    public void setData3(Object data3) {
        this.data3 = data3;
    }

    public Object getData4() {
        return data4;
    }

    public void setData4(Object data4) {
        this.data4 = data4;
    }

    public Object getData5() {
        return data5;
    }

    public void setData5(Object data5) {
        this.data5 = data5;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 0
         * risk_id : 0
         * section_id : 0
         * station_id : 0
         * risk_name : 施工设备隐患
         * sub_id : 0
         * count : 1
         * process_id : 0
         * staff_id : 0
         * awarda : 0
         */

        private int id;
        private int risk_id;
        private int section_id;
        private int station_id;
        private String risk_name;
        private int sub_id;
        private String count;
        private int process_id;
        private int staff_id;
        private int awarda;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRisk_id() {
            return risk_id;
        }

        public void setRisk_id(int risk_id) {
            this.risk_id = risk_id;
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

        public String getRisk_name() {
            return risk_name;
        }

        public void setRisk_name(String risk_name) {
            this.risk_name = risk_name;
        }

        public int getSub_id() {
            return sub_id;
        }

        public void setSub_id(int sub_id) {
            this.sub_id = sub_id;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public int getProcess_id() {
            return process_id;
        }

        public void setProcess_id(int process_id) {
            this.process_id = process_id;
        }

        public int getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(int staff_id) {
            this.staff_id = staff_id;
        }

        public int getAwarda() {
            return awarda;
        }

        public void setAwarda(int awarda) {
            this.awarda = awarda;
        }
    }
}