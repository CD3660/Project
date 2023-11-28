package com.example.and_mini_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    ArrayList<MenuVO> list;
    final String TAG = "클릭";
    MenuDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = new MenuDAO();
        setContentView(R.layout.activity_menu);
        list = dao.initList();
        dao.eventSet(this);
        Button pay = findViewById(R.id.pay);
        pay.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, PayActivity.class);
            intent.putExtra("oList", dao.getOList());
            startActivity(intent);

        });
    }
}