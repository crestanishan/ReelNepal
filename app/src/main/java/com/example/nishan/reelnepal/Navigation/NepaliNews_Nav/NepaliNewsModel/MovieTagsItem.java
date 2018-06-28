package com.example.nishan.reelnepal.Navigation.NepaliNews_Nav.NepaliNewsModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MovieTagsItem implements Serializable{

	@SerializedName("newsId")
	private int newsId;

	@SerializedName("urlTitle")
	private String urlTitle;

	@SerializedName("name")
	private String name;

	@SerializedName("coverPhoto")
	private String coverPhoto;

	@SerializedName("movieId")
	private int movieId;

	public void setNewsId(int newsId){
		this.newsId = newsId;
	}

	public int getNewsId(){
		return newsId;
	}

	public void setUrlTitle(String urlTitle){
		this.urlTitle = urlTitle;
	}

	public String getUrlTitle(){
		return urlTitle;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCoverPhoto(String coverPhoto){
		this.coverPhoto = coverPhoto;
	}

	public String getCoverPhoto(){
		return coverPhoto;
	}

	public void setMovieId(int movieId){
		this.movieId = movieId;
	}

	public int getMovieId(){
		return movieId;
	}

	@Override
 	public String toString(){
		return 
			"MovieTagsItem{" + 
			"newsId = '" + newsId + '\'' + 
			",urlTitle = '" + urlTitle + '\'' + 
			",name = '" + name + '\'' + 
			",coverPhoto = '" + coverPhoto + '\'' + 
			",movieId = '" + movieId + '\'' + 
			"}";
		}
}