
package com.titmouse.anton.translator.translate.utils.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TranslateEx {
	
	@SerializedName("text")
	@Expose
	public String text;
	@SerializedName("translateTr")
	@Expose
	public List<TranslateTr_> translateTr = null;
	
}
