package com.titmouse.anton.translator.database.entity;


import com.titmouse.anton.translator.oth.Translate;

import io.realm.RealmObject;

/**
 * Created by anton on 16.04.17.
 */



public class TranslateEntity extends RealmObject {


    private Long id;

    private Boolean favorite;
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
