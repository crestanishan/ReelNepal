package com.example.nishan.reelnepal.Adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models.FeaturedMoviesItem;
import com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models.TopVideosItem;
import com.example.nishan.reelnepal.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecycleViewAdapter";

    private Context mCtx;

    private ArrayList<FeaturedMoviesItem> featuredMoviesItems;


    public RecyclerViewAdapter(Context mCtx, ArrayList<FeaturedMoviesItem> featuredMoviesItems) {
        this.mCtx = mCtx;
        this.featuredMoviesItems = featuredMoviesItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called.");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Log.d(TAG, "onCreateViewHolder: called.");

        final FeaturedMoviesItem featuredMoviesItem = featuredMoviesItems.get(position);
        holder.movieName.setText(featuredMoviesItem.getName());


        if(featuredMoviesItem.getCoverPhoto() !=null && featuredMoviesItem.getCoverPhoto().length()>0)
        {
            if (featuredMoviesItem.getCoverPhoto().matches("profile.jpg")){

                Picasso.with(mCtx).load("https://content.reelnepal.com/photos/movieposters/im214x317/214x317" +featuredMoviesItem.getCoverPhoto()).into(holder.movieImage);
            }
            else {
                Picasso.with(mCtx).load("https://content.reelnepal.com/photos/movieposters/im214x317/214x317" +featuredMoviesItem.getCoverPhoto()).into(holder.movieImage);
            }
        }

        holder.movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx,"Movie ID: "+featuredMoviesItem.getMovieId(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return featuredMoviesItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        AppCompatImageView movieImage;
        TextView movieName;

        public ViewHolder (View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.ft_movies_image);
            movieName = itemView.findViewById(R.id.ft_movies_name);
             }
    }



}
