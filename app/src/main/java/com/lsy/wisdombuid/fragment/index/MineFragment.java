package com.lsy.wisdombuid.fragment.index;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.quality.QMSActivity;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.fragment.MyFragment;
import com.lsy.wisdombuid.mvp.safety.SafetySystemPresent;
import com.lsy.wisdombuid.mvp.upload.UploadInterface;
import com.lsy.wisdombuid.mvp.upload.UploadPresent;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.SharedUtils;
import com.lsy.wisdombuid.util.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.annotations.Nullable;

/**
 * Created by lsy on 2020/3/16
 * todo : 我的
 */
public class MineFragment extends MyFragment {

    private View view;

    private ImageView imageHead;

    private TextView phone, name;

    private SharedUtils sharedUtils;

    private UploadInterface.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_mine, container, false);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedUtils = new SharedUtils(getContext(), SharedUtils.WISDOM);
        initView();

    }


    private void initView() {
        imageHead = view.findViewById(R.id.head_img);

        phone = view.findViewById(R.id.mine_phone);
        name = view.findViewById(R.id.mime_name);

        phone.setText("" + sharedUtils.getData(SharedUtils.USER_PHONE, ""));

        if (sharedUtils.getData(SharedUtils.USER_NICKNAME, null) != null) {
            name.setText("" + sharedUtils.getData(SharedUtils.USER_NICKNAME, ""));
        } else {
            name.setText("" + sharedUtils.getData(SharedUtils.USER_NAME, ""));
        }


        if (sharedUtils.getData(SharedUtils.USER_IMAGE, null) != null) {
            Glide.with(getContext()).load(RequestURL.OssUrl + sharedUtils.getData(SharedUtils.USER_IMAGE, ""))
                    .error(R.mipmap.mine_head)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(imageHead);
        } else {
            Glide.with(getContext()).load(RequestURL.RequestImg + sharedUtils.getData(SharedUtils.USER_PIC, ""))
                    .error(R.mipmap.mine_head)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(imageHead);
        }


//        Glide.with(getActivity()).load("https://jjjt.oss-cn-shanghai.aliyuncs.com/1585294745713").into(imageHead);
    }

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onResume() {
        super.onResume();

        if (sharedUtils.getData(SharedUtils.USER_IMAGE, null) != null) {
            Glide.with(getContext()).load(RequestURL.OssUrl + sharedUtils.getData(SharedUtils.USER_IMAGE, ""))
                    .error(R.mipmap.mine_head)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(imageHead);
        } else {
            Glide.with(getContext()).load(RequestURL.RequestImg + sharedUtils.getData(SharedUtils.USER_PIC, ""))
                    .error(R.mipmap.mine_head)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(imageHead);
        }

        if (sharedUtils.getData(SharedUtils.USER_NICKNAME, null) != null) {
            name.setText("" + sharedUtils.getData(SharedUtils.USER_NICKNAME, ""));
        } else {
            name.setText("" + sharedUtils.getData(SharedUtils.USER_NAME, ""));
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.distory();
        }

    }



}