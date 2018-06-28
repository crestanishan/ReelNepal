package com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models;
import com.google.gson.annotations.SerializedName;


public class TopVideosItem{

	@SerializedName("displayCategory")
	private String displayCategory;

	@SerializedName("youTubeId")
	private String youTubeId;

	@SerializedName("videoId")
	private int videoId;

	@SerializedName("publishedDate")
	private String publishedDate;

	@SerializedName("title")
	private String title;

	public void setDisplayCategory(String displayCategory){
		this.displayCategory = displayCategory;
	}

	public String getDisplayCategory(){
		return displayCategory;
	}

	public void setYouTubeId(String youTubeId){
		this.youTubeId = youTubeId;
	}

	public String getYouTubeId(){
		return youTubeId;
	}

	public void setVideoId(int videoId){
		this.videoId = videoId;
	}

	public int getVideoId(){
		return videoId;
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

	@Override
 	public String toString(){
		return 
			"TopVideosItem{" + 
			"displayCategory = '" + displayCategory + '\'' + 
			",youTubeId = '" + youTubeId + '\'' + 
			",videoId = '" + videoId + '\'' + 
			",publishedDate = '" + publishedDate + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}