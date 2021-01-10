package com.lsy.wisdombuid.bean;

import java.util.List;

/**
 * Created by hxq on 2021/1/10
 * describe :  TODO
 */
public class CarGpsMessageEntity {

    /**
     * data : [{"alarm":"","altitude":0,"dir":319,"exData":"","imei":"860013350006512","isStop":false,"lat":23.159383,"latc":23.163176,"lon":113.406417,"lonc":113.418384,"pointDt":"2018-07-03 10:23:35","pointType":1,"remark":"","signalMile":0,"speed":9,"status":"5","stopTime":0}]
     * ret : 1
     */

    private int ret;
    private List<DataBean> data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * alarm :
         * altitude : 0
         * dir : 319
         * exData :
         * imei : 860013350006512
         * isStop : false
         * lat : 23.159383
         * latc : 23.163176
         * lon : 113.406417
         * lonc : 113.418384
         * pointDt : 2018-07-03 10:23:35
         * pointType : 1
         * remark :
         * signalMile : 0
         * speed : 9
         * status : 5
         * stopTime : 0
         */

        private String alarm;
        private int altitude;
        private int dir;
        private String exData;
        private String imei;
        private boolean isStop;
        private double lat;
        private double latc;
        private double lon;
        private double lonc;
        private String pointDt;
        private int pointType;
        private String remark;
        private int signalMile;
        private int speed;
        private String status;
        private int stopTime;

        public String getAlarm() {
            return alarm;
        }

        public void setAlarm(String alarm) {
            this.alarm = alarm;
        }

        public int getAltitude() {
            return altitude;
        }

        public void setAltitude(int altitude) {
            this.altitude = altitude;
        }

        public int getDir() {
            return dir;
        }

        public void setDir(int dir) {
            this.dir = dir;
        }

        public String getExData() {
            return exData;
        }

        public void setExData(String exData) {
            this.exData = exData;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public boolean isIsStop() {
            return isStop;
        }

        public void setIsStop(boolean isStop) {
            this.isStop = isStop;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLatc() {
            return latc;
        }

        public void setLatc(double latc) {
            this.latc = latc;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public double getLonc() {
            return lonc;
        }

        public void setLonc(double lonc) {
            this.lonc = lonc;
        }

        public String getPointDt() {
            return pointDt;
        }

        public void setPointDt(String pointDt) {
            this.pointDt = pointDt;
        }

        public int getPointType() {
            return pointType;
        }

        public void setPointType(int pointType) {
            this.pointType = pointType;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getSignalMile() {
            return signalMile;
        }

        public void setSignalMile(int signalMile) {
            this.signalMile = signalMile;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getStopTime() {
            return stopTime;
        }

        public void setStopTime(int stopTime) {
            this.stopTime = stopTime;
        }
    }
}
