package com.example.nishan.reelnepal.Search;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Actor implements Serializable{

	@SerializedName("urlTitle")
	private String urlTitle;

	@SerializedName("searchThumb")
	private String searchThumb;

	@SerializedName("contentId")
	private int contentId;

	@SerializedName("name")
	private String name;

	@SerializedName("details")
	private String details;

	@SerializedName("id")
	private int id;

	@SerializedName("category")
	private String category;

	public void setUrlTitle(String urlTitle){
		this.urlTitle = urlTitle;
	}

	public String getUrlTitle(){
		return urlTitle;
	}

	public void setSearchThumb(String searchThumb){
		this.searchThumb = searchThumb;
	}

	public String getSearchThumb(){
		return searchThumb;
	}

	public void setContentId(int contentId){
		this.contentId = contentId;
	}

	public int getContentId(){
		return contentId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDetails(String details){
		this.details = details;
	}

	public String getDetails(){
		return details;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	@Override
 	public String toString(){
		return 
			"Actor{" +
			"urlTitle = '" + urlTitle + '\'' + 
			",searchThumb = '" + searchThumb + '\'' + 
			",contentId = '" + contentId + '\'' + 
			",name = '" + name + '\'' + 
			",details = '" + details + '\'' + 
			",id = '" + id + '\'' + 
			",category = '" + category + '\'' + 
			"}";
		}
}