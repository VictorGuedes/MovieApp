package com.example.android.movieapp.viewModel;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import androidx.preference.PreferenceManager;

import com.example.android.movieapp.R;
import com.example.android.movieapp.model.Results;
import com.example.android.movieapp.model.service.NetworkState;
import com.example.android.movieapp.repository.DataSourcee;
import com.example.android.movieapp.repository.MovieDataSourceFactory;
import com.example.android.movieapp.repository.PopularMovieDataSource;

public class MoviesViewModel extends AndroidViewModel implements SharedPreferences.OnSharedPreferenceChangeListener {

    private LiveData<PagedList<Results>> moviePagedList;
    private LiveData<DataSourcee> liveDataSource;
    private MovieDataSourceFactory movieDataSourceFactory;
    private LiveData<NetworkState> networkState;


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

    public LiveData<DataSourcee> getLiveDataSource() {
        return liveDataSource;
    }

    public LiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    public void callApi(String text){
        movieDataSourceFactory.setContext(getApplication().getBaseContext());
        movieDataSourceFactory.setText(text);
        liveDataSource = movieDataSourceFactory.getMoviesLiveData();

        networkState = Transformations.switchMap(liveDataSource,  liveDataSource -> liveDataSource.getNetworkState());

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
