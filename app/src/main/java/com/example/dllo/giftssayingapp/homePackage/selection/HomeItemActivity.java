package com.example.dllo.giftssayingapp.homepackage.selection;

import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseActivity;

/**
 * Created by dllo on 16/9/30.
 */
public class HomeItemActivity extends BaseActivity {

    private WebView itemWeb;

    @Override
    protected int setLayout() {
        return R.layout.activity_home_item;
    }

    @Override
    protected void initView() {
        itemWeb = bindView(R.id.web_home_item);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        itemWeb.loadUrl(url);

        WebSettings webSettings = itemWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        itemWeb.setWebViewClient(new WebViewClient());
    }
}
