package com.example.currentpopular;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.currentpopular.models.MovieModel;
import com.example.currentpopular.request.service;
import com.example.currentpopular.response.MovieSearchResponse;
import com.example.currentpopular.utils.MovieApi;
import com.example.currentpopular.utils.credentials;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
private RecyclerView recyclerView;
public static final String Base_Url = "https://api.themoviedb.org";

//private RequestQueue requestQueue;
private List<MovieModel> movieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieList = new ArrayList<>();
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
      //  requestQueue =  VolleySingleton.getInstance(this).getRequestQueue();
        //fetchMovies();
        getretrofitresponse();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

   }

    private void getretrofitresponse() {

        MovieApi movieApi = service.getMovieApi();
        Call<MovieSearchResponse> responseCall = movieApi.searchMovie(credentials.API_KEY,"jack Reacher","1");
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if(response.code()==200){
                    Log.d("tag","the response"+response.body().toString());
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    for(MovieModel movie: movies){
                        Log.v("tag","the release date"+ movie.getRelease_date());
                        String title = movie.getTitle();
                        String releasedate = movie.getRelease_date();
                        String overView = movie.getMovie_overview();
                        float movieaverage = movie.getVote_average();
                        int id = movie.getMovie_id();
                        String posterpath = "https://image.tmdb.org/t/p/w500/" + movie.getPoster_path();
                        MovieModel movieModel = new MovieModel(title,posterpath,releasedate,movieaverage,overView,id);
                        movieList.add(movieModel);

                    }
                }
                else {
                    try {
                        Log.v("tag","error"+ response.errorBody().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                movieAdapter adapter = new movieAdapter(MainActivity.this,movieList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });
    }

    private  void getRetrofitresponseAccordingtoid(){
        MovieApi movieApi = service.getMovieApi();
        Call<MovieModel> responseCall = movieApi.getMovie(550,credentials.API_KEY);
        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if(response.code()==200){
                    MovieModel movie = response.body();
                    Log.v("tag", movie.getTitle());
                }
                else{
                    try{
                        Log.v("tag",response.errorBody().string());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
     }

  /*  private void fetchMovies() {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=840911cdbe5237886e34e7993f1ba95d";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i =0;i< response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String title = jsonObject.getString("original_title");
                        String overview= jsonObject.getString("adult");
                        String look = "hey";
                      //  String poster_path = jsonObject.getString("poster_path");
                        Double rating = jsonObject.getDouble("backdrop_path");
                        Movie movie = new Movie(title,overview,rating);
                        movieList.add(movie);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    movieAdapter adapter = new movieAdapter(MainActivity.this,movieList);
                    recyclerView.setAdapter(adapter);
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("dekhooo",error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }*/
}

