package com.titmouse.anton.translator.favorite.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.titmouse.anton.translator.favorite.model.FavoriteModelImpl;
import com.titmouse.anton.translator.favorite.view.FavoriteView;
import com.titmouse.anton.translator.oth.Translate;


public class FavoritePresenterImpl extends MvpBasePresenter<FavoriteView> implements FavoritePresenter {
	
	private final FavoriteModelImpl model;
	
	public FavoritePresenterImpl() {
		model = new FavoriteModelImpl();
	}
	
	
	@Override
	public void onResumeFavorite() {
		if (isViewAttached()) {
			getView().showFavoriteList(model.getTranslateFavoriteList());
		}
	}
	
	@Override
	public void deleteFromFavorite(final Translate translate) {
		if (isViewAttached()) {
			model.deleteFavoriteFromHistory(translate);
			getView().showFavoriteList(model.getTranslateFavoriteList());
		}
	}
}
