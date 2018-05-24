package com.jnyilin.basiclib.view;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.bigkoo.pickerview.TimePickerView;
import com.jnyilin.basiclib.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author:zy
 * @detetime:2017/12/1
 * @describe：类描述
 * @modifyRecord:修改记录
 */

public class PickerViewUtils {
    public static TimePickerView pvTime;

    /**
     * 日期选择
     * @param baseActivity
     * @param show 显示日期的位数
     * @param onTimeSelectListener  选择后的回调监听
     */
    public static void initTimePicker(BaseActivity baseActivity, @Nullable boolean[] show, TimePickerView.OnTimeSelectListener onTimeSelectListener) {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.set(selectedDate.get(Calendar.YEAR),selectedDate.get(Calendar.MONTH),selectedDate.get(Calendar.DAY_OF_MONTH));
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH));
        //时间选择器
        pvTime = new TimePickerView.Builder(baseActivity, onTimeSelectListener)
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(show)
                .setLabel("年", "月", "日", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
        pvTime.show();
    }
    public static String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
        return format.format(date);
    }
    public static String getdayTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }
}
