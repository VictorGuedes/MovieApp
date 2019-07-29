package com.example.android.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.android.movieapp.model.Results;
import com.example.android.movieapp.model.response.MovieResponse;
import com.example.android.movieapp.view.MoviePosterAdapter;
import com.example.android.movieapp.viewModel.MoviesViewModel;

public class MainActivity extends AppCompatActivity {

    private MoviesViewModel moviesViewModel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);

        final MoviePosterAdapter moviePosterAdapter = new MoviePosterAdapter(this);


        moviesViewModel.getMoviePagedList().observe(this, new Observer<PagedList<Results>>() {
            @Override
            public void onChanged(PagedList<Results> results) {
                moviePosterAdapter.submitList(results);
            }
        });

        recyclerView.setAdapter(moviePosterAdapter);
    }
}
