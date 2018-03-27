package com.rlzz.wms.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期选择控件工具类
 *
 * @author monty
 * @date 2017/8/30
 */

public class DatePickerUtil {
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd";

    /**
     * 选择日期Dilog(选中当前日期)
     *
     * @param context
     * @param listener
     */
    public static DatePickerDialog showDatePickerDialog(Context context, DatePickerDialog.OnDateSetListener listener) {
        return showDatePickerDialog(context, null, listener);
    }

    /**
     * 选择日期Dilog（选中传入的日期）
     *
     * @param context
     * @param listener
     */
    public static DatePickerDialog showDatePickerDialog(Context context, String dateStr, DatePickerDialog.OnDateSetListener listener) {
        int[] date = formatDate2Int(dateStr);
        return showDatePickerDialog(context, listener, date[0], date[1], date[2]);
    }

    /**
     * 选择日期Dilog（选中传入的日期）
     *
     * @param context
     * @param listener
     * @param year
     * @param month
     * @param dayOfMonth
     */
    public static DatePickerDialog showDatePickerDialog(Context context, DatePickerDialog.OnDateSetListener listener, int year, int month, int dayOfMonth) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, listener, year, month, dayOfMonth);
        // setCalendarViewShown在Material-style中是不生效的，所以如果版本号在5.0以下就设置不显示日历，否则就不设置
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            datePickerDialog.getDatePicker().setCalendarViewShown(false);
        }
        datePickerDialog.setCancelable(false);
        datePickerDialog.show();
        return datePickerDialog;
    }

    /**
     * 选择日期Dialog (可设置选择范围)
     *
     * @param context
     * @param listener
     * @param startDate  起始
     * @param endDate    结束
     * @param currenDate 当前选中的时间
     * @return
     */
    public static DatePickerDialog showDatePickerDialog(Context context, DatePickerDialog.OnDateSetListener listener, String startDate, String endDate, String currenDate) {
        int[] date = formatDate2Int(currenDate);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, listener, date[0], date[1], date[2]);
        // setCalendarViewShown在Material-style中是不生效的，所以如果版本号在5.0以下就设置不显示日历，否则就不设置
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            datePickerDialog.getDatePicker().setCalendarViewShown(false);
        }
        if (!TextUtils.isEmpty(startDate)) {
            datePickerDialog.getDatePicker().setMinDate(formatString2Date(startDate).getTime());
        }
        if (!TextUtils.isEmpty(endDate)) {
            datePickerDialog.getDatePicker().setMaxDate(formatString2Date(endDate).getTime());
        }
        datePickerDialog.setCancelable(false);
        datePickerDialog.show();
        return datePickerDialog;
    }


    /**
     * 默认返回当前日期（如dateString解析有问题就直接返回当前日期，否则使用解析出来的日期）
     *
     * @param dateString
     * @return
     */
    public static int[] formatDate2Int(String dateString) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int dayOfMonty = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        int[] data = {year, month, dayOfMonty};
        if (!Check.isEmpty(dateString)) {
            String[] split = dateString.split("[-]");
            if (!Check.isEmpty(split) && split.length == 3) {
                for (int i = 0; i < split.length; i++) {
                    if (i == 1) { //代码处理月份减一
                        data[i] = Integer.parseInt(split[i]) - 1;
                    } else {
                        data[i] = Integer.parseInt(split[i]);
                    }
                }
            }
        }
        return data;
    }

    public static String formatDate2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
        String dateStr = sdf.format(date);
        return dateStr;
    }

    public static String formatDate2String(int year, int month, int dayOfMonth) {
        month += 1; //显示给用户时月份+1
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(year);
        stringBuffer.append("-");
        addPrefix(month, stringBuffer);
        stringBuffer.append("-");
        addPrefix(dayOfMonth, stringBuffer);
        return stringBuffer.toString();
    }

    public static Date formatString2Date(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
        try {
            Date date = sdf.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void addPrefix(int num, StringBuffer stringBuffer) {
        if (num <= 9) {
            stringBuffer.append("0");
        }
        stringBuffer.append(num);

    }

}
