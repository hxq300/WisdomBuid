package com.lsy.wisdombuid.bean;

/**
 * @Author: a
 * @Date: 2021/5/10 10:02
 * @Description: TODO
 */
public class MaterialMonitoringSystemBean {

    /**
     * message : null
     * data : {"id":0,"pm25":"43","pm10":"54","noise":"66","tem":"23.1","hum":"58.0","wp":"2","ws":"1.2","wd":"东南风","tsp":"95"}
     * data2 : null
     * data3 : null
     * data4 : null
     * data5 : null
     * code : 200
     */

    private Object message;
    /**
     * id : 0
     * pm25 : 43
     * pm10 : 54
     * noise : 66
     * tem : 23.1
     * hum : 58.0
     * wp : 2
     * ws : 1.2
     * wd : 东南风
     * tsp : 95
     */

    private DataBean data;
    private Object data2;
    private Object data3;
    private Object data4;
    private Object data5;
    private int code;

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getData2() {
        return data2;
    }

    public void setData2(Object data2) {
        this.data2 = data2;
    }

    public Object getData3() {
        return data3;
    }

    public void setData3(Object data3) {
        this.data3 = data3;
    }

    public Object getData4() {
        return data4;
    }

    public void setData4(Object data4) {
        this.data4 = data4;
    }

    public Object getData5() {
        return data5;
    }

    public void setData5(Object data5) {
        this.data5 = data5;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
        private int id;
        private String pm25;
        private String pm10;
        private String noise;
        private String tem;
        private String hum;
        private String wp;
        private String ws;
        private String wd;
        private String tsp;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getNoise() {
            return noise;
        }

        public void setNoise(String noise) {
            this.noise = noise;
        }

        public String getTem() {
            return tem;
        }

        public void setTem(String tem) {
            this.tem = tem;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getWp() {
            return wp;
        }

        public void setWp(String wp) {
            this.wp = wp;
        }

        public String getWs() {
            return ws;
        }

        public void setWs(String ws) {
            this.ws = ws;
        }

        public String getWd() {
            return wd;
        }

        public void setWd(String wd) {
            this.wd = wd;
        }

        public String getTsp() {
            return tsp;
        }

        public void setTsp(String tsp) {
            this.tsp = tsp;
        }
    }
}

 
