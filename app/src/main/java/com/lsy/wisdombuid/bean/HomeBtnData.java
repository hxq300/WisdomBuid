package com.lsy.wisdombuid.bean;

import java.util.List;

/**
 * Created by lsy on 2020/3/27
 * todo : 首页按钮
 */
public class HomeBtnData {

//    {"message":null,"data":[{"id":1,"title_name":"main_anquanguanli","title_img":"main_anquanguanli.png"},{"id":2,"title_name":"main_jiankong","title_img":"main_jiankong.png"},{"id":3,"title_name":"main_jifen","title_img":"main_jifen.png"},{"id":4,"title_name":"main_jindu","title_img":"main_jindu.png"},{"id":5,"title_name":"main_peixun","title_img":"main_peixun.png"},{"id":6,"title_name":"main_renyuanguanli","title_img":"main_renyuanguanli.png"},{"id":7,"title_name":"main_wuliaojiancce","title_img":"main_wuliaojiancce.png"},{"id":8,"title_name":"main_zhiliangguanli","title_img":"main_zhiliangguanli.png"}],"data2":null,"data3":null,"data4":null,"data5":null,"code":200}

    private int code;
    private String message;
    private List<BtnData> data;

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

    public List<BtnData> getData() {
        return data;
    }

    public void setData(List<BtnData> data) {
        this.data = data;
    }

    public static class BtnData {
        //        {"id":1,"title_name":"main_anquanguanli","title_img":"main_anquanguanli.png"}
        private int id;
        private String title_name;
        private String title_img;

        public BtnData(int id, String title_name, String title_img) {
            this.id = id;
            this.title_name = title_name;
            this.title_img = title_img;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle_name() {
            return title_name;
        }

        public void setTitle_name(String title_name) {
            this.title_name = title_name;
        }

        public String getTitle_img() {
            return title_img;
        }

        public void setTitle_img(String title_img) {
            this.title_img = title_img;
        }
    }
}
