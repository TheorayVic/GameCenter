package com.alkv.ljw.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alkv.ljw.customview.MyMainArticleViewPager;

import java.util.List;

/**
 * Created by alkv1 on 2016/7/6.
 */
public class MyMainArticleViewPagerAdapter extends PagerAdapter {
    private List<ImageView> list;
    public MyMainArticleViewPagerAdapter(List<ImageView> list){
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
