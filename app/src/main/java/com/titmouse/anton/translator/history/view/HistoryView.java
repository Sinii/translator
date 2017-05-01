package com.titmouse.anton.translator.history.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.titmouse.anton.translator.database.entity.TranslateEntity;
import com.titmouse.anton.translator.oth.Translate;
import com.titmouse.anton.translator.translate.utils.api.TranslateFormat;

import java.util.List;

/**
 * Created by anton on 23.04.17.
 */

public interface HistoryView extends MvpView{
    void showTranslateList(List<Translate> translateList);

}
