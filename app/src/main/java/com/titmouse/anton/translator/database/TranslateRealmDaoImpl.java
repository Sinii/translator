package com.titmouse.anton.translator.database;

import com.titmouse.anton.translator.database.entity.TranslateEntity;
import com.titmouse.anton.translator.oth.Translate;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.Sort;


public class TranslateRealmDaoImpl implements TranslateRealmDao {
	
	private static class SingletonHolder {
		
		static final TranslateRealmDaoImpl HOLDER_INSTANCE = new TranslateRealmDaoImpl();
		
	}
	
	public static TranslateRealmDaoImpl getInstance() {
		return SingletonHolder.HOLDER_INSTANCE;
	}
	
	@Override
	public Boolean setTranslation(final Translate translate) {
		final Realm realm = Realm.getDefaultInstance();
		Boolean mElementAlreadyExist = false;
		Boolean mFavoriteElement = false;
		
		
		Long mRealmLength = 1L;
		for (final TranslateEntity translateEntity : realm.where(TranslateEntity.class).findAll()) {
			mRealmLength++;
			if (translateEntity.getText().equals(translate.getText()) && translateEntity.getLanguage().equals(translate.getLanguage())) {
				mElementAlreadyExist = true;
				mFavoriteElement = translateEntity.isFavorite();
			}
		}
		
		
		if (!mElementAlreadyExist) {
			final Long nextId = mRealmLength;
			realm.executeTransaction(new Realm.Transaction() {
				
				@Override
				public void execute(Realm realm) {
					//  realm.insertOrUpdate((RealmModel) translate);
					final TranslateEntity mTranslateEntity = realm.createObject(TranslateEntity.class);
					mTranslateEntity.setId(nextId);
					mTranslateEntity.setFavorite(translate.getFavorite());
					mTranslateEntity.setError(translate.getError());
					mTranslateEntity.setTranslate(translate.getTranslate());
					mTranslateEntity.setText(translate.getText());
					mTranslateEntity.setLanguage(translate.getLanguage());
				}
			});
		}
		
		realm.close();
		return mFavoriteElement;
	}
	
	@Override
	public List<Translate> getTranslations() {
		final Realm realm = Realm.getDefaultInstance();
		
		final List<Translate> translateList = new ArrayList<Translate>();
		
		for (final TranslateEntity translateEntity : realm.where(TranslateEntity.class).findAll().sort("id", Sort.DESCENDING)) {
			translateList.add(new Translate(translateEntity.getId(), translateEntity.isFavorite(), translateEntity.getTranslate(), translateEntity.getLanguage(), translateEntity.getText(), translateEntity.getError()));
		}
		
		realm.close();
		return translateList;
	}
	
	@Override
	public List<Translate> deleteTranslation(final Translate translate) {
		final Realm realm = Realm.getDefaultInstance();
		//Log.d("deleteTrans fav is ", Boolean.toString(translate.isFavorite()));
		
		final List<Translate> translateList = new ArrayList<Translate>();
		for (final TranslateEntity translateEntity : realm.where(TranslateEntity.class).findAll().sort("id", Sort.DESCENDING)) {
			if (translateEntity.getId().equals(translate.getId())) {
				realm.executeTransaction(new Realm.Transaction() {
					
					@Override
					public void execute(Realm realm) {
						translateEntity.deleteFromRealm();
					}
				});
			} else {
				translateList.add(new Translate(translateEntity.getId(), translateEntity.isFavorite(), translateEntity.getTranslate(), translateEntity.getLanguage(), translateEntity.getText(), translateEntity.getError()));
			}
		}
		
		realm.close();
		return translateList;
	}
	
	@Override
	public List<Translate> getFavorites() {
		final Realm realm = Realm.getDefaultInstance();
		
		final List<Translate> translateList = new ArrayList<Translate>();
		
		for (final TranslateEntity translateEntity : realm.where(TranslateEntity.class).findAll().sort("id", Sort.DESCENDING)) {
			
			if (translateEntity.isFavorite()) {
				translateList.add(new Translate(translateEntity.getId(), translateEntity.isFavorite(), translateEntity.getTranslate(), translateEntity.getLanguage(), translateEntity.getText(), translateEntity.getError()));
				// Log.d("isFavorite  ", String.valueOf(translateEntity.getId())+" "+String.valueOf(translateEntity.getText()));
				
			}
		}
		
		realm.close();
		return translateList;
	}
	
	@Override
	public void changeFavorite(final Translate translate) {
		Boolean favoriteExist = false;
		final Realm realm = Realm.getDefaultInstance();
		
		long mRealmLength = 1L;
		
		
		for (final TranslateEntity translateEntity : realm.where(TranslateEntity.class).findAll().sort("id", Sort.DESCENDING)) {
			
			if (translateEntity.getText().equals(translate.getText()) && translateEntity.getLanguage().equals(translate.getLanguage())) {
				favoriteExist = true;
				mRealmLength++;
				realm.executeTransaction(new Realm.Transaction() {
					
					@Override
					public void execute(Realm realm) {
						translateEntity.setFavorite(!translateEntity.isFavorite());
						//   Log.d("changeFavorite  ", String.valueOf(translateEntity.getId())+" "+String.valueOf(translateEntity.getText()));
						
					}
				});
				
			}
		}
		
		if (!favoriteExist) {
			final Long nextId = mRealmLength;
			realm.executeTransaction(new Realm.Transaction() {
				
				@Override
				public void execute(Realm realm) {
					
					final TranslateEntity mTranslateEntity = realm.createObject(TranslateEntity.class);
					mTranslateEntity.setId(nextId);
					mTranslateEntity.setFavorite(translate.getFavorite());
					mTranslateEntity.setError(translate.getError());
					mTranslateEntity.setTranslate(translate.getTranslate());
					mTranslateEntity.setText(translate.getText());
					mTranslateEntity.setLanguage(translate.getLanguage());
					
					realm.insert(mTranslateEntity);
				}
			});
		}
		
		
		realm.close();
	}
	
	@Override
	public void deleteAllTranslations() {
		final Realm realm = Realm.getDefaultInstance();
		realm.executeTransaction(new Realm.Transaction() {
			
			@Override
			public void execute(Realm realm) {
				realm.deleteAll();
			}
		});
		realm.close();
	}
	
	
}
