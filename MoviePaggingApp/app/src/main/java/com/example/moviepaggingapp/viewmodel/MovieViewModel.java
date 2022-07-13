package com.example.moviepaggingapp.viewmodel;

import android.graphics.Movie;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.example.moviepaggingapp.Sources.MoviePagingSource;
import com.example.moviepaggingapp.model.Movies;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class MovieViewModel extends ViewModel {

public Flowable<PagingData<Movies>> moviePaging;

public MovieViewModel(){
    init();
}
private void init(){

    MoviePagingSource moviePagingSource = new MoviePagingSource();

    Pager<Integer, Movies> pager = new Pager(new PagingConfig(
            20,
            20,
            false,
            20,
            20*499
    ),()->moviePagingSource);

    //intialze floawable
    moviePaging = PagingRx.getFlowable(pager);
    CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
    PagingRx.cachedIn(moviePaging,coroutineScope);
}

}
