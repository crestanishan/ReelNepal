package com.example.nishan.reelnepal.Navigation.NepaliNews_Nav.NepaliNewsModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Result{

	@SerializedName("movieTags")
	private List<MovieTagsItem> movieTags;

	@SerializedName("newsId")
	private int newsId;

	@SerializedName("crewTags")
	private List<CrewTagsItem> crewTags;

	@SerializedName("publishedDate")
	private String publishedDate;

	@SerializedName("title")
	private String title;

	@SerializedName("refinedContent")
	private String refinedContent;

	private String fullContent;

	public String getFullContent() {
		return fullContent;
	}

	public void setFullContent(String fullContent) {
		this.fullContent = fullContent;
	}

	@SerializedName("fullContent")



	public void setMovieTags(List<MovieTagsItem> movieTags){
		this.movieTags = movieTags;
	}

	public List<MovieTagsItem> getMovieTags(){
		return movieTags;
	}

	public void setNewsId(int newsId){
		this.newsId = newsId;
	}

	public int getNewsId(){
		return newsId;
	}

	public void setCrewTags(List<CrewTagsItem> crewTags){
		this.crewTags = crewTags;
	}

	public List<CrewTagsItem> getCrewTags(){
		return crewTags;
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
			"Result{" + 
			"movieTags = '" + movieTags + '\'' + 
			",newsId = '" + newsId + '\'' + 
			",crewTags = '" + crewTags + '\'' + 
			",publishedDate = '" + publishedDate + '\'' + 
			",title = '" + title + '\'' + 
			",refinedContent = '" + refinedContent + '\'' + 
			"}";
		}
}