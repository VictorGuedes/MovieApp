package com.example.android.movieapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.android.movieapp.model.Results;
import com.example.android.movieapp.repository.MovieDataSource;
import com.example.android.movieapp.repository.MovieDataSourceFactory;

public class MoviesViewModel extends AndroidViewModel {

    private LiveData<PagedList<Results>> moviePagedList;
    private LiveData<PageKeyedDataSource<Integer, Results>> liveDataSource;

    public MoviesViewModel(@NonNull Application application) {
        super(application);

        MovieDataSourceFactory movieDataSourceFactory = new MovieDataSourceFactory();
        liveDataSource = movieDataSourceFactory.getMoviesLiveData();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(MovieDataSource.pageSize).build();

        moviePagedList = (new LivePagedListBuilder(movieDataSourceFactory, pagedListConfig)).build();
    }

    public LiveData<PagedList<Results>> getMoviePagedList() {
        return moviePagedList;
    }
}
