package com.alkv.ljw.customview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by alkv1 on 2016/7/6.
 */
public class MyMainArticleViewPager extends ViewPager{
    public MyMainArticleViewPager(Context context) {
        super(context);
    }

    public MyMainArticleViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //不允许父类的viewpager拦截该viewpager的滑动效果
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
