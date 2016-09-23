package com.example.dllo.giftssayingapp.homepackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.giftssayingapp.R;

import java.util.ArrayList;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/9/21.
 */
public class HomeSelectAdapter extends BaseAdapter {
    ArrayList<HomeSelectBean> arrayList;
    private Context context;

    public HomeSelectAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<HomeSelectBean> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_home_select, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (arrayList.get(i).getData().getItems().get(i).getColumn() != null) {
            viewHolder.category.setText(arrayList.get(i).getData().getItems().get(i).getColumn().getCategory());
            viewHolder.title_little.setText(arrayList.get(i).getData().getItems().get(i).getColumn().getTitle());
            viewHolder.nickname.setText(arrayList.get(i).getData().getItems().get(i).getAuthor().getNickname());
            Picasso.with(context).load(arrayList.get(i).getData().getItems().get(i).getCover_image_url()).into(viewHolder.image);
            Picasso.with(context).load(arrayList.get(i).getData().getItems().get(i).getAuthor().getAvatar_url()).into(viewHolder.avatar);
            viewHolder.title.setText(arrayList.get(i).getData().getItems().get(i).getTitle());
        }

        return view;
    }

    class ViewHolder {

        private ImageView image;
        private TextView title;
        private TextView category;
        private TextView title_little;
        private TextView nickname;
        private ImageView avatar;

        public ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.iv_image_home_select);
            title = (TextView) view.findViewById(R.id.tv_title_home_select);
            category = (TextView) view.findViewById(R.id.tv_category);
            title_little = (TextView) view.findViewById(R.id.tv_title);
            nickname = (TextView) view.findViewById(R.id.nickname);
            avatar = (ImageView) view.findViewById(R.id.avatar);
        }

    }
}
