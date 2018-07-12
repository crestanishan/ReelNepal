package com.example.nishan.reelnepal.Movie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nishan.reelnepal.APIClient.APIClient;
import com.example.nishan.reelnepal.Actor.ActorProfileActivity;
import com.example.nishan.reelnepal.Facebook.FacebookActivity;
import com.example.nishan.reelnepal.Interface.ApiInterface;
import com.example.nishan.reelnepal.Movie.Casts.CastsAdapter;
import com.example.nishan.reelnepal.Movie.Casts.MovieCasts;
import com.example.nishan.reelnepal.Movie.Genres.MovieGenres;
import com.example.nishan.reelnepal.Movie.Genres.ResultItem;
import com.example.nishan.reelnepal.Movie.MovieModels.Cast;
import com.example.nishan.reelnepal.Movie.MovieModels.Director;
import com.example.nishan.reelnepal.Movie.MovieModels.MainCast;
import com.example.nishan.reelnepal.Movie.MovieModels.MovieProfile;
import com.example.nishan.reelnepal.Movie.MovieModels.Writer;
import com.example.nishan.reelnepal.R;
import com.example.nishan.reelnepal.Search.Actor;
import com.facebook.AccessToken;
import com.squareup.picasso.Picasso;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieProfileActivity extends Fragment {

    private static final String TAG = MovieProfileActivity.class.getSimpleName();

    MovieProfile movieInfo;

    MovieGenres movieGenres;

    MovieCasts movieCasts;

    List<ResultItem> result = new ArrayList<>();

    List <com.example.nishan.reelnepal.Movie.Casts.ResultItem> cast = new ArrayList<>();

    List <MainCast> mainCastList = new ArrayList<>();

    List <Director> directorList = new ArrayList<>();

    List <Writer> writerList = new ArrayList<>();

    String rateValue;

    int id;

    RatingBar ratingBar1;




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
        id = actor.getId();

       // Toast.makeText(getContext(),"Movie Id: "+id,Toast.LENGTH_LONG).show();



        final RecyclerView recyclerViewCast = view.findViewById(R.id.recyclerView_cast);
        recyclerViewCast.setNestedScrollingEnabled(false);
        recyclerViewCast.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));






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
                    Toast.makeText(getContext(), "Output:" + movieInfo.getResult().getName(), Toast.LENGTH_LONG).show();

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

                    TextView textViewCertificate = view.findViewById(R.id.tv_Certificatet);
                    textViewCertificate.setText(movieInfo.getResult().getCensorCertificate());

                    mainCastList = movieInfo.getResult().getMainCasts();

                    directorList = movieInfo.getResult().getDirectors();

                    writerList = movieInfo.getResult().getWriters();

                    //for mainCast of movie
                    String mCast = "";


                    for (int i = 0; i < mainCastList.size(); i++) {
                        //Append all the values to a string
                        mCast += mainCastList.get(i).getCrewName();
                        if (i != mainCastList.size()-1){
                            mCast+= ","+" ";
                        }

                        //Toast.makeText(getContext(),"Access array data: "+output,Toast.LENGTH_LONG).show();
                    }



                    TextView textViewMainCast = view.findViewById(R.id.tv_MainCast);
                    textViewMainCast.setText(mCast);


                    // for movie director
                    String director = "";


                    for (int i = 0; i < directorList.size(); i++) {
                        //Append all the values to a string
                        director = directorList.get(i).getCrewName();
                    }



                    TextView textViewDirector = view.findViewById(R.id.tv_Director);
                    textViewDirector.setText(director);

                    // for movie writer
                    String writer = "";


                    for (int i = 0; i < writerList.size(); i++) {
                        //Append all the values to a string
                       writer =writerList.get(i).getCrewName();
                    }



                    TextView textViewWriter = view.findViewById(R.id.tv_Writer);
                    textViewWriter.setText(writer);

                    //getting list of arrays
                    ArrayList<Cast> castItems = (ArrayList<Cast>) ((MovieProfile) response.body()).getResult().getCasts();

                    //set adapter
                    recyclerViewCast.setAdapter(new CastsAdapter(getContext(), castItems));



                    ImageView img = view.findViewById(R.id.movie_image);

                    ratingBar1 = view.findViewById(R.id.ratingBar);



                    ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                        @Override
                        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                            Toast.makeText(getContext(),"Rating bar is clicked: ", Toast.LENGTH_LONG).show();

                            // Intent intent = new Intent(getContext(), TestActivity.class);
                            //getContext().startActivity(intent);

                            rateValue = String.valueOf(ratingBar1.getRating());

                            new LovelyStandardDialog(getContext())
                                    .setTopColorRes(R.color.colorPrimaryDark)
                                    .setIcon(R.drawable.ic_star_icon)
                                    .setMessage("You rated "+rateValue+" for this movie")
                                    .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            // Toast.makeText(getApplicationContext(),"It is clicked",Toast.LENGTH_LONG).show();
                                            facebookPost();
                                        }
                                    })

                                    .setNegativeButton(android.R.string.no, null)
                                    .show();
                        }

                          /* rankDialog = new Dialog(getContext(), R.style.FullHeightDialog);
                           rankDialog.setContentView(R.layout.layout_rank_dialog);
                           rankDialog.setCancelable(true);

                           rateValue = String.valueOf(ratingBar1.getRating());
                           Log.d(TAG,"Rating:"+rateValue);

                          // Toast.makeText(getContext(),"Rating: "+rateValue,Toast.LENGTH_LONG).show();

                            TextView textViewDialogContent = rankDialog.findViewById(R.id.tv_dialog_Content);
                            textViewDialogContent.setText("You rated "+rateValue+" for this movie");
                          // myTextView.setText(String.format("%2.1f", rating));

                            Button button_Submit = rankDialog.findViewById(R.id.rank_dialog_button);
                            button_Submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            facebookPost();
                            rankDialog.dismiss();
                        }
                    });


                           //now that the dialog is set up, it's time to show it
                           rankDialog.show();*/


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


        //retrofit call for movie genres
        Toast.makeText(getContext(),"Checking for genres",Toast.LENGTH_LONG).show();

        ApiInterface apiInterface2= APIClient.getClient().create(ApiInterface.class);

        Call<MovieGenres> call2 = apiInterface2.findGenres(id);
        call2.enqueue(new Callback<MovieGenres>() {

            @Override
            public void onResponse(Call<MovieGenres> call, Response<MovieGenres> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body());
                    Log.d(TAG, "onCode: " + response.code());

                    movieGenres = response.body();

                    // Toast.makeText(getContext(),"Movie Genres: "+movieGenres,Toast.LENGTH_LONG).show();

                    result = movieGenres.getResult();

                    String output = "";


                    for (int i = 0; i < result.size(); i++) {
                        //Append all the values to a string
                        output += result.get(i).getGenreName();
                        if (i != result.size()-1){
                            output+= ","+" ";
                        }


                        Log.d(TAG, ":Genere Check: "+output);



                        //Toast.makeText(getContext(),"Access array data: "+output,Toast.LENGTH_LONG).show();
                    }



                    //Set the textview to the output string

                    TextView textViewGenres = view.findViewById(R.id.tv_Genre);
                    textViewGenres.setText(output);


                }


            }




            @Override
            public void onFailure(Call<MovieGenres> call, Throwable t) {

                Toast.makeText(getContext(), "Failed",Toast.LENGTH_SHORT).show();

            }
        });



        //retrofit call for movieCast

        ApiInterface apiInterface3= APIClient.getClient().create(ApiInterface.class);

        Call<MovieCasts> call3 = apiInterface3.findCast(id);
        call3.enqueue(new Callback<MovieCasts>() {
            @Override
            public void onResponse(Call<MovieCasts> call, Response<MovieCasts> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body());
                    Log.d(TAG, "onCode: " + response.code());

                    movieCasts = response.body();

                    // Toast.makeText(getContext(),"Movie Cast: "+movieCasts, Toast.LENGTH_LONG).show();

                    cast = movieCasts.getResult();

                    String output = "";


                    for (int i = 0; i < cast.size(); i++) {
                        //Append all the values to a string
                        output = cast.get(i).getCrewName();
                        //Toast.makeText(getContext(),"Access array data: "+output,Toast.LENGTH_LONG).show();
                    }




                    TextView textViewCastName = view.findViewById(R.id.textview_movie_cast);
                    textViewCastName.setText("Cast: "+output);
                    textViewCastName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getContext(), ActorProfileActivity.class);
                            getContext().startActivity(i);
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<MovieCasts> call, Throwable t) {

                Toast.makeText(getContext(), "Failed",Toast.LENGTH_SHORT).show();

            }
        });






        return view;
    }

    private void getDataFromFB() {

        String strtext = getArguments().getString("email");
        Toast.makeText(getContext(),"FB Email: "+strtext, Toast.LENGTH_LONG).show();

    }

    private void facebookPost() {
        //check login
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken == null) {

            Log.d(TAG, "FB login Status" + "Signed Out");

            String rating=String.valueOf(ratingBar1.getRating());

            Intent i = new Intent(getContext(),FacebookActivity.class);
            i.putExtra("movieId",id);
            i.putExtra("rating",rating);
            getContext().startActivity(i);

        } else {

            Log.d(TAG, "FB login Status" + "Signed In");

            String rating=String.valueOf(ratingBar1.getRating());
            Toast.makeText(getContext(), rating, Toast.LENGTH_LONG).show();

            Intent i = new Intent(getContext(),FacebookActivity.class);
            i.putExtra("movieId",id);
            i.putExtra("rating",rating);
            getContext().startActivity(i);

        }
    }





    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Movie Profile");

    }


}
