package com.example.nishan.reelnepal.Movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

;

public class Result{

	@SerializedName("movieStatusId")
	@Expose
	private int movieStatusId;

	@SerializedName("releaseDate")
	@Expose
	private String releaseDate;

	@SerializedName("censorCertificate")
	@Expose
	private String censorCertificate;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("coverPhoto")
	@Expose
	private String coverPhoto;

	@SerializedName("movieId")

	private int id;

	@SerializedName("runTime")

	private int runTime;

	@SerializedName("synopsis")

	private String synopsis;

	@SerializedName("nameNepali")

	private String nameNepali;

	public void setMovieStatusId(int movieStatusId){
		this.movieStatusId = movieStatusId;
	}

	public int getMovieStatusId(){
		return movieStatusId;
	}

	public void setReleaseDate(String releaseDate){
		this.releaseDate = releaseDate;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public void setCensorCertificate(String censorCertificate){
		this.censorCertificate = censorCertificate;
	}

	public String getCensorCertificate(){
		return censorCertificate;
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

	public void setMovieId(int id){
		this.id = id;
	}

	public int getMovieId(){
		return id;
	}

	public void setRunTime(int runTime){
		this.runTime = runTime;
	}

	public int getRunTime(){
		return runTime;
	}

	public void setSynopsis(String synopsis){
		this.synopsis = synopsis;
	}

	public String getSynopsis(){
		return synopsis;
	}

	public void setNameNepali(String nameNepali){
		this.nameNepali = nameNepali;
	}

	public String getNameNepali(){
		return nameNepali;
	}
}