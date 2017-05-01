package com.titmouse.anton.translator.translate.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.titmouse.anton.translator.translate.view.TranslateView;
import com.titmouse.anton.translator.oth.Translate;

/**
 * Created by anton on 11.04.17.
 */

public interface TranslatePresenter extends MvpPresenter<TranslateView> {
    void onTranslateTextChanged(String text, String language, Boolean favorite);
    void buttonNewSaveStatus(Translate translate);
}
