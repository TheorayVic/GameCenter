package com.alkv.ljw.utils;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by alkv1 on 2016/7/5.
 */
public class HttpUtils {
    //进行http链接
    public static byte[] httpGet(String urlStr){
        try {
            URL url = new URL(urlStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setRequestMethod("GET");
            Log.i("kobe",httpURLConnection.getResponseCode()+"");
            if(httpURLConnection.getResponseCode()==200){
                InputStream is = httpURLConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte [] b = new byte[1024];
                int l = 0;
                while((l = is.read(b))!=-1){
                    baos.write(b,0,l);
                }
                return baos.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
