package com.alkv.ljw.adapter;

import android.content.Context;
import android.graphics.Bitmap;
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
 * Created by alkv1 on 2016/7/7.
 */
public class ArticlePullToRefreshAdapter extends BaseAdapter{
    private List<HashMap<String,String>> list;
    private Context context;
    public ArticlePullToRefreshAdapter(List<HashMap<String,String>> list, Context context){
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
            view = View.inflate(context, R.layout.article_item01,null);
            viewHolder = new ViewHolder();
            viewHolder.article_item01_newsiv = (ImageView) view.findViewById(R.id.article_item01_newsiv);
            viewHolder.article_item01_newstitle = (TextView) view.findViewById(R.id.article_item01_newstitle);
            viewHolder.article_item01_pubdate = (TextView) view.findViewById(R.id.article_item01_pubdate);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.article_item01_newstitle.setText(list.get(i).get("title"));
        viewHolder.article_item01_newsiv.setImageBitmap(BitmapFactory.decodeFile(list.get(i).get("litpic")));
        String data = DateUtils.dataChange(Integer.parseInt(list.get(i).get("pubdate")));
        viewHolder.article_item01_pubdate.setText(data);

        return view;
    }

    class ViewHolder{
        ImageView article_item01_newsiv;
        TextView article_item01_newstitle;
        TextView article_item01_pubdate;
    }
}
