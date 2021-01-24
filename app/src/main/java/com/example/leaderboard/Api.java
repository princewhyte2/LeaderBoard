package com.example.leaderboard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://gadsapi.herokuapp.com/";

    @GET("/api/hours")
    Call<List<Learners>> getLearners();


}
