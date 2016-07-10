package com.alkv.ljw.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alkv.ljw.gamecenter.R;
import com.alkv.ljw.utils.DateUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by alkv1 on 2016/7/9.
 */
public class GamePullToRefreshAdapter extends BaseAdapter {
    private List<HashMap<String,String>> list;
    private Context context;
    public GamePullToRefreshAdapter(List<HashMap<String,String>> list, Context context){
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view = View.inflate(context, R.layout.game_item01,null);
            viewHolder = new ViewHolder();
            viewHolder.game_item01_iv = (ImageView) view.findViewById(R.id.game_item01_iv);
            viewHolder.game_item01_title = (TextView) view.findViewById(R.id.game_item01_title);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.game_item01_title.setText(list.get(i).get("title"));
        viewHolder.game_item01_iv.setImageBitmap(BitmapFactory.decodeFile(list.get(i).get("litpic")));

        return view;
    }
    class ViewHolder{
        ImageView game_item01_iv;
        TextView game_item01_title;
    }
}
