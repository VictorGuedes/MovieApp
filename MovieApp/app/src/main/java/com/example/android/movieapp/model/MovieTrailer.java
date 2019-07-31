package com.example.android.movieapp.model;

import com.google.gson.annotations.SerializedName;

public class MovieTrailer {

    @SerializedName("key")
    private String keyYoutube;

    @SerializedName("site")
    private String site;

    public String getKeyYoutube() {
        return keyYoutube;
    }

    public String getSite() {
        return site;
    }
}
