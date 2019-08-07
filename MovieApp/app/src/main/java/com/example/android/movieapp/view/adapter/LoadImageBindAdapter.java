
package com.example.android.movieapp.view.adapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.android.movieapp.model.service.ApiService;
import com.squareup.picasso.Picasso;

public class LoadImageBindAdapter {

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.get()
                .load(ApiService.basePosterUrl + imageUrl)
                .into(view);
    }

    @BindingAdapter("UrlYouTube")
    public static void loadImageYouTube(ImageView view, String videoID){
        Picasso.get()
                .load(ApiService.youTubeImageFrameURL + videoID + "/0.jpg")
                .fit()
                .centerCrop()
                .into(view);

    }
}
