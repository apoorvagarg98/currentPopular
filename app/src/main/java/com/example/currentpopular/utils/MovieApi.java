package com.example.currentpopular.utils;

import com.example.currentpopular.models.MovieModel;
import com.example.currentpopular.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    //search for movies
    @GET("3/movie/popular")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String Key,@Query("query") String query,@Query("page") String page
    );

    @GET("3/movie/popular")
    Call<MovieModel> getMovie(@Path("movie id") int movie_id,@Query("api_key") String api_key );


}
