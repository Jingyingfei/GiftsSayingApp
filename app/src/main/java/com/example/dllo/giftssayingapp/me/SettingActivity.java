package com.example.dllo.giftssayingapp.me;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.base.BaseActivity;
import com.example.dllo.giftssayingapp.tools.DataCleanManager;

/**
 * Created by dllo on 16/10/8.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_holo_dark;
    private RelativeLayout feedback;
    private RelativeLayout mobile_service;
    private RelativeLayout clean_cache;
    private TextView tv_clean_cache;
    private RelativeLayout setting_notification;

    @Override
    protected int setLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        iv_holo_dark = bindView(R.id.iv_holo_dark);
        feedback = bindView(R.id.ic_more_feedback);
        mobile_service = bindView(R.id.rv_setting_mobile_service);
        clean_cache = bindView(R.id.rv_setting_clean_cache);
        tv_clean_cache = bindView(R.id.tv_clean_cache);
        setting_notification = bindView(R.id.rv_setting_notification);
        mobile_service.setOnClickListener(this);
        clean_cache.setOnClickListener(this);
        setting_notification.setOnClickListener(this);
    }


    @Override
    protected void initData() {
        jumpBackData();
        jumpFeedBackData();
        //查看app缓存
        try {
            tv_clean_cache.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //返回上一页
    public void jumpBackData() {
        iv_holo_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void jumpFeedBackData() {
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, FeedBackActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rv_setting_mobile_service:
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setView(R.layout.setting_dialog);
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String num = "4009992053";
                        Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + num));
                        startActivity(intent);
                    }
                });
                builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(SettingActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                break;
            case R.id.rv_setting_clean_cache:
                DataCleanManager.clearAllCache(this);
                //查看app缓存
                try {
                    tv_clean_cache.setText(DataCleanManager.getTotalCacheSize(this));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.rv_setting_notification:
                Intent intent = new Intent(SettingActivity.this, PushActivity.class);
                startActivity(intent);
                break;
        }
    }

}
