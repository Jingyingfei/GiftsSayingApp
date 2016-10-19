package com.example.dllo.giftssayingapp.tools;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/18.
 */
public class JsonUtil {
    private static Gson sGson = new Gson();

    /**
     * 将json字符串转化成实体对象
     * @param json
     * @param classOfT
     * @return
     */
    public static Object stringToObject(String json,Class classOfT){
        return sGson.fromJson(json, classOfT);
    }

    /**
     * 将对象准换为json字符串 或者 把list 转化成json
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String objectOfString(T object){
        return sGson.toJson(object);
    }

    /**
     * 把json 字符串转化成list
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> stringToList(String json , Class<T> cls  ){
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            list.add(gson.fromJson(elem, cls));
        }
        return list ;
    }
}
