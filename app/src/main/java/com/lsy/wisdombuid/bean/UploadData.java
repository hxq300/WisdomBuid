package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/4/13
 * todo :
 */
public class UploadData {

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
    private int station_id;
    private String process_name;
    private int person_count;
    private String subcontractors_name;

    //质量
//     "id":1,
//             "section_id":0,
//             "station_id":0,
//             "quality_category":"材料质量1"

    private String quality_category;

    //安全
//    "id":1,
//            "section_id":0,
//            "station_id":0,
//            "risk_category":"施工设备隐患"

    private String risk_category;

    public String getQuality_category() {
        return quality_category;
    }

    public void setQuality_category(String quality_category) {
        this.quality_category = quality_category;
    }

    public String getRisk_category() {
        return risk_category;
    }

    public void setRisk_category(String risk_category) {
        this.risk_category = risk_category;
    }

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

    public int getPerson_count() {
        return person_count;
    }

    public void setPerson_count(int person_count) {
        this.person_count = person_count;
    }

    public String getSubcontractors_name() {
        return subcontractors_name;
    }

    public void setSubcontractors_name(String subcontractors_name) {
        this.subcontractors_name = subcontractors_name;
    }
}
