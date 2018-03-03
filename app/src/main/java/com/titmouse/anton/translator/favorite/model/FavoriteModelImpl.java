package com.titmouse.anton.translator.favorite.model;

import com.titmouse.anton.translator.database.TranslateRealmDaoImpl;
import com.titmouse.anton.translator.oth.Translate;

import java.util.List;


public class FavoriteModelImpl implements FavoriteModel {
	
	@Override
	public List<Translate> getTranslateFavoriteList() {
		return TranslateRealmDaoImpl.getInstance().getFavorites();
	}
	
	@Override
	public void deleteFavoriteFromHistory(final Translate translate) {
		TranslateRealmDaoImpl.getInstance().changeFavorite(translate);
	}
}
