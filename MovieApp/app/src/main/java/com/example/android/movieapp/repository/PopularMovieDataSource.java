package com.example.android.movieapp.repository;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.android.movieapp.model.Results;
import com.example.android.movieapp.model.response.MovieResponse;
import com.example.android.movieapp.service.ApiService;
import com.example.android.movieapp.service.MoviesService;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularMovieDataSource extends PageKeyedDataSource<Integer, Results> {

    private int firstPage = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Results> callback) {
        MoviesService moviesService = ApiService.getRetrofitInstance().create(MoviesService.class);

        moviesService.getPopularMovies(ApiService.apiKey, firstPage).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.body() != null){
                    callback.onResult(response.body().getMovieResults(), null, firstPage+1);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Results> callback) {
        MoviesService moviesService = ApiService.getRetrofitInstance().create(MoviesService.class);

        moviesService.getPopularMovies(ApiService.apiKey, params.key).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                if (response.body() != null){
                    callback.onResult(response.body().getMovieResults(), adjacentKey);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Results> callback) {
        MoviesService moviesService = ApiService.getRetrofitInstance().create(MoviesService.class);

        moviesService.getPopularMovies(ApiService.apiKey, params.key).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.body() != null){
                    Integer key = (params.key < response.body().getTotalPages()) ? params.key + 1 : null;
                    callback.onResult(response.body().getMovieResults(), key);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }


}
