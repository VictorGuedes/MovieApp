package com.example.android.movieapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.android.movieapp.databinding.ActivityDetailsBinding;
import com.example.android.movieapp.model.MovieTrailer;
import com.example.android.movieapp.service.ApiService;
import com.example.android.movieapp.view.DetailsTrailerListAdapter;
import com.example.android.movieapp.view.MoviePosterAdapter;
import com.example.android.movieapp.viewModel.DetailsViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.NavUtils;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding activityDetailsBinding;

    private ImageView imageView;
    private TextView releaseDate;
    private TextView rated_movie;
    private TextView synopsis;

    private DetailsViewModel detailsViewModel;
    private RecyclerView recyclerView;
    private LinearLayout linearLayout;
    private int movieId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle bundle = getIntent().getExtras();

        activityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        String urlPhoto = "";
        String tittle = "";

        releaseDate = (TextView) findViewById(R.id.release_date_text_view);
        rated_movie = (TextView) findViewById(R.id.movie_rated_text_view);
        synopsis = (TextView) findViewById(R.id.synopis_text_view);

        recyclerView = findViewById(R.id.recyclerView_trailers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        if (bundle != null){
            movieId = bundle.getInt("id");
            releaseDate.setText(bundle.getString("date"));
            rated_movie.setText(String.valueOf(bundle.getFloat("voteAverage")) + " / 10");
            synopsis.setText(bundle.getString("overview"));

            urlPhoto = bundle.getString("photo");
            tittle = bundle.getString("tittle");
        }

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle(tittle);

        imageView = (ImageView) findViewById(R.id.image_movie_poster_details);
        Picasso.get().load(urlPhoto).into(imageView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        detailsViewModel.callApi(movieId);


        detailsViewModel.getMoviesLiveData().observe(this, new Observer<List<MovieTrailer>>() {
            @Override
            public void onChanged(List<MovieTrailer> movieTrailers) {
                recyclerView.setVisibility(View.VISIBLE);
                DetailsTrailerListAdapter movieTrailerAdapter = new DetailsTrailerListAdapter(getBaseContext(), movieTrailers);
                recyclerView.setAdapter(movieTrailerAdapter);
            }
        });

        detailsViewModel.getNoTrailer().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                linearLayout = (LinearLayout) findViewById(R.id.linearVideoAvalable);
                linearLayout.setVisibility(View.VISIBLE);
            }
        });

    }

    public void viewMovieComments(View view) {
        startActivity(new Intent(this, CommentActivity.class).putExtra("id", movieId));
        //Toast.makeText(this, "Open a dialog here", Toast.LENGTH_SHORT).show();
    }
}
