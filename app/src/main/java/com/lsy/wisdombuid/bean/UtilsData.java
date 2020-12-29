package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/4/13
 * todo :
 */
public class UtilsData {
//    "id":1,
//            "person_count":0,
//            "section_id":2,
//            "station_id":0,
//            "subcontractors_name":"无"

    private int id;
    private int person_count;
    private int section_id;
    private int station_id;
    private String subcontractors_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerson_count() {
        return person_count;
    }

    public void setPerson_count(int person_count) {
        this.person_count = person_count;
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

    public String getSubcontractors_name() {
        return subcontractors_name;
    }

    public void setSubcontractors_name(String subcontractors_name) {
        this.subcontractors_name = subcontractors_name;
    }
}
