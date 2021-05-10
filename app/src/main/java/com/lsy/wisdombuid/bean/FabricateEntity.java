package com.lsy.wisdombuid.bean;

/**
 * Created by hxq on 2021/4/27
 * describe :  TODO
 */
public class FabricateEntity {

    /**
     * girder_name : string
     * id : 0
     * manufacture_pedestal : string
     * section_id : 0
     * state : string
     * station_id : 0
     */

    private String girder_name;
    private int id;
    private String manufacture_pedestal;
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

    public String getManufacture_pedestal() {
        return manufacture_pedestal;
    }

    public void setManufacture_pedestal(String manufacture_pedestal) {
        this.manufacture_pedestal = manufacture_pedestal;
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
