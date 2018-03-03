package com.titmouse.anton.translator.database;

import com.titmouse.anton.translator.oth.Translate;

import java.util.List;


public interface TranslateRealmDao {
	
	Boolean setTranslation(Translate translate);
	
	List<Translate> getTranslations();
	
	List<Translate> deleteTranslation(Translate translate);
	
	List<Translate> getFavorites();
	
	void changeFavorite(Translate translate);
	
	void deleteAllTranslations();
}
