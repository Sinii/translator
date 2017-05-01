package com.titmouse.anton.translator.translate.model;


import com.titmouse.anton.translator.oth.Translate;
import com.titmouse.anton.translator.translate.utils.api.TranslateFormat;

import rx.Observable;

/**
 * Created by anton on 11.04.17.
 */

public interface TranslateModel {
    Observable<TranslateFormat> getTranslate(String text, String language);
    Boolean saveTranslate(Translate translate);
}
