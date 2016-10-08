package com.example.dllo.giftssayingapp.homepackage.elsetab;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseFragment;
import com.example.dllo.giftssayingapp.basepackage.VolleySingleton;
import com.example.dllo.giftssayingapp.homepackage.selection.HomeItemActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/20.
 * http://api.liwushuo.com/v2/channels/9/items_v2?gender=2&limit=20&offset=0&generation=1
 */
public class HomeKindFragment extends BaseFragment {

    private ListView listView;
    private String strUrl;

    @Override
    protected int setLayout() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.ll_home_kind);
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        String urlNum = bundle.getString("url");
        strUrl = "http://api.liwushuo.com/v2/channels/" + urlNum + "/items_v2?limit=20&ad=2&gender=2&offset=0&generation=2%20HTTP/1.1";
        requestData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    HomeKindBean bean = (HomeKindBean) adapterView.getItemAtPosition(i);
                if (bean.getData().getItems().get(i).getUrl() != null){
                    Intent intent = new Intent(context, HomeItemActivity.class);
                    intent.putExtra("url_kind", bean.getData().getItems().get(i).getUrl());
                    getActivity().startActivity(intent);
                } else {
                    Toast.makeText(context, "没有接口", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void requestData() {

        StringRequest request = new StringRequest(strUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("HomeKindFragment", "网络请求了?");
                ArrayList<HomeKindBean> arrayList = new ArrayList<>();
                Gson gson = new Gson();
                HomeKindBean bean = gson.fromJson(response, HomeKindBean.class);
                for (int i = 0; i < bean.getData().getItems().size(); i++) {
                    bean.getData().getItems().get(i).getCover_image_url();
                    bean.getData().getItems().get(i).getTitle();
                    bean.getData().getItems().get(i).getAuthor().getNickname();
                    arrayList.add(bean);
                }
                HomeKindAdapter adapter = new HomeKindAdapter(context);
                adapter.setArrayList(arrayList);
                listView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络获取失败", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequest(request);
    }
}
