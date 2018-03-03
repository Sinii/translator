package com.titmouse.anton.translator.favorite.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.titmouse.anton.translator.favorite.view.FavoriteView;
import com.titmouse.anton.translator.oth.Translate;


public interface FavoritePresenter extends MvpPresenter<FavoriteView> {
	
	void onResumeFavorite();
	
	void deleteFromFavorite(Translate translate);
}
