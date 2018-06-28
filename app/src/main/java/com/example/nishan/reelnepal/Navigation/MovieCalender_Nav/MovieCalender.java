package com.example.nishan.reelnepal.Navigation.MovieCalender_Nav;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nishan.reelnepal.Interface.ApiInterface;
import com.example.nishan.reelnepal.Navigation.MovieCalender_Nav.Calender_Models.Calender;
import com.example.nishan.reelnepal.Navigation.MovieCalender_Nav.Calender_Models.GeneralItem;
import com.example.nishan.reelnepal.Navigation.MovieCalender_Nav.Calender_Models.ResultItem;
import com.example.nishan.reelnepal.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class MovieCalender extends Fragment {

    Calender calender;
    ApiInterface apiInterface;

    ArrayList<ResultItem> resultItems;

    List<ListItem> consolidatedList = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private Adapter adapter;






    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_movie_calender, container, false);

        apiInterface = CalenderApiClient.getClient().create(ApiInterface.class);

       // final RecyclerView recycleViewMovieCalender = view.findViewById(R.id.recyclerView_movie_calender);
        //recycleViewMovieCalender.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        mRecyclerView = view.findViewById(R.id.recyclerView_movie_calender);

       // ResultItem resultItem = new ResultItem();
      //  Month = resultItem.getReleaseMonth();


        Call call = apiInterface.findMoviesDate();
        call.enqueue(new Callback<Calender>() {
            @Override
            public void onResponse(Call call, Response response) {

                if (response.isSuccessful() && response.body() != null) {
                    //Log.d(TAG, "onResponse:Server Response: "+ response.toString());
                    Log.d(TAG, "onResponseCalender: " + response.body());
                    Log.d(TAG, "onCode: " + response.code());
                    Log.d(TAG, "Is response success? " + response.isSuccessful());


                    calender = (Calender) response.body();
                    Log.d(TAG, "Output:" + calender.getResult());


                    //  Toast.makeText(getContext(), "Output:" + calender.getResult(), Toast.LENGTH_LONG).show();


                        resultItems = (ArrayList<ResultItem>) ((Calender) response.body()).getResult();
                   // }

                    HashMap<String, List<ResultItem>> groupedHashMap = groupDataIntoHashMap(resultItems);

                    Map<String, List<ResultItem>> sortedGroupedHashMap= new TreeMap<>(groupedHashMap);

                    for (String date : sortedGroupedHashMap.keySet()) {
                        DateItem dateItem = new DateItem();
                        dateItem.setMonth(date);
                        consolidatedList.add(dateItem);


                        for (ResultItem resultItem : sortedGroupedHashMap.get(date)) {
                            GeneralItem generalItem = new GeneralItem();
                            generalItem.setResultItem(resultItem);//setBookingDataTabs(bookingDataTabs);
                            consolidatedList.add(generalItem);
                        }
                    }

                    Log.d(TAG, "groupby value :" + sortedGroupedHashMap);
                    //set adapter
                  // recycleViewMovieCalender.setAdapter(new MovieCalenderAdapter(getContext(),consolidatedList));
                    adapter = new Adapter(getContext(), consolidatedList);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setAdapter(adapter);




                } else {
                    Log.e("unSuccess", new Gson().toJson(response.errorBody()));
                }


            }

            @Override
            public void onFailure(Call call, Throwable t) {

                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();


            }
        });


        return view;

    }

    private HashMap<String,List<ResultItem>> groupDataIntoHashMap(ArrayList<ResultItem> resultItems) {
       HashMap<String, List<ResultItem>> groupedHashMap = new HashMap<>();

        for (ResultItem resultItem : resultItems) {

            String hashMapKey = resultItem.getReleaseMonth();



            if (groupedHashMap.containsKey(hashMapKey)) {
                // The key is already in the HashMap; add the pojo object
                // against the existing key.
               groupedHashMap.get(hashMapKey).add(resultItem);


            } else {
                // The key is not there in the HashMap; create a new key-value pair
                List<ResultItem> list = new ArrayList<>();
                list.add(resultItem);
                groupedHashMap.put(hashMapKey, list);
            }
        }


        return groupedHashMap;

        //List<Map.Entry<String, List<ResultItem>>> list = new LinkedList<Map.Entry<String,List<ResultItem>>>(resultItems.entrySet());

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Movie Calender");
    }
}
