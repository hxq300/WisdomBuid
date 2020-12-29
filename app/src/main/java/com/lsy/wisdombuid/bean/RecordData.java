package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/4/8
 * todo : 兑换记录实体
 */
public class RecordData {

//    "id":1,
//            "staff_id":3,
//            "commodity_id":1,
//            "section_id":2,
//            "station_id":1,
//            "sub_id":1,
//            "conversion_time":"2020-03-23",
//            "active":"请取件",
//            "staff_name":"ssw",
//            "commodity_name":"宝宝幼儿童80抽5包大包装",
//            "station_name":"鹿城路站",
//            "sub_name":"无",
//            "commodity_img":"宝宝幼儿童80抽5包大包装.png"

    private int id;
    private int staff_id;
    private int commodity_id;
    private int section_id;
    private int station_id;
    private int sub_id;
    private String conversion_time;
    private String active;
    private String staff_name;
    private String commodity_name;
    private String station_name;
    private String sub_name;
    private String commodity_img;

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

    public int getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(int commodity_id) {
        this.commodity_id = commodity_id;
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

    public int getSub_id() {
        return sub_id;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }

    public String getConversion_time() {
        return conversion_time;
    }

    public void setConversion_time(String conversion_time) {
        this.conversion_time = conversion_time;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getCommodity_img() {
        return commodity_img;
    }

    public void setCommodity_img(String commodity_img) {
        this.commodity_img = commodity_img;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", staff_id=" + staff_id +
                ", commodity_id=" + commodity_id +
                ", section_id=" + section_id +
                ", station_id=" + station_id +
                ", sub_id=" + sub_id +
                ", conversion_time='" + conversion_time + '\'' +
                ", active='" + active + '\'' +
                ", staff_name='" + staff_name + '\'' +
                ", commodity_name='" + commodity_name + '\'' +
                ", station_name='" + station_name + '\'' +
                ", sub_name='" + sub_name + '\'' +
                ", commodity_img='" + commodity_img + '\'' +
                '}';
    }
}
