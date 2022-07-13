package com.example.countrynameapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit=null;
    private static String BASE_URL="https://api.printful.com/";

    //Singanton

    public static CreateCountryDataService getService(){
        if(retrofit ==null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit.create(CreateCountryDataService.class);
    }
}
