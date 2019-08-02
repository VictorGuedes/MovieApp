package com.example.android.movieapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding activityDetailsBinding;
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

        if (bundle != null){
            movieId = bundle.getInt("id");
            activityDetailsBinding.releaseDateTextView.setText(bundle.getString("date"));
            activityDetailsBinding.movieRatedTextView.setText(String.valueOf(bundle.getFloat("voteAverage")) + " / 10");
            activityDetailsBinding.synopisTextView.setText(bundle.getString("overview"));
            activityDetailsBinding.toolbarLayout.setTitle(bundle.getString("tittle"));

            urlPhoto = bundle.getString("photo");
            Picasso.get().load(urlPhoto).into(activityDetailsBinding.imageMoviePosterDetails);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void viewMovieComments(View view) {
        startActivity(new Intent(this, CommentActivity.class).putExtra("id", movieId));
    }

    public void viewMovieTrailers(View view) {
        startActivity(new Intent(this, TrailersActivity.class).putExtra("id", movieId));
    }
}
