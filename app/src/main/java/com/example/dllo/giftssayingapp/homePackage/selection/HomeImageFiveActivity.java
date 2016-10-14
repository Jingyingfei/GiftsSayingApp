package com.example.dllo.giftssayingapp.homepackage.selection;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseActivity;
import com.example.dllo.giftssayingapp.basepackage.VolleySingleton;
import com.google.gson.Gson;

import java.util.ArrayList;

public class HomeImageFiveActivity extends BaseActivity {


    private ListView listView_five;
    private String url;
    private TextView title;
    private ImageView iv_holo_dark;
    private int five_id;
    private String title_url;
    ArrayList<HomeImageFiveBean> arrayList = new ArrayList<>();

    @Override
    protected int setLayout() {
        return R.layout.activity_home_image_item;
    }

    @Override
    protected void initView() {
        iv_holo_dark = bindView(R.id.iv_holo_dark);
        title = bindView(R.id.tv_sea_baby);
        listView_five = bindView(R.id.list_view_image_item_five);
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        five_id = (int) intent.getExtras().get("five");
        Log.d("HomeImageFiveActivity", "five_id:" + five_id);
        title_url = intent.getStringExtra("title_five");
        title.setText(title_url);
        url = "http://api.liwushuo.com/v2/collections/"+ five_id +"/posts?gender=1&generation=2&limit=20&offset=0";
        requestData();
    }

    //第五张轮播图点击进入网络获取
    public void requestData(){
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                final HomeImageFiveBean fiveBean = gson.fromJson(response,HomeImageFiveBean.class);
                arrayList.add(fiveBean);

                HomeImageFiveAdapter adpter = new HomeImageFiveAdapter(HomeImageFiveActivity.this);
                adpter.setArrayList(arrayList);
                listView_five.setAdapter(adpter);
                
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(request);
    }

}
