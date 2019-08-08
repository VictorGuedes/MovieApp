package com.example.android.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.android.movieapp.databinding.ActivityCommentBinding;
import com.example.android.movieapp.model.Comment;
import com.example.android.movieapp.view.CommentListAdapter;
import com.example.android.movieapp.viewModel.CommentViewModel;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {

    private CommentViewModel commentViewModel;
    private CommentListAdapter commentListAdapter;
    private RecyclerView recyclerView;
    private ActivityCommentBinding activityCommentBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        int movieId = 0;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            movieId = bundle.getInt("id");
        }

        activityCommentBinding = DataBindingUtil.setContentView(this, R.layout.activity_comment);

        recyclerView = activityCommentBinding.recyclerViewMovieComment;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        commentViewModel = ViewModelProviders.of(this).get(CommentViewModel.class);
        commentListAdapter = new CommentListAdapter();

        commentViewModel.callApi(movieId);
        getAllComments();
    }

    private void getAllComments(){

        commentViewModel.getCommentsLiveData().observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                commentListAdapter.setComments((ArrayList<Comment>) comments);
                recyclerView.setAdapter(commentListAdapter);
                activityCommentBinding.progressComments.setVisibility(View.GONE);
            }
        });

        commentViewModel.getNoComment().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                activityCommentBinding.progressComments.setVisibility(View.GONE);
                activityCommentBinding.commentTextView.setText(getString(R.string.no_comments_movies_text));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
