package com.example.nishan.reelnepal.Navigation.NepaliNews_Nav;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nishan.reelnepal.APIClient.APIClient;
import com.example.nishan.reelnepal.Adapters.RecyclerViewAdapter;
import com.example.nishan.reelnepal.Adapters.TopNewsAdapter;
import com.example.nishan.reelnepal.Interface.ApiInterface;
import com.example.nishan.reelnepal.Movie.MovieProfile2;
import com.example.nishan.reelnepal.Movie.ScreenChanger.ScreenCheck;
import com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models.TopNewsItem;
import com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models.TopVideosItem;
import com.example.nishan.reelnepal.Navigation.NepaliNews_Nav.NepaliNewsModel.CrewTagsItem;
import com.example.nishan.reelnepal.Navigation.NepaliNews_Nav.NepaliNewsModel.MovieTagsItem;
import com.example.nishan.reelnepal.Navigation.NepaliNews_Nav.NepaliNewsModel.NepaliNewsDetails;
import com.example.nishan.reelnepal.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NepaNewsDetails extends Fragment
                            implements MovieTagsAdapter.OnMovieTagsItemClickInterface{

    private static final String TAG = NepaNewsDetails.class.getSimpleName();

    MovieTagsAdapter.OnMovieTagsItemClickInterface onMovieTagsItemClickInterface;

    NepaliNewsDetails nepaliNewsDetails;

    TextView textViewMovieTags;
    TextView textViewCrewTags;

    Date date;

    public static NepaNewsDetails newInstanceFromHome(TopNewsItem topNewsItem) {


//        staticActor = actor;
        Bundle args = new Bundle();
        args.putSerializable("News", topNewsItem);
        NepaNewsDetails fragment = new NepaNewsDetails();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_news_details, container, false);


       TopNewsItem topNewsItem = (TopNewsItem) getArguments().getSerializable("News");

        Log.d(TAG, "Actor Id: "+ topNewsItem.getNewsId());
        int id = topNewsItem.getNewsId();

        //getting news id
        //  Intent intent = getIntent();
        //int id = intent.getIntExtra("ID", 0);

        onMovieTagsItemClickInterface = this;

        ScreenCheck.currentScreen = 2;




        final RecyclerView recyclerViewCrewTags = view.findViewById(R.id.recyclerView_crew_tags);
        recyclerViewCrewTags.setNestedScrollingEnabled(false);
        recyclerViewCrewTags.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        final RecyclerView recyclerViewMovieTags = view.findViewById(R.id.recyclerView_movie_tags);
        recyclerViewMovieTags.setNestedScrollingEnabled(false);
        recyclerViewMovieTags.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

        Call<NepaliNewsDetails> call = apiInterface.findNews(id);
        call.enqueue(new Callback<NepaliNewsDetails>() {
            @Override
            public void onResponse(Call<NepaliNewsDetails> call, Response<NepaliNewsDetails> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body());
                    Log.d(TAG, "onCode: " + response.code());

                    nepaliNewsDetails = response.body();
                    //Toast.makeText(getApplicationContext(), "Output:" + response.body().getResult(), Toast.LENGTH_SHORT).show();

                    TextView textNewsTitle = view.findViewById(R.id.tv_news_details_title);
                    textNewsTitle.setText(nepaliNewsDetails.getResult().getTitle());

                    TextView textNewsContent = view.findViewById(R.id.tv__news_details_refinedContent);
                    textNewsContent.setText(Html.fromHtml((nepaliNewsDetails.getResult().getFullContent())).toString());
                    // Html.fromHtml(data).toString()

                    TextView textNewsPublishedDate = view.findViewById(R.id.tv_news_details_published_date);
                    //textNewsPublishedDate.setText(nepaliNewsDetails.getResult().getPublishedDate());

                    textViewMovieTags = view.findViewById(R.id.textview_movie_tags);
                    textViewCrewTags = view.findViewById(R.id.textview_crew_tags);

                    // convert to date format
                    String dtStart = nepaliNewsDetails.getResult().getPublishedDate();
                    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    SimpleDateFormat output = new SimpleDateFormat("MMM dd, yyyy");
                    try {
                        date = input.parse(dtStart);
                        textNewsPublishedDate.setText(output.format(date));

                        System.out.println(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        Log.d(TAG, "Date format problem:" + e);
                    }


                    //getting list of arrays
                    ArrayList<MovieTagsItem> movieTagsItems = (ArrayList<MovieTagsItem>) ((NepaliNewsDetails) response.body()).getResult().getMovieTags();

                    ArrayList<CrewTagsItem> crewTagsItems = (ArrayList<CrewTagsItem>) ((NepaliNewsDetails) response.body()).getResult().getCrewTags();


                    //set adapter

                    if (crewTagsItems.isEmpty()) {

                        textViewCrewTags.setVisibility(View.GONE);
                    } else {
                        recyclerViewCrewTags.setAdapter(new CrewTagsAdapter(getContext(), crewTagsItems));
                    }


                    if (movieTagsItems.isEmpty()) {

                        textViewMovieTags.setVisibility(View.GONE);
                    } else {
                        recyclerViewMovieTags.setAdapter(new MovieTagsAdapter(getContext(), movieTagsItems, onMovieTagsItemClickInterface));
                    }


                }

            }

            @Override
            public void onFailure(Call<NepaliNewsDetails> call, Throwable t) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();


            }
        });


        return view;

    }

    /* @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_news_details);

        //getting news id
        Intent intent = getIntent();
        int id = intent.getIntExtra("ID", 0);

        onMovieTagsItemClickInterface = this;

        final RecyclerView recyclerViewCrewTags = findViewById(R.id.recyclerView_crew_tags);
        recyclerViewCrewTags.setNestedScrollingEnabled(false);
        recyclerViewCrewTags.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));

        final RecyclerView recyclerViewMovieTags = findViewById(R.id.recyclerView_movie_tags);
        recyclerViewMovieTags.setNestedScrollingEnabled(false);
        recyclerViewMovieTags.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));


        ApiInterface apiInterface = APIClient.getClient().create(ApiInterface.class);

        Call<NepaliNewsDetails> call = apiInterface.findNews(id);
        call.enqueue(new Callback<NepaliNewsDetails>() {
            @Override
            public void onResponse(Call<NepaliNewsDetails> call, Response<NepaliNewsDetails> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body());
                    Log.d(TAG, "onCode: " + response.code());

                   nepaliNewsDetails =  response.body();
                   //Toast.makeText(getApplicationContext(), "Output:" + response.body().getResult(), Toast.LENGTH_SHORT).show();

                    TextView textNewsTitle = findViewById(R.id.tv_news_details_title);
                    textNewsTitle.setText(nepaliNewsDetails.getResult().getTitle());

                    TextView textNewsContent = findViewById(R.id.tv__news_details_refinedContent);
                    textNewsContent.setText(Html.fromHtml((nepaliNewsDetails.getResult().getFullContent())).toString());
                   // Html.fromHtml(data).toString()

                    TextView textNewsPublishedDate = findViewById(R.id.tv_news_details_published_date);
                    //textNewsPublishedDate.setText(nepaliNewsDetails.getResult().getPublishedDate());

                    textViewMovieTags = findViewById(R.id.textview_movie_tags);
                    textViewCrewTags = findViewById(R.id.textview_crew_tags);

                    // convert to date format
                    String dtStart = nepaliNewsDetails.getResult().getPublishedDate();
                    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    SimpleDateFormat output = new SimpleDateFormat("MMM dd, yyyy");
                    try {
                        date = input.parse(dtStart);
                        textNewsPublishedDate.setText(output.format(date));

                        System.out.println(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        Log.d(TAG, "Date format problem:"+e);
                    }


                    //getting list of arrays
                    ArrayList<MovieTagsItem> movieTagsItems = (ArrayList<MovieTagsItem>) ((NepaliNewsDetails) response.body()).getResult().getMovieTags();

                    ArrayList<CrewTagsItem> crewTagsItems = (ArrayList<CrewTagsItem>) ((NepaliNewsDetails) response.body()).getResult().getCrewTags();


                    //set adapter

                    if (crewTagsItems.isEmpty()){

                        textViewCrewTags.setVisibility(View.GONE);
                    }
                    else {
                        recyclerViewCrewTags.setAdapter(new CrewTagsAdapter(getApplicationContext(), crewTagsItems));
                    }


                   if (movieTagsItems.isEmpty()){

                     textViewMovieTags.setVisibility(View.GONE);
                     }
                     else {
                        recyclerViewMovieTags.setAdapter(new MovieTagsAdapter(getApplicationContext(), movieTagsItems, onMovieTagsItemClickInterface));
                    }




                }

                }

            @Override
            public void onFailure(Call<NepaliNewsDetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed",Toast.LENGTH_SHORT).show();


            }
        });

    }*/



    @Override
    public void onItemClick(MovieTagsItem movieTagsItem) {
        Log.d(TAG,"It is clicked");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contain_main, MovieProfile2.newInstanceFromMovieTags(movieTagsItem));
        ft.commit();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Nepali News");

    }



}
