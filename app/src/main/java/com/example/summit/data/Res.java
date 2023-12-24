package com.example.summit.data;

import com.google.gson.annotations.SerializedName;

public class Res{

	@SerializedName("summary_text")
	private String summaryText;

	public String getSummaryText(){
		return summaryText;
	}
}