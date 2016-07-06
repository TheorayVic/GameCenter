package com.alkv.ljw.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by alkv1 on 2016/7/5.
 */
public class NetUtils {
    public static boolean isNetOpen(Activity activity){
        boolean flag = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager==null){
            return flag;
        }
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null || networkInfo.isAvailable()){
            return true;
        }
        return flag;
    }
}
