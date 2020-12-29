package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/3/16
 * todo : 登录时用户数据
 */
public class UserData {
//    {"id":3,"staff_age":24,"history_integral":100000000,"end_integral":0,"section_id":2,"sub_id":4,"worktype_id":2,"station_id":2,"count":0,"position_id":1,"staff_name":"ssw","staff_img":"c08a16d1-5d88-4213-b259-1038fe620e92.jpg","staff_sex":"男","staff_nation":"汉","staff_card":"341100000000000000","staff_address":"北京市西平县","staff_province":"河南省","staff_phone":"13100000000","sos_name":"石头1","sos_ship":"亲戚","sos_phone":"13000000000","entry_time":"2020-03-16","password":"123456","state":"1","status":"1","type":"劳务人员","train":"0","department_id":2}

    private int id;//员工id
    private int staff_age;//年龄
    private int history_integral;//历史积分
    private int end_integral;//剩余积分
    private int section_id;//标段id
    private int sub_id;//分包单位id
    private int worktype_id;//工种id
    private int station_id;//站点id
    private int count;//统计总数
    private int position_id;//职务id
    private int department_id;//部门id
    private String staff_name;//姓名
    private String section_name;//标段名称
    private String staff_img;//照片
    private String staff_sex;//性别
    private String staff_nation;//民族
    private String staff_card;//身份证号码
    private String staff_address;//身份证地址
    private String staff_province;//所在省
    private String staff_phone;//电话号码
    private String sos_name;//紧急联系人姓名
    private String sos_ship;//紧急联系人关系
    private String sos_phone;//紧急联系人电话
    private String entry_time;//入职时间
    private String password;//密码
    private String picture;//头像（暂无使用身份证）
    private int state;//在职状态（）
    private int status;//实名认证状态
    private String type;//员工类型（）
    private String train;//培训状态

//    "section_name":"苏州轨道交通S1线TG06标",
//    "station_name":"鹿城路站",
//                    "sub_name":"圣盈工程集团有限公司",
//                    "worktype_name":"后勤",
//                    "department_id":2,
//                    "department_name":"施工部"

    private String station_name;
    private String sub_name;
    private String worktype_name;
    private String department_name;
    private String nikename;


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

    public int getSub_id() {
        return sub_id;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }

    public int getWorktype_id() {
        return worktype_id;
    }

    public void setWorktype_id(int worktype_id) {
        this.worktype_id = worktype_id;
    }

    public int getStation_id() {
        return station_id;
    }

    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }


    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getWorktype_name() {
        return worktype_name;
    }

    public void setWorktype_name(String worktype_name) {
        this.worktype_name = worktype_name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }
}
