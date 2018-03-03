package com.titmouse.anton.translator.translate.model;

import com.titmouse.anton.translator.database.TranslateRealmDaoImpl;
import com.titmouse.anton.translator.oth.Translate;
import com.titmouse.anton.translator.oth.YandexApiData;
import com.titmouse.anton.translator.translate.utils.api.TranslateFormat;
import com.titmouse.anton.translator.translate.utils.api.TranslateRetrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class TranslateModelImpl implements TranslateModel {
	
	@Override
	public Observable<TranslateFormat> getTranslate(final String text, final String language) {
		
		final Retrofit retrofit = new Retrofit.Builder()
			.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
			.baseUrl(YandexApiData.getTranslatorUrl())
			.addConverterFactory(GsonConverterFactory.create())
			.build();
		
		return retrofit.create(TranslateRetrofit.class)
			.getTranslate(YandexApiData.getTranslatorApiKey(), text, language)
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread());
		
	}
	
	@Override
	public Boolean saveTranslate(final Translate translate) {
		TranslateRealmDaoImpl.getInstance().changeFavorite(translate);
		return translate.getFavorite();
	}
	
	
}
