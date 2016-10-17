package com.example.dllo.giftssayingapp.hotspotpackage.originality;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.beanpackage.OriginalityBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/23.
 */
public class OriginalityAdapter extends BaseAdapter {
    private ArrayList<OriginalityBean> arrayList;
    private Context context;
    private OnListViewItemClick onListViewItemClick;
    private OnListViewItemRightClick onListViewItemRightClick;

    public void setOnListViewItemRightClick(OnListViewItemRightClick onListViewItemRightClick) {
        this.onListViewItemRightClick = onListViewItemRightClick;
    }

    public void setOnListViewItemClick(OnListViewItemClick onListViewItemClick) {
        this.onListViewItemClick = onListViewItemClick;
    }

    public void setArrayList(ArrayList<OriginalityBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public OriginalityAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.get(0).getData().getItems().size() / 2 - 1;
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
            view = LayoutInflater.from(context).inflate(R.layout.item_originality, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        RelativeLayout left_item = (RelativeLayout) view.findViewById(R.id.left_item);
        RelativeLayout right_item = (RelativeLayout) view.findViewById(R.id.right_item);


        i = i + i;
        Picasso.with(context).load(arrayList.get(0).getData().getItems().get(i).getCover_image_url()).into(viewHolder.image);
        viewHolder.name.setText(arrayList.get(0).getData().getItems().get(i).getName());
        viewHolder.price.setText(arrayList.get(0).getData().getItems().get(i).getPrice());

        Picasso.with(context).load(arrayList.get(0).getData().getItems().get(i + 1).getCover_image_url()).into(viewHolder.image1);
        viewHolder.name1.setText(arrayList.get(0).getData().getItems().get(i + 1).getName());
        viewHolder.price1.setText(arrayList.get(0).getData().getItems().get(i + 1).getPrice());

        final int finalI = i;
        left_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = finalI;
                onListViewItemClick.click(itemPosition);
            }
        });
        final int finalI1 = i;
        right_item.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int rightPosition = finalI1 + 1;
                onListViewItemRightClick.rightClick(rightPosition);
            }
        });


        return view;
    }

    class ViewHolder {

        private final ImageView image;
        private final ImageView image1;
        private final TextView name;
        private final TextView name1;
        private final TextView price;
        private final TextView price1;

        public ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.iv_originality_image);
            image1 = (ImageView) view.findViewById(R.id.iv_originality_image1);
            name = (TextView) view.findViewById(R.id.tv_originality_name);
            name1 = (TextView) view.findViewById(R.id.tv_originality_name1);
            price = (TextView) view.findViewById(R.id.tv_originality_price);
            price1 = (TextView) view.findViewById(R.id.tv_originality_price1);

        }
    }
}
