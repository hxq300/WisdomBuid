package com.lsy.wisdombuid.fragment.index;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.MainActivity;
import com.lsy.wisdombuid.activity.OnlineSignatureActivity;
import com.lsy.wisdombuid.activity.PersonnelManagementActivity;
import com.lsy.wisdombuid.activity.exam.TrainingCheckActivity;
import com.lsy.wisdombuid.activity.integral.CreditsExchangeActivity;
import com.lsy.wisdombuid.activity.materia.MaterialMonitoringSystemActivity;
import com.lsy.wisdombuid.activity.persion.ExperienceRecordActivity;
import com.lsy.wisdombuid.activity.progress.ScheduleControlActivity;
import com.lsy.wisdombuid.activity.quality.QMSActivity;
import com.lsy.wisdombuid.activity.safety.SafetyManagementActivity;
import com.lsy.wisdombuid.adapter.RateAdapter;
import com.lsy.wisdombuid.bean.HomeBtnData;
import com.lsy.wisdombuid.bean.UserData;
import com.lsy.wisdombuid.fragment.MyFragment;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RJYunUrl;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.request.Request_CanShu;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.SharedUtils;
import com.lsy.wisdombuid.util.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.annotations.Nullable;

/**
 * Created by lsy on 2020/3/16
 * todo :
 */
public class HomePageFragment extends MyFragment {

    private View view;

    private List<Integer> images = new ArrayList<>();


    private RecyclerView rateRecycle;
    private RateAdapter rateAdapter;

    private LinearLayout lineSearch;

    private int jobID = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home_page, container, false);
        }
        return view;
    }


    @Override
    public void onActivityCreated(@androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SharedPreferences share = getActivity().getSharedPreferences(SharedUtils.WISDOM, getActivity().MODE_PRIVATE);
        jobID = share.getInt(SharedUtils.JOB_ID, 0);
        initView();
        initBanner();

        getBtnPermission();

    }

    private void initView() {

        lineSearch = view.findViewById(R.id.home_line_search);

        rateRecycle = (RecyclerView) view.findViewById(R.id.id_rate_recycler);
        rateRecycle.setItemViewCacheSize(100);
        rateRecycle.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        rateRecycle.setNestedScrollingEnabled(false);

        lineSearch.getBackground().mutate().setAlpha(98);

    }

    private void getBtnPermission() {
        Map<String, Object> listcanshu = new HashMap<>();
        OKHttpClass okHttpClass = new OKHttpClass();

        L.log("home", "id==" + jobID);
        listcanshu.put("position_id", "" + jobID);

        //设置请求类型、地址和参数
        okHttpClass.setPostCanShu(getContext(), RequestURL.homeTab, listcanshu);
        okHttpClass.setGetIntenetData(new OKHttpClass.GetData() {
            @Override
            public String requestData(String dataString) {
                //请求成功数据回调
                L.log("home", "" + dataString);
                Gson gson = new Gson();

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(dataString);
                    int code = jsonObject.getInt("code");
                    String data = jsonObject.getString("data");
                    String message = jsonObject.getString("message");

                    if (code == 200 && data != null) {
                        HomeBtnData btnData = gson.fromJson(dataString, HomeBtnData.class);

                        if (btnData.getData() != null) {
                            rateAdapter = new RateAdapter(getActivity(), btnData.getData());

                            //按钮点击事件
                            rateAdapter.setOnClick(new RateAdapter.OnClick() {
                                @Override
                                public void zixunNow(int position) {
                                    switch (position) {

                                        case 1://安全管理
                                            Intent guanli = new Intent(getContext(), SafetyManagementActivity.class);
                                            startActivity(guanli);
                                            break;

                                        case 2://AI监控
//                                            Intent jifen = new Intent(getContext(), SafetyManagementActivity.class);
//                                            startActivity(jifen);
                                            break;

                                        case 3://积分兑换
                                            Intent jifen = new Intent(getContext(), CreditsExchangeActivity.class);
                                            startActivity(jifen);
                                            break;

                                        case 4://进度管理
                                            Intent process = new Intent(getContext(), ScheduleControlActivity.class);
                                            startActivity(process);
                                            break;

                                        case 5://培训考核
                                            Intent peixun = new Intent(getContext(), TrainingCheckActivity.class);
                                            startActivity(peixun);
                                            break;

                                        case 6://人员管理
                                            Intent renyuan = new Intent(getContext(), PersonnelManagementActivity.class);
                                            startActivity(renyuan);
                                            break;

                                        case 7://物料监测
                                            Intent materia = new Intent(getContext(), MaterialMonitoringSystemActivity.class);
                                            startActivity(materia);
                                            break;

                                        case 8://质量管理
                                            Intent qms = new Intent(getContext(), QMSActivity.class);
                                            startActivity(qms);
                                            break;

                                        case 9://安全体验
                                            Intent tiyan = new Intent(getContext(), ExperienceRecordActivity.class);
                                            startActivity(tiyan);
                                            break;

                                        case 10://在线签名
                                            Intent online = new Intent(getContext(), OnlineSignatureActivity.class);
                                            startActivity(online);
                                            break;
                                        case 11://物料申领
//                                            Intent tiyan = new Intent(getContext(), ExperienceRecordActivity.class);
//                                            startActivity(tiyan);
                                            break;

                                        default:
                                            break;
                                    }
                                }
                            });
                            rateRecycle.setAdapter(rateAdapter);
                        }

//                        presenter.responseLabor(userData);

                    } else {
                        ToastUtils.showBottomToast(getContext(), "" + message);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return dataString;
            }
        });
    }

    private void initBanner() {
        images.clear();
        images.add(R.mipmap.home_banner1);
        images.add(R.mipmap.home_banner3);
        images.add(R.mipmap.home_banner1);
//        images.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2237069434,3717949658&fm=26&gp=0.jpg");
//        images.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=343743878,3804103777&fm=26&gp=0.jpg");
//        images.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2703222355,318514214&fm=26&gp=0.jpg");
//        images.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3183964165,3759807205&fm=26&gp=0.jpg");

        Banner banner = (Banner) view.findViewById(R.id.home_list_banner);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                Intent intent =new Intent(StoreClassfityActivity.this, OtherActivity.class);
//                intent.putExtra("url",homeData.getData().getBanner().get(position).getLink_url());
//                startActivity(intent);
            }
        });

        //设置图片加载器
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setImageResource((int) path);
            }
        });
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    public static HomePageFragment newInstance() {
        Bundle args = new Bundle();
        HomePageFragment fragment = new HomePageFragment();
        fragment.setArguments(args);
        return fragment;
    }


}