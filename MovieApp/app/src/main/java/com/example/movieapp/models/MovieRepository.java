package com.example.movieapp.models;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.R;
import com.example.movieapp.services.MovieAPI;
import com.example.movieapp.services.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private ArrayList<MovieModel>  movies = new ArrayList<>();
    private Application application;
    private MutableLiveData<List<MovieModel>> mutableLiveData = new MutableLiveData<>();

    public MovieRepository(Application application) {
        this.application = application;
    }


    public MutableLiveData<List<MovieModel>> getAllPopulatMovies()
    {
        MovieAPI obj = RetrofitInstance.getRetorfitInstance();
        Call<Results> resultsCall = obj.getPopularMovie(application.getApplicationContext().getString(R.string.api_key));
        resultsCall.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                Results results = response.body();
                if(results!=null && results.getResults()!=null)
                {
                    movies = (ArrayList<MovieModel>) results.getResults();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }
}
