package com.titmouse.anton.translator.history.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.titmouse.anton.translator.database.entity.TranslateEntity;
import com.titmouse.anton.translator.history.view.HistoryView;
import com.titmouse.anton.translator.oth.Translate;

/**
 * Created by anton on 23.04.17.
 */

public interface HistoryPresenter extends MvpPresenter<HistoryView> {
    void onResumeHistory();
    void deleteTranslate(Translate translate);

}
