package com.example.moviepaggingapp.api;

import static com.example.moviepaggingapp.uttil.utils.API_KEY;
import static com.example.moviepaggingapp.uttil.utils.BASE_URL;

import com.example.moviepaggingapp.model.MovieResponse;
import com.example.moviepaggingapp.model.Movies;
import com.example.moviepaggingapp.uttil.utils;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiRetrofit {



    private static ApiInterface apiInterface;

    //singleton

    public static ApiInterface getRetrofit(){
        if(apiInterface ==null)
        {
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.addInterceptor(chain -> {
                Request original = chain.request();
                HttpUrl originalUrl = original.url();
                HttpUrl url = originalUrl.newBuilder().addQueryParameter("api_key",utils.API_KEY).build();
                Request.Builder requestBuilder = original.newBuilder().url(url);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            });

            Retrofit retrofit = new Retrofit.Builder().baseUrl(utils.BASE_URL).client(client.build()).addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();

            apiInterface = retrofit.create(ApiInterface.class);
        }

        return apiInterface;
    }


    public interface ApiInterface {

        @GET("movie/popular")
        Single<MovieResponse> getMovieByPage(@Query("page") int page);
    }
}
