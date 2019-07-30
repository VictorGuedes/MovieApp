package com.example.android.movieapp.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.movieapp.DetailsActivity;
import com.example.android.movieapp.R;
import com.example.android.movieapp.model.Results;
import com.example.android.movieapp.service.ApiService;
import com.example.android.movieapp.viewModel.MoviesViewModel;
import com.squareup.picasso.Picasso;

public class MoviePosterAdapter extends PagedListAdapter<Results, MoviePosterAdapter.MovieViewHolder>
        implements MovieEventListener  {

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
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {
        final Results movie = getItem(position);
        if (movie != null){
            Picasso.get().load(ApiService.basePosterUrl + movie.getPosterPath()).into(holder.imageView);

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {

                    //Toast.makeText(context, movie.getOriginalTittle(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, DetailsActivity.class);
                    holder.imageView.setTransitionName("movie_Poster");
                    intent.putExtra("photo",ApiService.basePosterUrl + movie.getPosterPath());
                    intent.putExtra("tittle", movie.getOriginalTittle());

                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            (Activity) context, holder.imageView, ViewCompat.getTransitionName(holder.imageView));

                    context.startActivity(intent, options.toBundle());

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

    @Override
    public void getClickedMovie(Results movie) {

    }


    public class MovieViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public MovieViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.image_movie_poster);
        }

    }


}
