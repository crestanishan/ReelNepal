package com.example.nishan.reelnepal.Navigation.MovieCalender_Nav;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nishan.reelnepal.Navigation.MovieCalender_Nav.Calender_Models.ResultItem;
import com.example.nishan.reelnepal.R;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class MovieCalenderAdapter extends RecyclerView.Adapter<MovieCalenderAdapter.ViewHolder>{

    private static final int TYPE_HEAD = 0;
    private static final int TYPE_LIST = 1;

    private Context mCtx;

    private ArrayList<ResultItem> resultItems;

    public MovieCalenderAdapter(Context mCtx, ArrayList<ResultItem> resultItems) {
        this.mCtx = mCtx;
        this.resultItems = resultItems;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called.");

        if (viewType == TYPE_LIST){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movie_calender_details, parent, false);

            return new ViewHolder(view, viewType);

        }

        else if (viewType == TYPE_HEAD){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_calender_head, parent, false);

            return new ViewHolder(view, viewType);

        }

            return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ResultItem resultItem;

        Log.d(TAG, "onCreateViewHolder: called.");

        if (holder.view_type == TYPE_LIST){

            resultItem = resultItems.get(position-1);
            //holder.tvReleasedMonth.setText(resultItem.getReleaseMonth());
            holder.tvMovieName.setText(resultItem.getName());
            holder.tvReleasedDate.setText(resultItem.getReleaseDateBS());
            holder.tvDirector.setText(resultItem.getDirector());
            holder.tvCast.setText(resultItem.getCrews());

        }

        else if (holder.view_type == TYPE_HEAD){
            resultItem = resultItems.get(position);
            holder.tvReleasedMonth.setText(resultItem.getReleaseMonth());

        }




    }

    @Override
    public int getItemCount() {
        return resultItems.size()+1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        int view_type;

        TextView tvMovieName,tvReleasedMonth,tvReleasedDate, tvDirector, tvCast ;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);

            if (viewType == TYPE_LIST){
                tvMovieName= itemView.findViewById(R.id.textView_movie_name_in_calender);
                tvReleasedDate= itemView.findViewById(R.id.textView_released_date);
                tvDirector = itemView.findViewById(R.id.textView_director_in_calender);
                tvCast = itemView.findViewById(R.id.textView_cast);
                view_type = 1;
            }

            else if (viewType == TYPE_HEAD){
                tvReleasedMonth= itemView.findViewById(R.id.textView_released_month);
                view_type = 0;

            }



        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEAD;
            return TYPE_LIST;

    }
}
