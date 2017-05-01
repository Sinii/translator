package com.titmouse.anton.translator.translate.model;

import rx.Observable;

import com.titmouse.anton.translator.database.TranslateRealmDaoImpl;
import com.titmouse.anton.translator.oth.Translate;
import com.titmouse.anton.translator.translate.utils.api.TranslateFormat;
import com.titmouse.anton.translator.translate.utils.api.TranslateRetrofit;
import com.titmouse.anton.translator.oth.YandexApiData;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by anton on 11.04.17.
 */

public class TranslateModelImpl  implements TranslateModel {
    TranslateRetrofit mTranslateRetrofit;

    @Override
    public Observable<TranslateFormat> getTranslate(String text, String language) {

        Retrofit mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(YandexApiData.getTranslatorUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mTranslateRetrofit = mRetrofit.create(TranslateRetrofit.class);

       return mRetrofit.create(TranslateRetrofit.class)
                        .getTranslate(YandexApiData.getTranslatorApiKey(), text, language)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Boolean saveTranslate(Translate translate) {
        TranslateRealmDaoImpl.getInstance().changeFavorite(translate);
        return translate.getFavorite();
    }



}
