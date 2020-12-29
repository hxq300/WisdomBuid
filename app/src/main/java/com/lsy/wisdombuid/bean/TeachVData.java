package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/4/9
 * todo : 培训课程教学视频
 */
public class TeachVData {

    private int code;
    private String message;
    private VideoData data;

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

    public VideoData getData() {
        return data;
    }

    public void setData(VideoData data) {
        this.data = data;
    }

    public class VideoData {

//                "id":12,
//                "type_id":3,
//                "section_id":2,
//                "train_name":"现场应急处理",
//                "type_name":"特种作业人员必备",
//                "content":"1",
//                "video_url":"video/lbygczy.mp4"

        private int id;
        private int type_id;
        private int section_id;
        private String train_name;
        private String type_name;
        private String video_url;
        private String content;//图文教程路径

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

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
