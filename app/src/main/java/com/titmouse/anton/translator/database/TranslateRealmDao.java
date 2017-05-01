package com.titmouse.anton.translator.database;

import com.titmouse.anton.translator.database.entity.TranslateEntity;
import com.titmouse.anton.translator.oth.Translate;
import java.util.List;


/**
 * Created by anton on 22.04.17.
 */

public interface TranslateRealmDao {
    Boolean setTranslation(Translate translate);
    List<Translate> getTranslations();
    List<Translate> deleteTranslation(Translate translate);
    List<Translate> getFavorites();
    void changeFavorite(Translate translate);
    void deleteAllTranslations();
}
