
package com.titmouse.anton.translator.translate.utils.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DictionaryFormat {

    @SerializedName("translateHead")
    @Expose
    public TranslateHead translateHead;
    @SerializedName("dictionaryDef")
    @Expose
    public List<DictionaryDef> dictionaryDef = null;

}
