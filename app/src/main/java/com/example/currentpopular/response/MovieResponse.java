package com.example.currentpopular.response;

import com.example.currentpopular.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//this class is for single movierequest
public class MovieResponse {
    //finding the movie object

    @SerializedName("results")
    @Expose()
    private MovieModel movie;
    public MovieModel getMovie(){return movie;}

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
