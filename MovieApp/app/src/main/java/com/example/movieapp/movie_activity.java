package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.movieapp.databinding.ActivityMovieBinding;
import com.example.movieapp.models.MovieModel;

public class movie_activity extends AppCompatActivity {

    private ActivityMovieBinding activityMovieBinding;
    private MovieModel movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activityMovieBinding = DataBindingUtil.setContentView(this,R.layout.activity_movie);
        Intent i = getIntent();

        if (i != null) {
            movie = i.getParcelableExtra("Movie");
            activityMovieBinding.setMovie(movie);
            getSupportActionBar().setTitle(movie.getTitle());

        }

    }
}