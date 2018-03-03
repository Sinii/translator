package com.titmouse.anton.translator.history.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.titmouse.anton.translator.history.view.HistoryView;
import com.titmouse.anton.translator.oth.Translate;


public interface HistoryPresenter extends MvpPresenter<HistoryView> {
	
	void onResumeHistory();
	
	void deleteTranslate(Translate translate);
	
}
