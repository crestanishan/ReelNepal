package com.example.nishan.reelnepal.Navigation.NepaliNews_Nav;



import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nishan.reelnepal.Adapters.RecyclerViewAdapter;
import com.example.nishan.reelnepal.MainActivity;
import com.example.nishan.reelnepal.Movie.MovieProfile2;
import com.example.nishan.reelnepal.Movie.MovieProfileActivity;
import com.example.nishan.reelnepal.Navigation.NepaliNews_Nav.NepaliNewsModel.MovieTagsItem;
import com.example.nishan.reelnepal.Navigation.NepaliNews_Nav.NepaliNewsModel.NepaliNewsDetails;
import com.example.nishan.reelnepal.R;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class MovieTagsAdapter extends RecyclerView.Adapter<MovieTagsAdapter.ViewHolder> {

    private Context mCtx;

    private ArrayList<MovieTagsItem> movieTagsItems;

    OnMovieTagsItemClickInterface onMovieTagsItemClickInterface;



    public MovieTagsAdapter(Context mCtx, ArrayList<MovieTagsItem>movieTagsItems, OnMovieTagsItemClickInterface my) {
        this.mCtx = mCtx;
        this.movieTagsItems = movieTagsItems;
        onMovieTagsItemClickInterface = my;
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolderMovieTags: called.");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movie_tags, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        Log.d(TAG, "onBindViewHolderMovieTags: called.");

        final MovieTagsItem movieTagsItem = movieTagsItems.get(position);

        holder.textViewMovieName.setText(movieTagsItem.getName());
        Log.d(TAG, "Movie Tag Name: "+movieTagsItem.getName());


      if(movieTagsItem.getCoverPhoto() !=null && movieTagsItem.getCoverPhoto().length()>0)
        {
            if (movieTagsItem.getCoverPhoto().matches("profile.jpg")){

                Picasso.with(mCtx).load("https://content.reelnepal.com/photos/movieposters/im214x317/214x317" +movieTagsItem.getCoverPhoto()).into(holder.movieTagsImage);
            }
            else {
                Picasso.with(mCtx).load("https://content.reelnepal.com/photos/movieposters/im214x317/214x317" +movieTagsItem.getCoverPhoto()).into(holder.movieTagsImage);
            }
        }


        holder.movieTagsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(mCtx,"Movie ID: "+movieTagsItem.getMovieId(),Toast.LENGTH_LONG).show();
              /*  Intent intent = new Intent(mCtx, MovieProfileActivity.class);
                intent.putExtra("ID", movieTagsItem.getMovieId());
                mCtx.startActivity(intent);*/

              /*  FragmentTransaction ft = ((Fragment)mCtx).getFragmentManager().beginTransaction();
                ft.replace(R.id.contain_main, MovieProfile2.newInstanceFromMovieTags(movieTagsItem));
                ft.commit();*/

                onMovieTagsItemClickInterface.onItemClick(movieTagsItem);

            }
        });

    }






    @Override
    public int getItemCount() {
        Log.d(TAG, "GetItemCountMovie: called.");
        return movieTagsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatImageView movieTagsImage;
        TextView textViewMovieName ;

        public ViewHolder(View itemView) {
            super(itemView);
            movieTagsImage = itemView.findViewById(R.id.movie_tags_image);
           textViewMovieName = itemView.findViewById(R.id.movie_tags_name);

        }
    }

        public interface OnMovieTagsItemClickInterface{

            void onItemClick(MovieTagsItem movieTagsItem);

        }


    }
