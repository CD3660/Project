package com.example.and_mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ScrollView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Receipt_Activity extends AppCompatActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        ReceiptDAO dao = new ReceiptDAO();

        dao.initViewList(this);
        dao.display(( ArrayList<OrderListVO>)getIntent().getSerializableExtra("oList"), getIntent().getIntExtra("charge",0) );



        Button back_Menu = findViewById(R.id.back_Menu);
        back_Menu.setOnClickListener(v -> {
            Intent intent = new Intent(Receipt_Activity.this, MenuActivity.class);
            startActivity(intent);
            finish();

        });



    }
}