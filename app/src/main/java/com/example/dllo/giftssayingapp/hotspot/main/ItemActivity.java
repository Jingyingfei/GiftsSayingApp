package com.example.dllo.giftssayingapp.hotspot.main;

import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.base.BaseActivity;

public class ItemActivity extends BaseActivity {


    private WebView webView;

    @Override
    protected int setLayout() {
        return R.layout.activity_recommenditem;
    }

    @Override
    protected void initView() {
        webView = bindView(R.id.recommend_item);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("purchase_url");
        webView.loadUrl(url);

        String purchase_url = intent.getStringExtra("url");
        webView.loadUrl(purchase_url);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

    }

    //按返回键时， 不退出程序而是返回上一浏览页面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
            Log.d("ItemActivity", "000000000000");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
