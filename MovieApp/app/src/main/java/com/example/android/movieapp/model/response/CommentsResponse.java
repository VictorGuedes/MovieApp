package com.example.android.movieapp.model.response;

import com.example.android.movieapp.model.Comment;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentsResponse  {

    @SerializedName("results")
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }
}
