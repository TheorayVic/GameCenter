package com.alkv.ljw.utils;

import java.text.SimpleDateFormat;

/**
 * Created by alkv1 on 2016/7/7.
 */
public class DateUtils {
    public static String dataChange(int time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        return simpleDateFormat.format(time);
    }
}
