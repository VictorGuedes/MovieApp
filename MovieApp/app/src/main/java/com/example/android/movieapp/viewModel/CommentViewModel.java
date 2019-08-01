package com.example.android.movieapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.android.movieapp.model.Comment;
import com.example.android.movieapp.model.response.CommentsResponse;
import com.example.android.movieapp.service.ApiService;
import com.example.android.movieapp.service.MoviesService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentViewModel extends AndroidViewModel {

    private MutableLiveData<List<Comment>> commentsLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> noComment= new MutableLiveData<>();


    public CommentViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Comment>> getCommentsLiveData() {
        return commentsLiveData;
    }

    public MutableLiveData<Boolean> getNoComment() {
        return noComment;
    }

    public void callApi(int movieId){
        MoviesService moviesService = ApiService.getRetrofitInstance().create(MoviesService.class);

        moviesService.getMovieComments(movieId, ApiService.apiKey).enqueue(new Callback<CommentsResponse>() {
            @Override
            public void onResponse(Call<CommentsResponse> call, Response<CommentsResponse> response) {
                if (response.body().getComments().size() > 0){
                    commentsLiveData.setValue(response.body().getComments());
                } else {
                    noComment.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<CommentsResponse> call, Throwable t) {

            }
        });
    }

}
