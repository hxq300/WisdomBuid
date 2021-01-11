package com.lsy.wisdombuid.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hxq on 2021/1/11
 * describe :  TODO
 */
public class RectifyEntity implements Serializable{

    /**
     * items : [{"id":17,"risk_id":4,"section_id":2,"station_id":1,"description":"现场路面损坏","uptime":"2021-01-03","url":"[img/y17.jpg]","active":"已整改","risk_name":"现场环境隐患","staff_name":"孟令华","station_name":"项目经理部一分部","title":"现场","sub_id":4,"sub_name":"安徽盛石建设工程有限公司桩基四标","responsible":"孟令华","plan_time":"2021-01-04","zg_url":"[img/z17.jpg]","process_id":0,"staff_id":27,"zg_description":"立即对损坏处进行修复","awarda":0},{"id":18,"risk_id":1,"section_id":2,"station_id":1,"description":"沙颍河南岸50墩吊车未贴标识牌","uptime":"2021-01-07","url":"[img/y18.jpg]","active":"已整改","risk_name":"施工设备隐患","staff_name":"孟令华","station_name":"项目经理部一分部","title":"吊车","sub_id":3,"sub_name":"河南省叁犇劳务工程有限公司桩基一标","responsible":"孟令华","plan_time":"2021-01-08","zg_url":"[img/z18.jpg]","process_id":0,"staff_id":27,"zg_description":"按要求张贴标示牌","awarda":0},{"id":19,"risk_id":9,"section_id":2,"station_id":1,"description":"标示标牌倾倒","uptime":"2021-01-07","url":"[img/y19.jpg]","active":"已整改","risk_name":"场地设备","staff_name":"孟令华","station_name":"项目经理部一分部","title":"标示牌","sub_id":5,"sub_name":"郑州荣凯基础工程有限公司水泥搅拌桩","responsible":"孟令华","plan_time":"2021-01-08","zg_url":"[img/z19.jpg]","process_id":0,"staff_id":27,"zg_description":"及时恢复","awarda":0},{"id":20,"risk_id":4,"section_id":2,"station_id":1,"description":"FK0+290涵洞基坑防护不到位、人员未佩戴安全帽、砖渣未覆盖","uptime":"2021-01-07","url":"[img/y20.jpg]","active":"已整改","risk_name":"现场环境隐患","staff_name":"孟令华","station_name":"项目经理部一分部","title":"基坑","sub_id":3,"sub_name":"河南省叁犇劳务工程有限公司桩基一标","responsible":"孟令华","plan_time":"2021-01-08","zg_url":"[img/z20.jpg]","process_id":0,"staff_id":27,"zg_description":"按要求设置围挡、人员按要求佩戴安全帽、砖渣按要求覆盖","awarda":0},{"id":21,"risk_id":3,"section_id":2,"station_id":1,"description":"拌合站人员未佩戴安全帽","uptime":"2021-01-07","url":"[img/y21.jpg]","active":"已整改","risk_name":"人员装备隐患","staff_name":"孟令华","station_name":"项目经理部一分部","title":"安全帽","sub_id":5,"sub_name":"郑州荣凯基础工程有限公司水泥搅拌桩","responsible":"孟令华","plan_time":"2021-01-07","zg_url":"[img/z21.jpg]","process_id":0,"staff_id":27,"zg_description":"按要求佩戴安全帽","awarda":0},{"id":22,"risk_id":9,"section_id":2,"station_id":1,"description":"钢筋场钢筋笼存放过高","uptime":"2021-01-07","url":"[img/y22.jpg]","active":"已整改","risk_name":"场地设备","staff_name":"孟令华","station_name":"项目经理部一分部","title":"钢筋笼","sub_id":4,"sub_name":"安徽盛石建设工程有限公司桩基四标","responsible":"孟令华","plan_time":"2021-01-08","zg_url":"[img/z22.jpg]","process_id":0,"staff_id":27,"zg_description":"按要求存放钢筋笼","awarda":0}]
     * pageNo : 2
     * pageSize : 15
     * total : 21
     * end_integral : 0
     */

    private int pageNo;
    private int pageSize;
    private int total;
    private int end_integral;
    private List<ItemsBean> items;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getEnd_integral() {
        return end_integral;
    }

    public void setEnd_integral(int end_integral) {
        this.end_integral = end_integral;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean  implements Serializable {
        /**
         * id : 17
         * risk_id : 4
         * section_id : 2
         * station_id : 1
         * description : 现场路面损坏
         * uptime : 2021-01-03
         * url : [img/y17.jpg]
         * active : 已整改
         * risk_name : 现场环境隐患
         * staff_name : 孟令华
         * station_name : 项目经理部一分部
         * title : 现场
         * sub_id : 4
         * sub_name : 安徽盛石建设工程有限公司桩基四标
         * responsible : 孟令华
         * plan_time : 2021-01-04
         * zg_url : [img/z17.jpg]
         * process_id : 0
         * staff_id : 27
         * zg_description : 立即对损坏处进行修复
         * awarda : 0
         */

        private int id;
        private int risk_id;
        private int section_id;
        private int station_id;
        private String description;
        private String uptime;
        private String url;
        private String active;
        private String risk_name;
        private String staff_name;
        private String station_name;
        private String title;
        private int sub_id;
        private String sub_name;
        private String responsible;
        private String plan_time;
        private String zg_url;
        private int process_id;
        private int staff_id;
        private String zg_description;
        private int awarda;

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

        public String getPlan_time() {
            return plan_time;
        }

        public void setPlan_time(String plan_time) {
            this.plan_time = plan_time;
        }

        public String getZg_url() {
            return zg_url;
        }

        public void setZg_url(String zg_url) {
            this.zg_url = zg_url;
        }

        public int getProcess_id() {
            return process_id;
        }

        public void setProcess_id(int process_id) {
            this.process_id = process_id;
        }

        public int getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(int staff_id) {
            this.staff_id = staff_id;
        }

        public String getZg_description() {
            return zg_description;
        }

        public void setZg_description(String zg_description) {
            this.zg_description = zg_description;
        }

        public int getAwarda() {
            return awarda;
        }

        public void setAwarda(int awarda) {
            this.awarda = awarda;
        }
    }
}
