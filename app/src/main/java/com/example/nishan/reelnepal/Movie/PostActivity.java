package com.example.nishan.reelnepal.Movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nishan.reelnepal.APIClient.APIClient;
import com.example.nishan.reelnepal.Interface.ApiInterface;
import com.example.nishan.reelnepal.Movie.MovieRating.Model.MovieRatingInsertModel;
import com.example.nishan.reelnepal.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    private static final String TAG = PostActivity.class.getSimpleName();

    int movieID;

    String fbID;

    String rating;

    ApiInterface apiInterface;


    private TextView mResponseTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        fbID= getIntent().getStringExtra("FbID");
        Toast.makeText(getApplicationContext(),"FB ID pass : "+fbID, Toast.LENGTH_LONG).show();


        movieID = getIntent().getIntExtra("movieId",0);
        Toast.makeText(getApplicationContext(),"FB movieID pass : "+movieID, Toast.LENGTH_LONG).show();

        rating = getIntent().getStringExtra("rating");
        Toast.makeText(getApplicationContext(),"FB rating pass : "+rating, Toast.LENGTH_LONG).show();

        mResponseTv =  findViewById(R.id.tv_response);

        apiInterface= APIClient.getClient().create(ApiInterface.class);


        // sendPost(fbID, movieID, rating);

        Call<MovieRatingInsertModel> call = apiInterface.savePost(fbID, movieID, rating);
        call.enqueue(new Callback<MovieRatingInsertModel>() {
            @Override
            public void onResponse(Call<MovieRatingInsertModel> call, Response<MovieRatingInsertModel> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body());
                    Log.d(TAG, "onCode: " + response.code());

                    response.body().setUserName(fbID);
                    response.body().setMovieId(movieID);
                    response.body().setRating(rating);


                    //  movieRatingInsertModel = response.body();
                    //Toast.makeText(getApplicationContext(),"POst data :"+movieRatingInsertModel,Toast.LENGTH_LONG).show();

                    showResponse(response.body().toString());
                    Log.i(TAG, "post submitted to API." + response.body().toString());

                }


            }

            @Override
            public void onFailure(Call<MovieRatingInsertModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed",Toast.LENGTH_SHORT).show();

            }
        });


    }



    private void showResponse(String s) {

        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(s);
    }

}

