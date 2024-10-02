package com.aditya.schooglinkassignment;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private static final String BaseUrl = "https://newsapi.org/v2/";
    private final ApiService apiService;
    private static final String ApiKey = "bc4b35c85e64464684b95ce95e14e6e9";

    public Repository(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }


    public LiveData<List<Article>> getArticles(){
        MutableLiveData<List<Article>> data = new MutableLiveData<>();
        apiService.getTopHeadlines("us", "business", ApiKey).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                if(response.isSuccessful() && response.body() != null){
                    data.setValue(response.body().getArticles());
                }
            }
            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                    data.setValue(null);
            }
        });

        return data;

    }



}
