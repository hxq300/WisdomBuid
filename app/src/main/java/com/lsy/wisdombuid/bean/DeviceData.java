package com.lsy.wisdombuid.bean;

import java.util.List;

/**
 * Created by lsy on 2020/4/2
 * todo :
 */
public class DeviceData {

    //"groupId":"101531",
//        "deviceKey":"101531",
//        "deviceAddr":40048949,
//        "nodeID":1,
//        "nodeType":3,
//        "deviceDisabled":false,
//        "deviceName":"PM",
//        "lng":0,
//        "lat":0,
//        "deviceStatus":2,
//        "realTimeData":[]

    private String groupId;//组编号
    private String deviceKey;//设备编号
    private int deviceAddr;//设备地址
    private int nodeID;//节点编号
    private int nodeType;//节点类型1:模拟量1启用;2:模拟量2启用;3:同时启用
    private boolean deviceDisabled;//停用状态，true停用
    private String deviceName;//设备名称
    private Float lng;//经度
    private Float lat;//维度
    private int deviceStatus;//设备运行状态，0未运行，1离线，2在线

    private List<RealData> realTimeData;//


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

    public int getDeviceAddr() {
        return deviceAddr;
    }

    public void setDeviceAddr(int deviceAddr) {
        this.deviceAddr = deviceAddr;
    }

    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    public int getNodeType() {
        return nodeType;
    }

    public void setNodeType(int nodeType) {
        this.nodeType = nodeType;
    }

    public boolean isDeviceDisabled() {
        return deviceDisabled;
    }

    public void setDeviceDisabled(boolean deviceDisabled) {
        this.deviceDisabled = deviceDisabled;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public int getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(int deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public List<RealData> getRealTimeData() {
        return realTimeData;
    }

    public void setRealTimeData(List<RealData> realTimeData) {
        this.realTimeData = realTimeData;
    }

    public class RealData {

//        "dataName":"PM10(ug/m³)",
//                "dataValue":"48.00",
//                "isAlarm":false,
//                "alarmMsg":"",
//                "alarm":false

        private String dataName;//模拟量名称
        private String dataValue;//实时数据
        private Boolean isAlarm;//是否报警
        private String alarmMsg;//报警信息

        public String getDataName() {
            return dataName;
        }

        public void setDataName(String dataName) {
            this.dataName = dataName;
        }

        public String getDataValue() {
            return dataValue;
        }

        public void setDataValue(String dataValue) {
            this.dataValue = dataValue;
        }

        public Boolean getAlarm() {
            return isAlarm;
        }

        public void setAlarm(Boolean alarm) {
            isAlarm = alarm;
        }

        public String getAlarmMsg() {
            return alarmMsg;
        }

        public void setAlarmMsg(String alarmMsg) {
            this.alarmMsg = alarmMsg;
        }

        @Override
        public String toString() {
            return "{" +
                    "dataName='" + dataName + '\'' +
                    ", dataValue='" + dataValue + '\'' +
                    ", isAlarm=" + isAlarm +
                    ", alarmMsg='" + alarmMsg + '\'' +
                    '}';
        }
    }
}
