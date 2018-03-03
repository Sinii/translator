package com.titmouse.anton.translator.favorite.model;

import com.titmouse.anton.translator.oth.Translate;

import java.util.List;


public interface FavoriteModel {
	
	List<Translate> getTranslateFavoriteList();
	
	void deleteFavoriteFromHistory(Translate translate);
}
