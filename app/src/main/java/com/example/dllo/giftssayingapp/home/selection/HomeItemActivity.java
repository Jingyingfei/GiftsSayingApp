package com.example.dllo.giftssayingapp.home.selection;

import android.content.Intent;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.base.BaseActivity;

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

        String url_kind = intent.getStringExtra("url_kind");
        itemWeb.loadUrl(url_kind);

        String url_image = intent.getStringExtra("ImageUrl");
        itemWeb.loadUrl(url_image);

        WebSettings webSettings = itemWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        itemWeb.setWebViewClient(new WebViewClient());

        //设置WebView的一些缩放功能点
        itemWeb.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
        itemWeb.setHorizontalScrollBarEnabled(false);
        itemWeb.getSettings().setSupportZoom(true);
        //设置WebView可触摸放大缩小
        itemWeb.getSettings().setBuiltInZoomControls(true);
        itemWeb.setInitialScale(200);
        itemWeb.setHorizontalScrollbarOverlay(true);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && itemWeb.canGoBack()) {
            itemWeb.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
