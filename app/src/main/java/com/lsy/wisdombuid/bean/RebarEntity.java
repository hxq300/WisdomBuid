package com.lsy.wisdombuid.bean;

/**
 * Created by hxq on 2021/4/27
 * describe :  TODO
 */
public class RebarEntity {

    /**
     * girder_name : string
     * id : 0
     * rebar_name : string
     * section_id : 0
     * state : string
     * station_id : 0
     */

    private String girder_name;
    private int id;
    private String rebar_name;
    private int section_id;
    private String state;
    private int station_id;

    public String getGirder_name() {
        return girder_name;
    }

    public void setGirder_name(String girder_name) {
        this.girder_name = girder_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRebar_name() {
        return rebar_name;
    }

    public void setRebar_name(String rebar_name) {
        this.rebar_name = rebar_name;
    }

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getStation_id() {
        return station_id;
    }

    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }
}
