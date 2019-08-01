package com.example.android.movieapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.android.movieapp.databinding.ActivityMainBinding;
import com.example.android.movieapp.model.Comment;
import com.example.android.movieapp.model.Results;
import com.example.android.movieapp.model.response.MovieResponse;
import com.example.android.movieapp.view.MoviePosterAdapter;
import com.example.android.movieapp.view.SettingsActivity;
import com.example.android.movieapp.viewModel.DetailsViewModel;
import com.example.android.movieapp.viewModel.MoviesViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private MoviesViewModel moviesViewModel;
    private RecyclerView recyclerView;
    private ActivityMainBinding activityMainBinding;
    private MoviePosterAdapter moviePosterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        recyclerView = activityMainBinding.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);

        moviePosterAdapter = new MoviePosterAdapter(this);

        moviesViewModel.getMoviePagedList().observe(this, new Observer<PagedList<Results>>() {
            @Override
            public void onChanged(PagedList<Results> results) {
                moviePosterAdapter.submitList(results);
                recyclerView.setAdapter(moviePosterAdapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.setting_action){
            startActivity(new Intent(this, SettingsActivity.class));
        }

        return true;
    }
}
