package com.example.dllo.giftssayingapp.homepackage;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by dllo on 16/9/20.
 * http://api.liwushuo.com/v2/channels/103/items?limit=20&ad=2&gender=2&offset=0&generation=1
 */
public class HomeSelectFragment extends BaseFragment {
    private String strNetImage = "http://api.liwushuo.com/v2/banners";
    private String strNetSpe = "http://api.liwushuo.com/v2/secondary_banners?gender=2&generation=1";
    private PullToRefreshListView homeSelect;
    private ViewPager homeImage;
    private HomeImageBean bean;
    private int currentItem = 0;
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
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        }
    };
    private LinearLayout ll_dot;
    private ArrayList<ImageView> dots;
    private ArrayList<HomeImageBean> images;
    private LinearLayout home_special;

    @Override
    protected int setLayout() {
        return R.layout.fragment_home_select;
    }

    @Override
    protected void initView() {
        homeSelect = bindView(R.id.ll_home_select);
        ll_dot = bindView(R.id.ll_dot);
    }

    @Override
    protected void initData() {
        //轮播图加头
        View view = LayoutInflater.from(context).inflate(R.layout.vp_home_image, null);
        homeImage = (ViewPager) view.findViewById(R.id.vp_home_lunbo);
        home_special = (LinearLayout) view.findViewById(R.id.ll_home_image);
        ListView listView = homeSelect.getRefreshableView();
        listView.addHeaderView(view);
        //开启定时器
        handler.sendEmptyMessageDelayed(0, 2000);
        requestImageData();
        requestData();
        requestSpecial();

        initDots();

        homeImage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dots.size(); i++) {
                    if (i == position % images.size()) {
                        dots.get(i).setImageResource(R.drawable.dot_focus);
                    } else {
                        dots.get(i).setImageResource(R.drawable.dot_normal);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        homeSelect.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {


            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        homeSelect.onRefreshComplete();


    }


    public void requestData() {
        //创建网络请求
        StringRequest request = new StringRequest(URLValues.HOME_CELL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // 解析
                ArrayList<HomeSelectBean> arrayList = new ArrayList<>();
                Gson gson = new Gson();
                HomeSelectBean bean = gson.fromJson(response, HomeSelectBean.class);
                for (int i = 0; i < bean.getData().getItems().size(); i++) {
                    bean.getData().getItems().get(i).getCover_image_url();
                    bean.getData().getItems().get(i).getTitle();
                    bean.getData().getItems().get(i).getAuthor().getNickname();
                    bean.getData().getItems().get(i).getAuthor().getAvatar_url();
                    arrayList.add(bean);
                }
                HomeSelectAdapter adapter = new HomeSelectAdapter(getContext());
                adapter.setArrayList(arrayList);
                homeSelect.setAdapter(adapter);
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
        images = new ArrayList<>();
        StringRequest request = new StringRequest(strNetImage, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                bean = gson.fromJson(response, HomeImageBean.class);
                images.add(bean);
                HomeImageAdapter adapter = new HomeImageAdapter(getContext());
                adapter.setBean(bean);
                homeImage.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequest(request);
    }

    //创建对应个数的小圆点
    public void initDots() {
        dots = new ArrayList<>();
        dots.clear();
        for (int i = 0; i < images.size(); i++) {
            ImageView imageView = new ImageView(context);
            if (i == 0) {
                imageView.setImageResource(R.drawable.dot_focus);
            } else {
                imageView.setImageResource(R.drawable.dot_normal);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.setMargins(5, 0, 5, 5);
            dots.add(imageView);
            //加载到布局容器中
            ll_dot.addView(dots.get(i), params);

        }
    }

    public void requestSpecial() {
        StringRequest request = new StringRequest(strNetSpe, new Response.Listener<String>() {
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


}



