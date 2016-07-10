package com.alkv.ljw.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by alkv1 on 2016/7/6.
 */
public class SDCardHelper {

    //判断SD卡是否加载成功
    public static boolean isSDCardMounted(){
        boolean flag = false;
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            Log.i("kobe","sd卡加载成功");
            return true;
        }
        return flag;
    }
    //返回SD卡的路径
    public static String getSDCardPath(){
        if(isSDCardMounted()){
//            Log.i("kobe","SD卡的路径是："+Environment.getExternalStoragePublicDirectory(
//                    Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        }
        return null;
    }
    //判断sd卡剩余大小是否满足

    //保存图片内容到sd卡,返回值是图片在sdk上的保存路径
    public static String savePicToSDCard(final String picUrl, String id){
        String sdCardPath = getSDCardPath();
        if(sdCardPath!=null){
                    byte [] pic = HttpUtils.httpGet(picUrl);
                    if(pic!=null){
                        File add = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                        File newpic = new File(add,id+".jpg");
                        try {
                            FileOutputStream fos = new FileOutputStream(newpic);
                            fos.write(pic,0,pic.length);
                            if(fos!=null){
                                fos.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//                        Log.i("kobe",Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//                                +File.separator+id+".jpg");
                        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                                +File.separator+id+".jpg";
                    }
        }
        return null;
    }
    //清除sd卡保存的图片
    public static void sdCardClear(){

    }

}
