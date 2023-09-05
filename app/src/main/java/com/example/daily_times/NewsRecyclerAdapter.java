package com.example.daily_times;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kwabenaberko.newsapilib.models.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>{
    List<Article> articleList;
    NewsRecyclerAdapter(List<Article> articleList) {
        this.articleList=articleList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_view,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article=articleList.get(position);
        holder.sourceTextView.setText(article.getSource().getName());
        holder.titleTextView.setText(article.getTitle());
        Picasso.get().load(article.getUrlToImage())
                .error(R.drawable.baseline_hide_image_24)
                .placeholder(R.drawable.baseline_hide_image_24)
                .into(holder.imageView);


        holder.itemView.setOnClickListener((v)->{
            Intent intent=new Intent(v.getContext(), NewsFullActivity.class);
            intent.putExtra("url",article.getUrl());
            v.getContext().startActivity(intent);
        });
    }

    void updateData(List<Article> data) {
        if (data != null) {
            if (articleList == null) {
                articleList = new ArrayList<>(); // Initialize articleList if it's null
            }
            articleList.clear();
            articleList.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        if (articleList != null) {
            return articleList.size();
        } else {
            return 0; // Or another suitable default value
        }
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView,sourceTextView;
        ImageView imageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            sourceTextView=itemView.findViewById(R.id.article_source);
            imageView=itemView.findViewById(R.id.article_image_view);
            titleTextView=itemView.findViewById(R.id.article_title);
        }
    }
}
