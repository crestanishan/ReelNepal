package com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models;

import com.google.gson.annotations.SerializedName;


public class FeaturedMoviesItem{

	@SerializedName("name")
	private String name;

	@SerializedName("coverPhoto")
	private String coverPhoto;

	@SerializedName("movieId")
	private int movieId;

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
			"FeaturedMoviesItem{" + 
			"name = '" + name + '\'' + 
			",coverPhoto = '" + coverPhoto + '\'' + 
			",movieId = '" + movieId + '\'' + 
			"}";
		}
}