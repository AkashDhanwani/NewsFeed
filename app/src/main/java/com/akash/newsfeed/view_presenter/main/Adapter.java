package com.akash.newsfeed.view_presenter.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.akash.newsfeed.R;
import com.akash.newsfeed.data.network.model_classes.ApiResponse;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by AKASH on 3/25/2019.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ApiResponse.Article> newsList;
    private Context context;

    @Inject
    MainMvpPresenter<MainMvpView> mMainMvpPresenter;

    public Adapter(List<ApiResponse.Article> itemList, Context context, MainMvpPresenter<MainMvpView> mMainMvpPresenter) {
        this.newsList = itemList;
        this.context = context;
        this.mMainMvpPresenter = mMainMvpPresenter;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_for_main, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ApiResponse.Article listItem = newsList.get(position);

        holder.imagepath = listItem.getUrlToImage();

        Glide.with(context).load(holder.imagepath).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.imageView);
        holder.titleMain.setText(listItem.getTitle());
        holder.sourceMain.setText(listItem.getAuthor());
        holder.publishAtMain.setText(listItem.getPublishedAt());

        holder.title = listItem.getTitle();
        holder.source = listItem.getAuthor();
        holder.publish = listItem.getPublishedAt();
        holder.description = listItem.getDescription();
        holder.urltopage = listItem.getUrl();
        holder.content = listItem.getContent();
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public String title, source, publish, description, imagepath, urltopage, content;

        public ImageView imageView;
        public TextView titleMain, sourceMain, publishAtMain;


        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageMain);
            titleMain = itemView.findViewById(R.id.titleMain);
            sourceMain = itemView.findViewById(R.id.sourceMain);
            publishAtMain = itemView.findViewById(R.id.publishAtMain);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Bundle bundle = new Bundle();
                    bundle.putString("imagepath", imagepath);
                    bundle.putString("publish", publish);
                    bundle.putString("description", description);
                    bundle.putString("title", title);
                    bundle.putString("urltopage", urltopage);
                    bundle.putString("content", content);

                    mMainMvpPresenter.onCardClicked(bundle, imageView, titleMain);
                }
            });
        }
    }
}
