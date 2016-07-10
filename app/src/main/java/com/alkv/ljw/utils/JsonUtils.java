package com.alkv.ljw.utils;

import android.util.Log;

import com.alkv.ljw.model.OneNewInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alkv1 on 2016/7/5.
 */
public class JsonUtils {
    public static List<OneNewInfo> jsonToList(String jsonStr){
        List<OneNewInfo> list;
        try {
            JSONObject root = new JSONObject(jsonStr);
            JSONObject data = root.getJSONObject("data");
            list = new ArrayList<>();
            for(int i = 0; i < 10; i++){
                JSONObject jsonObject = data.getJSONObject(i+"");
                String id = jsonObject.getString("id");
                String typeid = jsonObject.getString("typeid");
                String title = jsonObject.getString("title");
                String pubdate = jsonObject.getString("pubdate");
                String senddate = jsonObject.getString("senddate");
                String shorttitle = jsonObject.getString("shorttitle");
                String description = jsonObject.getString("description");
                String arcurl = jsonObject.getString("arcurl");
                String litpicUrl = jsonObject.getString("litpic");
                Log.i("kobe",title);
                OneNewInfo oneNewInfo = new OneNewInfo();
                oneNewInfo.setId(id);
                oneNewInfo.setTypeid(typeid);
                oneNewInfo.setTitle(title);
                oneNewInfo.setPubdate(pubdate);
                oneNewInfo.setSenddate(senddate);
                oneNewInfo.setShorttitle(shorttitle);
                oneNewInfo.setDescription(description);
                oneNewInfo.setArcurl(arcurl);
                String litpic = SDCardHelper.savePicToSDCard("http://www.3dmgame.com"+litpicUrl,id);
                oneNewInfo.setLitpic(litpic);
                list.add(oneNewInfo);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
