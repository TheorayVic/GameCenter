package com.alkv.ljw.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.alkv.ljw.model.OneNewInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alkv1 on 2016/7/7.
 */
public class GetFromDb {
    public static List<OneNewInfo> getFromDB(Context context,String typeId){
        List<OneNewInfo> newsList = new ArrayList<>();
        //连接数据库
        SQLiteHelper sqLiteHelper = new SQLiteHelper(context);
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
        //获取数据库中的数据
        Cursor dataCursor = db.rawQuery("select id,typeid,title,pubdate,senddate,shorttitle,description,arcurl,litpic from news",null);
        while(dataCursor.moveToNext()){
            if(typeId.equals(dataCursor.getString(dataCursor.getColumnIndex("typeid")))){
                String id = dataCursor.getString(dataCursor.getColumnIndex("id"));
                String typeid = dataCursor.getString(dataCursor.getColumnIndex("typeid"));
                String title = dataCursor.getString(dataCursor.getColumnIndex("title"));
                String pubdate = dataCursor.getString(dataCursor.getColumnIndex("pubdate"));
                String senddate = dataCursor.getString(dataCursor.getColumnIndex("senddate"));
                String shorttitle = dataCursor.getString(dataCursor.getColumnIndex("shorttitle"));
                String description = dataCursor.getString(dataCursor.getColumnIndex("description"));
                String arcurl = dataCursor.getString(dataCursor.getColumnIndex("arcurl"));
                String litpic = dataCursor.getString(dataCursor.getColumnIndex("litpic"));

                OneNewInfo oneNewInfo = new OneNewInfo();
                oneNewInfo.setId(id);
                oneNewInfo.setTypeid(typeid);
                oneNewInfo.setTitle(title);
                oneNewInfo.setPubdate(pubdate);
                oneNewInfo.setSenddate(senddate);
                oneNewInfo.setShorttitle(shorttitle);
                oneNewInfo.setDescription(description);
                oneNewInfo.setArcurl(arcurl);
                oneNewInfo.setLitpic(litpic);

                newsList.add(oneNewInfo);
            }
        }
        return newsList;
    }
}
