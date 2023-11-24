package com.example.and_mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, Receipt_Activity.class);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                startActivity(intent);
                finish();

            }
        }, 1500);

    }
}