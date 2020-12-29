package com.lsy.wisdombuid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.bean.ShopGoodsData;
import com.lsy.wisdombuid.request.RequestURL;
import com.lsy.wisdombuid.util.GeneralMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsy on 2020/3/17
 * todo : 积分商城适配器
 */
public class InteralShopAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ShopGoodsData> messageList = new ArrayList();
    private LayoutInflater inflater;

    public InteralShopAdapter(Context context, List<ShopGoodsData> messageList) {
        this.context = context;
        this.messageList = messageList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(inflater.inflate(R.layout.view_interal_shop_list, parent, false));
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


        private ImageView sImage;
        private TextView sTitle;
        private TextView jifen;

        public ItemView(View itemView) {
            super(itemView);
            sImage = itemView.findViewById(R.id.shop_image);
            sTitle = itemView.findViewById(R.id.shop_title);
            jifen = itemView.findViewById(R.id.interal_jifen);
        }

        public void bindData(Object item, final int position) {
            final ShopGoodsData datas = (ShopGoodsData) item;

            Glide.with(context).load(RequestURL.OssUrl + datas.getCommodity_img()).error(R.mipmap.good1).into(sImage);

            sTitle.setText("" + datas.getCommodity_name());

            jifen.setText(datas.getCommodity_price() + "积分");


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (GeneralMethod.isFastClick()) {
                        if (onClick != null) {
                            onClick.toConvert("" + datas.getId(), datas.getCommodity_price());
                        }
                    }


                }
            });

        }
    }


    public interface OnClick {
        void toConvert(String commodity_id, int commodity_price);//兑换
    }

    public OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }


}
