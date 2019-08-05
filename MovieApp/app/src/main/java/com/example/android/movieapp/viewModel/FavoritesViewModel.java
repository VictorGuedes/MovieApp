package com.example.android.movieapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.movieapp.model.Results;
import com.example.android.movieapp.repository.FavoritesRepository;

import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {

    private FavoritesRepository favoritesRepository;
    private LiveData<List<Results>> allFavoriteMovies;
    private LiveData<List<Results>> favoriteById;

    public FavoritesViewModel(@NonNull Application application) {
        super(application);
        favoritesRepository = new FavoritesRepository(application);

    }

    public LiveData<List<Results>> getAllFavoriteMovies() {
        return allFavoriteMovies;
    }

    public LiveData<List<Results>> getFavoriteById() {
        return favoriteById;
    }

    public void insert(Results movie) {
        favoritesRepository.insert(movie);
    }

    public void deleteMovie(Results movie){
        favoritesRepository.deleteMovie(movie);
    }

    public void getMovieById(Results movie){
        favoritesRepository.getMovieByid(movie);
        favoriteById = favoritesRepository.getFavoriteById();
    }

    public void getAllMovies(){
        favoritesRepository.getAllMovies();
        allFavoriteMovies = favoritesRepository.getAllFavoriteMovies();
    }

}
