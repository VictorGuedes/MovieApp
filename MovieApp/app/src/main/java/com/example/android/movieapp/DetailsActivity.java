package com.example.android.movieapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.android.movieapp.databinding.ActivityDetailsBinding;
import com.example.android.movieapp.model.Results;
import com.example.android.movieapp.model.service.ApiService;
import com.example.android.movieapp.viewModel.FavoritesViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding activityDetailsBinding;
    private Results actualMovie;
    private FavoritesViewModel favoritesViewModel;
    private boolean movieInDatabase = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        activityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        setSupportActionBar(activityDetailsBinding.toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        favoritesViewModel =  ViewModelProviders.of(this).get(FavoritesViewModel.class);
        actualMovie = (Results) getIntent().getParcelableExtra("movie");

        if (actualMovie != null){
            activityDetailsBinding.releaseDateTextView.setText(actualMovie.getReleaseDate());
            activityDetailsBinding.movieRatedTextView.setText(String.valueOf(actualMovie.getVoteAvarage()) + " / 10");
            activityDetailsBinding.synopisTextView.setText(actualMovie.getSynopsisMovie());
            activityDetailsBinding.toolbarLayout.setTitle(actualMovie.getOriginalTittle());

            Picasso.get().load(ApiService.basePosterUrl + actualMovie.getPosterPath()).into(activityDetailsBinding.imageMoviePosterDetails);
            favoritesViewModel.getMovieById(actualMovie);
        }


        favoritesViewModel.getFavoriteById().observe(this, new Observer<List<Results>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceType")
            @Override
            public void onChanged(List<Results> results) {
                if (results.size() == 0){
                    movieInDatabase = false;
                    activityDetailsBinding.fab.setBackgroundTintList(ContextCompat.getColorStateList(DetailsActivity.this, R.color.white_background));
                } else {
                    movieInDatabase = true;
                    activityDetailsBinding.fab.setBackgroundTintList(ContextCompat.getColorStateList(DetailsActivity.this, R.color.yellow_background));
                }
            }
        });
    }


    public void viewMovieComments(View view) {
        startActivity(new Intent(this, CommentActivity.class).putExtra("id", actualMovie.getId()));
    }

    public void viewMovieTrailers(View view) {
        startActivity(new Intent(this, TrailersActivity.class).putExtra("id", actualMovie.getId()));
    }

    public void saveAsFavorite(View view) {
        if (!movieInDatabase){
            favoritesViewModel.insert(actualMovie);
            Snackbar.make(view, getString(R.string.add_movie_text), Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } else {
            favoritesViewModel.deleteMovie(actualMovie);
            Snackbar.make(view, getString(R.string.delete_movie_text), Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
