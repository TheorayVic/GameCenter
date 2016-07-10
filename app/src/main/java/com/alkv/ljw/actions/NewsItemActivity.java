package com.alkv.ljw.actions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.alkv.ljw.gamecenter.R;

public class NewsItemActivity extends AppCompatActivity implements View.OnClickListener{
    private WebView newsitem_activity_webview;
    private Button newsitem_activity_btn_back,newsitem_activity_btn_forward,
             newsitem_activity_btn_bigger,newsitem_activity_btn_smaller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_news_item);
        initViews();
    }
    //初始化控件
    private void initViews(){
        newsitem_activity_webview = (WebView) this.findViewById(R.id.newsitem_activity_webview);
        newsitem_activity_btn_back = (Button) this.findViewById(R.id.newsitem_activity_btn_back);
        newsitem_activity_btn_forward = (Button) this.findViewById(R.id.newsitem_activity_btn_forward);
        newsitem_activity_btn_bigger = (Button) this.findViewById(R.id.newsitem_activity_btn_bigger);
        newsitem_activity_btn_smaller = (Button) this.findViewById(R.id.newsitem_activity_btn_smaller);
        newsitem_activity_btn_back.setOnClickListener(this);
        newsitem_activity_btn_forward.setOnClickListener(this);
        newsitem_activity_btn_bigger.setOnClickListener(this);
        newsitem_activity_btn_smaller.setOnClickListener(this);
        WebSettings webSettings = newsitem_activity_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        Intent intent = getIntent();
        String itemUrl = intent.getStringExtra("itemUrl");
        Log.i("kobe","接收到的地址为："+itemUrl);
        newsitem_activity_webview.loadUrl(itemUrl);
        newsitem_activity_webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    //设置四个按钮的监听
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.discuz_webview_btn_back:
                if(newsitem_activity_webview.canGoBack()){
                    newsitem_activity_webview.goBack();
                }
                break;
            case R.id.discuz_webview_btn_forward:
                if(newsitem_activity_webview.canGoForward()){
                    newsitem_activity_webview.goForward();
                }
                break;
            case R.id.discuz_webview_btn_bigger:
                Toast.makeText(NewsItemActivity.this,"点击上方加号放大",Toast.LENGTH_SHORT).show();
                break;
            case R.id.discuz_webview_btn_smaller:
                Toast.makeText(NewsItemActivity.this,"点击上方减号放大",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
