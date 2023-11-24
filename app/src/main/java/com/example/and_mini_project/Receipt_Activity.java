package com.example.and_mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        dao.display(dao.initList());
    }
}