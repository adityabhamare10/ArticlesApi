package com.aditya.schooglinkassignment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Article article = (Article) getIntent().getSerializableExtra("article");
        assert article != null;
        TextView titleTextView = findViewById(R.id.title);
        TextView authorTextView = findViewById(R.id.author);
        ImageView imageView = findViewById(R.id.image);
        TextView publisedAtTextView = findViewById(R.id.publishedAt);
        TextView contentTextView = findViewById(R.id.content);
        titleTextView.setText(article.getTitle());
        authorTextView.setText(article.getAuthor());
        Glide.with(this).load(article.getUrlToImage()).into(imageView);
        publisedAtTextView.setText(MyAdapter.formatPublishedDate(article.getPublishedAt()));
        contentTextView.setText(article.getContent());

    }
}