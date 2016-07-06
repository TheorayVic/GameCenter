package com.alkv.ljw.actions;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Toast;

import com.alkv.ljw.gamecenter.MainActivity;
import com.alkv.ljw.gamecenter.R;
import com.alkv.ljw.service.DownloadService;
import com.alkv.ljw.utils.NetUtils;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


public class WelcomeActivity extends AppCompatActivity {
    private GifImageView gifImageView;
    boolean isNetOpen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcome();
    }
    //开场动画
    public void welcome(){
        gifImageView = (GifImageView) this.findViewById(R.id.welcome_gifimage);
        Animation animation = new RotateAnimation(0,360f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(5000);
        gifImageView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            //检测网络、在播放的同时就开始加载数据
            @Override
            public void onAnimationStart(Animation animation) {
                isNetOpen = NetUtils.isNetOpen(WelcomeActivity.this);
                if(isNetOpen){
                    Intent intent = new Intent(WelcomeActivity.this, DownloadService.class);
                    startService(intent);
                }
            }
            //给用户提示 网络没有连接 加载失败 并判断是否是第一次登陆 进入页面
            @Override
            public void onAnimationEnd(Animation animation) {
                if(!isNetOpen){
                    Toast.makeText(WelcomeActivity.this,"麻烦检查一下您的网络",Toast.LENGTH_SHORT).show();
                }
                isFirstTime();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void isFirstTime(){
        SharedPreferences sp = getSharedPreferences("isFirstLogin",MODE_PRIVATE);
        boolean isFirstLogin = sp.getBoolean("isLogin",true);
        if(isFirstLogin){
            Intent intent = new Intent(WelcomeActivity.this,GuideActivity.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }
    }
}
