package com.example.nishan.reelnepal.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.nishan.reelnepal.R;
import com.example.nishan.reelnepal.YouTube.YouTubeConfig;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoPlayer extends YouTubeBaseActivity   {

    private static final String TAG = "VideoPlayer";

    YouTubePlayerView mYouTubePlayerView;
    String ID;

    YouTubePlayer.OnInitializedListener mOnInitializedListener;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_video_player);

        Intent intent = getIntent();
        ID = intent.getStringExtra("Youtube ID");
        Toast.makeText(getApplicationContext()," ID : "+ID,Toast.LENGTH_LONG).show();


        Log.d(TAG,"oncreate: Starting");

        mYouTubePlayerView = findViewById(R.id.youtubePlay);

        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG," Done intializing");

                youTubePlayer.loadVideo(ID);

                }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG," Failed to intialize");

            }
        };

        videoplay();


    }

    public void videoplay (){

        String videopath = "https://www.youtube.com/watch?v="+ID;

        Uri uri = Uri.parse(videopath);
       // videoView.setVideoURI(uri);
        //videoView.start();

        Log.d(TAG,"oncreate: Intializing YouTube Player");

        mYouTubePlayerView.initialize(YouTubeConfig.getApiKey(),mOnInitializedListener);

         }

}

