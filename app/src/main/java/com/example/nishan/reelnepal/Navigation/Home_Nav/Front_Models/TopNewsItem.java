package com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class TopNewsItem implements Serializable{

	@SerializedName("newsId")
	private int newsId;

	@SerializedName("publishedDate")
	private String publishedDate;

	@SerializedName("title")
	private String title;

	@SerializedName("refinedContent")
	private String refinedContent;

	public void setNewsId(int newsId){
		this.newsId = newsId;
	}

	public int getNewsId(){
		return newsId;
	}

	public void setPublishedDate(String publishedDate){
		this.publishedDate = publishedDate;
	}

	public String getPublishedDate(){
		return publishedDate;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setRefinedContent(String refinedContent){
		this.refinedContent = refinedContent;
	}

	public String getRefinedContent(){
		return refinedContent;
	}

	@Override
 	public String toString(){
		return 
			"TopNewsItem{" + 
			"newsId = '" + newsId + '\'' + 
			",publishedDate = '" + publishedDate + '\'' + 
			",title = '" + title + '\'' + 
			",refinedContent = '" + refinedContent + '\'' + 
			"}";
		}
}