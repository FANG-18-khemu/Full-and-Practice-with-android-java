package com.example.countrynameapp;

import com.example.countrynameapp.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CreateCountryDataService {

    @GET("countries")
    Call<Result> getCountry();

}
