package com.example.dllo.giftssayingapp.homepackage.selection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.beanpackage.HomeImageFiveBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/10/11.
 */
public class HomeImageFiveAdapter extends BaseAdapter {
    private ArrayList<HomeImageFiveBean> arrayList;
    private Context context;

    public HomeImageFiveAdapter(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<HomeImageFiveBean> arrayList) {

        this.arrayList = arrayList;
        notifyDataSetChanged();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_home_image_five, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.category.setText(arrayList.get(i).getData().getPosts().get(i).getColumn().getTitle());
        viewHolder.column_title.setText(arrayList.get(i).getData().getPosts().get(i).getAuthor().getNickname());
        viewHolder.nickname.setText(arrayList.get(i).getData().getPosts().get(i).getColumn().getCategory());
        viewHolder.posts_title.setText(arrayList.get(i).getData().getPosts().get(i).getTitle());
        Picasso.with(context).load(arrayList.get(i).getData().getCover_image_url()).into(viewHolder.cover_image_url);
        Picasso.with(context).load(arrayList.get(i).getData().getPosts().get(i).getAuthor().getAvatar_url()).into(viewHolder.avatar_url);

        return view;
    }
    class ViewHolder{

        private final TextView category;
        private final TextView column_title;
        private final TextView nickname;
        private final TextView posts_title;
        private final CircleImageView avatar_url;
        private final ImageView cover_image_url;

        public ViewHolder(View view) {
            category = (TextView) view.findViewById(R.id.home_image_category);
            column_title = (TextView) view.findViewById(R.id.home_image_column_title);
            nickname = (TextView) view.findViewById(R.id.home_image_nickname);
            posts_title = (TextView) view.findViewById(R.id.home_image_posts_title);
            avatar_url = (CircleImageView) view.findViewById(R.id.home_image_avatar_url);
            cover_image_url = (ImageView) view.findViewById(R.id.home_image_cover_image_url);

        }
    }
}
