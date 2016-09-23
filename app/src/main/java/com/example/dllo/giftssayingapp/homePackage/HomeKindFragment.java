package com.example.dllo.giftssayingapp.homepackage;

import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseFragment;
import com.example.dllo.giftssayingapp.basepackage.VolleySingleton;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/20.
 * http://api.liwushuo.com/v2/channels/9/items_v2?gender=2&limit=20&offset=0&generation=1
 */
public class HomeKindFragment extends BaseFragment {
    private String strNet = "http://api.liwushuo.com/v2/channels/9/items_v2?gender=2&limit=20&offset=0&generation=1";

    private ListView listView;

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
        requestData();
    }

    public void requestData() {

        StringRequest request = new StringRequest(strNet, new Response.Listener<String>() {
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
