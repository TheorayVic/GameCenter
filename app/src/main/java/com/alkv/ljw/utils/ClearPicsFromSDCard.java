package com.alkv.ljw.utils;

import android.util.Log;

import java.io.File;

/**
 * Created by alkv1 on 2016/7/9.
 */
public class ClearPicsFromSDCard {
    public static void clearAllPics(File root){
            File [] files = root.listFiles();
            if (files != null)
                for (File f : files) {
                    if (f.isDirectory()) { // 判断是否为文件夹
                        clearAllPics(f);
                        try {
                            f.delete();
                        } catch (Exception e) {
                        }
                    } else {
                        if (f.exists()) { // 判断是否存在
                            clearAllPics(f);
                            try {
                                f.delete();
                            } catch (Exception e) {
                            }
                        }
                    }
                }
        Log.i("kobe","sd卡清除成功");
    }
}
