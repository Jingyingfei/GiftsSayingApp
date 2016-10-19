package com.example.dllo.giftssayingapp.home.selection;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.base.BaseFragment;
import com.example.dllo.giftssayingapp.base.URLValues;
import com.example.dllo.giftssayingapp.netrequest.VolleySingleton;
import com.example.dllo.giftssayingapp.bean.HomeImageBean;
import com.example.dllo.giftssayingapp.bean.HomeSelectBean;
import com.example.dllo.giftssayingapp.bean.HomeSpecialBean;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by dllo on 16/9/20.
 * http://api.liwushuo.com/v2/channels/103/items?limit=20&ad=2&gender=2&offset=0&generation=1
 */
public class HomeSelectFragment extends BaseFragment {
    private ViewPager homeImage;
    private HomeImageBean bean;
    private PullToRefreshListView homeSelect;

    //设置轮播图的定时器
    //判断是否自己滚动
    private boolean isRunning = true;
    //执行滑动到下一个界面
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //执行滑动到下一个界面
            homeImage.setCurrentItem(homeImage.getCurrentItem() + 1);
            if (isRunning) {
                //发出一个hander的延时
                handler.sendEmptyMessageDelayed(0, 4000);
            }
        }
    };
    private ArrayList<ImageView> dots = new ArrayList<>();
    private ArrayList<HomeImageBean> images = new ArrayList<>();
    private LinearLayout home_special;
    private LinearLayout ll_viewpager;
    ArrayList<HomeSelectBean> arrayList = new ArrayList<>();

    @Override
    protected int setLayout() {
        return R.layout.fragment_home_select;
    }

    @Override
    protected void initView() {
        homeSelect = bindView(R.id.ll_home_select);
    }

    @Override
    protected void initData() {
        //轮播图加头
        View view = LayoutInflater.from(context).inflate(R.layout.vp_home_image, null);
        homeImage = (ViewPager) view.findViewById(R.id.vp_home_lunbo);
        ll_viewpager = (LinearLayout) view.findViewById(R.id.ll_viewpager);
        home_special = (LinearLayout) view.findViewById(R.id.ll_home_image);
        ListView listView = homeSelect.getRefreshableView();
        listView.addHeaderView(view);
        //开启定时器
        handler.sendEmptyMessageDelayed(0, 4000);
        requestImageData();
        requestData(URLValues.HOME_CELL);
        homeSelect.onRefreshComplete();
        requestSpecial();
        jumpItem();


        homeImage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int a;

            @Override
            public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {

                //当手指按住轮播图不动时，轮播图停止滚动；当点击轮播图时，跳转到相关界面
                homeImage.setOnTouchListener(new View.OnTouchListener() {
                    private long downTime;
                    private int downX;

                    //给轮播图图片注册触摸事件
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        switch (motionEvent.getAction()) {
                            // 按下去时，记录按下的坐标和时间，用于判断是否是点击事件
                            case MotionEvent.ACTION_DOWN:
                                // 手指按下时，取消所有事件，即轮播图不在滚动了
                                handler.removeCallbacksAndMessages(null);
                                downX = (int) motionEvent.getX();
                                downTime = System.currentTimeMillis();
                                break;
                            // 抬起手指时，判断落下抬起的时间差和坐标，符合以下条件为点击
                            case MotionEvent.ACTION_UP:
                                handler.sendEmptyMessageDelayed(0, 4000);
                                break;
                        }
                        return false;
                    }
                });
            }

            @Override
            public void onPageSelected(int position) {
                if (dots.size() > 0){
                    a = position % dots.size();
                    for (int i = 0; i < dots.size(); i++) {
                        if (i == a) {
                            dots.get(i).setImageResource(R.drawable.dot_normal);
                        } else {
                            dots.get(i).setImageResource(R.drawable.dot_focus);
                        }
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //刷新加载
        homeSelect.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                requestData(URLValues.HOME_CELL);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }


    //item的点击跳转事件
    public void jumpItem() {
        homeSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HomeSelectBean bean = (HomeSelectBean) adapterView.getItemAtPosition(i);
                if (bean.getData().getItems().get(i-2).getUrl() != null) {
                    Intent intent = new Intent(context, HomeItemActivity.class);
                    intent.putExtra("url", bean.getData().getItems().get(i - 2).getUrl());
                    getActivity().startActivity(intent);
                } else {
                    Toast.makeText(context, "没有接口", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void requestData(String url) {
        //创建网络请求
        StringRequest request = new StringRequest(URLValues.HOME_CELL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // 解析
                Gson gson = new Gson();
                HomeSelectBean bean = gson.fromJson(response, HomeSelectBean.class);
                for (int i = 0; i < bean.getData().getItems().size(); i++) {
                    arrayList.add(bean);
                }
                HomeSelectAdapter adapter = new HomeSelectAdapter(getContext());
                adapter.setArrayList(arrayList);
                homeSelect.setAdapter(adapter);
                homeSelect.onRefreshComplete();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络获取失败", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequest(request);

    }

    public void requestImageData() {
        //创建轮播图的网络请求
        StringRequest request = new StringRequest(URLValues.HOME_CAROUSEL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                bean = gson.fromJson(response, HomeImageBean.class);
                images.add(bean);
                HomeImageAdapter adapter = new HomeImageAdapter(getContext());
                adapter.setBean(bean);
                homeImage.setAdapter(adapter);
                //创建小圆点个数
                if (bean != null) {
                    for (int i = 0; i < bean.getData().getBanners().size(); i++) {
                        ImageView imageView = new ImageView(context);
                        if (0 == i) {
                            imageView.setImageResource(R.drawable.dot_normal);
                        } else {
                            imageView.setImageResource(R.drawable.dot_focus);
                        }

                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
                        params.setMargins(15, 0, 15, 0);
                        imageView.setLayoutParams(params);
                        dots.add(imageView);
                        ll_viewpager.addView(dots.get(i));
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequest(request);
    }

    public void requestSpecial() {
        StringRequest request = new StringRequest(URLValues.HOME_BOX, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                HomeSpecialBean bean = gson.fromJson(response, HomeSpecialBean.class);
                for (int i = 0; i < bean.getData().getSecondary_banners().size(); i++) {
                    View view = LayoutInflater.from(context).inflate(R.layout.item_image, null);
                    ImageView imageView = (ImageView) view.findViewById(R.id.iv_home_special_image);
                    Picasso.with(context).load(bean.getData().getSecondary_banners().get
                            (i % bean.getData().getSecondary_banners().size()).getImage_url()).into(imageView);

                    home_special.addView(view);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(request);
    }

    @Override
    public void onDestroyView() {
        dots.clear();
        isRunning = false;
        super.onDestroyView();
    }
}



