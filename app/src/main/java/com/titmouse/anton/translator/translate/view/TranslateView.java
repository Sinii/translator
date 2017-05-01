package com.titmouse.anton.translator.translate.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.titmouse.anton.translator.oth.Translate;

/**
 * Created by anton on 11.04.17.
 */

public interface TranslateView extends MvpView {
    void showTranslate(Translate translate);
    void showNewSaveStatus(Boolean saved);
    void showError(String text);

}
