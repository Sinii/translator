package com.titmouse.anton.translator.oth;

public class Translate {
	
	private Long id;
	private Boolean favorite;
	private String translate;
	private String language;
	private String text;
	private int error;
	
	public Translate() {
	
	}
	
	public Translate(final Long id, final Boolean favorite, final String translate, final String language, final String text, final int error) {
		this.id = id;
		this.favorite = favorite;
		this.translate = translate;
		this.language = language;
		this.text = text;
		this.error = error;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void changeSavedStatus() {
		this.favorite = !this.favorite;
	}
	
	public Boolean getFavorite() {
		return favorite;
	}
	
	public void setFavorite(Boolean favorite) {
		this.favorite = favorite;
	}
	
	public String getTranslate() {
		return translate;
	}
	
	public void setTranslate(String translate) {
		this.translate = translate;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public int getError() {
		return error;
	}
	
	public void setError(int error) {
		this.error = error;
	}
}
