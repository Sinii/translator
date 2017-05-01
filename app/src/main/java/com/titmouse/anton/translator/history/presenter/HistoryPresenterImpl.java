package com.titmouse.anton.translator.history.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.titmouse.anton.translator.database.entity.TranslateEntity;
import com.titmouse.anton.translator.history.model.HistoryModelImpl;
import com.titmouse.anton.translator.history.view.HistoryView;
import com.titmouse.anton.translator.oth.Translate;
import com.titmouse.anton.translator.translate.model.TranslateModelImpl;

import java.util.List;

/**
 * Created by anton on 23.04.17.
 */




public class HistoryPresenterImpl extends MvpBasePresenter<HistoryView> implements HistoryPresenter {

    private final HistoryModelImpl model;

    public HistoryPresenterImpl() {
        model = new HistoryModelImpl();
    }


    @Override
    public void onResumeHistory() {
        if (isViewAttached()) {
            getView().showTranslateList( model.getTranslateHistoryList());

            Log.d("History Presenter", "onResumeHistory()");
        }
    }

    @Override
    public void deleteTranslate(Translate translate) {
        if (isViewAttached()) {
            getView().showTranslateList(model.deleteTranslateFromHistory(translate));
            Log.d("History Presenter", "onDeleteTranslate()");
        }
    }



}
