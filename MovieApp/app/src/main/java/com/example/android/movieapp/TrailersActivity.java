package com.example.android.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.android.movieapp.databinding.ActivityTrailersBinding;
import com.example.android.movieapp.model.MovieTrailer;
import com.example.android.movieapp.view.DetailsTrailerListAdapter;
import com.example.android.movieapp.viewModel.DetailsViewModel;

import java.util.ArrayList;
import java.util.List;

public class TrailersActivity extends AppCompatActivity {

    private DetailsViewModel detailsViewModel;
    private LinearLayout linearLayout;
    private int movieId = 0;
    private RecyclerView recyclerView;
    private ActivityTrailersBinding activityTrailersBinding;
    private DetailsTrailerListAdapter movieTrailerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailers);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            movieId = bundle.getInt("id");
        }

        activityTrailersBinding = DataBindingUtil.setContentView(this, R.layout.activity_trailers);

        recyclerView = activityTrailersBinding.recyclerViewTrailers;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        movieTrailerAdapter = new DetailsTrailerListAdapter();

        detailsViewModel.callApi(movieId);

        detailsViewModel.getMoviesLiveData().observe(this, new Observer<List<MovieTrailer>>() {
            @Override
            public void onChanged(List<MovieTrailer> movieTrailers) {

                movieTrailerAdapter.setTrailers((ArrayList<MovieTrailer>) movieTrailers);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setAdapter(movieTrailerAdapter);
            }
        });

        detailsViewModel.getNoTrailer().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                linearLayout = activityTrailersBinding.linearVideoAvalable;
                linearLayout.setVisibility(View.VISIBLE);
            }
        });

    }
}
