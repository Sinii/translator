package com.titmouse.anton.translator.favorite.model;

import com.titmouse.anton.translator.oth.Translate;

import java.util.List;

/**
 * Created by anton on 24.04.17.
 */

public interface FavoriteModel  {
    List<Translate> getTranslateFavoriteList();
    void deleteFavoriteFromHistory(Translate translate);
}
