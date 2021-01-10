package com.lsy.wisdombuid.bean;

/**
 * Created by hxq on 2021/1/10
 * describe :  TODO
 */
public class ImmediatelyGpsLoginEntity {

    /**
     * data : {"address":"","email":"","joinTime":"2018-10-19 14:02:09","lang":"zh_CN","linkMan":"","linkPhone":"","name":"真实设备测试 2","parentId":1,"password":"E10ADC3949BA59ABBE56E057F20F883E","rechargeURL":"","remark":"","subAlarm":true,"timeZoneSecond":28800,"token":"ce4c90eb-5285-47ac-93d3-1f5ad8718bad","updateTime":"2018-10-19 14:02:09","userId":1324,"userName":"test02","userType":1}
     * ret : 1
     */

    private DataBean data;
    private int ret;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public static class DataBean {
        /**
         * address :
         * email :
         * joinTime : 2018-10-19 14:02:09
         * lang : zh_CN
         * linkMan :
         * linkPhone :
         * name : 真实设备测试 2
         * parentId : 1
         * password : E10ADC3949BA59ABBE56E057F20F883E
         * rechargeURL :
         * remark :
         * subAlarm : true
         * timeZoneSecond : 28800
         * token : ce4c90eb-5285-47ac-93d3-1f5ad8718bad
         * updateTime : 2018-10-19 14:02:09
         * userId : 1324
         * userName : test02
         * userType : 1
         */

        private String address;
        private String email;
        private String joinTime;
        private String lang;
        private String linkMan;
        private String linkPhone;
        private String name;
        private int parentId;
        private String password;
        private String rechargeURL;
        private String remark;
        private boolean subAlarm;
        private int timeZoneSecond;
        private String token;
        private String updateTime;
        private int userId;
        private String userName;
        private int userType;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getJoinTime() {
            return joinTime;
        }

        public void setJoinTime(String joinTime) {
            this.joinTime = joinTime;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getLinkMan() {
            return linkMan;
        }

        public void setLinkMan(String linkMan) {
            this.linkMan = linkMan;
        }

        public String getLinkPhone() {
            return linkPhone;
        }

        public void setLinkPhone(String linkPhone) {
            this.linkPhone = linkPhone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRechargeURL() {
            return rechargeURL;
        }

        public void setRechargeURL(String rechargeURL) {
            this.rechargeURL = rechargeURL;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public boolean isSubAlarm() {
            return subAlarm;
        }

        public void setSubAlarm(boolean subAlarm) {
            this.subAlarm = subAlarm;
        }

        public int getTimeZoneSecond() {
            return timeZoneSecond;
        }

        public void setTimeZoneSecond(int timeZoneSecond) {
            this.timeZoneSecond = timeZoneSecond;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }
    }
}
