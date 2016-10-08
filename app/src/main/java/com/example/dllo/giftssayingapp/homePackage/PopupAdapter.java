package com.example.dllo.giftssayingapp.homepackage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dllo.giftssayingapp.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/8.
 */

public class PopupAdapter extends RecyclerView.Adapter<PopupAdapter.ViewHolder> {
    private ArrayList<String> arrayList;
    private Context context;

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public PopupAdapter(Context context) {

        this.context = context;
    }

    @Override
    public PopupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_popup, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name_popup.setText(arrayList.get(position));
    }


    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private final TextView name_popup;

        public ViewHolder(View itemView) {
            super(itemView);
            name_popup = (TextView) itemView.findViewById(R.id.tv_popup_window);
        }
    }
}

