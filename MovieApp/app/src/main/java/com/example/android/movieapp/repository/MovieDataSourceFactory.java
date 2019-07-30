package com.example.android.movieapp.repository;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.android.movieapp.model.Results;

public class MovieDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Results>> moviesLiveData = new MutableLiveData<>();
    private String text;

    @Override
    public DataSource<Integer, Results> create() {

        PageKeyedDataSource movieDataSource = selectMovieDataSource(text);
        moviesLiveData.postValue(movieDataSource);

        return movieDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Results>> getMoviesLiveData() {
        return moviesLiveData;
    }

    private PageKeyedDataSource selectMovieDataSource(String type){
        PageKeyedDataSource dataSource;

        if (type.equals("Popular movies")){
            dataSource = new PopularMovieDataSource();
        } else {
            dataSource = new TopRatedMovieDataSource();
        }

        return dataSource;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
