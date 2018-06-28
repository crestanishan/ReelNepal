package com.example.nishan.reelnepal.Movie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nishan.reelnepal.APIClient.APIClient;
import com.example.nishan.reelnepal.Facebook.FacebookActivity;
import com.example.nishan.reelnepal.Interface.ApiInterface;
import com.example.nishan.reelnepal.Navigation.NepaliNews_Nav.NepaliNewsModel.MovieTagsItem;
import com.example.nishan.reelnepal.R;
import com.example.nishan.reelnepal.Search.Actor;
import com.facebook.AccessToken;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieProfileActivity extends Fragment {

    private static final String TAG = MovieProfileActivity.class.getSimpleName();

    MovieProfile movieInfo;

    RatingBar ratingBar;

    Button buttonSubmit;

    FacebookActivity facebookActivity;


    public static MovieProfileActivity newInstance(Actor actor) {


//        staticActor = actor;
        Bundle args = new Bundle();
        args.putSerializable("actor", actor);
        MovieProfileActivity fragment = new MovieProfileActivity();
        fragment.setArguments(args);
        return fragment;
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_movie_profile, container,false);
        Log.d(TAG, "layout from search to profile is working: "+ inflater.inflate(R.layout.activity_movie_profile, container,false));


        //  int id = getArguments().getInt("ID");

        // description of events to be populated in this row
      //  actor = (Actor)getArguments().getSerializable("ID");


//            staticActor.getId()

        Actor actor = (Actor) getArguments().getSerializable("actor");

        Log.d(TAG, "Actor Id: "+ actor.getId());
        int id = actor.getId();











        ApiInterface apiInterface= APIClient.getClient().create(ApiInterface.class);



        Call<MovieProfile> call = apiInterface.findMovieInfo(id); // findMovieByID(),   findMovieByTag
        call.enqueue(new Callback<MovieProfile>() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body());
                    Log.d(TAG, "onCode: " + response.code());




                    movieInfo = (MovieProfile) response.body();
                    // Toast.makeText(getApplicationContext(), "Output:" + response.body(), Toast.LENGTH_SHORT).show();
                    // Toast.makeText(getApplicationContext(), "Output:" + movieInfo.getResult().getName(), Toast.LENGTH_LONG).show();

                    TextView textMovieName = view.findViewById(R.id.tv_Movie_Name);
                    textMovieName.setText(movieInfo.getResult().getName());

                    TextView textNameNepali = view.findViewById(R.id.tv_Name_Nepali);
                    textNameNepali.setText("("+movieInfo.getResult().getNameNepali() +")");


                    TextView textRunTime = view.findViewById(R.id.tv_run_time);
                    textRunTime.setText(Integer.toString(movieInfo.getResult().getRunTime())+"mins");


                    TextView textReleasedDate= view.findViewById(R.id.tv_released_date);
                    textReleasedDate.setText(movieInfo.getResult().getReleaseDate());

                    TextView textViewSynopsis = view.findViewById(R.id.tv_synopsis);
                    textViewSynopsis.setText(movieInfo.getResult().getSynopsis());

                    ImageView img = view.findViewById(R.id.movie_image);

                    ratingBar = view.findViewById(R.id.ratingBar);

                    buttonSubmit = view.findViewById(R.id.btn_submit);

                    /*ratingBar.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                           new FacebookActivity();
                           return true;
                        }
                    });*/

                    buttonSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            facebookPost();

                        }
                    });

                    if(movieInfo.getResult().getCoverPhoto() !=null && movieInfo.getResult().getCoverPhoto().length()>0)
                    {
                        if (movieInfo.getResult().getCoverPhoto().matches("profile.jpg")){

                            Picasso.with(getContext()).load("https://content.reelnepal.com/photos/movieposters/im214x317/214x317" +movieInfo.getResult().getCoverPhoto()).fit().centerInside().into(img);
                        }
                        else {
                            Picasso.with(getContext()).load("https://content.reelnepal.com/photos/movieposters/im214x317/214x317" +movieInfo.getResult().getCoverPhoto()).fit().centerInside().into(img);
                        }
                    }



                }else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getContext(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    }

                    catch (Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getContext(), "Failed",Toast.LENGTH_SHORT).show();

            }
        });





        return view;
    }

    private void facebookPost() {
        //check login
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken == null) {

            Log.d(TAG, "FB login Status" + "Signed Out");

            Intent i = new Intent(getContext(),FacebookActivity.class);
            getContext().startActivity(i);

        } else {

            Log.d(TAG, "FB login Status" + "Signed In");

            String rating=String.valueOf(ratingBar.getRating());
            Toast.makeText(getContext(), rating, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Movie Profile");

    }


}
