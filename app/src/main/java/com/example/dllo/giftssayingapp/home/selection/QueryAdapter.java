package com.example.dllo.giftssayingapp.home.selection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.giftssayingapp.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/30.
 */
public class QueryAdapter extends BaseAdapter {
    private ArrayList<String> strings;
    private Context context;

    public interface OnQueryItemClickListener {
        void onClick(int position);
    }

    private OnQueryItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnQueryItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public QueryAdapter(Context context) {
        this.context = context;
    }

    public void setStrings(ArrayList<String> strings) {
        this.strings = strings;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return strings == null ? 0 : strings.size();
    }

    @Override
    public Object getItem(int i) {
        return strings.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_record, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.record.setText(strings.get(i));

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(i);
            }
        });
        return view;
    }

    class ViewHolder {

        private final TextView record;
        private final ImageView delete;

        public ViewHolder(View view) {
            record = (TextView) view.findViewById(R.id.tv_search_query);
            delete = (ImageView) view.findViewById(R.id.iv_single_dele);
        }
    }
}
