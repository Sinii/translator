
package com.titmouse.anton.translator.translate.utils.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DictionaryDef {
	
	@SerializedName("text")
	@Expose
	public String text;
	@SerializedName("pos")
	@Expose
	public String pos;
	@SerializedName("ts")
	@Expose
	public String ts;
	@SerializedName("translateTr")
	@Expose
	public List<TranslateTr> translateTr = null;
	
}
