package com.alkv.ljw.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.alkv.ljw.utils.HttpUtils;

public class DownloadService extends Service {
    public DownloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //网络加载数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte [] b = HttpUtils.httpGet("http://122.226.122.6/sitemap/api.php?row=10&typeid=1,2&paging=1&page=1");
                if(b != null){
                    Log.i("kobe","进入Json解析");

                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }
}
