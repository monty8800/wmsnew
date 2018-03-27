package com.rlzz.library.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by monty on 2017/11/10.
 */

public class DateUtil {
    public static String toLocalDate(Date date) {
        if (date == null) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        cal.add(Calendar.HOUR, +8);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
        return df.format(cal.getTime());
    }
}
