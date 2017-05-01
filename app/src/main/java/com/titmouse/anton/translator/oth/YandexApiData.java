package com.titmouse.anton.translator.oth;

/**
 * Created by anton on 12.04.17.
 */

public class YandexApiData {
    private static final String TRANSLATOR_API_KEY = "trnsl.1.1.20170411T121549Z.890004e5e922a8cb.c34ab675efd5552b19d944cfedcd7bffe51d8c9c";
    public static final String DICTIONARY_API_KEY = "dict.1.1.20170413T173421Z.0ab46a0fbb362a81.3660e093b48ad864a0ad9d98c387d4c7b49c0a27";

    private static final String TRANSLATOR_URL = "https://translate.yandex.net/";


    public static String getTranslatorApiKey() {
        return TRANSLATOR_API_KEY;
    }

    public static String getTranslatorUrl() {
        return TRANSLATOR_URL;
    }
}
