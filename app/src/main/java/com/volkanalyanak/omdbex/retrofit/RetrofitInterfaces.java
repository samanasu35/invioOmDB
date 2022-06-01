package com.volkanalyanak.omdbex.retrofit;

import com.volkanalyanak.omdbex.models.Movie;
import com.volkanalyanak.omdbex.models.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterfaces {

    @GET("/?apikey=c6f278e")
    Call<MovieList> getMovieList(@Query("s") String s,@Query("page") int page);

    @GET("/?apikey=c6f278e")
    Call<Movie> getMovie(@Query("i") String imdbID);
}
