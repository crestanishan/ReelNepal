package com.example.nishan.reelnepal.Adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nishan.reelnepal.Navigation.Home_Nav.Front_Models.TopNewsItem;
import com.example.nishan.reelnepal.Navigation.NepaliNews_Nav.NepaNewsDetails;
import com.example.nishan.reelnepal.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TopNewsAdapter extends RecyclerView.Adapter<TopNewsAdapter.ViewHolder> {

    Date date;

    private static final String TAG = "RecycleViewAdapter";

    private Context mCtx;

    private ArrayList<TopNewsItem> topNewsItems;

    OnNewsItemClickInterface onNewsItemClickInterface;


    public TopNewsAdapter(Context mCtx, ArrayList<TopNewsItem> topNewsItems, OnNewsItemClickInterface my) {
        this.mCtx = mCtx;
        this.topNewsItems = topNewsItems;
        onNewsItemClickInterface = my;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called.");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_top_news, parent, false);

        return new TopNewsAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final TopNewsItem  topNewsItem = topNewsItems.get(position);
        holder.tv_NewsTitle.setText(topNewsItem.getTitle());
        holder.tv_RefinedContent.setText(topNewsItem.getRefinedContent());
       // holder.tv_PublishedDate.setText(topNewsItem.getPublishedDate());

        // convert to date format
        String dtStart = topNewsItem.getPublishedDate();
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("MMM dd, yyyy");
        try {
            date = input.parse(dtStart);
            holder.tv_PublishedDate.setText(output.format(date));

            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d(TAG, "Date format problem:"+e);
        }

        holder.tv_NewsTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx,"Youtube News ID : "+topNewsItem.getNewsId(),Toast.LENGTH_LONG).show();

                /*try {
                    Intent intent = new Intent(mCtx, NepaNewsDetails.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("ID", topNewsItem.getNewsId());
                    mCtx.startActivity(intent);


                } catch ( ActivityNotFoundException e) {
                    e.printStackTrace();
                }*/

                onNewsItemClickInterface.onItemClick(topNewsItem);


            }
        });
       holder.cardViewNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx, " News ID : " + topNewsItem.getNewsId(), Toast.LENGTH_LONG).show();
               /* try {
                   Intent intent = new Intent(mCtx, NepaNewsDetails.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("ID", topNewsItem.getNewsId());
                    mCtx.startActivity(intent);


                } catch ( ActivityNotFoundException e) {
                    e.printStackTrace();
                }*/

                onNewsItemClickInterface.onItemClick(topNewsItem);
            }
        });


    }

    @Override
    public int getItemCount() {
        return topNewsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_NewsTitle, tv_RefinedContent, tv_PublishedDate;
        CardView cardViewNews;

        public ViewHolder(View itemView) {
            super(itemView);
            cardViewNews = itemView.findViewById(R.id.News_card_view);
            tv_NewsTitle = itemView.findViewById(R.id.tv_news_title);
            tv_RefinedContent = itemView.findViewById(R.id.tv_refinedContent);
            tv_PublishedDate = itemView.findViewById(R.id.tv_published_date);
        }
    }

    public interface OnNewsItemClickInterface{

        void onItemClick(TopNewsItem topNewsItem);
    }

    }
