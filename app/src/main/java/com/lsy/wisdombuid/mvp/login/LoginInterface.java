package com.lsy.wisdombuid.mvp.login;

import com.lsy.wisdombuid.bean.LaborData;
import com.lsy.wisdombuid.bean.LoginData;
import com.lsy.wisdombuid.bean.PMData;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.bean.UserData;

import java.util.List;

/**
 * Created by lsy on 2020/3/23
 * todo : 站点信息
 */
public interface LoginInterface {

    interface Model {
        //管理人员登录
        void getManagerLogin(String admin_phone, String password);//{"admin_phone":"13100000000", "password":"123456"}

        //劳务人员登录
        void getLaborLogin(String staff_phone, String password);//{"staff_phone":"13100000000", "password":"123456"}

    }

    interface View {
        //setData方法是为了 Activity实现view接口以后，重写这个方法就可以得到数据，为View赋值
        void setManagerLogin(UserData managerData);//管理人员

        void setLaborLogin(LaborData.Labor laborLogin);//劳务人员
    }

    interface Presenter {
        //管理人员登录
        void getManagerLogin(String admin_phone, String password);//{"admin_phone":"13100000000", "password":"123456"}

        //劳务人员登录
        void getLaborLogin(String staff_phone, String password);//{"staff_phone":"13100000000", "password":"123456"}

        //获取数据以后回调
        void responseManager(UserData managerData);

        void responseLabor(LaborData.Labor laborLogin);

        void distory();
    }

}
