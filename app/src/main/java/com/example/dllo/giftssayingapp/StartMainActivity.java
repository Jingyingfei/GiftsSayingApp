package com.example.dllo.giftssayingapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.example.dllo.giftssayingapp.basePackage.BaseActivity;
import com.example.dllo.giftssayingapp.homePackage.ClassifyFragment;
import com.example.dllo.giftssayingapp.homePackage.HomeFragment;
import com.example.dllo.giftssayingapp.homePackage.HotSpotFragment;
import com.example.dllo.giftssayingapp.homePackage.MeFragment;

public class StartMainActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton home;
    private RadioButton hotspot;
    private RadioButton classify;
    private RadioButton me;

    @Override
    protected int setLayout() {

        return R.layout.activity_start_main;

    }

    @Override
    protected void initView() {
        home = bindView(R.id.rBtn_home);
        hotspot = bindView(R.id.rBtn_hotspot);
        classify = bindView(R.id.rBtn_classify);
        me = bindView(R.id.rBtn_me);

        home.setOnClickListener(this);
        hotspot.setOnClickListener(this);
        classify.setOnClickListener(this);
        me.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout, new HomeFragment());
        home.setChecked(true);
        transaction.commit();



    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()){
            case R.id.rBtn_home:
                transaction.replace(R.id.frameLayout, new HomeFragment());

                break;
            case R.id.rBtn_hotspot:
                transaction.replace(R.id.frameLayout, new HotSpotFragment());
                break;
            case R.id.rBtn_classify:
                transaction.replace(R.id.frameLayout, new ClassifyFragment());
                break;
            case R.id.rBtn_me:
                transaction.replace(R.id.frameLayout, new MeFragment());
                break;
        }
        transaction.commit();

    }
}
