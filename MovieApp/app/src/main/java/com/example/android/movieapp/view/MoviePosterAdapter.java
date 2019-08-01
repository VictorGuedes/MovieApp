package com.example.android.movieapp.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.movieapp.DetailsActivity;
import com.example.android.movieapp.R;
import com.example.android.movieapp.databinding.RecyclerMoviePosterBinding;
import com.example.android.movieapp.model.Comment;
import com.example.android.movieapp.model.Results;
import com.example.android.movieapp.service.ApiService;
import com.example.android.movieapp.viewModel.MoviesViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviePosterAdapter extends PagedListAdapter<Results, MoviePosterAdapter.MovieViewHolder> {


    public MoviePosterAdapter(Context context){
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerMoviePosterBinding recyclerMoviePosterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recycler_movie_poster, parent, false);

        return new MoviePosterAdapter.MovieViewHolder(recyclerMoviePosterBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {
        final Results movie = getItem(position);
        if (movie != null){
            holder.recyclerMoviePosterBinding.setModel(movie);
        }
    }

    private static DiffUtil.ItemCallback<Results> DIFF_CALLBACK = new DiffUtil.ItemCallback<Results>(){
        @Override
        public boolean areItemsTheSame(@NonNull Results oldItem, @NonNull Results newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Results oldItem, @NonNull Results newItem) {
            return oldItem.equals(newItem);
        }
    };

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        private RecyclerMoviePosterBinding recyclerMoviePosterBinding;

        public MovieViewHolder(@NonNull RecyclerMoviePosterBinding recyclerMoviePosterBinding){
            super(recyclerMoviePosterBinding.getRoot());
            this.recyclerMoviePosterBinding = recyclerMoviePosterBinding;
        }
    }


}
