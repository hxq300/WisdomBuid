package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/4/29
 * todo :
 */
public class ProjectData {
//
//    "id":1,
//            "train_name":"安全带体验",
//            "content":"\t\t\t\t①材料和外观：采用镀锌方管组合焊接，模板和铝塑
//    板双层包裹；气动遥控控制，配CI喷绘广告和企业
//\n\t\t\t\t②尺寸：整体高4.45米，长5.4米，可根据客户需求
//    定制；\n\t\t\t\t③功能：将安全带的正确的使用方法融合于实体体验
//    活动中，让职工切实体验到无安全措施时高处坠落后
//    人体片刻感受和精神压力，从而增强职工对于高处作
//    业时必须正确佩戴安全带的自我保护意识，充分认识
//    到高处作业无安全措施的危险性，杜绝项目施工中高
//    处坠落事故的发生，做到安全文明施工。",
//            "img_url":"aqdty.png"

    private int id;
    private String train_name;
    private String content;
    private String img_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrain_name() {
        return train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", train_name='" + train_name + '\'' +
                ", content='" + content + '\'' +
                ", img_url='" + img_url + '\'' +
                '}';
    }
}
