package com.example.android.movieapp.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.movieapp.R;
import com.example.android.movieapp.databinding.RecyclerItemMovieTrailersBinding;
import com.example.android.movieapp.model.MovieTrailer;

import java.util.ArrayList;

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
