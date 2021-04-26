package com.lsy.wisdombuid.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.SharedUtils;
import com.lsy.wisdombuid.util.net.OkHttpManager;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Create by lsy on 2019/09/19
 * MODO :
 */
public class OKHttpClass {
    public interface GetData {
        String requestData(String dataString);
    }

    public GetData getData;

    public void setGetIntenetData(GetData getData) {
        this.getData = getData;
    }

    /**
     * 请求超时错误次数
     */
    private int maxLoadTimes = 3;
    private int serversLoadTimes = 0;

    private Handler handler;
    /**
     * 请求正确时返回的数据
     */
    private String res;

    private final int WHAT_OK = 123;
    private final int WHAT_ERROR = 321;
    private final int WHAT_ERROR_TWO = 312;
    public static final String NET_ERROR = "error";
    public static final String NET_ERROR_TWO = "error2";

    public OKHttpClass() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                int what = msg.what;
                switch (what) {
                    case WHAT_OK:
                        //请求成功
//                        getMaintainCode(res);
                        getData.requestData(res);
                        break;

                    case WHAT_ERROR:
                        //请求错误
                        getData.requestData(NET_ERROR);
                        break;

                    case WHAT_ERROR_TWO:
                        //上传活体文件,文件不存在
                        getData.requestData(NET_ERROR_TWO);
                        break;
                }
            }
        };
    }

    /**
     * 获取token
     */
    public static int getToken(Context context) {
        int token = 0;
        if (context != null) {
            //指定操作的文件名称
            SharedPreferences share = context.getSharedPreferences(SharedUtils.WISDOM, context.MODE_PRIVATE);
            token = share.getInt(SharedUtils.TOKEN, 0);
        }
//        L.log("token", token);
        return token;
    }

    /**
     * 获取token
     */
    public static int getUserId(Context context) {
        int userid = 0;
        if (context != null) {
            //指定操作的文件名称
            SharedPreferences share = context.getSharedPreferences(SharedUtils.WISDOM, context.MODE_PRIVATE);
            userid = share.getInt(SharedUtils.USER_ID, 0);
        }
//        L.log("token", token);
        return userid;
    }

    //
    public static void delToken(Context context) {
        if (context != null) {
            //指定操作的文件名称
            SharedUtils sharedUtils = new SharedUtils(context, SharedUtils.WISDOM);
            sharedUtils.remove_data();
        }
    }
//    //删除选中标签
//    public static void delSp(Context context) {
//        String token = "";
//        if (context != null) {
//            //指定操作的文件名称
//            SharedUtils sharedUtils = new SharedUtils(context, SharedUtils.SPECS);
//            sharedUtils.remove_data();
//        }
//    }

    /**
     * 设置post请求的参数
     */
    public void setPostCanShu(String resUrl, String token, List<Request_CanShu> listcanshu) {
        if (token == null) {
            token = "";
        }
        String time = (System.currentTimeMillis() + "").substring(0, 10);
        String url = resUrl + "timestamp=" + time + "&sign=" + MD5Utils.MD5(time, 32) + "&token=" + token;
        PostKeyValue(url, listcanshu);
    }

    public void setPostNoToken(String url, Object listcanshu) {
        PostKeyValue(url, listcanshu);
    }

    public void setPostYesToken(Context context, String resUrl, String token, Object listcanshu, String cars) {
        if (token == null) {
            token = "";
        }
        SharedUtils sharedUtils = new SharedUtils(context, SharedUtils.WISDOM);
        String url = resUrl + "&token=" + token;
        if (resUrl.equals("http://www.gpsnow.net/carStatus/getByCarIds.do?")) {
            url = url + "&userId=" + sharedUtils.getIntData(SharedUtils.GPS_USER_ID) + "&carIds=" + cars;
        }

        PostKeyValue(url, listcanshu);
    }

    /**
     * 设置post请求的参数
     */
    public void setPostCanShu(Context context, String resUrl, Object listcanshu) {
        String time = (System.currentTimeMillis() + "").substring(0, 10);
        String url = resUrl + "timestamp=" + time + "&sign=" + MD5Utils.MD5(time, 32) + "&token=" + getToken(context);
        PostKeyValue(url, listcanshu);
//        L.log(""+url);
    }

    /**
     * 设置post请求的字符串
     */
    public void setPostString(Context context, String resUrl, String data) {
        String time = (System.currentTimeMillis() + "").substring(0, 10);
        String url = resUrl + "timestamp=" + time + "&sign=" + MD5Utils.MD5(time, 32) + "&token=" + getToken(context);
        L.log("uuuuuuu", url);
        PostString(url, data);
    }

//    /**
//     * 设置post请求的参数
//     */
//    public void setPostFile(Context context, String resUrl, List<Request_CanShu> listcanshu) {
//        String time = (System.currentTimeMillis() + "").substring(0, 10);
//        String url = resUrl + "timestamp=" + time + "&sign=" + MD5Utils.MD5(time + NetAbout.apiKey, 32) + "&token=" + getToken(context);
//        postFile(HuoTiCanShu.FilePath + "livenessResult", url, "livenessEncryFile.dat");
//    }

    /**
     * 设置put请求的参数
     */
    public void setPutCanShu(Context context, String resUrl, List<Request_CanShu> listcanshu) {
        String time = (System.currentTimeMillis() + "").substring(0, 10);
        String url = resUrl + "timestamp=" + time + "&sign=" + MD5Utils.MD5(time, 32) + "&token=" + getToken(context);
        PutString(url, listcanshu);
    }


    /**
     * 设置get请求的参数
     */
    public void setGetCanShu(String resUrl, String token, List<Request_CanShu> listcanshu) {
        if (token == null) {
            token = "";
        }
        String time = (System.currentTimeMillis() + "").substring(0, 10);
        String url = resUrl + "timestamp=" + time + "&sign=" + MD5Utils.MD5(time, 32) + "&token=" + token;
        for (int i = 0; i < listcanshu.size(); i++) {
            Request_CanShu request_canShu = listcanshu.get(i);
            url = url + "&" + request_canShu.getKey() + "=" + request_canShu.getValue();
        }
        DoGet(url);
    }

    /**
     * 设置get请求的参数
     */
    public void setGetCanShu(Context context, String resUrl, List<Request_CanShu> listcanshu) {
        String time = (System.currentTimeMillis() + "").substring(0, 10);
        String url = resUrl + "timestamp=" + time + "&sign=" + MD5Utils.MD5(time, 32) + "&token=" + getToken(context);
        if (listcanshu != null) {
            for (int i = 0; i < listcanshu.size(); i++) {
                Request_CanShu request_canShu = listcanshu.get(i);
                url = url + "&" + request_canShu.getKey() + "=" + request_canShu.getValue();
            }
        }
        L.log(url);
        DoGet(url);
    }


    /**
     * post请求，提交键值对
     * todo :此处有修改 单例OkHttpClient
     */
    public void PostKeyValue(String urlPath, Object listcanshu) {

//        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(10000, TimeUnit.SECONDS)
//                .readTimeout(10000, TimeUnit.SECONDS)
//                .build();
        OkHttpClient okHttpClient = OkHttpManager.getInstance().getOkHttpClient();

        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , new Gson().toJson(listcanshu));

        Request request = new Request.Builder()
                .url(urlPath)
                .post(requestBody)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.log("res==", "onFailure=" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                res = response.body().string();
                L.log("res==", "" + res);
                Message message = handler.obtainMessage();
                message.what = WHAT_OK;
                handler.sendMessage(message);

            }
        });
    }

    /**
     * 向服务器post一个字符串（json）
     * todo :此处有修改 单例OkHttpClient
     */
    public void PostString(String urlPath, String dataString) {

//        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(10000, TimeUnit.SECONDS)
//                .readTimeout(10000, TimeUnit.SECONDS)
//                .build();

        OkHttpClient okHttpClient = OkHttpManager.getInstance().getOkHttpClient();

        MediaType mediaType = MediaType.parse("text/plain;charset=utf-8");

        RequestBody requestBody = RequestBody.create(mediaType, dataString);
        Request.Builder builder = new Request.Builder();
//        builder.addHeader()
        Request request = builder.url(urlPath).post(requestBody).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                if (serversLoadTimes < maxLoadTimes)//如果超时并未超过指定次数，则重新连接
//                {
//                    serversLoadTimes++;
//                    okHttpClient.newCall(call.request()).enqueue(this);
//                } else {
//                    e.printStackTrace();
//                    getData.requestData("error");
//                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                res = response.body().string();

                Message message = handler.obtainMessage();
                message.what = WHAT_OK;
                handler.sendMessage(message);

            }
        });

    }

    /**
     * get请求 暴力请求
     *
     * @param urlPath
     */
    public void DoGet2(String urlPath) {
        OkHttpClient okHttpClient = OkHttpManager.getInstance().getOkHttpClient();
        //2、构建Request
        Request.Builder builder = new Request.Builder();
        builder.addHeader("userId", "31439");
        Request request = builder.get().url(urlPath).build();
        //3、执行Call
        Call call = okHttpClient.newCall(request);
        //4、异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                res = response.body().string();

                Message message = handler.obtainMessage();
                message.what = WHAT_OK;
                handler.sendMessage(message);
            }
        });
    }

    /**
     * 使用okhttp发送get请求
     * todo :此处有修改 单例OkHttpClient
     */
    public void DoGet(String urlPath) {
        //1、拿到okhttpClient对象
//        final OkHttpClient okHttpClient = new OkHttpClient();

        OkHttpClient okHttpClient = OkHttpManager.getInstance().getOkHttpClient();
        //2、构建Request
        Request.Builder builder = new Request.Builder();
        builder.addHeader("userId", "31439");
        Request request = builder.get().url(urlPath).build();

        //3、执行Call
        Call call = okHttpClient.newCall(request);
        //4、异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求出错时返回
//                if (serversLoadTimes < maxLoadTimes)//如果超时并未超过指定次数，则重新连接
//                {
//                    serversLoadTimes++;
//                    okHttpClient.newCall(call.request()).enqueue(this);
//                } else {
//                    e.printStackTrace();
//                    Message message = handler.obtainMessage();
//                    message.what = WHAT_ERROR;
//                    handler.sendMessage(message);
//                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                res = response.body().string();

                Message message = handler.obtainMessage();
                message.what = WHAT_OK;
                handler.sendMessage(message);
            }
        });
    }


    /**
     * PUT请求
     *
     * @param urlPath
     */
    public void PutString(String urlPath, List<Request_CanShu> listcanshu) {

//        final OkHttpClient okHttpClient = new OkHttpClient();
        //设置请求超时时间
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(0, TimeUnit.SECONDS)
                .readTimeout(0, TimeUnit.SECONDS)
                .build();
        FormBody.Builder builder = new FormBody.Builder();
        for (int i = 0; i < listcanshu.size(); i++) {
            Request_CanShu canShuClass = listcanshu.get(i);
            builder.add(canShuClass.getKey(), canShuClass.getValue()).build();
        }
        //post提交键值对
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(urlPath).put(requestBody).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

//                if (serversLoadTimes < maxLoadTimes)//如果超时并未超过指定次数，则重新连接
//                {
//                    serversLoadTimes++;
//                    okHttpClient.newCall(call.request()).enqueue(this);
//                } else {
//                    e.printStackTrace();
//                    Message message = handler.obtainMessage();
//                    message.what = WHAT_ERROR;
//                    handler.sendMessage(message);
//                }

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                res = response.body().string();

                Message message = handler.obtainMessage();
                message.what = WHAT_OK;
                handler.sendMessage(message);

            }
        });
    }

    /**
     * 设置post请求的参数
     */
    public void setPostFile(Context context, String resUrl, List<Request_CanShu> listcanshu) {
        L.log("上传文件");
        String time = (System.currentTimeMillis() + "").substring(0, 10);
        String url = resUrl + "timestamp=" + time + "&sign=" + MD5Utils.MD5(time, 32) + "&token=" + getToken(context);
//        postFile(HuoTiCanShu.FilePath + "livenessResult", url, "livenessEncryFile.dat");
//        postFile(HuoTiCanShu.FilePath + "livenessResult", url, "livenessEncryFile.dat");
    }

    /**
     * post提交文件
     */
    public void postFile(String filepath, String urlPath, String filename) {

        //不同文件的MediaType.parse(),不同，百度搜索mime type
        File file = new File(filepath);
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);

        if (!file.exists()) {
            Message message = handler.obtainMessage();
            message.what = WHAT_ERROR_TWO;
            handler.sendMessage(message);
            return;
        }

        //设置请求超时时间
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1500, TimeUnit.SECONDS)
                .readTimeout(2000, TimeUnit.SECONDS)
                .build();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(Headers.of("Content-Disposition", "form-data; name=\"file\"; filename=\"" + filename + "\""), fileBody).build();

        Request request = new Request.Builder().url(urlPath).post(requestBody).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.log(e.getMessage());

//                if (serversLoadTimes < maxLoadTimes)//如果超时并未超过指定次数，则重新连接
//                {
//                    serversLoadTimes++;
//                    okHttpClient.newCall(call.request()).enqueue(this);
//                } else {
//                    e.printStackTrace();
//                    Message message = handler.obtainMessage();
//                    message.what = WHAT_ERROR;
//                    handler.sendMessage(message);
//                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                res = response.body().string();

                Message message = handler.obtainMessage();
                message.what = WHAT_OK;
                handler.sendMessage(message);
            }
        });


    }


    public void uploadImage(String url, String imagePath) {
        OkHttpClient okHttpClient = new OkHttpClient();
        File file = new File(imagePath);
        RequestBody image = RequestBody.create(MediaType.parse("image/png"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", imagePath, image)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
//        Response response = okHttpClient.newCall(request).execute();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.log(e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                res = response.body().string();
                Message message = handler.obtainMessage();
                message.what = WHAT_OK;
                handler.sendMessage(message);
            }
        });
//        JSONObject jsonObject = new JSONObject(response.body().string());
//        return jsonObject.optString("image");
    }

}
