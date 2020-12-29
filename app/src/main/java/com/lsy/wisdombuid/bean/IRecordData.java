package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/4/7
 * todo : 检查记录实体
 */
public class IRecordData {

//    "id":12,
//            "risk_id":1,
//            "section_id":2,
//            "station_id":2,
//            "description":"11",
//            "uptime":"2020-04-05",
//            "url":"[https://jjjt.oss-cn-shanghai.aliyuncs.com/6699.jpg, https://jjjt.oss-cn-shanghai.aliyuncs.com/324890.jpg, ]",
//            "active":"待审核",
//            "risk_name":"施工设备隐患",
//            "staff_name":"ssw",
//            "station_name":"西藏北路站",
//            "title":"标题1",
//            "sub_id":4,
//            "sub_name":"圣盈工程集团有限公司",
//            "responsible":"leo",
//            "process_id":1,
//            "process_name":"左侧进洞",
//            "staff_id":3
//    "plan_time":"2020-04-09",

    private int id;//数据id
    private int risk_id;//隐患类型id
    private int section_id;//标段id
    private int station_id;//站点id
    private String description;//上传内容详情
    private String uptime;//上传时间
    private String url;//图片地址
    private String active;//zhuangtai
    private String risk_name;//安全隐患类型名称
    private String staff_name;//上报人
    private String station_name;//站点名称
    private String title;//标题
    private int sub_id;//分包单位id
    private String sub_name;//分包单位
    private String responsible;//责任人
    private int process_id;//工序id
    private String process_name;//工序名称
    private int staff_id;//员工ID

    private String plan_time;

    //===质量
//    "quality_id":1,
//            "quality_name":"材料质量1",

    private int quality_id;
    private String quality_name;

    public int getQuality_id() {
        return quality_id;
    }

    public void setQuality_id(int quality_id) {
        this.quality_id = quality_id;
    }

    public String getQuality_name() {
        return quality_name;
    }

    public void setQuality_name(String quality_name) {
        this.quality_name = quality_name;
    }

    public String getPlan_time() {
        return plan_time;
    }

    public void setPlan_time(String plan_time) {
        this.plan_time = plan_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRisk_id() {
        return risk_id;
    }

    public void setRisk_id(int risk_id) {
        this.risk_id = risk_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getRisk_name() {
        return risk_name;
    }

    public void setRisk_name(String risk_name) {
        this.risk_name = risk_name;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public int getProcess_id() {
        return process_id;
    }

    public void setProcess_id(int process_id) {
        this.process_id = process_id;
    }

    public String getProcess_name() {
        return process_name;
    }

    public void setProcess_name(String process_name) {
        this.process_name = process_name;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }


    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", risk_id=" + risk_id +
                ", section_id=" + section_id +
                ", station_id=" + station_id +
                ", description='" + description + '\'' +
                ", uptime='" + uptime + '\'' +
                ", url='" + url + '\'' +
                ", active='" + active + '\'' +
                ", risk_name='" + risk_name + '\'' +
                ", staff_name='" + staff_name + '\'' +
                ", station_name='" + station_name + '\'' +
                ", title='" + title + '\'' +
                ", sub_id=" + sub_id +
                ", sub_name='" + sub_name + '\'' +
                ", responsible='" + responsible + '\'' +
                ", process_id=" + process_id +
                ", process_name='" + process_name + '\'' +
                ", staff_id=" + staff_id +
                ", plan_time='" + plan_time + '\'' +
                ", quality_id=" + quality_id +
                ", quality_name='" + quality_name + '\'' +
                '}';
    }
}
