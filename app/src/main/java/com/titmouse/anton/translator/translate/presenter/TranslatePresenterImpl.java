package com.titmouse.anton.translator.translate.presenter;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.titmouse.anton.translator.database.TranslateRealmDaoImpl;
import com.titmouse.anton.translator.translate.utils.api.TranslateFormat;
import com.titmouse.anton.translator.translate.view.TranslateView;
import com.titmouse.anton.translator.oth.Translate;
import com.titmouse.anton.translator.translate.model.TranslateModelImpl;


import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by anton on 11.04.17.
 */

public class TranslatePresenterImpl extends MvpBasePresenter<TranslateView> implements TranslatePresenter {
    private final TranslateModelImpl model;
    private final Context context;

    private Subscription translationTextChanges = Subscriptions.empty();
    private final int ONE_SECOND_MILLIS = 1000;
    private final int DELAY_IN_SECONDS = 1;

    public TranslatePresenterImpl(Context context) {
        model = new TranslateModelImpl();
        this.context = context;
    }

    @Override
    public void onTranslateTextChanged(final String text, final String language, final Boolean favorite) {
        prepareSubscription(translationTextChanges);

        translationTextChanges = Observable.interval(ONE_SECOND_MILLIS, TimeUnit.MILLISECONDS)
                .take(DELAY_IN_SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        callModelGetTranslate(text, language, favorite);
                    }
                });


    }

    private void callModelGetTranslate(final String text, final String language, final Boolean favorite) {
        Observable<TranslateFormat> mTranslateObservable = model.getTranslate(text,language);
        mTranslateObservable.subscribe(new Subscriber<TranslateFormat>() {
                                           @Override public void onCompleted() {
                                           }
                                           @Override public void onError(Throwable e) {
                                               Log.e("observable",e.getMessage());
                                               if (isViewAttached()) {
                                                   getView().showError(e.getMessage());
                                               }

                                           }
                                           @Override public void onNext(TranslateFormat translateFormat) {
                                               if (isViewAttached()) {
                                                   getView().showTranslate(new Translate(0L, favorite, translateFormat.getText().get(0), language, text, 0) );
                                                   getView().showNewSaveStatus(TranslateRealmDaoImpl.getInstance().setTranslation(new Translate(0L, favorite, translateFormat.getText().get(0), language, text, 0) ));                                              }
                                           }
                                       }
        );
    }

    @Override
    public void buttonNewSaveStatus(Translate translate) {
        if (isViewAttached()) {
            getView().showNewSaveStatus(model.saveTranslate(translate));
        }
    }

    private void prepareSubscription(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
