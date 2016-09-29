package com.example.dllo.giftssayingapp.classifypackage.main;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import com.example.dllo.giftssayingapp.classifypackage.Single.SingleFragment;
import com.example.dllo.giftssayingapp.classifypackage.Strategy.EditActivity;
import com.example.dllo.giftssayingapp.classifypackage.Strategy.EditBean;
import com.example.dllo.giftssayingapp.classifypackage.Strategy.StrategyFragment;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class ClassifyMainFragment extends BaseFragment implements View.OnClickListener {
    private EditText mEditText;
    private TabLayout tb_calssify;
    private ViewPager vp_classify;

    @Override
    protected int setLayout() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initView() {
        mEditText = bindView(R.id.edit_strategy);
        tb_calssify = bindView(R.id.tb_classify);
        vp_classify = bindView(R.id.vp_classify);
        mEditText.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new StrategyFragment());
        fragments.add(new SingleFragment());

        ClassifyMainAdapter adapter = new ClassifyMainAdapter(getChildFragmentManager(), fragments);
        vp_classify.setAdapter(adapter);
        tb_calssify.setupWithViewPager(vp_classify);

        tb_calssify.getTabAt(0).setText("攻略");
        tb_calssify.getTabAt(1).setText("单品");
        requestDataEdit();
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
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edit_strategy:
                startActivity(new Intent(getContext(), EditActivity.class));
                break;
        }
    }
}
