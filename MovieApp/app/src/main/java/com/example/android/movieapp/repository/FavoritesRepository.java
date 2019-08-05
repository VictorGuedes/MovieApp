package com.example.android.movieapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.android.movieapp.model.Results;
import com.example.android.movieapp.model.local.MovieDao;
import com.example.android.movieapp.model.local.MovieRoomDatabase;

import java.util.List;

public class FavoritesRepository {

    private MovieDao movieDao;
    private LiveData<List<Results>> allFavoriteMovies;
    private LiveData<List<Results>> favoriteById;

    public FavoritesRepository(Application application){
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        movieDao = db.movieDao();
    }

    public LiveData<List<Results>> getAllFavoriteMovies() {
        return allFavoriteMovies;
    }

    public LiveData<List<Results>> getFavoriteById() {
        return favoriteById;
    }

    public void getAllMovies(){
        allFavoriteMovies = movieDao.getAllFavoriteMovies();
    }

    public void getMovieByid(Results movie){
        favoriteById = movieDao.getMovieById(movie.getId());
    }

    public void insert(Results movie){
        new InsertAsyncTask(movieDao).execute(movie);
    }

    public static class InsertAsyncTask extends AsyncTask<Results, Void, Void>{

        private MovieDao movieDaoAsync;

        InsertAsyncTask(MovieDao movieDao){
            movieDaoAsync = movieDao;
        }


        @Override
        protected Void doInBackground(Results... results) {
            movieDaoAsync.insert(results[0]);
            return null;
        }
    }

    public void deleteMovie(Results movie){
        new DeleteAsyncTask(movieDao).execute(movie);
    }

    public static class DeleteAsyncTask extends AsyncTask<Results, Void, Void>{

        private MovieDao movieDaoAsync;

        DeleteAsyncTask(MovieDao movieDao){
            movieDaoAsync = movieDao;
        }


        @Override
        protected Void doInBackground(Results... results) {
            movieDaoAsync.deletMovie(results[0]);
            return null;
        }
    }

}
