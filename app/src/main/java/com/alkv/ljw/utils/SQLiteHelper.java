package com.alkv.ljw.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by alkv1 on 2016/7/7.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    public SQLiteHelper(Context context) {
        super(context, "news.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists news (_id integer primary key autoincrement,id varchar," +
                "typeid varchar,title varchar,pubdate varchar,senddate varchar,shorttitle varchar," +
                "description varchar,arcurl varchar,litpic varchar)");
        Log.i("kobe","数据库连接成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
