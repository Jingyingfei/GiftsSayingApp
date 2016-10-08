package com.example.dllo.giftssayingapp.homepackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseFragment;
import com.example.dllo.giftssayingapp.basepackage.EditBean;
import com.example.dllo.giftssayingapp.basepackage.URLValues;
import com.example.dllo.giftssayingapp.basepackage.VolleySingleton;
import com.example.dllo.giftssayingapp.homepackage.elsetab.HomeKindFragment;
import com.example.dllo.giftssayingapp.homepackage.selection.HomeSelectFragment;
import com.example.dllo.giftssayingapp.mainpackage.LoginActivity;
import com.google.gson.Gson;

import java.util.ArrayList;


/**
 * Created by dllo on 16/9/19.
 */
public class HomeMainFragment extends BaseFragment implements View.OnClickListener {

    private TabLayout tb_homeTab;
    private ViewPager vp_homeTab;
    private ArrayList<String> strings = new ArrayList<>();
    private EditText et_name;
    private ImageView imageB_sigin;
    ArrayList<Fragment> fragments = new ArrayList<>();
    private ImageView dropDownButton;
    private RecyclerView rv_popupWindow;
    private HomeTabBean bean;

    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        tb_homeTab = bindView(R.id.tb_home_top);
        vp_homeTab = bindView(R.id.vp_home_top);
        et_name = bindView(R.id.et_name);
        imageB_sigin = bindView(R.id.imageB_signin);
        dropDownButton = bindView(R.id.iv_dropDownButton);

        dropDownButton.setOnClickListener(this);
        et_name.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        imageB_sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        request();
        requestDataEdit();
    }

    //标题的网络获取
    public void request() {
        //创建网络请求
        StringRequest request = new StringRequest(URLValues.HOME_TITLE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //解析
                Gson gson = new Gson();
                bean = gson.fromJson(response, HomeTabBean.class);
                for (int i = 0; i < bean.getData().getChannels().size(); i++) {
                    strings.add(bean.getData().getChannels().get(i).getName());
                }
                for (int i = 0; i < strings.size(); i++) {

                    if (i == 0) {
                        fragments.add(new HomeSelectFragment());
                    } else {
                        HomeKindFragment homeKindFragment = new HomeKindFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("url", String.valueOf(bean.getData().getChannels().get(i).getId()));
                        homeKindFragment.setArguments(bundle);
                        fragments.add(homeKindFragment);
                    }
                }

                HomeMainAdapter adapter = new HomeMainAdapter(getChildFragmentManager());
                adapter.setFragments(fragments);
                adapter.setStrings(strings);
                vp_homeTab.setAdapter(adapter);
                tb_homeTab.setupWithViewPager(vp_homeTab);
                tb_homeTab.setTabMode(TabLayout.MODE_SCROLLABLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });

        //将请求放到请求队列中
        VolleySingleton.getInstance().addRequest(request);


    }
    //edittext的网络获取
    public void requestDataEdit(){
        StringRequest mStringRequests = new StringRequest(URLValues.EDIT_NAME, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson mGson = new Gson();
                EditBean editBean = mGson.fromJson(response, EditBean.class);
                et_name.setHint(editBean.getData().getPlaceholder());
                et_name.setTextSize(10);
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
            case R.id.et_name:
                Intent intent = new Intent(context, HomeQueryActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
                break;
            case R.id.iv_dropDownButton:
                showPopupWindow(view);
                break;
        }
    }

    private void showPopupWindow(View view) {
        //自定义布局,显示内容
        View view1 = LayoutInflater.from(context).inflate(R.layout.popup_window, null);
        rv_popupWindow = (RecyclerView) view1.findViewById(R.id.rv_popup_window);
        PopupAdapter adapter = new PopupAdapter(context);
        adapter.setArrayList(strings);
        Log.d("HomeMainFragment", "strings:" + strings);
        GridLayoutManager manager = new GridLayoutManager(context, 17);
        rv_popupWindow.setLayoutManager(manager);
        rv_popupWindow.setAdapter(adapter);

        final PopupWindow popupWindow = new PopupWindow(view1, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    Log.d("HomeMainFragment", "点击了popupWindow外侧");
                    popupWindow.dismiss();

                    return true;
                }
                return false;
            }
        });
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.dot_focus));
        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);


    }
}