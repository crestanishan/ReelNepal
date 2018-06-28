package com.example.nishan.reelnepal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models.FeaturedMoviesItem;
import com.example.nishan.reelnepal.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FlipperAdapter extends BaseAdapter {
    private Context mCtx;
    private ArrayList<FeaturedMoviesItem> featuredMoviesItems;

    public FlipperAdapter(Context mCtx, ArrayList<FeaturedMoviesItem> featuredMoviesItems){
        this.mCtx = mCtx;
        this.featuredMoviesItems = featuredMoviesItems;
    }


    @Override
    public int getCount() {
        return featuredMoviesItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FeaturedMoviesItem featuredMoviesItem = featuredMoviesItems.get(position);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.flipper_items, null);
        TextView textView = (TextView) view.findViewById(R.id.featured_movie_textView);
        ImageView imageView = (ImageView) view.findViewById(R.id.featured_movie_image);
        textView.setText(featuredMoviesItem.getName());

        if(featuredMoviesItem.getCoverPhoto() !=null && featuredMoviesItem.getCoverPhoto().length()>0)
        {
            if (featuredMoviesItem.getCoverPhoto().matches("profile.jpg")){

                Picasso.with(mCtx).load("http://content.reelnepal.com/photos/movieposters/im214x317/214x317" +featuredMoviesItem.getCoverPhoto()).into(imageView);
            }
            else {
                Picasso.with(mCtx).load("http://content.reelnepal.com/photos/movieposters/im214x317/214x317" +featuredMoviesItem.getCoverPhoto()).into(imageView);
            }
        }



        return view;
    }
}
