package com.example.nishan.reelnepal.Navigation.MovieCalender_Nav.Calender_Models;


import com.google.gson.annotations.SerializedName;

public class ResultItem{

	@SerializedName("releaseDateBS")
	private String releaseDateBS;

	@SerializedName("releaseMonth")
	private String releaseMonth;

	@SerializedName("releaseDate")
	private String releaseDate;

	@SerializedName("director")
	private String director;

	@SerializedName("name")
	private String name;

	@SerializedName("movieId")
	private int movieId;

	@SerializedName("crews")
	private String crews;

	@SerializedName("releaseMonthId")
	private String releaseMonthId;

	@SerializedName("coverPhoto")
	private String coverPhoto;

	public String getCoverPhoto() {
		return coverPhoto;
	}

	public void setCoverPhoto(String coverPhoto) {
		this.coverPhoto = coverPhoto;
	}

	public void setReleaseDateBS(String releaseDateBS){
		this.releaseDateBS = releaseDateBS;
	}

	public String getReleaseDateBS(){
		return releaseDateBS;
	}

	public void setReleaseMonth(String releaseMonth){
		this.releaseMonth = releaseMonth;
	}

	public String getReleaseMonth(){
		return releaseMonth;
	}

	public void setReleaseDate(String releaseDate){
		this.releaseDate = releaseDate;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public void setDirector(String director){
		this.director = director;
	}

	public String getDirector(){
		return director;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
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

	public void setReleaseMonthId(String releaseMonthId){
		this.releaseMonthId = releaseMonthId;
	}

	public String getReleaseMonthId(){
		return releaseMonthId;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"releaseDateBS = '" + releaseDateBS + '\'' + 
			",releaseMonth = '" + releaseMonth + '\'' + 
			",releaseDate = '" + releaseDate + '\'' + 
			",director = '" + director + '\'' + 
			",name = '" + name + '\'' + 
			",movieId = '" + movieId + '\'' + 
			",crews = '" + crews + '\'' + 
			",releaseMonthId = '" + releaseMonthId + '\'' + 
			"}";
		}
}