package com.alkv.ljw.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by alkv1 on 2016/7/7.
 */
public class DbClear {
    public static void clearDB(Context context){
        //连接数据库
        SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();

        db.execSQL("delete from news where id>0");
        Log.i("kobe","数据库清理完成");
        db.close();
        Log.i("kobe","关闭数据库");
    }
}
