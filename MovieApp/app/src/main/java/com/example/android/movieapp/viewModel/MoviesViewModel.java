package com.example.android.movieapp.viewModel;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import androidx.preference.PreferenceManager;

import com.example.android.movieapp.R;
import com.example.android.movieapp.model.Results;
import com.example.android.movieapp.repository.MovieDataSourceFactory;

public class MoviesViewModel extends AndroidViewModel implements SharedPreferences.OnSharedPreferenceChangeListener {

    private LiveData<PagedList<Results>> moviePagedList;
    private LiveData<PageKeyedDataSource<Integer, Results>> liveDataSource;
    private MovieDataSourceFactory movieDataSourceFactory;

    public MoviesViewModel(@NonNull Application application) {
        super(application);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication().getBaseContext());
        String text = sharedPreferences.getString(application.getResources().getString(R.string.pref_search_key), "");
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        movieDataSourceFactory = new MovieDataSourceFactory();

        callApi(text);
    }

    public LiveData<PagedList<Results>> getMoviePagedList() {
        return moviePagedList;
    }

    public LiveData<PageKeyedDataSource<Integer, Results>> getLiveDataSource() {
        return liveDataSource;
    }


    public void callApi(String text){
        movieDataSourceFactory.setText(text);
        liveDataSource = movieDataSourceFactory.getMoviesLiveData();
        PagedList.Config pagedListConfig = new PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(20).build();

        moviePagedList = (new LivePagedListBuilder(movieDataSourceFactory, pagedListConfig)).build();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        String text = sharedPreferences.getString(s, "");
        liveDataSource.getValue().invalidate();
        callApi(text);
    }

}
