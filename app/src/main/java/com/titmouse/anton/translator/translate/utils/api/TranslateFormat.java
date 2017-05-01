package com.titmouse.anton.translator.translate.utils.api;

import java.util.List;

/**
 * Created by anton on 12.04.17.
 */

public class TranslateFormat {
    private int code;
    private String lang;
   // private String message;
    private List<String> text;



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }
}
