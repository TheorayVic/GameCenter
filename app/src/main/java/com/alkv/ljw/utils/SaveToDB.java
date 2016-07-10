package com.alkv.ljw.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.alkv.ljw.model.OneNewInfo;

import java.util.List;

/**
 * Created by alkv1 on 2016/7/7.
 */
public class SaveToDB {
    public static boolean saveListToDB(List<OneNewInfo> newsList,Context context){
        //首先连接数据库
        SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
        //存入数据库
        for(OneNewInfo i:newsList){
            String id = i.getId();
            String typeid = i.getTypeid();
            String title = i.getTitle();
            String pubdate = i.getPubdate();
            String senddate = i.getSenddate();
            String shorttitle = i.getShorttitle();
            String description = i.getDescription();
            String arcurl = i.getArcurl();
            String litpic = i.getLitpic();
            db.execSQL("insert into news (id,typeid,title,pubdate,senddate,shorttitle,description,arcurl,litpic)" +
                    " values ('"+id+"','"+typeid+"','"+title+"','"+pubdate+"','"+senddate+"','"+shorttitle+"','"+description+"'" +
                    ",'"+arcurl+"','"+litpic+"')");
        }
        db.close();
        return true;
    }
    public static boolean saveGamePicsToDB(List<OneNewInfo> newsList,Context context){
        //首先连接数据库
        SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
        //存入数据库
        for(OneNewInfo i:newsList){
            String typeid = i.getTypeid();
            String title = i.getTitle();
            String arcurl = i.getArcurl();
            String litpic = i.getLitpic();
            db.execSQL("insert into news (typeid,title,arcurl,litpic)" +
                    " values ('"+typeid+"','"+title+"','"+arcurl+"','"+litpic+"')");
        }
        db.close();
        return true;
    }
}
