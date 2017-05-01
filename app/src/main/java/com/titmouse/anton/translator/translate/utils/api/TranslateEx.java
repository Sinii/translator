
package com.titmouse.anton.translator.translate.utils.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TranslateEx {

    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("translateTr")
    @Expose
    public List<TranslateTr_> translateTr = null;

}
