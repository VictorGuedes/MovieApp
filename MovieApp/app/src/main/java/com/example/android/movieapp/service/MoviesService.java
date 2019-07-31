package com.example.android.movieapp.service;

import com.example.android.movieapp.model.response.MovieResponse;
import com.example.android.movieapp.model.response.MovieTrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesService {

    // Get popular Movies
    @GET("popular?")
    Call <MovieResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    // Get Top Rated Movies
    @GET("top_rated?")
    Call <MovieResponse> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );

    // Get Movie Trailer
    @GET("{movieid}/videos?")
    Call <MovieTrailerResponse> getMovieTrailers(
            @Path("movieid") int movieid,
            @Query("api_key") String apiKey
    );

}
