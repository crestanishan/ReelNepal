package com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models;


import com.google.gson.annotations.SerializedName;

public class NextWeekMoviesItem{

	@SerializedName("director")
	private String director;

	@SerializedName("coverPhoto")
	private String coverPhoto;

	@SerializedName("movieId")
	private int movieId;

	@SerializedName("crews")
	private String crews;

	@SerializedName("movieName")
	private String movieName;

	public void setDirector(String director){
		this.director = director;
	}

	public String getDirector(){
		return director;
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

	public void setCrews(String crews){
		this.crews = crews;
	}

	public String getCrews(){
		return crews;
	}

	public void setMovieName(String movieName){
		this.movieName = movieName;
	}

	public String getMovieName(){
		return movieName;
	}

	@Override
 	public String toString(){
		return 
			"NextWeekMoviesItem{" + 
			"director = '" + director + '\'' + 
			",coverPhoto = '" + coverPhoto + '\'' + 
			",movieId = '" + movieId + '\'' + 
			",crews = '" + crews + '\'' + 
			",movieName = '" + movieName + '\'' + 
			"}";
		}
}