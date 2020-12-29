package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/3/16
 * todo :
 */
public class LoginData {
    //        "message":"登录成功!",
//            "data":{}
    private int code;
    private String message;
    private UserData data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public static class UserData {

        private int id;
        private int staff_age;
        private int history_integral;
        private int end_integral;
        private int section_id;
        private int department_id;
        private int worktype_id;
        private String staff_name;
        private String staff_img;
        private String staff_sex;
        private String staff_nation;
        private String staff_card;
        private String staff_address;
        private String staff_province;
        private String staff_phone;
        private String sos_name;
        private String sos_ship;
        private String sos_phone;
        private String entry_time;
        private String password;
        private String picture;
        private int state;
        private int status;
        private int type;
        private String train;

        private int sub_id;//分包单位ID
        private String sub_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStaff_age() {
            return staff_age;
        }

        public void setStaff_age(int staff_age) {
            this.staff_age = staff_age;
        }

        public int getHistory_integral() {
            return history_integral;
        }

        public void setHistory_integral(int history_integral) {
            this.history_integral = history_integral;
        }

        public int getEnd_integral() {
            return end_integral;
        }

        public void setEnd_integral(int end_integral) {
            this.end_integral = end_integral;
        }

        public int getSection_id() {
            return section_id;
        }

        public void setSection_id(int section_id) {
            this.section_id = section_id;
        }

        public int getDepartment_id() {
            return department_id;
        }

        public void setDepartment_id(int department_id) {
            this.department_id = department_id;
        }

        public int getWorktype_id() {
            return worktype_id;
        }

        public void setWorktype_id(int worktype_id) {
            this.worktype_id = worktype_id;
        }

        public String getStaff_name() {
            return staff_name;
        }

        public void setStaff_name(String staff_name) {
            this.staff_name = staff_name;
        }

        public String getStaff_img() {
            return staff_img;
        }

        public void setStaff_img(String staff_img) {
            this.staff_img = staff_img;
        }

        public String getStaff_sex() {
            return staff_sex;
        }

        public void setStaff_sex(String staff_sex) {
            this.staff_sex = staff_sex;
        }

        public String getStaff_nation() {
            return staff_nation;
        }

        public void setStaff_nation(String staff_nation) {
            this.staff_nation = staff_nation;
        }

        public String getStaff_card() {
            return staff_card;
        }

        public void setStaff_card(String staff_card) {
            this.staff_card = staff_card;
        }

        public String getStaff_address() {
            return staff_address;
        }

        public void setStaff_address(String staff_address) {
            this.staff_address = staff_address;
        }

        public String getStaff_province() {
            return staff_province;
        }

        public void setStaff_province(String staff_province) {
            this.staff_province = staff_province;
        }

        public String getStaff_phone() {
            return staff_phone;
        }

        public void setStaff_phone(String staff_phone) {
            this.staff_phone = staff_phone;
        }

        public String getSos_name() {
            return sos_name;
        }

        public void setSos_name(String sos_name) {
            this.sos_name = sos_name;
        }

        public String getSos_ship() {
            return sos_ship;
        }

        public void setSos_ship(String sos_ship) {
            this.sos_ship = sos_ship;
        }

        public String getSos_phone() {
            return sos_phone;
        }

        public void setSos_phone(String sos_phone) {
            this.sos_phone = sos_phone;
        }

        public String getEntry_time() {
            return entry_time;
        }

        public void setEntry_time(String entry_time) {
            this.entry_time = entry_time;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTrain() {
            return train;
        }

        public void setTrain(String train) {
            this.train = train;
        }

        public int getSub_id() {
            return sub_id;
        }

        public void setSub_id(int sub_id) {
            this.sub_id = sub_id;
        }

        public String getSub_name() {
            return sub_name;
        }

        public void setSub_name(String sub_name) {
            this.sub_name = sub_name;
        }

        //         "id":1,
//                "staff_age":24,
//                "history_integral":0,
//                "end_integral":0,
//                "section_id":1,
//                "department_id":1,
//                "worktype_id":1,
//                "staff_name":"dyf",
//                "staff_img":"",
//                "staff_sex":"男",
//                "staff_nation":"汉",
//                "staff_card":"340000000000000000",
//                "staff_address":"安徽省合肥市包河区",
//                "staff_province":"安徽省",
//                "staff_phone":"13000000000",
//                "sos_name":"dy",
//                "sos_ship":"朋友",
//                "sos_phone":"13000000000",
//                "entry_time":"2020-03-09",
//                "password":"123456",
//                "picture":"1.png",
//                "state":"1",
//                "status":"1",
//                "type":"1",
//                "train":"0"
    }


}
