package com.titmouse.anton.translator.history.model;

import com.titmouse.anton.translator.database.TranslateRealmDaoImpl;
import com.titmouse.anton.translator.oth.Translate;

import java.util.List;


public class HistoryModelImpl implements HistoryModel {
	
	@Override
	public List<Translate> getTranslateHistoryList() {
		return TranslateRealmDaoImpl.getInstance().getTranslations();
		
	}
	
	@Override
	public List<Translate> deleteTranslateFromHistory(final Translate translate) {
		return TranslateRealmDaoImpl.getInstance().deleteTranslation(translate);
	}
}
