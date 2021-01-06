package com.lsy.wisdombuid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.quality.QMSReportActivity;
import com.lsy.wisdombuid.activity.safety.RectificationNoticeActivity;
import com.lsy.wisdombuid.activity.safety.ZhengGaiReportActivity;
import com.lsy.wisdombuid.bean.IRecordData;
import com.lsy.wisdombuid.util.GeneralMethod;
import com.lsy.wisdombuid.util.SharedUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy on 2020/3/25
 * todo : 待整改
 */
public class AfterRectificationAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<IRecordData> messageList = new ArrayList();
    private LayoutInflater inflater;

    private SharedUtils sharedUtils;

    private int type = 1;//1、安全  2、质量

    public AfterRectificationAdapter(Context context, List<IRecordData> messageList, int type) {
        this.context = context;
        this.messageList = messageList;
        this.type = type;

        sharedUtils = new SharedUtils(context, SharedUtils.WISDOM);

        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(inflater.inflate(R.layout.view_after_rectification, parent, false));
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

        private TextView danwei;
        private TextView insPeople;//检查人
        private TextView projectName;//项目名称
        private TextView zerenPeople;//责任人
        private TextView shangbao;//整改上报
        private TextView tognzhidan;//整改通知单
        private TextView planTime;//整改期限

        public ItemView(View itemView) {
            super(itemView);
            danwei = itemView.findViewById(R.id.rect_danwei);
            insPeople = itemView.findViewById(R.id.rect_ins_people);
            projectName = itemView.findViewById(R.id.rect_project_name);
            shangbao = itemView.findViewById(R.id.after_shangbao);
            tognzhidan = itemView.findViewById(R.id.after_tongzhidna);
            zerenPeople = itemView.findViewById(R.id.rect_zeren_people);
            planTime = itemView.findViewById(R.id.rect_plan_itme);
        }

        public void bindData(Object item, final int position) {
            final IRecordData datas = (IRecordData) item;

            danwei.setText("" + datas.getSub_name());

            insPeople.setText("" + datas.getStaff_name());
            projectName.setText("" + datas.getTitle());
            zerenPeople.setText("" + datas.getResponsible());
            planTime.setText("" + datas.getPlan_time());

            //整改上报
            shangbao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (GeneralMethod.isFastClick()) {
                        sharedUtils.setData(sharedUtils.IRDETAILS, "" + datas.toString());//存入对象
                        if (type == 1) {
                            Intent sbao = new Intent(context, ZhengGaiReportActivity.class);
                            sbao.putExtra("url",datas.getUrl());
                            context.startActivity(sbao);
                        } else {
                            Intent sbao = new Intent(context, QMSReportActivity.class);
                            context.startActivity(sbao);
                        }


                    }
                }
            });

            //整改通知单
            tognzhidan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (GeneralMethod.isFastClick()) {
                        sharedUtils.setData(sharedUtils.IRDETAILS, "" + datas.toString());//存入对象
                        Intent sbao = new Intent(context, RectificationNoticeActivity.class);
                        context.startActivity(sbao);
                    }
                }
            });

//            desc.setText(""+datas.getIntroduce());
//
//            if (datas.getLogo().get(0)!=null){
//                Glide.with(context).load("http://a2.sayyin.com/community" + datas.getLogo().get(0))
//                        .error(R.mipmap.shangping) //异常时候显示的图片
//                        .placeholder(R.mipmap.shangping) //加载成功前显示的图片
//                        .fallback(R.mipmap.shangping) //url为空的时候,显示的图片
////                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
//                        .into(headImage);
//            }


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    ToastUtils.showBottomToast(context, "正在登陆账号");
//                    Intent toAbout = new Intent(context, MainActivity.class);
////                    toAbout.putExtra("goodsId",""+datas.getGoodsId());
////                    toAbout.putExtra("articleId",datas.getArticleId());
//                    context.startActivity(toAbout);
//
//                }
//            });

//            photo.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    return false;
//                }
//            });

//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    relativeLayout.setVisibility(View.VISIBLE);
//                    return true;
//                }
//            });
        }
    }


}
