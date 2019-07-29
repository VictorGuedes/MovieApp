package com.example.android.movieapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.movieapp.R;
import com.example.android.movieapp.model.Results;
import com.example.android.movieapp.service.ApiService;
import com.squareup.picasso.Picasso;

public class MoviePosterAdapter extends PagedListAdapter<Results, MoviePosterAdapter.MovieViewHolder> {

    private Context context;

    public MoviePosterAdapter(Context context){
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_movie_poster, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        final Results movies = getItem(position);
        if (movies != null){
            Picasso.get().load(ApiService.basePosterUrl + movies.getPosterPath()).into(holder.imageView);

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, movies.getTittle(), Toast.LENGTH_SHORT).show();
                }
            });
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

        ImageView imageView;

        public MovieViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.image_movie_poster);
        }

    }


}
