package com.titmouse.anton.translator.translate.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.titmouse.anton.translator.oth.Translate;
import com.titmouse.anton.translator.translate.view.TranslateView;


public interface TranslatePresenter extends MvpPresenter<TranslateView> {
	
	void onTranslateTextChanged(String text, String language, Boolean favorite);
	
	void buttonNewSaveStatus(Translate translate);
}
