package com.example.moviepaggingapp.Sources;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.example.moviepaggingapp.api.ApiRetrofit;
import com.example.moviepaggingapp.model.MovieResponse;
import com.example.moviepaggingapp.model.Movies;

import java.util.List;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MoviePagingSource extends RxPagingSource<Integer, Movies> {

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Movies> pagingState) {
        return null;
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, Movies>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        try{
            int page = loadParams.getKey() !=null?loadParams.getKey():1;
           return  ApiRetrofit.getRetrofit()
                   .getMovieByPage(page)
                   .subscribeOn(Schedulers.io())
                   .map(MovieResponse::getMovies)
                   .map(movies ->toLoadResult(movies, page))
                   .onErrorReturn(LoadResult.Error::new);
        } catch (Exception e) {
           return Single.just(new LoadResult.Error(e));
        }
    }

    private LoadResult<Integer,Movies> toLoadResult(List<Movies> movies, int page){
        return new LoadResult.Page(movies,page==1?null:page-1,page+1);

    }
}
