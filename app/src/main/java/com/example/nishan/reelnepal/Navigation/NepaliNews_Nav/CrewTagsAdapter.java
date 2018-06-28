package com.example.nishan.reelnepal.Navigation.NepaliNews_Nav;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.nishan.reelnepal.Actor.ActorProfileActivity;

import com.example.nishan.reelnepal.Navigation.NepaliNews_Nav.NepaliNewsModel.CrewTagsItem;
import com.example.nishan.reelnepal.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class CrewTagsAdapter extends RecyclerView.Adapter<CrewTagsAdapter.ViewHolder>{

    private Context mCtx;

    private ArrayList<CrewTagsItem> crewTagsItems;

    public CrewTagsAdapter(Context mCtx, ArrayList<CrewTagsItem> crewTagsItems) {
        this.mCtx = mCtx;
        this.crewTagsItems = crewTagsItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolderMovieTags: called.");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_crew_tags, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolderCrewTags: called.");

        final CrewTagsItem crewTagsItem = crewTagsItems.get(position);

        holder.textViewCrewName.setText(crewTagsItem.getName());
        Log.d(TAG, "Crew Tag Names: "+crewTagsItem.getName());

        if(crewTagsItem.getProfilePhoto() !=null && crewTagsItem.getProfilePhoto().length()>0)
        {
            if (crewTagsItem.getProfilePhoto().matches("profile.jpg")){

                Picasso.with(mCtx).load("https://content.reelnepal.com/photos/movieposters/im214x317/214x317" +crewTagsItem.getProfilePhoto()).into(holder.crewTagsImage);
            }
            else {
                Picasso.with(mCtx).load("https://content.reelnepal.com/photos/movieposters/im214x317/214x317" +crewTagsItem.getProfilePhoto()).into(holder.crewTagsImage);
            }
        }


        holder.crewTagsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, ActorProfileActivity.class);
                mCtx.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "GetItemCountCrew: called.");

        return crewTagsItems.size();


    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView crewTagsImage;
        TextView textViewCrewName ;


        public ViewHolder(View itemView) {
            super(itemView);
            crewTagsImage = itemView.findViewById(R.id.crew_tag_image);
            textViewCrewName = itemView.findViewById(R.id.crew_tag_name);

        }
    }
    }
