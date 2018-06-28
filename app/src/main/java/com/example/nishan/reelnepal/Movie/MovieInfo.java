package com.example.nishan.reelnepal.Movie;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.nishan.reelnepal.APIClient.APIClient;
import com.example.nishan.reelnepal.Interface.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class MovieInfo {
    private int ID;


    MovieProfile movieInfo;

    public MovieInfo(int ID) {
        this.ID = ID;

    }

    public int getIDFor(int id) {

        return ID;
    }

}
