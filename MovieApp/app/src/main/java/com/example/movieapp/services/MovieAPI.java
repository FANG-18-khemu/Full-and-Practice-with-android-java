package com.example.movieapp.services;

import com.example.movieapp.models.Results;

import javax.annotation.Generated;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieAPI {

    //implement the methods to get,post etc http request
    //Aoi key ea22a9b99401d4cc9b7152acdd6ebf19
    //base Url
    //https://api.themoviedb.org/3/
    //End point urlmovie/popular?api_key=<<api_key>>&language=en-US&page=1
    //

    @GET("movie/popular")
    Call<Results> getPopularMovie(@Query("api_key") String apikey);
}
