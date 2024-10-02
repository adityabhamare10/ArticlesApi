package com.aditya.schooglinkassignment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MyViewModel extends ViewModel {

    private final LiveData<List<Article>> articles;


    public MyViewModel(){
        Repository repository = new Repository();
        articles = repository.getArticles();
    }

    public LiveData<List<Article>> getArticles() {
        return articles;
    }

}
