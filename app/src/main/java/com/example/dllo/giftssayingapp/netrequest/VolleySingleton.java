package com.example.dllo.giftssayingapp.netrequest;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by dllo on 16/9/20.
 * 懒汉单例 建立一个请求队列,方便网络请求
 */
public class VolleySingleton {

    //设置静态全局变量
    private static VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    //私有化构造方法 Volley 是导包生成的
    private VolleySingleton(){
        requestQueue = Volley.newRequestQueue(VolleyApp.getContext());
    }
    //静态方法
    public static VolleySingleton getInstance(){
        if (volleySingleton == null){
            synchronized (VolleySingleton.class){
                if (volleySingleton == null){
                    volleySingleton = new VolleySingleton();
                }
            }
        }
        return volleySingleton;
    }
    public RequestQueue getRequestQueue(){
        return requestQueue;
    }
    /**
     * 把请求加到请求队列中
     * request :各种网络请求
     */
    public void addRequest(Request request){
        requestQueue.add(request);
    }
}
