package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

public class SoldOrder extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sold_order);
        DatabaseHelper  databaseHelper = new DatabaseHelper(this);
        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String[] userInfo = {sh.getString("UserName","")};
        Cursor c = databaseHelper.getOrderInfo(userInfo);
        recyclerView = findViewById(R.id.recyclerView3);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new OrderAdapter(c.getCount(),userInfo);
        recyclerView.setAdapter(adapter);
    }
}