
package com.titmouse.anton.translator.translate.utils.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TranslateTr {

    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("pos")
    @Expose
    public String pos;
    @SerializedName("gen")
    @Expose
    public String gen;
    @SerializedName("translateSyn")
    @Expose
    public List<TranslateSyn> translateSyn = null;
    @SerializedName("translateMean")
    @Expose
    public List<TranslateMean> translateMean = null;
    @SerializedName("translateEx")
    @Expose
    public List<TranslateEx> translateEx = null;

}
