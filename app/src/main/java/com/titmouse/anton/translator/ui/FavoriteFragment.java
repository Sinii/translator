package com.titmouse.anton.translator.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.titmouse.anton.translator.R;
import com.titmouse.anton.translator.favorite.adapter.FavoriteAdapter;
import com.titmouse.anton.translator.favorite.presenter.FavoritePresenter;
import com.titmouse.anton.translator.favorite.presenter.FavoritePresenterImpl;
import com.titmouse.anton.translator.favorite.view.FavoriteView;
import com.titmouse.anton.translator.oth.RecyclerTouchListener;
import com.titmouse.anton.translator.oth.Translate;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends MvpFragment<FavoriteView, FavoritePresenter> implements FavoriteView, FragmentLifecycle, RecyclerTouchListener.ClickListener {
	
	private FavoriteAdapter mAdapter;
	private List<Translate> translateEntityList = new ArrayList<>();
	private TextView mEmptyText;
	
	@NonNull
	@Override
	public FavoritePresenter createPresenter() {
		return new FavoritePresenterImpl();
	}
	
	@Override
	public void onResume() {
		
		presenter.onResumeFavorite();
		super.onResume();
	}
	
	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		final View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
		
		mEmptyText = rootView.findViewById(R.id.favorite_empty_text);
		mAdapter = new FavoriteAdapter(translateEntityList);
		final RecyclerView recyclerView = rootView.findViewById(R.id.favorite_recyclerview);
		recyclerView.setAdapter(mAdapter);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, this));
		final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(mLayoutManager);
		recyclerView.setNestedScrollingEnabled(true);
		
		
		return rootView;
	}
	
	
	@Override
	public void onOpenFragment() {
		if (presenter != null) {
			presenter.onResumeFavorite();
		}
		
	}
	
	
	@Override
	public void showFavoriteList(final List<Translate> translateList) {
		if (translateList.size() > 0) {
			mEmptyText.setVisibility(View.GONE);
		} else {
			mEmptyText.setVisibility(View.VISIBLE);
		}
		this.translateEntityList = translateList;
		mAdapter.updateList(translateList);
	}
	
	@Override
	public void onClick(final View view, final int position) {
	
	}
	
	@Override
	public void onLongClick(final View view, final int position) {
		final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
					case DialogInterface.BUTTON_POSITIVE:
						presenter.deleteFromFavorite(translateEntityList.get(position));
						break;
					
				}
			}
		};
		
		final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
		builder.setMessage(R.string.delete_from_favorite).setPositiveButton(R.string.yes, dialogClickListener)
			.setNegativeButton(R.string.no, dialogClickListener).show();
		
		
	}
}
