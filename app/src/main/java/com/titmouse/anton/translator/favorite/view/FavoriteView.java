package com.titmouse.anton.translator.favorite.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.titmouse.anton.translator.oth.Translate;

import java.util.List;

/**
 * Created by anton on 24.04.17.
 */

public interface FavoriteView extends MvpView {
    void showFavoriteList(List<Translate> translateList);
}
