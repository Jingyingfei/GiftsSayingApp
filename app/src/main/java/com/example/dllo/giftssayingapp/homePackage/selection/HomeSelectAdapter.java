package com.example.dllo.giftssayingapp.homepackage.selection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.beanpackage.HomeSelectBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

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
        if (arrayList.get(0).getData().getItems().get(i).getColumn() != null) {
            viewHolder.category.setText(arrayList.get(0).getData().getItems().get(i).getColumn().getCategory());
            viewHolder.title_little.setText(arrayList.get(0).getData().getItems().get(i).getColumn().getTitle());

        }
        if (arrayList.get(0).getData().getItems().get(i).getAuthor() != null){
            Picasso.with(context).load(arrayList.get(0).getData().getItems().get(i).getAuthor().getAvatar_url()).into(viewHolder.avatar);
        }
        viewHolder.nickname.setText(arrayList.get(0).getData().getItems().get(i).getAuthor().getNickname());
        Picasso.with(context).load(arrayList.get(0).getData().getItems().get(i).getCover_image_url()).into(viewHolder.image);
        viewHolder.title.setText(arrayList.get(0).getData().getItems().get(i).getTitle());

        return view;
    }
    

    class ViewHolder {

        private ImageView image;
        private TextView title;
        private TextView category;
        private TextView title_little;
        private TextView nickname;
        private final CircleImageView avatar;


        public ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.iv_image_home_select);
            title = (TextView) view.findViewById(R.id.tv_title_home_select);
            category = (TextView) view.findViewById(R.id.tv_category);
            title_little = (TextView) view.findViewById(R.id.tv_title);
            nickname = (TextView) view.findViewById(R.id.nickname);
            avatar = (CircleImageView) view.findViewById(R.id.avatar);
        }

    }
}
