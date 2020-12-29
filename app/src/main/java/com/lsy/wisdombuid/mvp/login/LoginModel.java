package com.lsy.wisdombuid.mvp.login;

import android.content.Context;

import com.google.gson.Gson;
import com.lsy.wisdombuid.bean.LaborData;
import com.lsy.wisdombuid.bean.UserData;
import com.lsy.wisdombuid.activity.login.LoginActivity;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Create by lsy on 2019/12/06
 * MODO :
 */
public class LoginModel implements LoginInterface.Model {

    private LoginInterface.Presenter presenter;
    private Context context;

    public LoginModel(LoginInterface.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }


    @Override
    public void getManagerLogin(String admin_phone, String password) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        listcanshu.put("staff_phone", "" + admin_phone);
        listcanshu.put("password", "" + password);

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(context, RequestURL.login, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("login", "" + dataString);
                Gson gson = new Gson();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);
                    int code = jsonObject.getInt("code");
                    String data = jsonObject.getString("data");
                    String message = jsonObject.getString("message");

                    if (code == 200 && data != null) {
                        UserData userData = gson.fromJson(data, UserData.class);

                        presenter.responseManager(userData);

                    } else {
                        ToastUtils.showBottomToast(context, "" + message);
                    }

//                            saveUserMsg(loginData);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return dataString;
            }
        });
    }

    @Override
    public void getLaborLogin(String staff_phone, String password) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        listcanshu.put("staff_phone", "" + staff_phone);
        listcanshu.put("password", "" + password);

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(context, RequestURL.loginLabor, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("login", "" + dataString);
                Gson gson = new Gson();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);
                    int code = jsonObject.getInt("code");
                    String data = jsonObject.getString("data");
                    String message = jsonObject.getString("message");

                    if (code == 200 && data != null) {
                        LaborData.Labor userData = gson.fromJson(data, LaborData.Labor.class);
                        presenter.responseLabor(userData);

                    } else {
                        ToastUtils.showBottomToast(context, "" + message);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return dataString;
            }
        });
    }

}
