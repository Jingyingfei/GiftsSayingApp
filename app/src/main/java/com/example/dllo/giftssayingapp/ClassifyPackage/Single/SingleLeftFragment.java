package com.example.dllo.giftssayingapp.classifypackage.Single;

import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseFragment;
import com.example.dllo.giftssayingapp.basepackage.URLValues;
import com.example.dllo.giftssayingapp.basepackage.VolleySingleton;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/28.
 */
public class SingleLeftFragment extends BaseFragment {
    private ListView lv_left_single;
    @Override
    protected int setLayout() {
        return R.layout.fragment_single_left;
    }

    @Override
    protected void initView() {
        lv_left_single = bindView(R.id.lv_left_single);
    }

    @Override
    protected void initData() {
        requestData();

    }
    public void requestData(){
        StringRequest request = new StringRequest(URLValues.STRATEGY_SKU, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                SingleBean bean = gson.fromJson(response, SingleBean.class);
                ArrayList<SingleBean> arrayList = new ArrayList<>();
                for (int i = 0; i < bean.getData().getCategories().size(); i++) {
                    arrayList.add(bean);
                }
                SingleAdapter adapter = new SingleAdapter(context);
                adapter.setArrayList(arrayList);
                lv_left_single.setAdapter(adapter);
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
