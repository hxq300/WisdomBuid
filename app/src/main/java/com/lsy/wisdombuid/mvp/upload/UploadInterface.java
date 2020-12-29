package com.lsy.wisdombuid.mvp.upload;

import com.lsy.wisdombuid.bean.GongXuData;
import com.lsy.wisdombuid.bean.SafetyIndexData;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.bean.UploadData;
import com.lsy.wisdombuid.bean.UtilsData;

import java.util.List;

/**
 * Created by lsy on 2020/4/12
 * todo :
 */
public interface UploadInterface {

    interface Model {
        void getSelectStation(String section_id);//section_id部门id

        void getGongXu(String section_id, String station_id);//工序

        void getUtils(String section_id, String station_id);//分包单位

        void getAnquanType(String section_id, String station_id);//安全隐患类型

        void getZhiliang(String section_id, String station_id);//质量隐患类型

//        传参:title(标题),quality_id(安全隐患类id,安全隐患类型下拉框), staff_name(员工姓名,登录时返回),
//        section_id(标段id), station_id(站点id,下拉框查询),
//        sub_id(分包单位id) description(详细说明), url1(图片地址数组),
//        staff_id(劳务人员id,管理人员不需要传,登录返回), process_id(工序id,下拉框返回),responsible(责任人)

        //  risk_id ： 安全     quality_id ： 质量

        void addAnqaun(String title, int risk_id, int section_id, int station_id, int sub_id, String description, String url1,
                       int staff_id, int process_id, String responsible);//添加安全隐患

        void addZhiliang(String title, int quality_id, int section_id, int station_id, int sub_id, String description, String url1,
                         int staff_id, int process_id, String responsible);//添加安全隐患

    }

    interface View {
        //setData方法是为了 Activity实现view接口以后，重写这个方法就可以得到数据，为View赋值
        void setSelect(List<UploadData> dataList);//设置站点

        void setGongXu(List<UploadData> dataList);//设置工序

        void setUtils(List<UploadData> dataList);//设置分包单位

        void setType(List<UploadData> dataList);//设置分包单位

        void setSucess();//添加成功返回

    }


    interface Presenter {
        void getSelectStation(String section_id);//section_id

        //测试数据:section_id(标段id,登录返回),station_id(站点id,下拉框选择)
        void getGongXu(String section_id, String station_id);//获取工序

        void getUtils(String section_id, String station_id);//获取分包单位

        void getAnquan(String section_id, String station_id);//获取分包单位

        void getZhiliang(String section_id, String station_id);//获取分包单位

        void addAnqaun(String title, int risk_id, int section_id, int station_id, int sub_id, String description, String url1,
                       int staff_id, int process_id, String responsible);//添加安全隐患

        void addZhiliang(String title, int quality_id, int section_id, int station_id, int sub_id, String description, String url1,
                         int staff_id, int process_id, String responsible);//添加安全隐患

        //获取数据以后回调
        void responseSelect(List<UploadData> dataList);

        void responseGongXue(List<UploadData> dataList);

        void responseUtils(List<UploadData> dataList);

        void responseAnquan(List<UploadData> dataList);

        void responseZhiliang(List<UploadData> dataList);

        void responseSucess();

        void distory();
    }

}
