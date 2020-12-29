package com.lsy.wisdombuid.mvp.test;

import android.content.Context;

import com.google.gson.Gson;
import com.lsy.wisdombuid.activity.safety.ZhengGaiReviewActivity;
import com.lsy.wisdombuid.bean.NewQuestionsData;
import com.lsy.wisdombuid.bean.QuestiondData;
import com.lsy.wisdombuid.bean.SafetyIndexData;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.bean.SubjectData;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsy on 2020/4/14
 * todo :
 */
public class ExamModel implements ExamInterface.Model {

    private ExamPresent present;
    private Context context;

    public ExamModel(ExamPresent present, Context context) {
        this.present = present;
        this.context = context;
    }

    //获取最新试卷
    @Override
    public void getNewExam(String section_id) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();
//        {"section_id":1,"station_id":1} //标段ID   站点ID
        listcanshu.put("section_id", section_id);

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(context, RequestURL.findExamNew, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("exam", "findExamNew==" + dataString);
                Gson gson = new Gson();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

//                    JSONObject dataObject = new JSONObject(jsonObject.get("data").toString());

                    String data = jsonObject.getString("data");

                    NewQuestionsData questionsData = gson.fromJson(data, NewQuestionsData.class);


//                    String message = jsonObject.getString("message");
                    present.responseNewExam(questionsData);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });
    }

    @Override
    public void getExamPaper(String examination_id) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        listcanshu.put("examination_id", examination_id);

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(context, RequestURL.findExaminationById, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("exam", "findExaminationById==" + dataString);
                Gson gson = new Gson();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

//                    JSONObject dataObject = new JSONObject(jsonObject.get("data").toString());

                    String data = jsonObject.getString("data");

                    QuestiondData questionsData = gson.fromJson(data, QuestiondData.class);


//                    String message = jsonObject.getString("message");
                    present.responseExamPaper(questionsData);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });
    }

    @Override
    public void getSubject(String id) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        listcanshu.put("id", id);

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(context, RequestURL.findQuestionsById, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("exam", "findQuestionsById==" + dataString);
                Gson gson = new Gson();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

//                    JSONObject dataObject = new JSONObject(jsonObject.get("data").toString());

                    String data = jsonObject.getString("data");

                    //题目
                    SubjectData subjectData = gson.fromJson(data, SubjectData.class);


//                    String message = jsonObject.getString("message");
                    present.responseSubject(subjectData);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return dataString;
            }
        });
    }

    //提交试卷
    @Override
    public void addExamRecord(int exam_id, int staff_id, int section_id, int exam_count) {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        listcanshu.put("exam_id", exam_id);
        listcanshu.put("staff_id", staff_id);
        listcanshu.put("section_id", section_id);
        listcanshu.put("exam_count", exam_count);

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(context, RequestURL.insertExamRecord, listcanshu);

        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("exam", "insertExamRecord==" + dataString);
                Gson gson = new Gson();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);

                    int code = jsonObject.getInt("code");
                    String data = jsonObject.getString("data");
                    String message = jsonObject.getString("message");

                    if (code == 200) {
                        present.responseSuccess();
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
