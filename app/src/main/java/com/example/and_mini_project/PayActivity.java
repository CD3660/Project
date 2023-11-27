package com.example.and_mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class PayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        Intent intent = getIntent();
        ArrayList<OrderListVO> oList = (ArrayList<OrderListVO>)intent.getSerializableExtra("oList");
        PayDAO dao = new PayDAO(oList);
        dao.payViewList(this);

        if(intent.getIntExtra() == 0){

        }


        Button pay = findViewById(R.id.btn_2);

        Button charge = findViewById(R.id.btn_1);



    }
}