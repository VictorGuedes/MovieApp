package com.example.android.movieapp.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.databinding.BindingAdapter;

import com.example.android.movieapp.DetailsActivity;
import com.example.android.movieapp.model.Results;
import com.example.android.movieapp.service.ApiService;

public class OnClickBindAdapter {


    @BindingAdapter("MoviePosterOnClick")
    public static void bindOnMoviePosterClickListener(final ImageView imageView, final Results movie){
        imageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                Context context = imageView.getContext();
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("id", movie.getId());
                intent.putExtra("tittle", movie.getOriginalTittle());
                intent.putExtra("overview", movie.getSynopsisMovie());
                intent.putExtra("date", movie.getReleaseDate());
                intent.putExtra("photo", ApiService.basePosterUrl + movie.getPosterPath());
                intent.putExtra("voteAverage", movie.getVoteAvarage());

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) context, imageView, ViewCompat.getTransitionName(imageView));

                context.startActivity(intent, options.toBundle());
            }
        });






    }


}
