package com.lsy.wisdombuid.mvp.safety;

import com.lsy.wisdombuid.bean.PMData;
import com.lsy.wisdombuid.bean.SafetyIndexData;
import com.lsy.wisdombuid.bean.StationData;

import java.util.List;

/**
 * Created by lsy on 2020/3/31
 * todo : 安全管理系统
 */
public interface SafetySystemInterface {

    interface Model {
        void getSelectStation(String section_id);//section_id部门id

        void getSafetyZGL(String section_id, String station_id);//获取安全隐患整改率

        void getQMS(String section_id, String station_id);//获取质量管理系统首页数据

    }

    interface View {
        //setData方法是为了 Activity实现view接口以后，重写这个方法就可以得到数据，为View赋值
        void setSelect(List<StationData> dataList);//设置站点

        void setSafetyZGL(SafetyIndexData safetyZGL);//获取安全隐患整改率

    }

    interface Presenter {
        void getSelectStation(String section_id);//section_id

        //测试数据:section_id(标段id,登录返回),station_id(站点id,下拉框选择)
        void getSafetyZGL(String section_id, String station_id);//获取人员管理数据
        void getQMS(String section_id, String station_id);//获取人员管理数据

        //获取数据以后回调
        void responseSelect(List<StationData> dataList);

        void responseSafetyZGL(SafetyIndexData safetyZGL);

        void distory();
    }

}
