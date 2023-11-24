package com.example.and_mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ScrollView;

public class Receipt_Activity extends AppCompatActivity {

    ScrollView scrollView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);



        scrollView = findViewById(R.id.scrollView);


    }
}