package com.example.android.movieapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.movieapp.R;
import com.example.android.movieapp.databinding.RecyclerMoviePosterBinding;
import com.example.android.movieapp.model.Results;

import java.util.ArrayList;

public class FavoriterPostAdapter extends RecyclerView.Adapter<FavoriterPostAdapter.FavoriteViewHolder> {

    private ArrayList<Results> favoriteMovies;

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerMoviePosterBinding itemMovieCommentBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recycler_movie_poster, parent, false);

        return new FavoriteViewHolder(itemMovieCommentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Results movies = favoriteMovies.get(position);
        holder.moviePosterBinding.setModel(movies);
    }

    @Override
    public int getItemCount() {
        return favoriteMovies.size();
    }

    public void setFavorites(ArrayList<Results> favorites){
        this.favoriteMovies = favorites;
        notifyDataSetChanged();
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {

        private RecyclerMoviePosterBinding moviePosterBinding;

        public FavoriteViewHolder(@NonNull RecyclerMoviePosterBinding moviePosterBinding) {
            super(moviePosterBinding.getRoot());
            this.moviePosterBinding = moviePosterBinding;
        }
    }
}
