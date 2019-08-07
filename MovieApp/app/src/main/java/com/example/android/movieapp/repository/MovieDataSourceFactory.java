package com.example.android.movieapp.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.android.movieapp.model.Results;

public class MovieDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<DataSourcee> moviesLiveData = new MutableLiveData<>();
    private String text;
    private Context context;


    @Override
    public DataSource<Integer, Results> create() {

        DataSourcee movieDataSource = selectMovieDataSource(text);

        moviesLiveData.postValue(movieDataSource);

        return movieDataSource;
    }

    public MutableLiveData<DataSourcee> getMoviesLiveData() {
        return moviesLiveData;
    }

    private DataSourcee selectMovieDataSource(String type){
        DataSourcee dataSource;

        if (type.equals("Popular movies") || type.isEmpty()){
            dataSource = new PopularMovieDataSource(context);
        } else {
            dataSource = new TopRatedMovieDataSource(context);
        }

        return dataSource;
    }


    public void setContext(Context context) {
        this.context = context;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
