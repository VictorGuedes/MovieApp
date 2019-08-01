package com.example.android.movieapp.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.movieapp.R;
import com.example.android.movieapp.model.MovieTrailer;
import com.example.android.movieapp.service.ApiService;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailsTrailerListAdapter extends RecyclerView.Adapter<DetailsTrailerListAdapter.DetailsTrailerViewHolder> {

    private Context context;
    private List<MovieTrailer> movieTrailers;

    public DetailsTrailerListAdapter(Context context, List<MovieTrailer> movieTrailers){
        this.context = context;
        this.movieTrailers = movieTrailers;
    }

    @NonNull
    @Override
    public DetailsTrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item_movie_trailers, parent, false);
        return new DetailsTrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsTrailerViewHolder holder, int position) {
        final MovieTrailer movieTrailer = movieTrailers.get(position);
        if (movieTrailer != null){
            holder.nameTrailer.setText(movieTrailer.getName());

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + movieTrailer.getKeyYoutube()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return movieTrailers.size();
    }

    class DetailsTrailerViewHolder extends RecyclerView.ViewHolder{
        TextView nameTrailer;
        LinearLayout linearLayout;

        public DetailsTrailerViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.itemClicked);
            nameTrailer = (TextView) itemView.findViewById(R.id.name_trailer_text_view);
        }
    }

}
