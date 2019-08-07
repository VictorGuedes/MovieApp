package com.example.android.movieapp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.android.movieapp.model.Results;

public abstract class DataSourcee extends PageKeyedDataSource<Integer, Results> {

    private MutableLiveData networkState = new MutableLiveData();

    public MutableLiveData getNetworkState() {
        return networkState;
    }



    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Results> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Results> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Results> callback) {

    }
}
