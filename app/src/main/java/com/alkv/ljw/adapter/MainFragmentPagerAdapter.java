package com.alkv.ljw.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.alkv.ljw.fragment.MainArticleFragment;

import java.util.List;

/**
 * Created by alkv1 on 2016/7/6.
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<MainArticleFragment> fragmentList;
    public MainFragmentPagerAdapter(FragmentManager fm,List<MainArticleFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
