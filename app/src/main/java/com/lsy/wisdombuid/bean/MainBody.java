package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/3/16
 * todo :
 */
public class MainBody {

//        "code": "string",
//            "data": {},
//        "message": "string",
//            "statistics": {}

    private int code;
    private Object data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
