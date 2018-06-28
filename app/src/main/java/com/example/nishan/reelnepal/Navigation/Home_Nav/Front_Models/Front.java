package com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models;


import com.google.gson.annotations.SerializedName;

public class Front{

	@SerializedName("result")
	private Result result;

	@SerializedName("version")
	private String version;

	@SerializedName("statusCode")
	private int statusCode;

	public void setResult(Result result){
		this.result = result;
	}

	public Result getResult(){
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
			"Front{" + 
			"result = '" + result + '\'' + 
			",version = '" + version + '\'' + 
			",statusCode = '" + statusCode + '\'' + 
			"}";
		}
}