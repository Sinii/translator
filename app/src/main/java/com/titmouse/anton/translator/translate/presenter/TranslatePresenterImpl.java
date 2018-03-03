package com.titmouse.anton.translator.translate.presenter;

import android.content.Context;
import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.titmouse.anton.translator.database.TranslateRealmDaoImpl;
import com.titmouse.anton.translator.oth.Translate;
import com.titmouse.anton.translator.translate.model.TranslateModelImpl;
import com.titmouse.anton.translator.translate.utils.api.TranslateFormat;
import com.titmouse.anton.translator.translate.view.TranslateView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;


public class TranslatePresenterImpl extends MvpBasePresenter<TranslateView> implements TranslatePresenter {
	
	private static final int ONE_SECOND_MILLIS = 1000;
	private static final int DELAY_IN_SECONDS = 1;
	private final TranslateModelImpl model;
	private Subscription translationTextChanges = Subscriptions.empty();
	
	public TranslatePresenterImpl(final Context context) {
		model = new TranslateModelImpl();
	}
	
	@Override
	public void onTranslateTextChanged(final String text, final String language, final Boolean favorite) {
		prepareSubscription(translationTextChanges);
		
		translationTextChanges = Observable.interval(ONE_SECOND_MILLIS, TimeUnit.MILLISECONDS)
			.take(DELAY_IN_SECONDS)
			.subscribeOn(Schedulers.io())
			.doOnNext(a -> callModelGetTranslate(text, language, favorite))
			.subscribe();
		
	}
	
	private void callModelGetTranslate(final String text, final String language, final Boolean favorite) {
		final Observable<TranslateFormat> formatObservable = model.getTranslate(text, language);
		formatObservable
			.doOnNext(translateFormat -> {
				if (isViewAttached()) {
					getView().showTranslate(new Translate(0L, favorite, translateFormat.getText().get(0), language, text, 0));
					getView().showNewSaveStatus(TranslateRealmDaoImpl.getInstance().setTranslation(new Translate(0L, favorite, translateFormat.getText().get(0), language, text, 0)));
				}
			})
			.doOnError(e -> {
				Log.e("observable", e.getMessage());
				if (isViewAttached()) {
					getView().showError(e.getMessage());
				}
			})
			.subscribe();
	}
	
	@Override
	public void buttonNewSaveStatus(final Translate translate) {
		if (isViewAttached()) {
			getView().showNewSaveStatus(model.saveTranslate(translate));
		}
	}
	
	private static void prepareSubscription(final Subscription subscription) {
		if (subscription != null && !subscription.isUnsubscribed()) {
			subscription.unsubscribe();
		}
	}
}
