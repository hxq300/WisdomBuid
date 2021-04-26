package com.lsy.wisdombuid.bean;

import java.util.List;

/**
 * Created by hxq on 2021/3/22
 * describe :  TODO
 */
public class CarIdEntity {

    /**
     * data : [{"activeTime":"2021-01-09","carGroupId":50001707,"carId":161328,"carNO":"豫P·9C303","carType":8,"driverName":"","driverTel":"","imei":"863013050006264","joinTime":"2021-01-06","machineName":"⑥豫P·9C303","machineType":85,"password":"2CE0F90D08CC5BD68F5591C452EEF2D2","platformTime":"2099-09-09","remark":"","saleTime":"2021-01-07","serviceState":0,"serviceTime":"2099-09-09","simNO":"1440426667603","updateTime":"2021-03-10","userId":5372},{"activeTime":"2021-01-09","carGroupId":50001707,"carId":161329,"carNO":"豫A·Z6712","carType":8,"driverName":"","driverTel":"","imei":"863013050007296","joinTime":"2021-01-06","machineName":"⑪豫A·Z6712","machineType":85,"password":"28639544D045117A9BF2A99877C8AD0D","platformTime":"2099-09-09","remark":"","saleTime":"2021-01-07","serviceState":0,"serviceTime":"2099-09-09","simNO":"1440426667605","updateTime":"2021-01-16","userId":5372},{"activeTime":"2021-01-09","carGroupId":50001707,"carId":161330,"carNO":"豫A·L2851","carType":8,"driverName":"","driverTel":"","imei":"863013050007197","joinTime":"2021-01-06","machineName":"⑨豫A·L2851","machineType":85,"password":"5CE6B4230EAC35B0D9BACA57C24E349C","platformTime":"2099-09-09","remark":"","saleTime":"2021-01-07","serviceState":0,"serviceTime":"2099-09-09","simNO":"1440426667606","updateTime":"2021-01-16","userId":5372},{"activeTime":"2021-01-09","carGroupId":50001707,"carId":161331,"carNO":"豫Q·D5132","carType":8,"driverName":"","driverTel":"","imei":"863013050006504","joinTime":"2021-01-06","machineName":"③豫Q·D5132","machineType":85,"password":"ECB74383B57B4C0227CCBB879B10C0D6","platformTime":"2099-09-09","remark":"","saleTime":"2021-01-07","serviceState":0,"serviceTime":"2099-09-09","simNO":"1440426667609","updateTime":"2021-01-16","userId":5372},{"activeTime":"2021-01-09","carGroupId":50001707,"carId":161333,"carNO":"鲁H·85S02","carType":8,"driverName":"","driverTel":"","imei":"863013050006207","joinTime":"2021-01-06","machineName":"⑩鲁H·85S02","machineType":85,"password":"F8BAC9AA564F0D73024AECF78C2958AB","platformTime":"2099-09-09","remark":"","saleTime":"2021-01-07","serviceState":0,"serviceTime":"2099-09-09","simNO":"1440426667607","updateTime":"2021-01-16","userId":5372},{"activeTime":"2021-01-09","carGroupId":50001707,"carId":161334,"carNO":"豫N·82273","carType":8,"driverName":"","driverTel":"","imei":"863013050006876","joinTime":"2021-01-06","machineName":"⑮豫N·82273","machineType":85,"password":"AAF0A0A23C6A46C4DE480323FD17967B","platformTime":"2099-09-09","remark":"","saleTime":"2021-01-07","serviceState":0,"serviceTime":"2099-09-09","simNO":"1440426667608","updateTime":"2021-01-16","userId":5372},{"activeTime":"2021-01-10","carGroupId":50001707,"carId":162249,"carNO":"S105L-007585","carType":8,"driverName":"","driverTel":"","imei":"863013050007585","joinTime":"2021-01-10","machineName":"⑦豫P·1Z989","machineType":85,"password":"C0856FEA66B14B74E4A8E33FD9C3055A","platformTime":"2022-01-10","remark":"","serviceState":0,"serviceTime":"2022-01-10","simNO":"1440426667610","updateTime":"2021-01-16","userId":5372},{"activeTime":"2021-01-16","carGroupId":50001707,"carId":163195,"carNO":"S105L-000887","carType":8,"driverName":"","driverTel":"","imei":"863013050000887","joinTime":"2021-01-12","machineName":"②鲁H·18T16","machineType":85,"password":"7771E107141438A18192F422C50E8B0A","platformTime":"2022-01-16","remark":"","serviceState":0,"serviceTime":"2022-01-16","simNO":"1440426667504","updateTime":"2021-01-16","userId":5372},{"activeTime":"2021-01-16","carGroupId":50001707,"carId":163196,"carNO":"S105L-001422","carType":8,"driverName":"","driverTel":"","imei":"863013050001422","joinTime":"2021-01-12","machineName":"①豫P·G4326","machineType":85,"password":"C016C0582A02E525E6D77EE49022DD82","platformTime":"2022-01-16","remark":"","serviceState":0,"serviceTime":"2022-01-16","simNO":"1440426667505","updateTime":"2021-01-16","userId":5372}]
     * ret : 1
     * time : 1616399817902
     */

    private int ret;
    private long time;
    private List<DataBean> data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * activeTime : 2021-01-09
         * carGroupId : 50001707
         * carId : 161328
         * carNO : 豫P·9C303
         * carType : 8
         * driverName :
         * driverTel :
         * imei : 863013050006264
         * joinTime : 2021-01-06
         * machineName : ⑥豫P·9C303
         * machineType : 85
         * password : 2CE0F90D08CC5BD68F5591C452EEF2D2
         * platformTime : 2099-09-09
         * remark :
         * saleTime : 2021-01-07
         * serviceState : 0
         * serviceTime : 2099-09-09
         * simNO : 1440426667603
         * updateTime : 2021-03-10
         * userId : 5372
         */

        private String activeTime;
        private int carGroupId;
        private int carId;
        private String carNO;
        private int carType;
        private String driverName;
        private String driverTel;
        private String imei;
        private String joinTime;
        private String machineName;
        private int machineType;
        private String password;
        private String platformTime;
        private String remark;
        private String saleTime;
        private int serviceState;
        private String serviceTime;
        private String simNO;
        private String updateTime;
        private int userId;

        public String getActiveTime() {
            return activeTime;
        }

        public void setActiveTime(String activeTime) {
            this.activeTime = activeTime;
        }

        public int getCarGroupId() {
            return carGroupId;
        }

        public void setCarGroupId(int carGroupId) {
            this.carGroupId = carGroupId;
        }

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }

        public String getCarNO() {
            return carNO;
        }

        public void setCarNO(String carNO) {
            this.carNO = carNO;
        }

        public int getCarType() {
            return carType;
        }

        public void setCarType(int carType) {
            this.carType = carType;
        }

        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public String getDriverTel() {
            return driverTel;
        }

        public void setDriverTel(String driverTel) {
            this.driverTel = driverTel;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public String getJoinTime() {
            return joinTime;
        }

        public void setJoinTime(String joinTime) {
            this.joinTime = joinTime;
        }

        public String getMachineName() {
            return machineName;
        }

        public void setMachineName(String machineName) {
            this.machineName = machineName;
        }

        public int getMachineType() {
            return machineType;
        }

        public void setMachineType(int machineType) {
            this.machineType = machineType;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPlatformTime() {
            return platformTime;
        }

        public void setPlatformTime(String platformTime) {
            this.platformTime = platformTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSaleTime() {
            return saleTime;
        }

        public void setSaleTime(String saleTime) {
            this.saleTime = saleTime;
        }

        public int getServiceState() {
            return serviceState;
        }

        public void setServiceState(int serviceState) {
            this.serviceState = serviceState;
        }

        public String getServiceTime() {
            return serviceTime;
        }

        public void setServiceTime(String serviceTime) {
            this.serviceTime = serviceTime;
        }

        public String getSimNO() {
            return simNO;
        }

        public void setSimNO(String simNO) {
            this.simNO = simNO;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
