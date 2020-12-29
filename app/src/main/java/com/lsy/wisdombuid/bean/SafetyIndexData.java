package com.lsy.wisdombuid.bean;

/**
 * Created by lsy on 2020/4/6
 * todo :
 */
public class SafetyIndexData {

//    "CountCQWZG":1,
//            "CountWZG":1,
//            "CountYZG":6,
//            "CountZGL":54.54545454545454,
//            "CountAll":11

    private int CountCQWZG;//超期未整改
    private int CountWZG;//未整改
    private int CountYZG;//已整改
    private float CountZGL;//整改率
    private int CountAll;//隐患数量

    public int getCountCQWZG() {
        return CountCQWZG;
    }

    public void setCountCQWZG(int countCQWZG) {
        CountCQWZG = countCQWZG;
    }

    public int getCountWZG() {
        return CountWZG;
    }

    public void setCountWZG(int countWZG) {
        CountWZG = countWZG;
    }

    public int getCountYZG() {
        return CountYZG;
    }

    public void setCountYZG(int countYZG) {
        CountYZG = countYZG;
    }

    public float getCountZGL() {
        return CountZGL;
    }

    public void setCountZGL(float countZGL) {
        CountZGL = countZGL;
    }

    public int getCountAll() {
        return CountAll;
    }

    public void setCountAll(int countAll) {
        CountAll = countAll;
    }
}
