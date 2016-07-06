package com.alkv.ljw.actions;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alkv.ljw.adapter.GuideViewPagerAdapter;
import com.alkv.ljw.gamecenter.MainActivity;
import com.alkv.ljw.gamecenter.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    List<View> views;
    ImageView[] dots;
    int currentIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initDots();
    }
    //初始化view
    public void initView(){
        viewPager = (ViewPager) this.findViewById(R.id.guide_viewpager);
        views = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        View view1 = inflater.inflate(R.layout.guide_pager_01,null);
        View view2 = inflater.inflate(R.layout.guide_pager_02,null);
        View view3 = inflater.inflate(R.layout.guide_pager_03,null);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        GuideViewPagerAdapter adapter = new GuideViewPagerAdapter(views);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
    }
    //初始化dots
    public void initDots(){
        LinearLayout ll = (LinearLayout) this.findViewById(R.id.guide_imageview_ll);
        dots = new ImageView[views.size()];
        for(int i = 0 ; i < views.size() ;i++){
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(true);
        }
        currentIndex = 0;
        dots[currentIndex].setEnabled(false);
    }

    /**
     * 监听
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position<0 || position+1 > views.size()){
            return;
        }
        dots[position].setEnabled(false);
        dots[currentIndex].setEnabled(true);
        currentIndex = position;
        if(position == views.size()-1){
            Button btn = (Button) this.findViewById(R.id.guide_pager03_btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //保存登陆记录
                    saveLogin();
                    //跳转
                    Intent mainIntent = new Intent(GuideActivity.this,MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            });
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //保存登录信息到SharedPreference
    public void saveLogin(){
        SharedPreferences sharedPreferences = getSharedPreferences("isFirstLogin",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogin",false);
        editor.commit();
    }
}
