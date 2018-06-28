package com.example.nishan.reelnepal.Navigation.NepaliNews_Nav.NepaliNewsModel;


import com.google.gson.annotations.SerializedName;


public class CrewTagsItem{

	@SerializedName("newsId")
	private int newsId;

	@SerializedName("profilePhoto")
	private String profilePhoto;

	@SerializedName("urlTitle")
	private String urlTitle;

	@SerializedName("name")
	private String name;

	@SerializedName("crewId")
	private int crewId;

	public void setNewsId(int newsId){
		this.newsId = newsId;
	}

	public int getNewsId(){
		return newsId;
	}

	public void setProfilePhoto(String profilePhoto){
		this.profilePhoto = profilePhoto;
	}

	public String getProfilePhoto(){
		return profilePhoto;
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

	public void setCrewId(int crewId){
		this.crewId = crewId;
	}

	public int getCrewId(){
		return crewId;
	}

	@Override
 	public String toString(){
		return 
			"CrewTagsItem{" + 
			"newsId = '" + newsId + '\'' + 
			",profilePhoto = '" + profilePhoto + '\'' + 
			",urlTitle = '" + urlTitle + '\'' + 
			",name = '" + name + '\'' + 
			",crewId = '" + crewId + '\'' + 
			"}";
		}
}