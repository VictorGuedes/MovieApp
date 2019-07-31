package com.example.android.movieapp.model.response;

import com.example.android.movieapp.model.MovieTrailer;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieTrailerResponse {

    @SerializedName("results")
    private List<MovieTrailer> movieTrailers;

    public List<MovieTrailer> getMovieTrailers() {
        return movieTrailers;
    }
}
