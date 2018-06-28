package com.example.nishan.reelnepal.Navigation.Home_Nav;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.example.nishan.reelnepal.Adapters.FlipperAdapter;
import com.example.nishan.reelnepal.Adapters.NextWeekMoviesAdapter;
import com.example.nishan.reelnepal.Adapters.RecyclerViewAdapter;
import com.example.nishan.reelnepal.Adapters.ThisWeekMoviesAdapter;
import com.example.nishan.reelnepal.Adapters.TopMoviesVideoAdapter;
import com.example.nishan.reelnepal.Adapters.TopNewsAdapter;
import com.example.nishan.reelnepal.Interface.ApiInterface;
import com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models.FeaturedMoviesItem;
import com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models.Front;
import com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models.NextWeekMoviesItem;
import com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models.ThisWeekMoviesItem;
import com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models.TopNewsItem;
import com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models.TopVideosItem;
import com.example.nishan.reelnepal.Navigation.NepaliNews_Nav.NepaNewsDetails;
import com.example.nishan.reelnepal.R;
import com.google.gson.Gson;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class Home extends Fragment
                    implements TopNewsAdapter.OnNewsItemClickInterface {

    Front front;

    TopNewsAdapter.OnNewsItemClickInterface onNewsItemClickInterface;


    ThisWeekMoviesAdapter thisWeekMoviesAdapter;

    // ApiInterface mService;

    ApiInterface apiInterface;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_home, container,false);

        onNewsItemClickInterface = this;
        // sliderLayout = (SliderLayout) view.findViewById(R.id.slider);

       final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

      final RecyclerView recycleViewTopMovies = view.findViewById(R.id.recyclerView_TopMovies);
      recycleViewTopMovies.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        final RecyclerView recycleViewThisWeekMovies = view.findViewById(R.id.recyclerView_ThisWeekMovies);
        recycleViewThisWeekMovies.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        final RecyclerView recycleViewNextWeekMovies = view.findViewById(R.id.recyclerView_NextWeekMovies);
        recycleViewNextWeekMovies.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        final RecyclerView recycleViewTopNews = view.findViewById(R.id.recyclerView_TopNews);
        recycleViewTopNews.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));


        apiInterface = HomeApiClient.getClient().create(ApiInterface.class);



        Call call = apiInterface.findMovies();
        call.enqueue(new Callback<Front>() {
            @Override
            public void onResponse(Call call, Response response) {

                if (response.isSuccessful() && response.body() != null) {
                    //Log.d(TAG, "onResponse:Server Response: "+ response.toString());
                    Log.d(TAG, "onResponse: " + response.body());
                    Log.d(TAG, "onCode: " + response.code());
                    Log.d(TAG, "Is response success? " + response.isSuccessful());


                 front = (Front) response.body();

                    //Toast.makeText(getContext(), "Output:" +front.getResult().getFeaturedMovies(), Toast.LENGTH_LONG).show();

                    //getting list of arrays
                    ArrayList<FeaturedMoviesItem> featuredMoviesItems = (ArrayList<FeaturedMoviesItem>) ((Front) response.body()).getResult().getFeaturedMovies();


                    ArrayList<TopVideosItem> topVideosItems = (ArrayList<TopVideosItem>)((Front) response.body()).getResult().getTopVideos();

                    ArrayList<ThisWeekMoviesItem> thisWeekMoviesItems = (ArrayList<ThisWeekMoviesItem>)((Front) response.body()).getResult().getThisWeekMovies();

                    ArrayList<NextWeekMoviesItem> nextWeekMoviesItems = (ArrayList<NextWeekMoviesItem>)((Front) response.body()).getResult().getNextWeekMovies();

                    ArrayList<TopNewsItem> topNewsItems = (ArrayList<TopNewsItem>)((Front) response.body()).getResult().getTopNews();


                    //creating adapter object
                   // FlipperAdapter adapter = new FlipperAdapter(getContext(), featuredMoviesItems);

                    //adding it to adapterview flipper
                   // adapterViewFlipper.setAdapter(adapter);
                    //adapterViewFlipper.setFlipInterval(1000);
                    //adapterViewFlipper.startFlipping();


                    //set adapter
                    recyclerView.setAdapter(new RecyclerViewAdapter(getContext(),featuredMoviesItems));

                    recycleViewTopMovies.setAdapter(new TopMoviesVideoAdapter(getContext(),topVideosItems));

                    if (thisWeekMoviesItems.isEmpty() ){

                        TextView textViewThisWeek = view.findViewById(R.id.tv_header_this_week);
                        textViewThisWeek.setVisibility(View.GONE);

                    }

                    else {
                        recycleViewThisWeekMovies.setAdapter(new ThisWeekMoviesAdapter(getContext(),thisWeekMoviesItems));
                    }


                    if (thisWeekMoviesItems.isEmpty()){

                        TextView textViewThisWeek = view.findViewById(R.id.tv_header_next_week);
                        textViewThisWeek.setVisibility(View.GONE);


                    }

                    else {

                        recycleViewNextWeekMovies.setAdapter(new NextWeekMoviesAdapter(getContext(), nextWeekMoviesItems));

                    }

                    recycleViewTopNews.setAdapter(new TopNewsAdapter(getContext(),topNewsItems,onNewsItemClickInterface ));





                }

                else {
                    Log.e("unSuccess", new Gson().toJson(response.errorBody()));
                }


            }

            @Override
            public void onFailure(Call call, Throwable t) {

                Toast.makeText(getContext(), "Failed",Toast.LENGTH_SHORT).show();


            }
        });



        return view;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Home");

}


    @Override
    public void onItemClick(TopNewsItem topNewsItem) {

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.replace(R.id.contain_main, NepaNewsDetails.newInstanceFromHome(topNewsItem));
        ft.commit();

    }
}
