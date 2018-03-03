package com.titmouse.anton.translator.history.model;

import com.titmouse.anton.translator.oth.Translate;

import java.util.List;


public interface HistoryModel {
	
	List<Translate> getTranslateHistoryList();
	
	List<Translate> deleteTranslateFromHistory(Translate translate);
}
