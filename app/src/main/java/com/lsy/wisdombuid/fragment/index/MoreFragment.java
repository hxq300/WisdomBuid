package com.lsy.wisdombuid.fragment.index;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.quality.QMSActivity;
import com.lsy.wisdombuid.activity.safety.ZhengGaiReportActivity;
import com.lsy.wisdombuid.adapter.HorizontalImageAdapter;
import com.lsy.wisdombuid.adapter.UpdatePictureAdapter;
import com.lsy.wisdombuid.adapter.UploadPopuAdapter;
import com.lsy.wisdombuid.adapter.ZhandianAdapter;
import com.lsy.wisdombuid.bean.GongXuData;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.bean.UploadData;
import com.lsy.wisdombuid.bean.UtilsData;
import com.lsy.wisdombuid.fragment.MyFragment;
import com.lsy.wisdombuid.mvp.upload.UploadInterface;
import com.lsy.wisdombuid.mvp.upload.UploadPresent;
import com.lsy.wisdombuid.oss.OssService;
import com.lsy.wisdombuid.request.OKHttpClass;
import com.lsy.wisdombuid.tools.L;
import com.lsy.wisdombuid.util.RealPathFromUriUtils;
import com.lsy.wisdombuid.util.SharedUtils;
import com.lsy.wisdombuid.util.ToastUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.annotations.Nullable;

/**
 * Created by lsy on 2020/3/16
 * todo : 拍照上传
 */
public class MoreFragment extends MyFragment implements UploadInterface.View, UpdatePictureAdapter.OnClick, OssService.OssCallback, UploadPopuAdapter.OnClick {//implements HorizontalImageAdapter.OnClick

    private View view;

    //
    private File cameraSavePath;//拍照照片路径
    private Uri uri;
    private ImageView openFile;

    //上传图片部分
    private RecyclerView idListRecycle;
    private UpdatePictureAdapter listAdapter;
    private List<String> imageList = new ArrayList<>();
    private List<Uri> picPahts = new ArrayList<>();

    //=====
    private SharedUtils sharedUtils;

    private UploadInterface.Presenter presenter;

    private TextView tvStation, tvType, tvGongxu, tvUtils;
    private PopupWindow popupWindow;

    private List<UploadData> stationList = new ArrayList<>();
    private List<UploadData> gongXuList = new ArrayList<>();
    private List<UploadData> utilsList = new ArrayList<>();
    private List<UploadData> typeList = new ArrayList<>();

    private RadioGroup uploadRadiogroup;
    private int checkBtn = 1;

    private Button btn_upload;

    private EditText title;
    private EditText content;
    private EditText zenrenren;

    private int risk_id = 0;//安全类型id
    private int quality_id = 0;//质量类型id
    private int section_id = 0;//
    private int station_id = 0;//
    private int sub_id = 0;//
    private int staff_id = 0;//
    private int process_id = 0;//工序ID


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_more, container, false);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sharedUtils = new SharedUtils(getContext(), SharedUtils.WISDOM);

        presenter = new UploadPresent(this, getContext());

        section_id = OKHttpClass.getToken(getContext());
        staff_id = sharedUtils.getIntData(SharedUtils.USER_ID);
        //获取站点
        presenter.getSelectStation("" + section_id);


        initView();
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);

    }

    private void initView() {

        tvStation = view.findViewById(R.id.more_tv_station);
        tvType = view.findViewById(R.id.more_tv_type);
        tvGongxu = view.findViewById(R.id.more_tv_gongxu);
        tvUtils = view.findViewById(R.id.more_tv_utils);
        uploadRadiogroup = view.findViewById(R.id.upload_radiogroup);
        btn_upload = view.findViewById(R.id.btn_upload);
        title = view.findViewById(R.id.upload_title);
        content = view.findViewById(R.id.upload_content);
        zenrenren = view.findViewById(R.id.upload_zerenren);

        uploadRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rbtn_anquan://安全
                        if (checkBtn != 1) {
                            presenter.getAnquan("" + section_id, "" + stationList.get(0).getId());
                        }
                        checkBtn = 1;
                        break;

                    case R.id.rbtn_zhiliang://质量
                        if (checkBtn != 2) {
                            presenter.getZhiliang("" + section_id, "" + stationList.get(0).getId());
                        }
                        checkBtn = 2;
                        break;

                    default:
                        break;
                }
            }
        });

        picPahts.clear();
        picPahts.add(0, uri);
        //===添加图片部分
        idListRecycle = (RecyclerView) view.findViewById(R.id.main_recycler_update);
        idListRecycle.setItemViewCacheSize(100);
        idListRecycle.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        idListRecycle.setNestedScrollingEnabled(false);

        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
        listAdapter = new UpdatePictureAdapter(getActivity(), picPahts);
        listAdapter.setOnClick(this);
        idListRecycle.setAdapter(listAdapter);


        tvStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stationList != null) {
                    showPopupWindow(stationList, 1);
                } else {
                    ToastUtils.showBottomToast(getContext(), "当前暂无数据展示");
                }
            }
        });

        tvGongxu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gongXuList != null) {
                    showPopupWindow(gongXuList, 2);
                } else {
                    ToastUtils.showBottomToast(getContext(), "当前暂无数据展示");
                }
            }
        });


        tvUtils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (utilsList != null) {
                    showPopupWindow(utilsList, 3);
                } else {
                    ToastUtils.showBottomToast(getContext(), "当前暂无数据展示");
                }
            }
        });


        tvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (utilsList != null) {

                    if (checkBtn == 1) {
                        showPopupWindow(typeList, 4);//安全
                    } else {
                        showPopupWindow(typeList, 5);//质量
                    }
                } else {
                    ToastUtils.showBottomToast(getContext(), "当前暂无数据展示");
                }
            }
        });


        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (is_input()) {
                    if (checkBtn == 1) {
                        presenter.addAnqaun(title.getText().toString(), risk_id, section_id, station_id, sub_id,
                                content.getText().toString(), imageList.toString(), staff_id,
                                process_id, zenrenren.getText().toString());
                    } else {
                        presenter.addZhiliang(title.getText().toString(), quality_id, section_id, station_id, sub_id,
                                content.getText().toString(), imageList.toString(), staff_id, process_id, zenrenren.getText().toString());
                    }
                }

            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        String photoPath;

//        Log.d("拍照返回图片路径:", "requestCode=" + requestCode + "resultCode=" + resultCode + "uri=" + uri);

//        if (uri != null) {
            if (requestCode == 1500) {
//            uri = data.toUri(MediaStore.EXTRA_OUTPUT);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    photoPath = String.valueOf(cameraSavePath);
                } else {
                    photoPath = uri.getEncodedPath();
                }

                if (resultCode != 0) {
                    Log.d("拍照返回图片路径:", "requestCode=" + requestCode + "resultCode=" + resultCode);
                    Log.d("拍照返真实:", RealPathFromUriUtils.compressImage(photoPath, getActivity()));
                    picPahts.add(0, uri);

                    L.log("picture", "======" + picPahts.toString());

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    // 设置图片名字
                    String key = "images/" + sdf.format(new Date())+".jpg";

                    listAdapter.notifyDataSetChanged();
                    //上传的文件名filename，上传的文件路径filePath
                    updateOss(key, photoPath);
                } else {
                    ToastUtils.showBottomToast(getActivity(), "获取图片失败-----" + resultCode);
                }


            } else if (requestCode == 234) { // 相册
                if (null != data && data.getData() != null) {
                    photoPath = RealPathFromUriUtils.getRealPathFromUri(getActivity(), data.getData());
                    uri = data.getData();

                    if (uri != null) {
                        Log.d("相册返回图片路径:", photoPath);
                        Log.d("相册返真实:", RealPathFromUriUtils.compressImage(photoPath, getActivity()));

                        picPahts.add(0, uri);

                        L.log("picture", "======" + picPahts.toString());

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                        // 设置图片名字
                        String key = "images/" + sdf.format(new Date())+".jpg";

                        Log.d("testDmt", "onActivityResult: ");
                        listAdapter.notifyDataSetChanged();
                        //上传的文件名filename，上传的文件路径filePath
                        updateOss(key, photoPath);
                    } else {
                        ToastUtils.showBottomToast(getActivity(), "获取图片失败");
                    }

                }

            }
//        } else {
//            picPahts.clear();
//            picPahts.add(0, uri);
//            ToastUtils.showBottomToast(getActivity(), "获取图片失败");
//        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    //自定义底部弹出框
    private void showBottomDialog() {
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(getActivity(), R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(getActivity(), R.layout.dialog_custom_layout, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();

        //拍照
        dialog.findViewById(R.id.tv_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goCamera();
            }
        });

        //从相册中选择
        dialog.findViewById(R.id.tv_take_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goPhotoAlbum();
            }
        });

        //取消
        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }


    //激活相册操作
    private void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 234);
    }

    //激活相机操作
    private void goCamera() {

        uri = null;
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(getActivity(), "com.lsy.wisdombuid.fileprovider", cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, 1500);
    }

    //删除图片回调
//    @Override
//    public void deletedPic(int position) {
//        picPahts.remove(position);
//        filePahts.remove(position);
//        horPhotoAdapter = new HorizontalImageAdapter(getActivity(), picPahts);
//        horPhotoAdapter.setOnClick(this);
//        footPicture.setVisibility(View.VISIBLE);
//        horListPhoto.setAdapter(horPhotoAdapter);
//    }

    public static MoreFragment newInstance() {
        Bundle args = new Bundle();
        MoreFragment fragment = new MoreFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //添加图片
    @Override
    public void addPicture() {
        if (picPahts.size() <= 9) {
            showBottomDialog();
        } else {
            ToastUtils.showBottomToast(getActivity(), "图片过多，请选择主要图片上传");
        }
    }

    //上传图片成功
    @Override
    public void sucess(String backUrl) {
        imageList.add(0, backUrl);

        L.log("picture", "===imageList===" + imageList.toString());
        listAdapter.notifyDataSetChanged();

        L.log("picture", "imageList==" + imageList.toString());
    }

    //上传图片失败
    @Override
    public void failure(String message) {
        ToastUtils.showBottomToast(getActivity(), "图片上传失败");
    }


    //上传图片到oss
    private void updateOss(String filename, String filePath) {

        //初始化OssService类，参数分别是Content，accessKeyId，accessKeySecret，endpoint，bucketName（后4个参数是您自己阿里云Oss中参数）
        OssService ossService = new OssService(getActivity(),
                "LTAI4Fjcn7J9c5aCVFTYabqE",
                "EuufkpKHommuLDd6EawJQac8togdPn",
                "http://oss-cn-shanghai.aliyuncs.com",
                "jjjt");
        //初始化OSSClient
        ossService.initOSSClient();

        ossService.setCallback(this);

        ossService.getProgressCallback();
        String time = "" + System.currentTimeMillis();
        //开始上传，参数分别为content，上传的文件名filename，上传的文件路径filePath
        ossService.beginupload(getActivity(), filename, filePath);
        //上传的进度回调
        ossService.setProgressCallback(new OssService.ProgressCallback() {
            @Override
            public void onProgressCallback(double progress) {
            }
        });


    }

    //站点
    @Override
    public void setSelect(List<UploadData> dataList) {
        stationList.clear();
        if (dataList != null && dataList.size() > 0) {
            stationList = dataList;
            if (dataList.get(0).getId() > 0) {
                station_id = dataList.get(0).getId();
//                this.station_id = dataList.get(0).getId();
                tvStation.setText("" + dataList.get(0).getStation_name());
                presenter.getGongXu("" + section_id, "" + dataList.get(0).getId());
                presenter.getUtils("" + section_id, "" + dataList.get(0).getId());
                if (checkBtn == 1) {
                    presenter.getAnquan("" + section_id, "" + dataList.get(0).getId());
                } else {
                    presenter.getZhiliang("" + section_id, "" + dataList.get(0).getId());
                }

            }
        }
    }

    //工序
    @Override
    public void setGongXu(List<UploadData> dataList) {

        gongXuList.clear();
        if (dataList != null && dataList.size() > 0) {
            gongXuList = dataList;
            if (dataList.size() > 0) {
                process_id = dataList.get(0).getId();
                tvGongxu.setText("" + dataList.get(0).getProcess_name());
            }
        }
    }

    //单位
    @Override
    public void setUtils(List<UploadData> dataList) {
        utilsList.clear();
        if (dataList != null && dataList.size() > 0) {
            utilsList = dataList;
            if (dataList.size() > 0) {
                sub_id = dataList.get(0).getId();
                tvUtils.setText("" + dataList.get(0).getSubcontractors_name());
            }
        }
    }

    @Override
    public void setType(List<UploadData> dataList) {
        typeList.clear();
        if (dataList != null && dataList.size() > 0) {
            typeList = dataList;
            if (dataList.size() > 0) {
                if (checkBtn == 1) {
                    risk_id = dataList.get(0).getId();
                    tvType.setText("" + dataList.get(0).getRisk_category());
                } else {
                    quality_id = dataList.get(0).getId();
                    tvType.setText("" + dataList.get(0).getQuality_category());
                }
            }
        }
    }

    //添加数据成功后返回
    @Override
    public void setSucess() {
        ToastUtils.showBottomToast(getActivity(), "数据添加成功");

        title.setText("");
        content.setText("");
        zenrenren.setText("");

        uri = null;
        picPahts.clear();
        picPahts.add(0, uri);
        imageList.clear();
        listAdapter.notifyDataSetChanged();


    }


    //=======
    private void showPopupWindow(List<UploadData> datas, int type) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.view_popu_recyle, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.select);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        UploadPopuAdapter scoreTeamAdapter = new UploadPopuAdapter(getActivity(), datas, type);
        scoreTeamAdapter.setOnClick(this);
        recyclerView.setAdapter(scoreTeamAdapter);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        if (type == 1) {
            popupWindow.showAsDropDown(tvStation);
        } else if (type == 2) {
            popupWindow.showAsDropDown(tvGongxu);
        } else if (type == 3) {
            popupWindow.showAsDropDown(tvUtils);
        } else {
            popupWindow.showAsDropDown(tvType);
        }
    }

    //站点点击返回
    @Override
    public void checkZhandian(int position, int sectionId, String name) {
        station_id = section_id;
        tvStation.setText("" + name);
        popupWindow.dismiss();
    }

    @Override
    public void checkGongxu(int position, int sectionId, String name) {
        process_id = section_id;
        tvGongxu.setText("" + name);
        popupWindow.dismiss();
    }

    @Override
    public void checkUtils(int position, int sectionId, String name) {
        sub_id = section_id;
        tvUtils.setText("" + name);
        popupWindow.dismiss();
    }

    @Override
    public void checkType(int position, int sectionId, String name) {

        if (checkBtn == 1) {
            risk_id = sectionId;
        } else {
            quality_id = sectionId;
        }
        tvType.setText("" + name);
        popupWindow.dismiss();
    }

    /**
     * 判断输入完成情况
     */
    private boolean is_input() {

        if (title.getText().toString().trim().length() < 1) {
            Toast.makeText(getActivity(), "标题不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (content.getText().toString().trim().length() < 1) {
            Toast.makeText(getActivity(), "请填写上报内容", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (zenrenren.getText().toString().trim().length() < 1) {
            Toast.makeText(getActivity(), "责任人不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        if (presenter != null) {
            presenter.distory();
        }

        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }
}