package com.example.android.movieapp.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.movieapp.R;
import com.example.android.movieapp.databinding.RecyclerItemMovieTrailersBinding;
import com.example.android.movieapp.model.MovieTrailer;
import com.example.android.movieapp.service.ApiService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailsTrailerListAdapter extends RecyclerView.Adapter<DetailsTrailerListAdapter.DetailsTrailerViewHolder> {

    private ArrayList<MovieTrailer> movieTrailers;


    @NonNull
    @Override
    public DetailsTrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerItemMovieTrailersBinding itemMovieTrailersBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recycler_item_movie_trailers, parent, false);

        return new DetailsTrailerViewHolder(itemMovieTrailersBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsTrailerViewHolder holder, int position) {
        MovieTrailer movieTrailer = movieTrailers.get(position);
        holder.itemMovieTrailersBinding.setTrailer(movieTrailer);
    }

    public void setTrailers(ArrayList<MovieTrailer> movieTrailers){
        this.movieTrailers = movieTrailers;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return movieTrailers.size();
    }

    class DetailsTrailerViewHolder extends RecyclerView.ViewHolder{

        private RecyclerItemMovieTrailersBinding itemMovieTrailersBinding;

        public DetailsTrailerViewHolder(@NonNull RecyclerItemMovieTrailersBinding itemMovieTrailersBinding) {
            super(itemMovieTrailersBinding.getRoot());
            this.itemMovieTrailersBinding = itemMovieTrailersBinding;
        }
    }

}
