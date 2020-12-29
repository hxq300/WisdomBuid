package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/4/24
 * todo : 课程数据
 */
public class CoursesData {

//    "id":2,
//            "type_id":1,
//            "section_id":2,
//            "train_name":"安全生产的方针",
//            "content":"pdf/222.pdf",
//            "video_url":"video/video2.mp4"

    private int id;
    private int type_id;
    private int section_id;
    private String train_name;
    private String content;
    private String video_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
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

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }
}
