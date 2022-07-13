package com.example.moviepaggingapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.example.moviepaggingapp.adapter.LoadStateAdapter;
import com.example.moviepaggingapp.adapter.MovieAdapter;
import com.example.moviepaggingapp.databinding.ActivityMainBinding;
import com.example.moviepaggingapp.uttil.GridSpace;
import com.example.moviepaggingapp.uttil.MovieComparater;
import com.example.moviepaggingapp.uttil.utils;
import com.example.moviepaggingapp.viewmodel.MovieViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private MovieViewModel model;
    private MovieAdapter adapter;
    private ActivityMainBinding binding;

    @Inject
    RequestManager requestManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(utils.API_KEY == null || utils.API_KEY.isEmpty())
        {
            Toast.makeText(this, "error in api key", Toast.LENGTH_SHORT).show();
        }

        adapter = new MovieAdapter(new MovieComparater(),requestManager);
        model = new ViewModelProvider(this).get(MovieViewModel.class);

        initRecyclerView();

        //subscrib
        model.moviePaging.subscribe(moviesPagingData -> {
            adapter.submitData(getLifecycle(),moviesPagingData);
        });
    }

    private void initRecyclerView() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        binding.recyclerViewMovies.setLayoutManager(gridLayoutManager);

        binding.recyclerViewMovies.addItemDecoration(new GridSpace(2,20,true));
        binding.recyclerViewMovies.setAdapter(adapter);
        adapter.withLoadStateFooter(new LoadStateAdapter(View->{adapter.retry();}));

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.getItemViewType(position == adapter.LOADING_ITEM?1:2);
            }
        });
    }
}