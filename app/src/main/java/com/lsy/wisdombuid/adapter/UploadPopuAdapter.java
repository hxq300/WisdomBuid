package com.lsy.wisdombuid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.bean.GongXuData;
import com.lsy.wisdombuid.bean.StationData;
import com.lsy.wisdombuid.bean.UploadData;
import com.lsy.wisdombuid.bean.UtilsData;
import com.lsy.wisdombuid.util.GeneralMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy on 2020/3/19
 * todo : 站点下拉框适配器
 */
public class UploadPopuAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<UploadData> messageList = new ArrayList();
    private LayoutInflater inflater;
    private int type;

    public UploadPopuAdapter(Context context, List<UploadData> messageList, int type) {
        this.context = context;
        this.messageList = messageList;
        this.type = type;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(inflater.inflate(R.layout.view_zhandian_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ItemView) holder).bindData(messageList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }


    class ItemView extends RecyclerView.ViewHolder {

        private TextView name;

        public ItemView(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_zhandain_name);
        }

        public void bindData(Object item, final int position) {
            final UploadData datas = (UploadData) item;
            if (type == 1) {

                name.setText("" + datas.getStation_name());

                itemView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                itemView.setBackgroundColor(0xffb8e1fb);
                                break;
//                        case MotionEvent.ACTION_MOVE:
//                            LogUtil.log("action_move");
//                            break;
                            case MotionEvent.ACTION_UP:
                                itemView.setBackgroundColor(0xffFFFFFF);
                                if (GeneralMethod.isFastClick()) {
                                    if (onClick != null) {
                                        onClick.checkZhandian(position, datas.getStation_id(), datas.getStation_name());
                                    }
                                }
//                            ToastUtils.showBottomToast(context, "选择了" + datas.getStation_name());
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
            } else if (type == 2) {
                name.setText("" + datas.getProcess_name());

                itemView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                itemView.setBackgroundColor(0xffb8e1fb);
                                break;
//                        case MotionEvent.ACTION_MOVE:
//                            LogUtil.log("action_move");
//                            break;
                            case MotionEvent.ACTION_UP:
                                itemView.setBackgroundColor(0xffFFFFFF);
                                if (GeneralMethod.isFastClick()) {
                                    if (onClick != null) {
                                        onClick.checkGongxu(position, datas.getId(), datas.getProcess_name());
                                    }
                                }
//                            ToastUtils.showBottomToast(context, "选择了" + datas.getStation_name());
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
            } else if (type == 3) {
                name.setText("" + datas.getSubcontractors_name());

                itemView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                itemView.setBackgroundColor(0xffb8e1fb);
                                break;
//                        case MotionEvent.ACTION_MOVE:
//                            LogUtil.log("action_move");
//                            break;
                            case MotionEvent.ACTION_UP:
                                itemView.setBackgroundColor(0xffFFFFFF);
                                if (GeneralMethod.isFastClick()) {
                                    if (onClick != null) {
                                        onClick.checkUtils(position, datas.getId(), datas.getSubcontractors_name());
                                    }
                                }
//                            ToastUtils.showBottomToast(context, "选择了" + datas.getStation_name());
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
            } else if (type == 4) {//安全
                name.setText("" + datas.getRisk_category());

                itemView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                itemView.setBackgroundColor(0xffb8e1fb);
                                break;
//                        case MotionEvent.ACTION_MOVE:
//                            LogUtil.log("action_move");
//                            break;
                            case MotionEvent.ACTION_UP:
                                itemView.setBackgroundColor(0xffFFFFFF);
                                if (GeneralMethod.isFastClick()) {
                                    if (onClick != null) {
                                        onClick.checkType(position, datas.getId(), datas.getRisk_category());
                                    }
                                }
//                            ToastUtils.showBottomToast(context, "选择了" + datas.getStation_name());
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
            } else if (type == 5) {//质量
                name.setText("" + datas.getQuality_category());

                itemView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                itemView.setBackgroundColor(0xffb8e1fb);
                                break;
//                        case MotionEvent.ACTION_MOVE:
//                            LogUtil.log("action_move");
//                            break;
                            case MotionEvent.ACTION_UP:
                                itemView.setBackgroundColor(0xffFFFFFF);
                                if (GeneralMethod.isFastClick()) {
                                    if (onClick != null) {
                                        onClick.checkType(position, datas.getId(), datas.getQuality_category());
                                    }
                                }
//                            ToastUtils.showBottomToast(context, "选择了" + datas.getStation_name());
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
            }


        }
    }

    public interface OnClick {
        void checkZhandian(int position, int sectionId, String name);

        void checkGongxu(int position, int sectionId, String name);

        void checkUtils(int position, int sectionId, String name);

        void checkType(int position, int sectionId, String name);
    }

    public OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }


}
