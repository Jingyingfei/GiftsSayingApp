package com.example.dllo.giftssayingapp.classifypackage.Strategy;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
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
 * Created by dllo on 16/9/20.
 */
public class StrategyFragment extends BaseFragment implements View.OnClickListener {
    private String strNet = "http://api.liwushuo.com/v2/columns?limit=20&offset=0";
    private EditText mEditText;
    private RecyclerView raiders;
    private RecyclerView strategy_kind;

    @Override
    protected int setLayout() {
        return R.layout.fragment_strategy;
    }

    @Override
    protected void initView() {
        raiders = bindView(R.id.recycler_strategy);
        mEditText = bindView(R.id.edit_strategy);
        strategy_kind = bindView(R.id.rv_strategy_kind);
        mEditText.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        requestData();
        requestDataEdit();
        requestDataKind();

    }

    //获取recyclerview横向的图片
    public void requestData(){
        final ArrayList<StrategyBean> arrayList = new ArrayList<>();
        StringRequest request = new StringRequest(strNet, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                StrategyBean bean = gson.fromJson(response,StrategyBean.class);
                for (int i = 0; i < 11; i++) {
                    bean.getData().getColumns().get(i).getBanner_image_url();
                    bean.getData().getColumns().get(i).getTitle();
                    bean.getData().getColumns().get(i).getSubtitle();
                    bean.getData().getColumns().get(i).getAuthor();
                    arrayList.add(bean);
                }
                StrategyAdapter adapter = new StrategyAdapter(context);
                adapter.setArrayList(arrayList);
                GridLayoutManager manager = new GridLayoutManager(getContext(),3);
                manager.setOrientation(GridLayoutManager.HORIZONTAL);
                raiders.setLayoutManager(manager);
                raiders.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(request);
    }
    //editext中的获取文字数据
    public void requestDataEdit(){
        StringRequest mStringRequests = new StringRequest(URLValues.EDIT_NAME, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson mGson = new Gson();
                EditBean editBean = mGson.fromJson(response, EditBean.class);
                mEditText.setHint(editBean.getData().getPlaceholder());
                mEditText.setTextSize(10);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络获取失败", Toast.LENGTH_SHORT).show();
            }
        });

        VolleySingleton.getInstance().addRequest(mStringRequests);
    }
    //获取品类,风格等数据
    public void requestDataKind(){
        final ArrayList<StrategyColumnBean> arrayList = new ArrayList<>();
        StringRequest request = new StringRequest(URLValues.STRATEGY_COLUMN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                StrategyColumnBean bean = gson.fromJson(response, StrategyColumnBean.class);
                for (int i = 0; i < bean.getData().getChannel_groups().size(); i++) {
                    bean.getData().getChannel_groups().get(i).getName();
                    bean.getData().getChannel_groups().get(i).getChannels().get(i).getName();
                    bean.getData().getChannel_groups().get(i).getChannels().get(i).getCover_image_url();
                    arrayList.add(bean);
                }
                StrateryColumnAdapter adapter = new StrateryColumnAdapter(context);
                adapter.setArrayList(arrayList);
                strategy_kind.setAdapter(adapter);
                GridLayoutManager manager = new GridLayoutManager(context, 2);
                strategy_kind.setLayoutManager(manager);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络获取失败", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequest(request);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edit_strategy:
                startActivity(new Intent(getContext(), EditActivity.class));
                break;
        }
    }
}
