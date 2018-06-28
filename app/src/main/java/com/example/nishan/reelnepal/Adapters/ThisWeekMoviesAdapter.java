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

import com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models.ThisWeekMoviesItem;
import com.example.nishan.reelnepal.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ThisWeekMoviesAdapter extends RecyclerView.Adapter<ThisWeekMoviesAdapter.ViewHolder>{
    private static final String TAG = "RecycleViewAdapter";

    private Context mCtx;

    private ArrayList<ThisWeekMoviesItem> thisWeekMoviesItems;

    public ThisWeekMoviesAdapter(Context mCtx, ArrayList<ThisWeekMoviesItem>thisWeekMoviesItems) {
        this.mCtx = mCtx;
        this.thisWeekMoviesItems = thisWeekMoviesItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called.");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_this_week_movie, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

       final ThisWeekMoviesItem thisWeekMoviesItem = thisWeekMoviesItems.get(position);
        holder.textViewThisWeekMovie.setText(thisWeekMoviesItem.getMovieName());
        holder.textViewDirector.setText("Dir: "+thisWeekMoviesItem.getDirector());
        holder.textViewCrews.setText(thisWeekMoviesItem.getCrews());


        if(thisWeekMoviesItem.getCoverPhoto() !=null && thisWeekMoviesItem.getCoverPhoto().length()>0)
        {
            if (thisWeekMoviesItem.getCoverPhoto().matches("profile.jpg")){

                Picasso.with(mCtx).load("https://content.reelnepal.com/photos/movieposters/im214x317/214x317" +thisWeekMoviesItem.getCoverPhoto()).into(holder.imageViewThisWeek);
            }
            else {
                Picasso.with(mCtx).load("https://content.reelnepal.com/photos/movieposters/im214x317/214x317" +thisWeekMoviesItem.getCoverPhoto()).into(holder.imageViewThisWeek);
            }
        }
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx,"Youtube Movie ID : "+thisWeekMoviesItem.getMovieId(),Toast.LENGTH_LONG).show();
            }
        });*/

       holder.textViewThisWeekMovie.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(mCtx,"Youtube Movie ID : "+thisWeekMoviesItem.getMovieId(),Toast.LENGTH_LONG).show();

           }
       });

     holder.card_view.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             Toast.makeText(mCtx,"Youtube Movie ID : "+thisWeekMoviesItem.getMovieId(),Toast.LENGTH_LONG).show();

         }
     });


    }

    @Override
    public int getItemCount() {
        return thisWeekMoviesItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        AppCompatImageView imageViewThisWeek;
        TextView textViewDirector,textViewCrews,textViewThisWeekMovie ;
        CardView card_view;

        public ViewHolder(View itemView) {
            super(itemView);


            card_view =  itemView.findViewById(R.id.cardView);
            imageViewThisWeek = itemView.findViewById(R.id.imageView_thisWeek);
            textViewDirector = itemView.findViewById(R.id.textView_director);
            textViewCrews = itemView.findViewById(R.id.textView_crews);
            textViewThisWeekMovie = itemView.findViewById(R.id.textView_this_week_movie);
        }

       /* public ContactViewHolder(View view, Context ctx, ArrayList<Contact> contacts) {
            super(view);
            view.setOnClickListener(this);
            this.contacts = contacts;
            this.ctx = ctx;
            person_name = (TextView) view.findViewById(R.id.person_name);
            person_email = (TextView) view.findViewById(R.id.person_email);
        }*/
    }

}
