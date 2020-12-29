package com.lsy.wisdombuid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lsy.wisdombuid.R;
import com.lsy.wisdombuid.bean.DeviceData;
import com.lsy.wisdombuid.bean.HomeBtnData;
import com.lsy.wisdombuid.util.GeneralMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by lsy on 2019/8/14
 * MODO : 物料监测
 */
public class JianCeAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<DeviceData.RealData> deviceData = new ArrayList();
    private LayoutInflater inflater;

    public JianCeAdapter(Context context, List<DeviceData.RealData> deviceData) {
        this.context = context;
        this.deviceData = deviceData;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemView(inflater.inflate(R.layout.view_jiance_data, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ItemView) holder).bindData(deviceData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return deviceData.size();
    }


    class ItemView extends RecyclerView.ViewHolder {

        private TextView jValue;
        private TextView jName;

        private ImageView jImage;

        public ItemView(View itemView) {
            super(itemView);
            jValue = (TextView) itemView.findViewById(R.id.jiance_value);
            jName = (TextView) itemView.findViewById(R.id.jiance_name);
            jImage = (ImageView) itemView.findViewById(R.id.jiance_image);
        }

        public void bindData(Object item, final int position) {
            final DeviceData.RealData datas = (DeviceData.RealData) item;

//            "dataName":"TSP(ug/m³)",模拟量名称
//                    "dataValue":"58.00",实时数据
//                    "isAlarm":false,是否报警
//                    "alarmMsg":"",
//                    "alarm":false
            jValue.setText("" + datas.getDataValue());
            jName.setText("" + datas.getDataName());

            switch (position) {
                case 0:
                    jImage.setImageResource(R.mipmap.jiance_pm10);
                    break;
                case 1:
                    jImage.setImageResource(R.mipmap.jiance_pm25);
                    break;
                case 2:
                    jImage.setImageResource(R.mipmap.jiance_zhaosheng);
                    break;
                case 3:
                    jImage.setImageResource(R.mipmap.jiance_wendu);
                    break;
                case 4:
                    jImage.setImageResource(R.mipmap.jiance_shidu);
                    break;
                case 5:
                    jImage.setImageResource(R.mipmap.jiance_fenli);
                    break;
                case 6:
                    jImage.setImageResource(R.mipmap.fenshu);
                    break;
                case 7:
                    jImage.setImageResource(R.mipmap.jiance_fenxiang);
                    break;
                case 8:
                    jImage.setImageResource(R.mipmap.jiance_tsp);
                    break;
                case 9:
                    jImage.setImageResource(R.mipmap.jiance_daqiyali);
                    break;

                default:
                    break;
            }

        }
    }

    public interface OnClick {
        void zixunNow(int position);
    }

    public OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }


}
