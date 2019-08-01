package com.example.android.movieapp.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.android.movieapp.service.ApiService;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

public class Results {

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("id")
    private int id;

    @SerializedName("vote_average")
    private float voteAvarage;

    @SerializedName("title")
    private String tittle;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTittle;

    @SerializedName("overview")
    private String synopsisMovie;

    @SerializedName("release_date")
    private String releaseDate;

    public int getVoteCount() {
        return voteCount;
    }

    public int getId() {
        return id;
    }

    public float getVoteAvarage() {
        return voteAvarage;
    }

    public String getTittle() {
        return tittle;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTittle() {
        return originalTittle;
    }

    public String getSynopsisMovie() {
        return synopsisMovie;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
