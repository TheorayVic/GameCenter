package com.alkv.ljw.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.alkv.ljw.model.OneNewInfo;
import com.alkv.ljw.utils.HttpUtils;
import com.alkv.ljw.utils.JsonUtils;
import com.alkv.ljw.utils.SaveToDB;

import java.util.List;

public class DownloadService extends Service {
    String urlPart1 = "http://122.226.122.6/sitemap/api.php?row=10&typeid=";
    String urlPart2 = "&paging=1&page=";
    String typeid;
    String pageid;
    public DownloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        //网络加载数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                typeid = intent.getStringExtra("typeid");
                pageid = intent.getStringExtra("pageid");
                byte [] b = HttpUtils.httpGet(urlPart1+typeid+urlPart2+pageid);
                if(b != null){
                    Log.i("kobe","下载服务进入Json解析");
                    //开始进行json解析
                    List<OneNewInfo> list = JsonUtils.jsonToList(new String(b));
                    if(list!=null){
                        boolean flag = SaveToDB.saveListToDB(list,getApplicationContext());
                        if(flag){
                            Log.i("kobe","DownloadService完成");
                            stopSelf();
                            Log.i("kobe","完成后终止了Service");
                        }
                    }
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }
}
