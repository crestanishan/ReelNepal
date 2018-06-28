package com.example.nishan.reelnepal.Navigation.MovieCalender_Nav;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nishan.reelnepal.Movie.MovieProfileActivity;
import com.example.nishan.reelnepal.Navigation.MovieCalender_Nav.Calender_Models.GeneralItem;
import com.example.nishan.reelnepal.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    List<ListItem> consolidatedList = new ArrayList<>();

    public Adapter(Context context, List<ListItem> consolidatedList) {
        this.consolidatedList = consolidatedList;
        this.mContext = context;
        Log.d(TAG, "adapter:" + consolidatedList);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {

            case ListItem.TYPE_GENERAL:
                View v1 = inflater.inflate(R.layout.layout_movie_calender_details, parent,
                        false);
                viewHolder = new GeneralViewHolder(v1);
                break;

            case ListItem.TYPE_DATE:
                View v2 = inflater.inflate(R.layout.layout_calender_head, parent, false);
                viewHolder = new DateViewHolder(v2);
                break;
        }

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {

            case ListItem.TYPE_GENERAL:

                final GeneralItem generalItem   = (GeneralItem) consolidatedList.get(position);
                GeneralViewHolder generalViewHolder= (GeneralViewHolder)holder;
                generalViewHolder.txtName.setText(generalItem.getResultItem().getName());
                generalViewHolder.txtReleasedDate.setText("Released Date: "+generalItem.getResultItem().getReleaseDateBS());
                generalViewHolder.txtDirector.setText("Director: "+generalItem.getResultItem().getDirector());
                generalViewHolder.txtCast.setText("Cast: "+generalItem.getResultItem().getCrews());

                if(generalItem.getResultItem().getCoverPhoto() !=null && generalItem.getResultItem().getCoverPhoto() .length()>0)
                {
                    if (generalItem.getResultItem().getCoverPhoto() .matches("profile.jpg")){

                        Picasso.with(mContext).load("https://content.reelnepal.com/photos/movieposters/im214x317/214x317" +generalItem.getResultItem().getCoverPhoto() ).into(((GeneralViewHolder) holder).imageViewCalender);
                    }
                    else {
                        Picasso.with(mContext).load("https://content.reelnepal.com/photos/movieposters/im214x317/214x317" +generalItem.getResultItem().getCoverPhoto() ).into(((GeneralViewHolder) holder).imageViewCalender);
                    }
                }


                ((GeneralViewHolder) holder).txtName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, MovieProfileActivity.class);
                        intent.putExtra("ID", generalItem.getResultItem().getMovieId());
                        mContext.startActivity(intent);

                    }
                });

                ((GeneralViewHolder) holder).imageViewCalender.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, MovieProfileActivity.class);
                        intent.putExtra("ID", generalItem.getResultItem().getMovieId());
                        mContext.startActivity(intent);

                    }
                });



                break;

            case ListItem.TYPE_DATE:
                DateItem dateItem = (DateItem) consolidatedList.get(position);
                DateViewHolder dateViewHolder = (DateViewHolder) holder;

                dateViewHolder.txtReleasedMonth.setText(dateItem.getMonth());
                // Populate date item data here

                break;
        }


    }


    // ViewHolder for date row item
    class DateViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtReleasedMonth;

        public DateViewHolder(View v) {
            super(v);
            this.txtReleasedMonth= itemView.findViewById(R.id.textView_released_month);
        }
    }

    // View holder for general row item
    class GeneralViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtName,txtReleasedDate, txtDirector, txtCast;
        protected AppCompatImageView imageViewCalender;

        public GeneralViewHolder(View v) {
            super(v);
            this.txtName =  v.findViewById(R.id.textView_movie_name_in_calender);
            this.txtReleasedDate= v.findViewById(R.id.textView_released_date);
            this.txtDirector = v.findViewById(R.id.textView_director_in_calender);
            this.txtCast = v.findViewById(R.id.textView_cast);
            imageViewCalender = v.findViewById(R.id.imageView_Calender);


        }
    }

    @Override
    public int getItemViewType(int position) {
        return consolidatedList.get(position).getType();
    }



    @Override
    public int getItemCount() {
        return consolidatedList != null ? consolidatedList.size() : 0;
    }
}
