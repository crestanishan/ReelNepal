package com.example.nishan.reelnepal.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nishan.reelnepal.Activities.VideoPlayer;
import com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models.TopVideosItem;
import com.example.nishan.reelnepal.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TopMoviesVideoAdapter extends RecyclerView.Adapter<TopMoviesVideoAdapter.ViewHolder> {

    private static final String TAG = "RecycleViewAdapter";

    Date date;

    private Context mCtx;

    private ArrayList<TopVideosItem> topVideosItems;


    public TopMoviesVideoAdapter(Context mCtx, ArrayList<TopVideosItem> topVideosItems) {
        this.mCtx = mCtx;
        this.topVideosItems = topVideosItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called.");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_top_movies, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Log.d(TAG, "onCreateViewHolder: called.");
        final TopVideosItem topVideosItem = topVideosItems.get(position);
       // holder.textViewPublishedDate.setText(topVideosItem.getPublishedDate());
        holder.textViewTopVideos.setText(topVideosItem.getTitle());

        // convert to date format
        String dtStart = topVideosItem.getPublishedDate();
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("MMM dd, yyyy");
        try {
            date = input.parse(dtStart);
            holder.textViewPublishedDate.setText(output.format(date));

            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d(TAG, "Date format problem:"+e);
        }

        String url = "https://img.youtube.com/vi/"+topVideosItem.getYouTubeId()+"/sddefault.jpg";


        Picasso.with(mCtx).load(url).into(holder.topVideoImage);

        holder.textViewTopVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mCtx, VideoPlayer.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Youtube ID", topVideosItem.getYouTubeId());
                mCtx.startActivity(intent);


            }
        });

     holder.cardViewVideo.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
           //  Toast.makeText(mCtx,"Youtube ID : "+topVideosItem.getYouTubeId(),Toast.LENGTH_LONG).show();

             Intent intent = new Intent(mCtx, VideoPlayer.class);
             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             intent.putExtra("Youtube ID", topVideosItem.getYouTubeId());
             mCtx.startActivity(intent);
         }
     });


    }

    @Override
    public int getItemCount() {
        return topVideosItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatImageView topVideoImage;
        TextView textViewTopVideos,textViewPublishedDate ;
        CardView cardViewVideo;

        public ViewHolder(View itemView) {
            super(itemView);
            cardViewVideo = itemView.findViewById(R.id.video_card_view);
            topVideoImage = itemView.findViewById(R.id.top_videos_image);
            textViewTopVideos = itemView.findViewById(R.id.textView_top_vidoes);
            textViewPublishedDate = itemView.findViewById(R.id.textView_published_date);


        }
    }
}
