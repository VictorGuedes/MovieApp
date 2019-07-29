package com.example.android.movieapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.android.movieapp.model.response.MovieResponse;
import com.example.android.movieapp.repository.MoviesRepository;

public class MoviesViewModel extends AndroidViewModel {

    private MoviesRepository moviesRepository;
    private MutableLiveData<MovieResponse> moviesMutableLiveData;

    public MoviesViewModel(@NonNull Application application) {
        super(application);
        moviesRepository = new MoviesRepository(application);

        // Teste call por aqui
        moviesRepository.callPopularMovies(1);

        moviesMutableLiveData = moviesRepository.getMoviesLiveData();
    }

    public MutableLiveData<MovieResponse> getMovieMutableLiveData() {
        return moviesMutableLiveData;
    }



}
