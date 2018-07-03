package com.example.nishan.reelnepal.Interface;




import com.example.nishan.reelnepal.Movie.MovieProfile;
import com.example.nishan.reelnepal.Movie.MovieRating.Model.MovieRatingInsertModel;
import com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models.Front;
import com.example.nishan.reelnepal.Navigation.MovieCalender_Nav.Calender_Models.Calender;
import com.example.nishan.reelnepal.Navigation.NepaliNews_Nav.NepaliNewsModel.NepaliNewsDetails;
import com.example.nishan.reelnepal.Search.Actor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api/utilitiesapi/getsearch")
    Call<List<Actor>> queryActor(@Query("q") String q);

   @GET("/api/movie/{id}")
    Call<MovieProfile> findMovieInfo(@Path("id") int id);

  // @GET("api/movies/featured")
  // Observable<List<Response>> getMovieList();

    @GET("/api/front")
    Call<Front>findMovies();

   @GET("/api/movies/calendar")
    Call<Calender>findMoviesDate();

    @GET("/api/news/{id}")
    Call<NepaliNewsDetails> findNews(@Path("id") int id);

    @POST("/api/movie/rating")
    @FormUrlEncoded
    Call<MovieRatingInsertModel> savePost(@Field("UserName") String UserName,
                                          @Field("MovieId")int MovieId,
                                          @Field("Rating") String Rating);





}





