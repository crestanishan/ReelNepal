package com.example.nishan.reelnepal.Movie;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient2 {

    public static final String BASE_URL = "https://api.reelnepal.com/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {

       // HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
       // interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    //.client(client)
                    .build();
        }
        return retrofit;
    }

}

//API REquest
//https://api.themoviedb.org/3/movie/550?api_key=40fba6f1f59c581d279fa98ed4520438


