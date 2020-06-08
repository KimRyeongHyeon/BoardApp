package com.myandroid.boardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView tvID, tvTitle, tvUser, tvDate, tvContent;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvID = findViewById(R.id.txtID);
        tvTitle = findViewById(R.id.txtTitle);
        tvUser = findViewById(R.id.txtUser);
        tvDate = findViewById(R.id.txtDate);
        tvContent = findViewById(R.id.txtContent);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        tvID.setText(MainActivity.boardArrayList.get(position).getId());
        tvTitle.setText(MainActivity.boardArrayList.get(position).getTitle());
        tvUser.setText(MainActivity.boardArrayList.get(position).getUser());
        tvDate.setText(MainActivity.boardArrayList.get(position).getDate());
        tvContent.setText(MainActivity.boardArrayList.get(position).getContent());
    }
}
