package com.example.dllo.giftssayingapp.homepackage.selection;

import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseActivity;
import com.example.dllo.giftssayingapp.basepackage.URLValues;
import com.example.dllo.giftssayingapp.basepackage.VolleySingleton;
import com.google.gson.Gson;

public class HomeImageFiveActivity extends BaseActivity {


    private ListView listView_five;

    @Override
    protected int setLayout() {
        return R.layout.activity_home_image_item;
    }

    @Override
    protected void initView() {
        listView_five = bindView(R.id.list_view_image_item_five);
    }

    @Override
    protected void initData() {
        requestData();
    }

    //第五张轮播图点击进入网络获取
    public void requestData(){
        StringRequest request = new StringRequest(URLValues.HOME_CAROUSEL_FIVE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(request);
    }

}
