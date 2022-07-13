package com.example.countrynameapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.RecoverySystem;
import android.util.Log;

import com.example.countrynameapp.adapter.myAdapter;
import com.example.countrynameapp.model.Country;
import com.example.countrynameapp.model.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Country> countries;
    private myAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCountry();

    }

    public Object getCountry(){
        CreateCountryDataService instance = RetrofitInstance.getService();
        Call<Result> call = instance.getCountry();

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                if(result !=null && result.getResult() !=null)
                {
                    countries = (ArrayList<Country>) result.getResult();
                }

                usedata();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
        return countries;
    }
    public void usedata()
    {
        recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        adapter = new myAdapter(countries);
        recyclerView.setAdapter(adapter);
    }

}