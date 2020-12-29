package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/4/8
 * todo : 商品实体
 */
public class ShopGoodsData {

//    "id":1,
//            "commodity_price":101,
//            "section_id":2,
//            "commodity_name":"彩色简约纯棉洗脸毛巾1",
//            "commodity_img":"https://jjjt.oss-cn-shanghai.aliyuncs.com/2f22565e-dd62-40b3-8006-2b92fe0d0822.jpg",
//            "up_time":"2020-03-23 15:14:00",
//            "active":"1"

    private int id;
    private int commodity_price;
    private int section_id;
    private String commodity_name;
    private String commodity_img;
    private String up_time;
    private String active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommodity_price() {
        return commodity_price;
    }

    public void setCommodity_price(int commodity_price) {
        this.commodity_price = commodity_price;
    }

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name;
    }

    public String getCommodity_img() {
        return commodity_img;
    }

    public void setCommodity_img(String commodity_img) {
        this.commodity_img = commodity_img;
    }

    public String getUp_time() {
        return up_time;
    }

    public void setUp_time(String up_time) {
        this.up_time = up_time;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
