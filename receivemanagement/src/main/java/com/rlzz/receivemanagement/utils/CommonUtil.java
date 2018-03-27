package com.rlzz.receivemanagement.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by lml on 2018/3/27.
 * 各种杂方法
 */

public class CommonUtil {

    /**
     * 显示时间在TextView上
     * @param context
     * @param tvTime
     */
    public static void showDateSelectDialog(Context context, final TextView tvTime){
        final Calendar calender = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calender.set(year, monthOfYear, dayOfMonth);
                        tvTime.setText(DateFormat.format("yyy-MM-dd", calender));
                    }
                }, calender.get(Calendar.YEAR), calender.get(Calendar.MONTH), calender.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}
