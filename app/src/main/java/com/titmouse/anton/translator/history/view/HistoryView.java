package com.titmouse.anton.translator.history.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.titmouse.anton.translator.oth.Translate;

import java.util.List;


public interface HistoryView extends MvpView {
	
	void showTranslateList(List<Translate> translateList);
	
}
