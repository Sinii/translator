package com.titmouse.anton.translator.history.model;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.titmouse.anton.translator.database.entity.TranslateEntity;
import com.titmouse.anton.translator.oth.Translate;

import java.util.List;

/**
 * Created by anton on 23.04.17.
 */

public interface HistoryModel  {
    List<Translate> getTranslateHistoryList();
    List<Translate> deleteTranslateFromHistory(Translate translate);
}
