package com.titmouse.anton.translator.favorite.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.titmouse.anton.translator.R;
import com.titmouse.anton.translator.oth.Translate;

import java.util.List;


public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {
	
	private List<Translate> translateList;
	
	
	public FavoriteAdapter(final List<Translate> translateEntityList) {
		this.translateList = translateEntityList;
		
	}
	
	public void updateList(final List<Translate> translateEntityList) {
		translateList = translateEntityList;
		notifyDataSetChanged();
	}
	
	@Override
	public FavoriteAdapter.MyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
		final View itemView = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.history_translate_item, parent, false);
		
		return new MyViewHolder(itemView);
	}
	
	@Override
	public void onBindViewHolder(final MyViewHolder holder, final int position) {
		final Translate translate = translateList.get(position);
		holder.translateView.setText(translate.getTranslate());
		holder.textView.setText(translate.getText());
		
	}
	
	
	@Override
	public int getItemCount() {
		return translateList.size();
	}
	
	static class MyViewHolder extends RecyclerView.ViewHolder {
		
		private final TextView translateView;
		private final TextView textView;
		
		private MyViewHolder(final View v) {
			super(v);
			
			textView = v.findViewById(R.id.history_recyclerview_item_textview);
			translateView = v.findViewById(R.id.history_recyclerview_item_translate_textview);
			
		}
		
		
	}
}