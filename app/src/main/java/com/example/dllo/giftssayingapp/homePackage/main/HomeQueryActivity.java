package com.example.dllo.giftssayingapp.homepackage.main;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.basepackage.BaseActivity;
import com.example.dllo.giftssayingapp.beanpackage.EditBean;
import com.example.dllo.giftssayingapp.basepackage.GiftsSearchHelper;
import com.example.dllo.giftssayingapp.basepackage.URLValues;
import com.example.dllo.giftssayingapp.basepackage.VolleySingleton;
import com.example.dllo.giftssayingapp.beanpackage.HomeQueryBean;
import com.example.dllo.giftssayingapp.homepackage.selection.HomeSearchFragment;
import com.example.dllo.giftssayingapp.homepackage.selection.QueryAdapter;
import com.example.dllo.giftssayingapp.toolbar.FlowLayout;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/29.
 */
public class HomeQueryActivity extends BaseActivity implements View.OnClickListener {

    private EditText query_name;
    private TextView query_cancel;
    private FlowLayout flowLayout;
    private ListView lv;
    private ImageView deteAll;

    private List<String> arrayList = new ArrayList<>();
    private HomeQueryBean bean;
    private String wordUrl;
    private SQLiteDatabase database;
    private TextView search_record;
    private QueryAdapter adapter;
    private ArrayList<String> strings;
    private HomeSearchFragment searchFragment;
    private RelativeLayout ll_search;

    @Override
    protected int setLayout() {
        return R.layout.activity_home_query;
    }

    @Override
    protected void initView() {
        query_name = bindView(R.id.et_home_query_name);
        query_cancel = bindView(R.id.tv_home_query_cancel);
        flowLayout = bindView(R.id.flowlayout);
        lv = bindView(R.id.lv_search_query);
        ll_search = bindView(R.id.ll_search);
        deteAll = bindView(R.id.btn_search_deteall);
        search_record = bindView(R.id.tv_search_record);
        query_cancel.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        GiftsSearchHelper helper = new GiftsSearchHelper(this, "gifts.db", null, 1);
        database = helper.getWritableDatabase();
        editData();
        kindData();
        queryData();


        //搜索
        query_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String str = null;
                try {
                    str = URLEncoder.encode(query_name.getText().toString(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                wordUrl = "http://api.liwushuo.com/v2/search/word_completed?keyword=" + str;

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                if (searchFragment == null) {
                    searchFragment = new HomeSearchFragment();
                }
//                Bundle args = new Bundle();
//                args.putString("wordUrl", wordUrl);
//                searchFragment.setArguments(args);

                if (!searchFragment.isVisible()) {
                    transaction.replace(R.id.frame_home_query, searchFragment);
                    transaction.commit();
                }
                searchFragment.setUrl(wordUrl);


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

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
                    arrayList.add(bean.getData().getHot_words().get(i));
                }

                //流式布局
                ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.leftMargin = 15;
                lp.rightMargin = 15;
                lp.topMargin = 5;
                lp.bottomMargin = 5;
                for (int i = 0; i < arrayList.size(); i++) {
                    TextView view = new TextView(HomeQueryActivity.this);
                    view.setText(arrayList.get(i));
                    view.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bg));
                    flowLayout.addView(view, lp);
                }
                flowData();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(request);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    //流式布局的点击事件
    public void flowData() {
        flowLayout.setOnItemClickListener(new FlowLayout.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                query_name.setText(bean.getData().getHot_words().get(position));
            }
        });
    }

    public void dataBase() {
        if (query_name.getText().length() != 0) {
            ContentValues values = new ContentValues();
            values.put("name", query_name.getText().toString());
            database.insert("search", null, values);
        }
    }

    //查找数据库后放在listView
    public void queryData() {
        strings = new ArrayList<>();
        Cursor cursor = database.query("search", null, null, null, null, null, null);
//        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(HomeQueryActivity.this,R.layout.item_record,cursor,new String[]{"name"},new int[]{R.id.tv_search_query});
//        lv.setAdapter(adapter);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                strings.add(name);
            }
            if (!strings.isEmpty()) {
                for (int i = 0; i < strings.size(); i++) {
                    for (int j = strings.size() - 1; j > i; j--) {
                        if (strings.get(i).equals(strings.get(j))) {
                            strings.remove(j);
                        }
                    }
                }
            }
            adapter = new QueryAdapter(this);
            adapter.setStrings(strings);
            lv.setAdapter(adapter);
            ll_search.setVisibility(View.VISIBLE);

            adapter.setOnItemClickListener(new QueryAdapter.OnQueryItemClickListener() {
                @Override
                public void onClick(int position) {
                    database.delete("search", "name = ?", new String[]{strings.get(position)});
                    strings.remove(position);
                    adapter.notifyDataSetChanged();
                }
            });
        }
        //点击删除全部
        deteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.delete("search", null, null);
                strings.clear();
                ll_search.setVisibility(View.INVISIBLE);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //点击取消返回当前页,并存入数据库
            case R.id.tv_home_query_cancel:
                if (query_name.getText().toString() != null) {
                    dataBase();
                    adapter.notifyDataSetChanged();
                }
                onBackPressed();
                break;
        }
    }
}
