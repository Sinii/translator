package com.titmouse.anton.translator.favorite.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.titmouse.anton.translator.favorite.model.FavoriteModelImpl;
import com.titmouse.anton.translator.favorite.view.FavoriteView;
import com.titmouse.anton.translator.history.model.HistoryModelImpl;
import com.titmouse.anton.translator.oth.Translate;

/**
 * Created by anton on 24.04.17.
 */

public class FavoritePresenterImpl extends MvpBasePresenter<FavoriteView> implements FavoritePresenter {

    private final FavoriteModelImpl model;

    public FavoritePresenterImpl() {
        model = new FavoriteModelImpl();
    }


    @Override
    public void onResumeFavorite() {
        if (isViewAttached()) {
            getView().showFavoriteList( model.getTranslateFavoriteList());
        }
    }

    @Override
    public void deleteFromFavorite(Translate translate) {
        if (isViewAttached()) {
            model.deleteFavoriteFromHistory(translate);
            getView().showFavoriteList(model.getTranslateFavoriteList());
        }
    }
}
