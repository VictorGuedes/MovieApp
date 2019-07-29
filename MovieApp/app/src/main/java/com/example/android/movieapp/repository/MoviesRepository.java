package com.example.android.movieapp.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.movieapp.model.response.MovieResponse;
import com.example.android.movieapp.service.ApiService;
import com.example.android.movieapp.service.MoviesService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {

    private MoviesService moviesService;
    private MutableLiveData<MovieResponse> moviesLiveData = new MutableLiveData<>();

    public MoviesRepository(Application application){
        moviesService =  ApiService.retrofitBuild.create(MoviesService.class);
    }

    public MutableLiveData<MovieResponse> getMoviesLiveData() {
        return moviesLiveData;
    }

    public void callPopularMovies(int page){
        Call<MovieResponse> call = moviesService.getPopularMovies(
                ApiService.apiKey,
                page
        );

        makeRequest(call);
    }

    public void callTopRatedMovies(int page){
        Call<MovieResponse> call = moviesService.getTopRatedMovies(
                ApiService.apiKey,
                page
        );

        makeRequest(call);
    }

    private void makeRequest(Call<MovieResponse> call){
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                moviesLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("LOG-FALHOU", t.getMessage());
            }
        });
    }

}
