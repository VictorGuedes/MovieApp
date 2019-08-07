package com.example.android.movieapp.viewModel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.android.movieapp.model.MovieTrailer;
import com.example.android.movieapp.model.response.MovieTrailerResponse;
import com.example.android.movieapp.model.service.ApiService;
import com.example.android.movieapp.model.service.MoviesService;
import com.example.android.movieapp.model.service.NetworkConnectionInterceptor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsViewModel extends AndroidViewModel {

    private MutableLiveData<List<MovieTrailer>> moviesLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> noTrailer = new MutableLiveData<>();


    public DetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<MovieTrailer>> getMoviesLiveData() {
        return moviesLiveData;
    }

    public MutableLiveData<Boolean> getNoTrailer() {
        return noTrailer;
    }

    public void callApi(int movieId){
        MoviesService moviesService = ApiService.getRetrofitInstance(getApplication().getBaseContext()).create(MoviesService.class);

        moviesService.getMovieTrailers(movieId, ApiService.apiKey).enqueue(new Callback<MovieTrailerResponse>() {
            @Override
            public void onResponse(Call<MovieTrailerResponse> call, Response<MovieTrailerResponse> response) {

                if (response.body() != null){
                    if (response.body().getMovieTrailers().size() > 0){
                        moviesLiveData.setValue(response.body().getMovieTrailers());
                    } else {
                        noTrailer.setValue(true);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieTrailerResponse> call, Throwable t) {
                Log.d("Falhou-Trailer", t.getMessage());
                if (t instanceof NetworkConnectionInterceptor.NoConnectivityException){
                    Toast.makeText(getApplication().getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
