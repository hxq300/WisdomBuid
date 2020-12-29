package com.lsy.wisdombuid.tools;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.Collections;
import java.util.List;

/**
 * Created by lsy on 2020/3/31
 * todo :
 */
public class MyXAxisValueFormatter  implements IAxisValueFormatter {

    private List mValues;
    private List<?> reverse_mValues;

    public MyXAxisValueFormatter(List values) {
        this.mValues = values;
        this.reverse_mValues =  values.subList(0, values.size());
        Collections.reverse(reverse_mValues);
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        // "value" represents the position of the label on the axis (x or y)
        if((int) value < 0 || (int) value >= reverse_mValues.size()){
            return "";
        }else{
            return (String) reverse_mValues.get((int) value);
        }

    }

    /** this is only needed if numbers are returned, else return 0 */
    public int getDecimalDigits() { return 0; }
}
