package com.example.moviepaggingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.moviepaggingapp.databinding.SingleMovieItemBinding;
import com.example.moviepaggingapp.model.Movies;

import kotlinx.coroutines.CoroutineDispatcher;

public class MovieAdapter extends PagingDataAdapter<Movies,MovieAdapter.MyViewHolder> {

    public static final int LOADING_ITEM = 0;
    public static final int MOVIE_ITEM=1;

    RequestManager glid;

    public MovieAdapter(@NonNull DiffUtil.ItemCallback<Movies> diffCallback,RequestManager glid) {
        super(diffCallback);
        this.glid = glid;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new MyViewHolder(SingleMovieItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Movies movie= getItem(position);
            if(movie != null)
            {
                glid.load("https://image.tmdb.org/t/p/w500/"+movie.getPoster_path()).into(holder.singleMovieItemBinding.imageViewMovie);
                holder.singleMovieItemBinding.textViewRating.setText(String.valueOf(movie.getVote_average()));
            }
    }

    @Override
    public int getItemViewType(int position) {
        return position ==getItemCount()?MOVIE_ITEM:LOADING_ITEM;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        SingleMovieItemBinding singleMovieItemBinding;

        public MyViewHolder( SingleMovieItemBinding singleMovieItemBinding) {
            super(singleMovieItemBinding.getRoot());
            this.singleMovieItemBinding = singleMovieItemBinding;
        }


    }
}
