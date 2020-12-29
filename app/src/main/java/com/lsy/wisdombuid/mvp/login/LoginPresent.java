package com.lsy.wisdombuid.mvp.login;

import android.content.Context;

import com.lsy.wisdombuid.bean.LaborData;
import com.lsy.wisdombuid.bean.LoginData;
import com.lsy.wisdombuid.bean.PMData;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.bean.UserData;
import com.lsy.wisdombuid.mvp.SelectInterface;
import com.lsy.wisdombuid.mvp.SelectModel;

import java.util.List;

/**
 * Create by lsy on 2019/12/06
 * MODO : 获取分类
 */
public class LoginPresent implements LoginInterface.Presenter {

    private LoginInterface.View view;
    private LoginInterface.Model model;
    private Context context;

    public LoginPresent(LoginInterface.View view, Context context) {
        this.view = view;
        this.context = context;
        this.model = new LoginModel(this, context);
    }

    @Override
    public void getManagerLogin(String admin_phone, String password) {
        model.getManagerLogin(admin_phone, password);
    }

    @Override
    public void getLaborLogin(String staff_phone, String password) {
        model.getLaborLogin(staff_phone, password);
    }

    @Override
    public void responseManager(UserData managerData) {
        view.setManagerLogin(managerData);
    }

    @Override
    public void responseLabor(LaborData.Labor laborLogin) {
        view.setLaborLogin(laborLogin);
    }

    @Override
    public void distory() {
        view = null;
    }
}
