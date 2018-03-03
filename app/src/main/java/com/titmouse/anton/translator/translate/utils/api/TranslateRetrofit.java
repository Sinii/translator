package com.titmouse.anton.translator.translate.utils.api;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface TranslateRetrofit {
	
	@GET("api/v1.5/tr.json/translate?")
	Observable<TranslateFormat> getTranslate(@Query("key") String key, @Query("text") String text, @Query("lang") String lang);
	
	@GET("/api/v1/dicservice.json/lookup?")
	Observable<TranslateFormat> getDictionary(@Query("key") String key, @Query("text") String text, @Query("lang") String lang);
	
	
}