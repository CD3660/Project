package com.example.and_mini_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    final String TAG = "클릭";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MenuDAO dao = new MenuDAO();
        setContentView(R.layout.activity_menu);
        dao.initList();
        dao.eventSet(this);
        Button pay = findViewById(R.id.pay);
        pay.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, PayActivity.class);
            intent.putExtra("oList", dao.getOList());
            startActivity(intent);
        });
    }



}