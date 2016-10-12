package com.example.dllo.giftssayingapp.homepackage.selection;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseFragment;
import com.example.dllo.giftssayingapp.basepackage.VolleySingleton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 16/10/12.
 */
public class HomeSearchFragment extends BaseFragment {

    private ListView lv_home_search;
    private String strUrl;

    @Override
    protected int setLayout() {
        return R.layout.home_search;
    }

    @Override
    protected void initView() {
        lv_home_search = bindView(R.id.lv_home_search);
    }

    @Override
    protected void initData() {
        Bundle agrs = getArguments();
        strUrl = agrs.getString("wordUrl");
        wordData();
    }

    public void wordData(){
        StringRequest request = new StringRequest(strUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                WordBean bean = gson.fromJson(response,WordBean.class);
                List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
                for (int i = 0; i < bean.getData().getWords().size(); i++) {
                    Map<String, Object> listItem = new HashMap<String, Object>();
                    listItem.put("word",bean.getData().getWords().get(i));
                    Log.d("SearchFragment", bean.getData().getWords().get(i));
                    listItems.add(listItem);
                }

                SimpleAdapter adapter = new SimpleAdapter(getContext(),listItems,R.layout.simple_item,
                        new String[]{"word"}, new int[]{R.id.tv_search_slimply});
                lv_home_search.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(request);
    }
}
