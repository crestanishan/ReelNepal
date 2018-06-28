package com.example.nishan.reelnepal.Adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models.NextWeekMoviesItem;
import com.example.nishan.reelnepal.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NextWeekMoviesAdapter extends RecyclerView.Adapter<NextWeekMoviesAdapter.ViewHolder>{

    private static final String TAG = "RecycleViewAdapter";

    private Context mCtx;

    private ArrayList<NextWeekMoviesItem> nextWeekMoviesItems;

    public NextWeekMoviesAdapter(Context mCtx, ArrayList<NextWeekMoviesItem> nextWeekMoviesItems) {
        this.mCtx = mCtx;
        this.nextWeekMoviesItems = nextWeekMoviesItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called.");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_next_week_movies, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final NextWeekMoviesItem nextWeekMoviesItem = nextWeekMoviesItems.get(position);
        holder.tv_NextWeekMovie.setText(nextWeekMoviesItem.getMovieName());
        holder.tv_Director.setText("Dir: "+nextWeekMoviesItem.getDirector());
        holder.tv_Crews.setText(nextWeekMoviesItem.getCrews());

        if(nextWeekMoviesItem.getCoverPhoto() !=null && nextWeekMoviesItem.getCoverPhoto().length()>0)
        {
            if (nextWeekMoviesItem.getCoverPhoto().matches("profile.jpg")){

                Picasso.with(mCtx).load("https://content.reelnepal.com/photos/movieposters/im214x317/214x317" +nextWeekMoviesItem.getCoverPhoto()).into(holder.imageViewNextWeek);
            }
            else {
                Picasso.with(mCtx).load("https://content.reelnepal.com/photos/movieposters/im214x317/214x317" +nextWeekMoviesItem.getCoverPhoto()).into(holder.imageViewNextWeek);
            }
        }

        holder.cardViewNextWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx,"Youtube Movie ID : "+nextWeekMoviesItem.getMovieId(),Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return nextWeekMoviesItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatImageView imageViewNextWeek;
        TextView tv_Director,tv_Crews,tv_NextWeekMovie ;
        CardView cardViewNextWeek;

        public ViewHolder(View itemView) {
            super(itemView);
            cardViewNextWeek = itemView.findViewById(R.id.nextWeek_cardView);
            imageViewNextWeek = itemView.findViewById(R.id.imageView_NextWeek);
            tv_NextWeekMovie = itemView.findViewById(R.id.textView_next_week_movie);
            tv_Director = itemView.findViewById(R.id.textView_next_week_movie_director);
            tv_Crews = itemView.findViewById(R.id.textView_next_week_movie_crews);
        }
    }

}
