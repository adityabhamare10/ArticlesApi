package com.aditya.schooglinkassignment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Article> articles;
    private final List<Article> searchedArticles;
    private final OnItemClickListener listener;

    private static final int VIEW_TYPE_ARTICLE = 1;
    private static final int VIEW_TYPE_NO_RESULTS = 2;

    public interface OnItemClickListener {
        void onItemClick(Article article);
    }

    public MyAdapter(List<Article> articles, OnItemClickListener listener) {
        this.articles = articles;
        this.searchedArticles = new ArrayList<>(articles);
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_NO_RESULTS) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_no_result, parent, false);
            return new NoResultsViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
            return new MyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            Article article = searchedArticles.get(position);
            ((MyViewHolder) holder).bind(article, listener);
        }
    }

    @Override
    public int getItemCount() {
        return searchedArticles.isEmpty() ? 1 : searchedArticles.size(); // Show 1 item for no results
    }

    @Override
    public int getItemViewType(int position) {
        return searchedArticles.isEmpty() ? VIEW_TYPE_NO_RESULTS : VIEW_TYPE_ARTICLE;
    }

    static class NoResultsViewHolder extends RecyclerView.ViewHolder {
        TextView noResultsTextView;

        public NoResultsViewHolder(@NonNull View itemView) {
            super(itemView);
            noResultsTextView = itemView.findViewById(R.id.no_results_text);
        }
    }

    public void search(String query) {
        searchedArticles.clear();
        if (query.isEmpty()) {
            searchedArticles.addAll(articles);
        } else {
            String lowerCaseQuery = query.toLowerCase();
            for (Article article : articles) {
                if (article.getTitle().toLowerCase().contains(lowerCaseQuery)) {
                    searchedArticles.add(article);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static String formatPublishedDate(String publishedAt) {
        String formattedDate = "";
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        try {
            Date date = inputFormat.parse(publishedAt);
            if (date != null) {
                formattedDate = outputFormat.format(date);
            }
        } catch (ParseException e) {
            Log.e("Error Formatting Date", e.getMessage() != null ? e.getMessage() : "");
        }

        return formattedDate;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, descriptionTextView, publishedAtTextView;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.image);
            descriptionTextView = itemView.findViewById(R.id.description);
            publishedAtTextView = itemView.findViewById(R.id.publishedAt);
        }

        public void bind(final Article article, final OnItemClickListener listener) {
            titleTextView.setText(article.getTitle());
            Glide.with(itemView.getContext()).load(article.getUrlToImage()).into(imageView);
            descriptionTextView.setText(article.getDescription());
            publishedAtTextView.setText(formatPublishedDate(article.getPublishedAt()));
            itemView.setOnClickListener(v -> listener.onItemClick(article));
        }
    }
}
