package com.alkv.ljw.gamecenter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alkv.ljw.actions.DiscuzActivity;
import com.alkv.ljw.actions.GameActivity;
import com.alkv.ljw.adapter.MainFragmentPagerAdapter;
import com.alkv.ljw.fragment.MainArticleFragment;
import com.alkv.ljw.utils.ClearPicsFromSDCard;
import com.alkv.ljw.utils.DbClear;
import com.alkv.ljw.utils.SDCardHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener {
    HorizontalScrollView main_horizontal_scrollview;
    RadioGroup main_top_radiogroup,main_bottom_radiogroup;
    ViewPager main_center_viewpager;
    RadioButton main_top_btn01_article,main_top_btn02_news,main_top_btn03_gamechat,main_top_btn04_hardsoftware,
                main_top_btn05_gameforward,main_top_btn06_gamevoice,main_top_btn07_nicehomemade,
                main_top_btn08_gameresult,main_top_btn09_newsfocus,main_top_btn10_gameskill;
    RadioButton bottom_btn_01_article,bottom_btn_01_chat,bottom_btn_01_game;

    //存放首页所有文章fragment的list集合
    List<MainArticleFragment> fragmentList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化所有控件、因为控件太多 所以放在一个方法中、让代码清晰可见
        initViews();
        //初始化所有的监听
        initListener();
        //添加fragment（包涵轮播viewpager和pulltorefresh的列表内容）到首页中间的viewpager中
        lunboAndListToMainCenterViewPager();
    }

    //初始化控件
    private void initViews(){
        main_horizontal_scrollview = (HorizontalScrollView) this.findViewById(R.id.main_horizontal_scrollview);

        main_top_radiogroup = (RadioGroup) this.findViewById(R.id.main_top_radiogroup);
        main_top_btn01_article = (RadioButton) this.findViewById(R.id.main_top_btn01_article);
        main_top_btn02_news = (RadioButton) this.findViewById(R.id.main_top_btn02_news);
        main_top_btn03_gamechat = (RadioButton) this.findViewById(R.id.main_top_btn03_gamechat);
        main_top_btn04_hardsoftware = (RadioButton) this.findViewById(R.id.main_top_btn04_hardsoftware);
        main_top_btn05_gameforward = (RadioButton) this.findViewById(R.id.main_top_btn05_gameforward);
        main_top_btn06_gamevoice = (RadioButton) this.findViewById(R.id.main_top_btn06_gamevoice);
        main_top_btn07_nicehomemade = (RadioButton) this.findViewById(R.id.main_top_btn07_nicehomemade);
        main_top_btn08_gameresult = (RadioButton) this.findViewById(R.id.main_top_btn08_gameresult);
        main_top_btn09_newsfocus = (RadioButton) this.findViewById(R.id.main_top_btn09_newsfocus);
        main_top_btn10_gameskill = (RadioButton) this.findViewById(R.id.main_top_btn10_gameskill);
        //设置初始值 在上面文章为默认选项
        main_top_btn01_article.setChecked(true);

        main_bottom_radiogroup = (RadioGroup) this.findViewById(R.id.main_bottom_radiogroup);
        bottom_btn_01_article = (RadioButton) this.findViewById(R.id.bottom_btn_01_article);
        bottom_btn_01_chat = (RadioButton) this.findViewById(R.id.bottom_btn_01_chat);
        bottom_btn_01_game = (RadioButton) this.findViewById(R.id.bottom_btn_01_game);

        main_center_viewpager = (ViewPager) this.findViewById(R.id.main_center_viewpager);

    }

    //初始化监听
    private void initListener(){
        main_top_radiogroup.setOnCheckedChangeListener(this);
        main_bottom_radiogroup.setOnCheckedChangeListener(this);
        main_center_viewpager.addOnPageChangeListener(this);
    }

    //初始化main_top_radiogroup中所有按钮的颜色
    private void initBtnMainTopRadiogroup(){
        main_top_btn01_article.setTextColor(Color.parseColor("#000000"));
        main_top_btn02_news.setTextColor(Color.parseColor("#000000"));
        main_top_btn03_gamechat.setTextColor(Color.parseColor("#000000"));
        main_top_btn04_hardsoftware.setTextColor(Color.parseColor("#000000"));
        main_top_btn05_gameforward.setTextColor(Color.parseColor("#000000"));
        main_top_btn06_gamevoice.setTextColor(Color.parseColor("#000000"));
        main_top_btn07_nicehomemade.setTextColor(Color.parseColor("#000000"));
        main_top_btn08_gameresult.setTextColor(Color.parseColor("#000000"));
        main_top_btn09_newsfocus.setTextColor(Color.parseColor("#000000"));
        main_top_btn10_gameskill.setTextColor(Color.parseColor("#000000"));

    }

    //初始化main_bottom_radiogroup中所有按钮的颜色
    public void initBtnMainBottomRadiogroup(){
        bottom_btn_01_article.setEnabled(true);
        bottom_btn_01_chat.setEnabled(true);
        bottom_btn_01_game.setEnabled(true);
    }

    //top和bottom 两个radioGroup的监听
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch(i) {
            case R.id.main_top_btn01_article:
                main_center_viewpager.setCurrentItem(0);
                initBtnMainTopRadiogroup();
                main_top_btn01_article.setTextColor(Color.parseColor("#C2E6EA"));
            break;
            case R.id.main_top_btn02_news:
                main_center_viewpager.setCurrentItem(1);
                initBtnMainTopRadiogroup();
                main_top_btn02_news.setTextColor(Color.parseColor("#C2E6EA"));
            break;
            case R.id.main_top_btn03_gamechat:
                main_center_viewpager.setCurrentItem(2);
                initBtnMainTopRadiogroup();
                main_top_btn03_gamechat.setTextColor(Color.parseColor("#C2E6EA"));
            break;
            case R.id.main_top_btn04_hardsoftware:
                main_center_viewpager.setCurrentItem(3);
                initBtnMainTopRadiogroup();
                main_top_btn04_hardsoftware.setTextColor(Color.parseColor("#C2E6EA"));
            break;
            case R.id.main_top_btn05_gameforward:
                main_center_viewpager.setCurrentItem(4);
                initBtnMainTopRadiogroup();
                main_top_btn05_gameforward.setTextColor(Color.parseColor("#C2E6EA"));
            break;
            case R.id.main_top_btn06_gamevoice:
                main_center_viewpager.setCurrentItem(5);
                initBtnMainTopRadiogroup();
                main_top_btn06_gamevoice.setTextColor(Color.parseColor("#C2E6EA"));

            break;
            case R.id.main_top_btn07_nicehomemade:
                main_center_viewpager.setCurrentItem(6);
                initBtnMainTopRadiogroup();
                main_top_btn07_nicehomemade.setTextColor(Color.parseColor("#C2E6EA"));

            break;
            case R.id.main_top_btn08_gameresult:
                main_center_viewpager.setCurrentItem(7);
                initBtnMainTopRadiogroup();
                main_top_btn08_gameresult.setTextColor(Color.parseColor("#C2E6EA"));

            break;
            case R.id.main_top_btn09_newsfocus:
                main_center_viewpager.setCurrentItem(8);
                initBtnMainTopRadiogroup();
                main_top_btn09_newsfocus.setTextColor(Color.parseColor("#C2E6EA"));

            break;
            case R.id.main_top_btn10_gameskill:
                main_center_viewpager.setCurrentItem(9);
                initBtnMainTopRadiogroup();
                main_top_btn10_gameskill.setTextColor(Color.parseColor("#C2E6EA"));

            break;
            case R.id.bottom_btn_01_article:
                initBtnMainBottomRadiogroup();
                bottom_btn_01_article.setEnabled(false);
                main_center_viewpager.setCurrentItem(0);
                main_horizontal_scrollview.smoothScrollTo(0,0);
            break;
            case R.id.bottom_btn_01_chat:
                initBtnMainBottomRadiogroup();
                bottom_btn_01_chat.setEnabled(false);
                Intent intent = new Intent(MainActivity.this, DiscuzActivity.class);
                startActivity(intent);
            break;
            case R.id.bottom_btn_01_game:
                initBtnMainBottomRadiogroup();
                bottom_btn_01_game.setEnabled(false);
                Intent intent1 = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent1);
            break;
        }
    }

    //main_center_viewpager的监听
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //顶部main_horizontal_scrollview出现滚动效果
        main_horizontal_scrollview.setVisibility(View.VISIBLE);
        main_top_radiogroup.setVisibility(View.VISIBLE);
        //获得当前ViewPager对应的RadioButton
        RadioButton radioButton = (RadioButton) main_top_radiogroup.getChildAt(position);
        radioButton.setChecked(true);
        //让顶部的RadioButton随着ViewPager一起滚动
        int left = radioButton.getLeft();
        main_horizontal_scrollview.smoothScrollTo(left,0);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //初始化fragments
    private void lunboAndListToMainCenterViewPager(){
        fragmentList = new ArrayList<>();
        //添加fragment
        MainArticleFragment f_article = new MainArticleFragment("199",getApplicationContext());
        MainArticleFragment f_news = new MainArticleFragment("2",getApplicationContext());
        MainArticleFragment f_gamechat = new MainArticleFragment("151",getApplicationContext());
        MainArticleFragment f_hardsoftware = new MainArticleFragment("152",getApplicationContext());
        MainArticleFragment f_gameforward = new MainArticleFragment("153",getApplicationContext());
        MainArticleFragment f_gamevoice = new MainArticleFragment("154",getApplicationContext());
        MainArticleFragment f_nicehomemade = new MainArticleFragment("196",getApplicationContext());
        MainArticleFragment f_gameresult = new MainArticleFragment("197",getApplicationContext());
        MainArticleFragment f_newsfocus = new MainArticleFragment("199",getApplicationContext());
        MainArticleFragment f_gameskill = new MainArticleFragment("25",getApplicationContext());
        fragmentList.add(f_article);
        fragmentList.add(f_news);
        fragmentList.add(f_gamechat);
        fragmentList.add(f_hardsoftware);
        fragmentList.add(f_gameforward);
        fragmentList.add(f_gamevoice);
        fragmentList.add(f_nicehomemade);
        fragmentList.add(f_gameresult);
        fragmentList.add(f_newsfocus);
        fragmentList.add(f_gameskill);
        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(getSupportFragmentManager(),fragmentList);
        main_center_viewpager.setAdapter(adapter);
    }

    //当回到mainactivity时初始化底部按钮
    @Override
    protected void onResume() {
        super.onResume();
        initBtnMainBottomRadiogroup();
    }

    //销毁时清除记录
    @Override
    protected void onDestroy() {
        super.onDestroy();
        DbClear.clearDB(getApplicationContext());
        ClearPicsFromSDCard.clearAllPics(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
    }
}
