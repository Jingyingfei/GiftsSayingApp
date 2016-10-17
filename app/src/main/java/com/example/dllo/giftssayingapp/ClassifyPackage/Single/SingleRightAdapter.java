package com.example.dllo.giftssayingapp.classifypackage.Single;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.giftssayingapp.R;
import com.example.dllo.giftssayingapp.beanpackage.SingleBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/29.
 */
public class SingleRightAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<SingleBean> singleArrayList;


    public void setSingleArrayList(List<SingleBean> singleArrayList) {
        this.singleArrayList = singleArrayList;
        notifyDataSetChanged();
    }

    public SingleRightAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return singleArrayList.get(0).getData().getCategories().size();
    }

    @Override
    public int getChildrenCount(int i) {
        return singleArrayList.get(0).getData().getCategories().get(i).getSubcategories().size() / 3 + 1;
    }

    @Override
    public Object getGroup(int i) {
        return singleArrayList.get(0).getData().getCategories().get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return singleArrayList.get(0).getData().getCategories().get(i).getSubcategories().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupHolder groupHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_right_single_group, null);
            groupHolder = new GroupHolder();
            groupHolder.title = (TextView) view.findViewById(R.id.tv_single_right_title);
            view.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) view.getTag();
        }

        groupHolder.title.setText(singleArrayList.get(0).getData().getCategories().get(i).getName());


        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ItemHolder itemHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_right_single_child, null);
            itemHolder = new ItemHolder();
            itemHolder.photo_left = (ImageView) view.findViewById(R.id.iv_single_left_photo);
            itemHolder.photo_center = (ImageView) view.findViewById(R.id.iv_single_center_photo);
            itemHolder.photo_right = (ImageView) view.findViewById(R.id.iv_single_right_photo);
            itemHolder.name_left = (TextView) view.findViewById(R.id.tv_single_left_name);
            itemHolder.name_center = (TextView) view.findViewById(R.id.tv_single_center_name);
            itemHolder.name_right = (TextView) view.findViewById(R.id.tv_single_right_name);
            view.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder) view.getTag();
        }
        i1 = i1 * 3;
        if (i1 < singleArrayList.get(0).getData().getCategories().get(i).getSubcategories().size()) {
            itemHolder.name_left.setText(singleArrayList.get(0).getData().getCategories().get(i).getSubcategories()
                    .get(i1).getName());

            Picasso.with(context).load(singleArrayList.get(0).getData().getCategories().
                    get(i).getSubcategories().get(i1).getIcon_url()).into(itemHolder.photo_left);
        }
        if (i1 < singleArrayList.get(0).getData().getCategories().get(i).getSubcategories().size() - 1) {

            itemHolder.name_center.setText(singleArrayList.get(0).getData().getCategories().get(i).getSubcategories()
                    .get(i1 + 1).getName());
            Picasso.with(context).load(singleArrayList.get(0).getData().getCategories().
                    get(i).getSubcategories().get(i1 + 1).getIcon_url()).into(itemHolder.photo_center);

        }
        if (i1 < singleArrayList.get(0).getData().getCategories().get(i).getSubcategories().size() - 2) {
            itemHolder.name_right.setText(singleArrayList.get(0).getData().getCategories().get(i).getSubcategories()
                    .get(i1 + 2).getName());

            Picasso.with(context).load(singleArrayList.get(0).getData().getCategories().
                    get(i).getSubcategories().get(i1 + 2).getIcon_url()).into(itemHolder.photo_right);
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    class GroupHolder {
        public TextView title;
    }

    class ItemHolder {
        public ImageView photo_left;
        public ImageView photo_center;
        public ImageView photo_right;
        public TextView name_left;
        public TextView name_center;
        public TextView name_right;

    }
}
