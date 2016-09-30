package com.example.dllo.giftssayingapp.homepackage;

import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseActivity;
import com.example.dllo.giftssayingapp.basepackage.EditBean;
import com.example.dllo.giftssayingapp.basepackage.URLValues;
import com.example.dllo.giftssayingapp.basepackage.VolleySingleton;
import com.example.dllo.giftssayingapp.homepackage.selection.HomeQueryBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/29.
 */
public class HomeQueryActivity extends BaseActivity {

    private EditText query_name;
    private TextView query_cancel;
    private FlowLayout flowLayout;
    private HomeQueryBean bean;


    private List<HomeQueryBean> arrayList = new ArrayList<>();
    final int size = arrayList.size();
    String mNames[] = (String[]) arrayList.toArray(new String[size]);

    @Override
    protected int setLayout() {
        return R.layout.activity_home_query;
    }

    @Override
    protected void initView() {
        query_name = bindView(R.id.et_home_query_name);
        query_cancel = bindView(R.id.tv_home_query_cancel);
        flowLayout = bindView(R.id.flowlayout);
    }

    @Override
    protected void initData() {
        editData();
        kindData();

        flowLayout = (FlowLayout) findViewById(R.id.flowlayout);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        for (int i = 0; i < mNames.length; i++) {
            TextView view = new TextView(this);
            view.setText(mNames[i]);
            view.setTextColor(Color.WHITE);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bg));
            flowLayout.addView(view, lp);
        }
    }

    public void editData() {
        StringRequest request = new StringRequest(URLValues.EDIT_NAME, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                EditBean bean = gson.fromJson(response, EditBean.class);
                query_name.setHint(bean.getData().getPlaceholder());
                query_name.setTextSize(10);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeQueryActivity.this, "获取网络失败", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequest(request);
    }

    //搜索种类的网络获取解析
    public void kindData() {
        StringRequest request = new StringRequest(URLValues.HOME_QUERY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                bean = gson.fromJson(response, HomeQueryBean.class);

                for (int i = 0; i < bean.getData().getHot_words().size(); i++) {
                    arrayList.add(bean);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(request);
    }

}
