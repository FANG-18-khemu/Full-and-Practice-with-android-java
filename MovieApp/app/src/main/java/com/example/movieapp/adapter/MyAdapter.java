package com.example.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.databinding.MovieListItemBinding;
import com.example.movieapp.models.MovieModel;
import com.example.movieapp.movie_activity;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<MovieModel> movies;
    private Context context;

    public MyAdapter(ArrayList<MovieModel> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private MovieListItemBinding movieListItemBinding;

        public MyViewHolder(MovieListItemBinding movieListItemBinding) {
            super(movieListItemBinding.getRoot());
            this.movieListItemBinding = movieListItemBinding;

            movieListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                    {
                        MovieModel movie = movies.get(position);
                        Intent i = new Intent(context, movie_activity.class);
                        i.putExtra("Movie",movie);
                        context.startActivity(i);
                    }
                }
            });
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieListItemBinding movieListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_list_item,parent,false);
        return new MyViewHolder(movieListItemBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            MovieModel movieModel = movies.get(position);
            holder.movieListItemBinding.setMovie(movieModel);
    }

    @Override
    public int getItemCount() {
        return movies==null?0:movies.size();
    }


}
