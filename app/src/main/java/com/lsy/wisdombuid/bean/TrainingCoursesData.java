package com.lsy.wisdombuid.bean;

import java.util.List;

/**
 * Created by lsy on 2020/4/8
 * todo : 培训课程列表实体
 */
public class TrainingCoursesData {

//    "id":1,
//            "type_name":"施工安全常识了解",
//            "dataIn":[{}]

    private int id;
    private String type_name;
    private List<DataIn> dataIn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public List<DataIn> getDataIn() {
        return dataIn;
    }

    public void setDataIn(List<DataIn> dataIn) {
        this.dataIn = dataIn;
    }

    public class DataIn {

//        "id":1,
//                "section_id":0,
//                "train_name":"安全概念"

        private int id;
        private int section_id;
        private String train_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }
}
