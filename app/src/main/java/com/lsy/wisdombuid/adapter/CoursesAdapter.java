package com.lsy.wisdombuid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.activity.exam.TeachingVideoActivity;
import com.lsy.wisdombuid.bean.CoursesData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy on 2020/4/24
 * todo :
 */
public class CoursesAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<CoursesData> cateList = new ArrayList();
    private LayoutInflater inflater;

    public CoursesAdapter(Context context, List<CoursesData> cateList) {
        this.context = context;
        this.cateList = cateList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CoursesAdapter.ItemView(inflater.inflate(R.layout.item_vertical_inner, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CoursesAdapter.ItemView) holder).bindData(cateList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return cateList.size();
    }


    class ItemView extends RecyclerView.ViewHolder {

        private TextView hText;

        public ItemView(View itemView) {
            super(itemView);
            hText = itemView.findViewById(R.id.help_text_inner);
        }


        public void bindData(Object item, final int position) {
            final CoursesData datas = (CoursesData) item;

            hText.setText((position + 1) + "、" + datas.getTrain_name());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent record = new Intent();
                    record.putExtra("id", datas.getId());
                    record.putExtra("video_url",datas.getVideo_url());
                    record.putExtra("content",datas.getContent());
                    record.setClass(context, TeachingVideoActivity.class);
                    context.startActivity(record);
                }
            });

        }
    }

}
