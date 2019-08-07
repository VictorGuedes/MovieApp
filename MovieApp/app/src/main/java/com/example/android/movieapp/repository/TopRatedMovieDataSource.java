package com.example.android.movieapp.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.android.movieapp.model.Results;
import com.example.android.movieapp.model.response.MovieResponse;
import com.example.android.movieapp.model.service.ApiService;
import com.example.android.movieapp.model.service.MoviesService;
import com.example.android.movieapp.model.service.NetworkConnectionInterceptor;
import com.example.android.movieapp.model.service.NetworkState;
import com.example.android.movieapp.model.service.Status;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRatedMovieDataSource extends DataSourcee {

    private int firstPage = 1;

    private Context context;

    public TopRatedMovieDataSource(Context context){
        this.context = context;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Results> callback) {
        MoviesService moviesService = ApiService.getRetrofitInstance(context).create(MoviesService.class);

        moviesService.getTopRatedMovies(ApiService.apiKey, firstPage).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.code() == 200){
                    callback.onResult(response.body().getMovieResults(), null, firstPage+1);
                    getNetworkState().postValue(new NetworkState(Status.SUCCESS, response.message()));
                }  else {
                    getNetworkState().postValue(new NetworkState(Status.FAILED, response.message()));
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                /*if (t instanceof NetworkConnectionInterceptor.NoConnectivityException){
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                }*/
                String errorMessage = t == null ? "unknown error" : t.getMessage();
                getNetworkState().postValue(new NetworkState(Status.FAILED, errorMessage));
            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Results> callback) {
        MoviesService moviesService = ApiService.getRetrofitInstance(context).create(MoviesService.class);

        moviesService.getTopRatedMovies(ApiService.apiKey, params.key).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                if (response.isSuccessful() && response.code() == 200){
                    callback.onResult(response.body().getMovieResults(), adjacentKey);
                    getNetworkState().postValue(new NetworkState(Status.SUCCESS, response.message()));
                } else {
                    getNetworkState().postValue(new NetworkState(Status.FAILED, response.message()));
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                /*if (t instanceof NetworkConnectionInterceptor.NoConnectivityException){
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                }*/
                String errorMessage = t == null ? "unknown error" : t.getMessage();
                getNetworkState().postValue(new NetworkState(Status.FAILED, errorMessage));
            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Results> callback) {
        MoviesService moviesService = ApiService.getRetrofitInstance(context).create(MoviesService.class);

        moviesService.getTopRatedMovies(ApiService.apiKey, params.key).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful() && response.code() == 200){
                    Integer key = (params.key < response.body().getTotalPages()) ? params.key + 1 : null;
                    callback.onResult(response.body().getMovieResults(), key);
                    getNetworkState().postValue(new NetworkState(Status.SUCCESS, response.message()));
                } else {
                    getNetworkState().postValue(new NetworkState(Status.FAILED, response.message()));
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                /*if (t instanceof NetworkConnectionInterceptor.NoConnectivityException){
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                }*/
                String errorMessage = t == null ? "unknown error" : t.getMessage();
                getNetworkState().postValue(new NetworkState(Status.FAILED, errorMessage));
            }
        });
    }



}
