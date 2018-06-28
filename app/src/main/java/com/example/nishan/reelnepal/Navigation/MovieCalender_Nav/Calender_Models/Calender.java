package com.example.nishan.reelnepal.Navigation.MovieCalender_Nav.Calender_Models;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class Calender{

	@SerializedName("result")
	private List<ResultItem> result;

	@SerializedName("version")
	private String version;

	@SerializedName("statusCode")
	private int statusCode;

	public void setResult(List<ResultItem> result){
		this.result = result;
	}

	public List<ResultItem> getResult(){
		return result;
	}

	public void setVersion(String version){
		this.version = version;
	}

	public String getVersion(){
		return version;
	}

	public void setStatusCode(int statusCode){
		this.statusCode = statusCode;
	}

	public int getStatusCode(){
		return statusCode;
	}

	@Override
 	public String toString(){
		return 
			"Calender{" + 
			"result = '" + result + '\'' + 
			",version = '" + version + '\'' + 
			",statusCode = '" + statusCode + '\'' + 
			"}";
		}
}