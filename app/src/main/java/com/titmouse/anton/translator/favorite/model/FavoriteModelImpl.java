package com.titmouse.anton.translator.favorite.model;

import com.titmouse.anton.translator.database.TranslateRealmDaoImpl;
import com.titmouse.anton.translator.oth.Translate;

import java.util.List;

/**
 * Created by anton on 24.04.17.
 */

public class FavoriteModelImpl implements FavoriteModel {
    @Override
    public List<Translate> getTranslateFavoriteList() {
        return TranslateRealmDaoImpl.getInstance().getFavorites();
    }

    @Override
    public void deleteFavoriteFromHistory(Translate translate) {
        TranslateRealmDaoImpl.getInstance().changeFavorite(translate);
    }
}
