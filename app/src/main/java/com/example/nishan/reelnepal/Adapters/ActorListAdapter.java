package com.example.nishan.reelnepal.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.nishan.reelnepal.R;
import com.example.nishan.reelnepal.Search.Actor;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ActorListAdapter extends ArrayAdapter<Actor> {

    private Context context;
    private List<Actor> actors;

    OnActorItemClickInterface onActorItemClickInterface;


    public ActorListAdapter(Context context, List<Actor> actors, OnActorItemClickInterface my){
        super(context, R.layout.actor_list_layout, actors);
        this.context = context;
        this.actors = actors;
        onActorItemClickInterface = my;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.actor_list_layout, parent, false);
        final Actor actor = actors.get(position);

        TextView textViewName = convertView.findViewById(R.id.textViewName);
        textViewName.setText(actor.getName());
        TextView textViewDetails = convertView.findViewById(R.id.textViewDetails);
        textViewDetails.setText(actor.getDetails());

        ImageView img = convertView.findViewById(R.id.img);

        if(actor.getSearchThumb() !=null && actor.getSearchThumb().length()>0)
        {
            if (actor.getSearchThumb().matches("profile.jpg")){

                Picasso.with(context).load("https://content.reelnepal.com/photos/movieposters/im214x317/214x317" +actor.getSearchThumb()).resize(400, 500).into(img);
            }
            else {
                Picasso.with(context).load("https://content.reelnepal.com/photos/movieposters/im214x317/214x317" +actor.getSearchThumb()).resize(400, 500).into(img);
            }
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onActorItemClickInterface.onItemClick(actor);

            }
        });

        return convertView;
    }

    public interface OnActorItemClickInterface{

        void onItemClick(Actor actor);
    }
}
