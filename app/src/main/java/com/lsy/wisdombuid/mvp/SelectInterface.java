package com.lsy.wisdombuid.mvp;

import com.lsy.wisdombuid.bean.PMData;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.bean.WorkTypeData;

import java.util.List;

/**
 * Created by lsy on 2020/3/23
 * todo : 站点信息
 */
public interface SelectInterface {

    interface Model {
        void getSelectStation(String section_id);//section_id
        void getPersonCount(String section_id,String station_id);//获取人员管理数据

    }

    interface View {
        //setData方法是为了 Activity实现view接口以后，重写这个方法就可以得到数据，为View赋值
        void setSelect(List<StationData> dataList);//设置站点
        void setPersonCount(PMData pmData);//设置数据
    }

    interface Presenter {
        void getSelectStation(String section_id);//section_id
        void getPersonCount(String section_id,String station_id);//获取人员管理数据

        //获取数据以后回调
        void responseSelect(List<StationData> dataList);
        void responsePersonCount(PMData pmData);
        void distory();
    }

}
