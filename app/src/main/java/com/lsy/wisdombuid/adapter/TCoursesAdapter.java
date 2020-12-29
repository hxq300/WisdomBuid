package com.lsy.wisdombuid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.exam.TeachingVideoActivity;
import com.lsy.wisdombuid.bean.TrainingCoursesData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy on 2020/4/8
 * todo :
 */
public class TCoursesAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<TrainingCoursesData> cateList = new ArrayList();
    private LayoutInflater inflater;

    public TCoursesAdapter(Context context, List<TrainingCoursesData> cateList) {
        this.context = context;
        this.cateList = cateList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(inflater.inflate(R.layout.view_training_course_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ItemView) holder).bindData(cateList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return cateList.size();
    }


    class ItemView extends RecyclerView.ViewHolder {

        private TextView helpTv;
        private RecyclerView rvItem;

        private LinearLayout zongLine;
        private ImageView imageFangxiang;

        public ItemView(View itemView) {
            super(itemView);
            helpTv = itemView.findViewById(R.id.help_text);
            rvItem = itemView.findViewById(R.id.rv_item);
            zongLine = itemView.findViewById(R.id.zong_line);
            imageFangxiang = itemView.findViewById(R.id.image_fangxiang);
        }

        public void bindData(Object item, final int position) {
            final TrainingCoursesData datas = (TrainingCoursesData) item;

            helpTv.setText("" + datas.getType_name());

            final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            rvItem.setLayoutManager(layoutManager);
            InnerAdapter innerAdapter = new InnerAdapter(datas.getDataIn());
            rvItem.setAdapter(innerAdapter);

            rvItem.setVisibility(View.GONE);

            zongLine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rvItem.getVisibility() == View.VISIBLE) {
                        imageFangxiang.setImageResource(R.mipmap.black_into);
                        rvItem.setVisibility(View.GONE);
                    } else {
                        imageFangxiang.setImageResource(R.mipmap.black_bottom);
                        rvItem.setVisibility(View.VISIBLE);
                    }
                }
            });

        }
    }


    public interface OnClick {
//        void delAddress(int position);
    }

    public OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }


    private class InnerAdapter extends RecyclerView.Adapter {

        private List<TrainingCoursesData.DataIn> helps;

        public InnerAdapter(List<TrainingCoursesData.DataIn> helps) {
            this.helps = helps;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new InnerAdapter.ItemView(inflater.inflate(R.layout.item_vertical_inner, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((InnerAdapter.ItemView) holder).bindData(helps.get(position), position);
        }

        @Override
        public int getItemCount() {
            return helps.size();
        }


        class ItemView extends RecyclerView.ViewHolder {

            private TextView hText;

            public ItemView(View itemView) {
                super(itemView);
                hText = itemView.findViewById(R.id.help_text_inner);
            }

            public void bindData(Object item, final int position) {
                final TrainingCoursesData.DataIn datas = (TrainingCoursesData.DataIn) item;

                hText.setText((position + 1) + "、" + datas.getTrain_name());

//                final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//                rvItem.setLayoutManager(layoutManager);
//                InnerAdapter innerAdapter = new InnerAdapter(datas.getHelpList());
//                rvItem.setAdapter(innerAdapter);
//
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent record = new Intent();
                        record.putExtra("id", datas.getId());
                        record.setClass(context, TeachingVideoActivity.class);
                        context.startActivity(record);
                    }
                });
            }
        }
    }

}
