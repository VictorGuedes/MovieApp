package com.example.android.movieapp.model.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.android.movieapp.model.Results;

import java.util.List;
@Dao
public interface MovieDao {

    @Insert
    void insert(Results results);

    @Delete
    void deletMovie(Results movie);

    @Query("Select * from movies")
    LiveData<List<Results>> getAllFavoriteMovies();

    @Query("Select * from movies where id = :id")
    LiveData<List<Results>> getMovieById(int id);

}
