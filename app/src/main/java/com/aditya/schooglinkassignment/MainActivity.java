package com.aditya.schooglinkassignment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ProgressBar progressBar = findViewById(R.id.progress_loader);

        recyclerView = findViewById(R.id.recyclerView);
        SearchView searchView = findViewById(R.id.searchView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.getArticles().observe(this, articles -> {

            List<Article> validArticles = new ArrayList<>();
            for (Article article : articles) {
                boolean isTitleValid = article.getTitle() != null && !article.getTitle().equals("[Removed]");
                boolean isImageValid = article.getUrlToImage() != null && !article.getUrlToImage().isEmpty();


                if (isTitleValid && isImageValid) {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    validArticles.add(article);
                }
            }

            adapter = new MyAdapter(validArticles, article -> {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("article", article);
                startActivity(intent);
            });
            recyclerView.setAdapter(adapter);
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(adapter != null){
                    adapter.search(newText);
                }
                return true;
            }
        });


    }

}