package com.example.moviepaggingapp.uttil;





import com.example.moviepaggingapp.model.Movies;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class MovieComparater extends DiffUtil.ItemCallback<Movies> {


    @Override
    public boolean areItemsTheSame(@NonNull Movies oldItem, @NonNull Movies newItem) {

        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Movies oldItem, @NonNull Movies newItem) {
        return oldItem.getId() == newItem.getId();
    }
}
