package com.titmouse.anton.translator.favorite.adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.titmouse.anton.translator.R;
import com.titmouse.anton.translator.oth.Translate;

import java.util.List;

/**
 * Created by anton on 24.04.17.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>  {

    private List<Translate> translateList;


    public FavoriteAdapter(List<Translate> translateEntityList) {
        this.translateList = translateEntityList;

    }

    public void updateList(List<Translate> translateEntityList) {
        translateList = translateEntityList;
        notifyDataSetChanged();
    }

    @Override
    public FavoriteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_translate_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Translate translate = translateList.get(position);
        holder.translateView.setText(translate.getTranslate());
        holder.textView.setText(translate.getText());

    }



    @Override
    public int getItemCount() {
        return translateList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView translateView, textView;

        MyViewHolder(View v) {
            super(v);

            textView = (TextView) v.findViewById(R.id.history_recyclerview_item_textview);
            translateView = (TextView) v.findViewById(R.id.history_recyclerview_item_translate_textview);

        }


    }
}