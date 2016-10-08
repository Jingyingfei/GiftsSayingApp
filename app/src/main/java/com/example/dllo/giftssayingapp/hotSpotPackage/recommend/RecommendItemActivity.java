package com.example.dllo.giftssayingapp.hotspotpackage.recommend;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseActivity;

public class RecommendItemActivity extends BaseActivity {


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

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
    }
}
