package com.lsy.wisdombuid.bean;

/**
 * Created by hxq on 2021/4/27
 * describe :  TODO
 */
public class SvaeEntity {

    /**
     * id : 1
     * section_id : 0
     * station_id : 0
     * girder_name_one : ZC-1
     * girder_name_two : ZC-1
     * storage_pedestal : DC1-1
     * state : 2
     */

    private int id;
    private int section_id;
    private int station_id;
    private String girder_name_one;
    private String girder_name_two;
    private String storage_pedestal;
    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getGirder_name_one() {
        return girder_name_one;
    }

    public void setGirder_name_one(String girder_name_one) {
        this.girder_name_one = girder_name_one;
    }

    public String getGirder_name_two() {
        return girder_name_two;
    }

    public void setGirder_name_two(String girder_name_two) {
        this.girder_name_two = girder_name_two;
    }

    public String getStorage_pedestal() {
        return storage_pedestal;
    }

    public void setStorage_pedestal(String storage_pedestal) {
        this.storage_pedestal = storage_pedestal;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
