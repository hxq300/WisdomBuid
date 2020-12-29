package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/3/23
 * todo : 站点数据
 */
public class StationData {
//"id":1,
//        "station_time":200,
//        "section_id":2,
//        "station_name":"鹿城路站",
//        "start_time":"2019-05-20",
//        "end_time":"2020-05-20",
//        "station_principal":"交捷交通",
//        "station_area":"300000",
//        "userId":"31439",
//        "groupId":"101531",
//        "deviceKey":"101531"

    private int id;
    private int station_time;
    private int section_id;
    private String start_time;
    private String end_time;
    private String station_name;
    private String station_principal;
    private String station_area;
    private String userId;
    private String groupId;
    private String deviceKey;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStation_time() {
        return station_time;
    }

    public void setStation_time(int station_time) {
        this.station_time = station_time;
    }

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getStation_principal() {
        return station_principal;
    }

    public void setStation_principal(String station_principal) {
        this.station_principal = station_principal;
    }

    public String getStation_area() {
        return station_area;
    }

    public void setStation_area(String station_area) {
        this.station_area = station_area;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getDeviceKey() {
        return deviceKey;
    }

    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey;
    }
}
