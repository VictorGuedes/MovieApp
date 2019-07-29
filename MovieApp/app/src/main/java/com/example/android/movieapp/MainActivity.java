package com.example.android.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.example.android.movieapp.model.response.MovieResponse;
import com.example.android.movieapp.viewModel.MoviesViewModel;

public class MainActivity extends AppCompatActivity {

    private MoviesViewModel moviesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);

        moviesViewModel.getMovieMutableLiveData().observe(this, new Observer<MovieResponse>() {
            @Override
            public void onChanged(MovieResponse movieResponse) {
                Log.d("Reponse", movieResponse.getMovieResults().get(0).getTittle());
            }
        });
    }
}
