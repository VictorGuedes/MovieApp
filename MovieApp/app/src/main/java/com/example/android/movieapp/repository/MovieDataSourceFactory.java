package com.example.android.movieapp.repository;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.android.movieapp.model.Results;

public class MovieDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Results>> moviesLiveData = new MutableLiveData<>();

    @Override
    public DataSource<Integer, Results> create() {

        MovieDataSource movieDataSource = new MovieDataSource();
        moviesLiveData.postValue(movieDataSource);

        return movieDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Results>> getMoviesLiveData() {
        return moviesLiveData;
    }
}
