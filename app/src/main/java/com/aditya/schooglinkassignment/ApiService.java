package com.aditya.schooglinkassignment;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Query;

public interface ApiService {

    @GET("top-headlines")
    Call<Response> getTopHeadlines(
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String apiKey
    );

}
