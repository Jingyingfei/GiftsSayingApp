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
 * Created by dllo on 16/9/22.
 */
public class HomeKindAdapter extends BaseAdapter {
    private ArrayList<HomeKindBean> arrayList;
    private Context context;

    public void setArrayList(ArrayList<HomeKindBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public HomeKindAdapter(Context context) {

        this.context = context;
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
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_home_kind,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.nickname.setText(arrayList.get(i).getData().getItems().get(i).getAuthor().getNickname());
        viewHolder.title_kind.setText(arrayList.get(i).getData().getItems().get(i).getTitle());
        Picasso.with(context).load
                (arrayList.get(i).getData().getItems().get(i).getCover_image_url()).
                into(viewHolder.image_kind);
        viewHolder.category.setText(arrayList.get(i).getData().getItems().get(i).getColumn().getCategory());
        viewHolder.title.setText(arrayList.get(i).getData().getItems().get(i).getColumn().getTitle());
        Picasso.with(context).load(arrayList.get(i).getData().getItems().get(i).getAuthor().getAvatar_url()).into(viewHolder.avatar);

        return view;
    }

    class ViewHolder{

        private final ImageView image_kind;
        private final TextView title_kind;
        private final TextView nickname;
        private final TextView category;
        private final TextView title;
        private final ImageView avatar;

        public ViewHolder(View view) {
            nickname = (TextView) view.findViewById(R.id.tv_kind_nickname);
            image_kind = (ImageView) view.findViewById(R.id.iv_kind_home_select);
            title_kind = (TextView) view.findViewById(R.id.tv_kind_home_select);
            category = (TextView) view.findViewById(R.id.tv_kind_category);
            title = (TextView) view.findViewById(R.id.tv_kind_title);
            avatar = (ImageView) view.findViewById(R.id.iv_kind_avatar);

        }


    }
}
