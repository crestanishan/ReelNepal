package com.example.nishan.reelnepal.Movie;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nishan.reelnepal.APIClient.APIClient;
import com.example.nishan.reelnepal.Facebook.FacebookActivity;
import com.example.nishan.reelnepal.Interface.ApiInterface;
import com.example.nishan.reelnepal.Movie.ScreenChanger.ScreenCheck;
import com.example.nishan.reelnepal.Navigation.NepaliNews_Nav.MovieTagsAdapter;
import com.example.nishan.reelnepal.Navigation.NepaliNews_Nav.NepaliNewsModel.MovieTagsItem;
import com.example.nishan.reelnepal.R;
import com.example.nishan.reelnepal.Search.Actor;
import com.facebook.AccessToken;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieProfile2 extends Fragment {
    private static final String TAG = MovieProfile2.class.getSimpleName();

    MovieProfile movieInfo;

    RatingBar ratingBar1;

    Button buttonSubmit;

    Dialog rankDialog;

    String rateValue;

    int id;

    public static MovieProfile2 newInstanceFromMovieTags(MovieTagsItem movieTagsItem) {


//        staticActor = actor;
        Bundle args = new Bundle();
        args.putSerializable("movieTag", movieTagsItem);
        MovieProfile2 fragment = new MovieProfile2();
        fragment.setArguments(args);
        return fragment;
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_movie_profile, container,false);
        Log.d(TAG, "layout is working: "+ inflater.inflate(R.layout.activity_movie_profile, container,false));

        //  int id = getArguments().getInt("ID");

        // description of events to be populated in this row
        //  actor = (Actor)getArguments().getSerializable("ID");


//            staticActor.getId()




        MovieTagsItem movieTagsItem = (MovieTagsItem)getArguments().getSerializable("movieTag");
        Log.d(TAG, "Movie Id: "+ movieTagsItem.getMovieId());

        id = movieTagsItem.getMovieId();

        // getDataFromFB();


      /*  SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "defaultValue" );
        Toast.makeText(getContext(),"FB Email: "+email, Toast.LENGTH_LONG).show();*/


        ScreenCheck.currentScreen = 3;



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


                    ImageView img = view.findViewById(R.id.movie_image);

                    ratingBar1 = view.findViewById(R.id.ratingBar);

                   /* ratingBar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Toast.makeText(getContext(),"Rating bar is clicked: ", Toast.LENGTH_LONG).show();



                            rankDialog = new Dialog(getContext(), R.style.FullHeightDialog);
                            rankDialog.setContentView(R.layout.layout_rank_dialog);
                            rankDialog.setCancelable(true);

                            String rating=String.valueOf(ratingBar.getRating());
                           // Toast.makeText(getContext(), rating, Toast.LENGTH_LONG).show();
                            textViewDialogContent.setText("You rated "+rating+"for this movie");

                            Button button_Submit = view.findViewById(R.id.rank_dialog_button);
                            button_Submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    rankDialog.dismiss();
                                }
                            });

                            //now that the dialog is set up, it's time to show it
                            rankDialog.show();
                        }
                    });*/



                   ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                       @Override
                       public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                           Toast.makeText(getContext(),"Rating bar is clicked: ", Toast.LENGTH_LONG).show();



                           rankDialog = new Dialog(getContext(), R.style.FullHeightDialog);
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
                           rankDialog.show();


                       }
                   });


                   /* Button button_Submit = view.findViewById(R.id.rank_dialog_button);
                    button_Submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            rankDialog.dismiss();
                        }
                    });*/

                   /* buttonSubmit = view.findViewById(R.id.btn_submit);

                    buttonSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            facebookPost();



                        }
                    });*/



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
