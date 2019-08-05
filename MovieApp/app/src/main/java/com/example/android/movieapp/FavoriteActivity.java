package com.example.android.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.android.movieapp.databinding.ActivityFavoriteBinding;
import com.example.android.movieapp.model.Results;
import com.example.android.movieapp.view.FavoriterPostAdapter;
import com.example.android.movieapp.viewModel.FavoritesViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    private FavoritesViewModel favoritesViewModel;
    private RecyclerView recyclerView;
    private ActivityFavoriteBinding favoriteBinding;
    private FavoriterPostAdapter favoriterPostAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        favoriteBinding = DataBindingUtil.setContentView(this, R.layout.activity_favorite);

        recyclerView = favoriteBinding.recyclerViewFavorites;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        favoritesViewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);

        favoriterPostAdapter = new FavoriterPostAdapter();

        favoritesViewModel.getAllMovies();

        favoritesViewModel.getAllFavoriteMovies().observe(this, new Observer<List<Results>>() {
            @Override
            public void onChanged(List<Results> results) {

                if (results.size() == 0){
                    favoriteBinding.noFavoritesTextView.setVisibility(View.VISIBLE);
                }else {
                    favoriterPostAdapter.setFavorites((ArrayList<Results>) results);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setAdapter(favoriterPostAdapter);
                }
            }
        });


    }
}
