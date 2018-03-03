package com.titmouse.anton.translator.database.entity;


import io.realm.RealmObject;

public class TranslateEntity extends RealmObject {
	
	
	private Long id;
	
	private boolean favorite;
	private String translate;
	private String language;
	private String text;
	private int error;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Boolean isFavorite() {
		return favorite;
	}
	
	public void setFavorite(final boolean favorite) {
		this.favorite = favorite;
	}
	
	public String getTranslate() {
		return translate;
	}
	
	public void setTranslate(final String translate) {
		this.translate = translate;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(final String language) {
		this.language = language;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(final String text) {
		this.text = text;
	}
	
	public int getError() {
		return error;
	}
	
	public void setError(final int error) {
		this.error = error;
	}
}
