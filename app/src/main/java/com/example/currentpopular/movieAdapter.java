package com.example.currentpopular;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.currentpopular.models.MovieModel;

import java.util.List;

public class movieAdapter extends RecyclerView.Adapter<movieAdapter.MovieHolder> {

    private Context context;
    private List<MovieModel> movielist;
    public movieAdapter(Context context,List<MovieModel> movies){
        this.context = context;
        movielist = movies;
    }

    public movieAdapter(MovieModel movieModel) {

    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
       return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
MovieModel movie = movielist.get(position);
holder.rating.setText(String.valueOf( movie.getVote_average()));
holder.title.setText(movie.getTitle());
holder.overview.setText(movie.getMovie_overview());
holder.releasedate.setText(movie.getRelease_date());
//holder.id.setText(movie.getMovie_id());
        Glide.with(context).load(movie.getPoster_path()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return movielist.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title,overview,rating,releasedate,id;
        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.name);
            overview= itemView.findViewById(R.id.description);
           rating= itemView.findViewById(R.id.imbdrating);
           releasedate = itemView.findViewById(R.id.releasedte);
         //  id = itemView.findViewById(R.id.id);
        }
    }
}
