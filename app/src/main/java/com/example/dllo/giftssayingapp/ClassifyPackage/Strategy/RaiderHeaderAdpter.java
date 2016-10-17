package com.example.dllo.giftssayingapp.classifypackage.Strategy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.beanpackage.RaiderHeaderBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/27.
 */
public class RaiderHeaderAdpter extends RecyclerView.Adapter {
    Context context;
    ArrayList<RaiderHeaderBean> raidersBeen;
    int all = 0;

    public void setRaiderHeaderBean(ArrayList<RaiderHeaderBean> raidersBeen) {
        this.raidersBeen = raidersBeen;
        notifyDataSetChanged();
    }

    public RaiderHeaderAdpter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType < raidersBeen.get(0).getData().getColumns().size() - 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.raider_header_item, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        } else {
            View viewAll = LayoutInflater.from(context).inflate(R.layout.all, parent, false);

            All a = new All(viewAll);
            return a;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);

        if (position < raidersBeen.get(0).getData().getColumns().size() - 1) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;

            Picasso.with(context).load(raidersBeen.get(0).getData()
                    .getColumns().get(position).getBanner_image_url()).into(myViewHolder.imageView);
            myViewHolder.subtitle.setText(raidersBeen.get(0).getData()
                    .getColumns().get(position).getSubtitle());
            myViewHolder.author.setText(raidersBeen.get(0).getData()
                    .getColumns().get(position).getAuthor());
            myViewHolder.title.setText(raidersBeen.get(0).getData()
                    .getColumns().get(position).getTitle());

        } else {
            All all1 = (All) holder;

        }


    }


    @Override
    public int getItemCount() {
        return raidersBeen.get(0).getData().getColumns().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView author;
        private final TextView subtitle;
        private final TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.raider_header_iv);
            author = (TextView) itemView.findViewById(R.id.raider_author_tv);
            subtitle = (TextView) itemView.findViewById(R.id.raider_subtitle_tv);
            title = (TextView) itemView.findViewById(R.id.raider_title_tv);
        }
    }

    public class All extends RecyclerView.ViewHolder {

        public All(View itemView) {
            super(itemView);
        }
    }
}
