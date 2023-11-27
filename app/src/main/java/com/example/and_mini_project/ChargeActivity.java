package com.example.and_mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChargeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge);

        Button button = findViewById(R.id.button);//id를 통해서 레이아수에 있는 뷰객
        EditText editText = findViewById(R.id.editText);
        TextView textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str =editText.getText().toString();
                try {
                    //int charge = Integer.parseInt(str);//강제 타입 변환 String에서 int로
                    //Intent intent = new Intent(ChargeActivity.this, PayActivity.class);//화면을 바꿈
                   // intent.putExtra("charge", charge);//데이터 intent 장소로 가지고 감
                   // startActivity(intent);
                    PayActivity.money += Integer.parseInt(str) ;
                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });



    }
}