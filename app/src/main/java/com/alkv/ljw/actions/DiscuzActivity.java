package com.alkv.ljw.actions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.alkv.ljw.gamecenter.R;


public class DiscuzActivity extends AppCompatActivity implements View.OnClickListener{
    private WebView discuz_webview;
    private String urlStr = "http://bbs.3dmgame.com/forum.php";
    private Button discuz_webview_btn_back,discuz_webview_btn_forward,
                    discuz_webview_btn_bigger,discuz_webview_btn_smaller;
    WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuz);
        setTitle("论坛");
        //初始化控件 设置监听
        initViews();

    }

     //初始化控件 设置监听
    private void initViews(){
        discuz_webview = (WebView) this.findViewById(R.id.discuz_webview);
        discuz_webview_btn_back = (Button) this.findViewById(R.id.discuz_webview_btn_back);
        discuz_webview_btn_forward = (Button) this.findViewById(R.id.discuz_webview_btn_forward);
        discuz_webview_btn_bigger = (Button) this.findViewById(R.id.discuz_webview_btn_bigger);
        discuz_webview_btn_smaller = (Button) this.findViewById(R.id.discuz_webview_btn_smaller);
        discuz_webview_btn_back.setOnClickListener(this);
        discuz_webview_btn_forward.setOnClickListener(this);
        discuz_webview_btn_bigger.setOnClickListener(this);
        discuz_webview_btn_smaller.setOnClickListener(this);
        webSettings = discuz_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        discuz_webview.loadUrl(urlStr);
        discuz_webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
    //监听
    public void onClick(View view){
        switch(view.getId()){
            case R.id.discuz_webview_btn_back:
                if(discuz_webview.canGoBack()){
                    discuz_webview.goBack();
                }
                break;
            case R.id.discuz_webview_btn_forward:
                if(discuz_webview.canGoForward()){
                    discuz_webview.goForward();
                }
                break;
            case R.id.discuz_webview_btn_bigger:
                Toast.makeText(DiscuzActivity.this,"点击上方加号放大",Toast.LENGTH_SHORT).show();
                break;
            case R.id.discuz_webview_btn_smaller:
                Toast.makeText(DiscuzActivity.this,"点击上方减号放大",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
