package com.example.android.movieapp.model;

import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("author")
    private String author;

    @SerializedName("content")
    private String content;

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
