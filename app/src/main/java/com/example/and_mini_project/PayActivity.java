package com.example.and_mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        Intent intent = getIntent();
        ArrayList<OrderListVO> oList = (ArrayList<OrderListVO>) intent.getSerializableExtra("oList");
        PayDAO dao = new PayDAO(oList);
        dao.payViewList(this);


        TextView chargeview = findViewById(R.id.listview_list11);
        int charge = intent.getIntExtra("charge", 0);
        chargeview.setText(charge);

        Button pay = findViewById(R.id.btn_2);
        pay.setOnClickListener(view -> {
            if(charge>dao.total()){
                Intent intenta = new Intent(this, Receipt_Activity.class);
                intenta.putExtra("oList", oList);
                intenta.putExtra("charge", charge);
                startActivity(intenta);
                finish();
            } else {
                Toast.makeText(this, "금액 부족", Toast.LENGTH_SHORT).show();
            }
        });

        Button chargebtn = findViewById(R.id.btn_1);
        chargebtn.setOnClickListener(view -> {
            Intent intenta = new Intent(this, ChargeActivity.class);
            startActivity(intenta);
        });


    }

}