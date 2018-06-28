package com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Result{

	@SerializedName("topNews")
	private List<TopNewsItem> topNews;

	@SerializedName("thisWeekMovies")
	private List<ThisWeekMoviesItem> thisWeekMovies;

	@SerializedName("featuredMovies")
	private List<FeaturedMoviesItem> featuredMovies;

	@SerializedName("nextWeekMovies")
	private List<NextWeekMoviesItem> nextWeekMovies;

	@SerializedName("topVideos")
	private List<TopVideosItem> topVideos;

	public void setTopNews(List<TopNewsItem> topNews){
		this.topNews = topNews;
	}

	public List<TopNewsItem> getTopNews(){
		return topNews;
	}

	public void setThisWeekMovies(List<ThisWeekMoviesItem> thisWeekMovies){
		this.thisWeekMovies = thisWeekMovies;
	}

	public List<ThisWeekMoviesItem> getThisWeekMovies(){
		return thisWeekMovies;
	}

	public void setFeaturedMovies(List<FeaturedMoviesItem> featuredMovies){
		this.featuredMovies = featuredMovies;
	}

	public List<FeaturedMoviesItem> getFeaturedMovies(){
		return featuredMovies;
	}

	public void setNextWeekMovies(List<NextWeekMoviesItem> nextWeekMovies){
		this.nextWeekMovies = nextWeekMovies;
	}

	public List<NextWeekMoviesItem> getNextWeekMovies(){
		return nextWeekMovies;
	}

	public void setTopVideos(List<TopVideosItem> topVideos){
		this.topVideos = topVideos;
	}

	public List<TopVideosItem> getTopVideos(){
		return topVideos;
	}

	@Override
 	public String toString(){
		return 
			"Result{" + 
			"topNews = '" + topNews + '\'' + 
			",thisWeekMovies = '" + thisWeekMovies + '\'' + 
			",featuredMovies = '" + featuredMovies + '\'' + 
			",nextWeekMovies = '" + nextWeekMovies + '\'' + 
			",topVideos = '" + topVideos + '\'' + 
			"}";
		}
}