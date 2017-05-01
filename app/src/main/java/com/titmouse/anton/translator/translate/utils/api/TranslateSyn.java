
package com.titmouse.anton.translator.translate.utils.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TranslateSyn {

    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("pos")
    @Expose
    public String pos;
    @SerializedName("gen")
    @Expose
    public String gen;

}
