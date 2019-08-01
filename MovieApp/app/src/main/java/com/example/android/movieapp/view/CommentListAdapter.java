package com.example.android.movieapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.movieapp.R;
import com.example.android.movieapp.databinding.RecyclerItemMovieCommentBinding;
import com.example.android.movieapp.model.Comment;

import java.util.ArrayList;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentViewHolder> {

    private ArrayList<Comment> comments;

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerItemMovieCommentBinding itemMovieCommentBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recycler_item_movie_comment, parent, false);

        return new CommentViewHolder(itemMovieCommentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.itemMovieCommentBinding.setComment(comment);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public void setComments(ArrayList<Comment> comments){
        this.comments = comments;
        notifyDataSetChanged();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder{

        private RecyclerItemMovieCommentBinding itemMovieCommentBinding;

        public CommentViewHolder(@NonNull RecyclerItemMovieCommentBinding itemMovieCommentBinding) {
            super(itemMovieCommentBinding.getRoot());
            this.itemMovieCommentBinding = itemMovieCommentBinding;
        }
    }

}
