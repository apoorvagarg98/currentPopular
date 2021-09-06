package com.example.currentpopular.request;

import com.example.currentpopular.utils.MovieApi;
import com.example.currentpopular.utils.credentials;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class service {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(credentials.Base_Url).addConverterFactory(GsonConverterFactory.create());

private static Retrofit retrofit = retrofitBuilder.build();
private static MovieApi movieApi = retrofit.create(MovieApi.class);
public static MovieApi getMovieApi(){ return movieApi;}

}
