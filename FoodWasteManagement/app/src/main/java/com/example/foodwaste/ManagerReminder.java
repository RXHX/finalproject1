package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ManagerReminder extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_reminder);
        Button addMessage = findViewById(R.id.addReminder);
        TextView reminderMsg = findViewById(R.id.txtReminder);
        databaseHelper = new DatabaseHelper(this);
        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String userInfo = sh.getString("UserName","");

        addMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 databaseHelper.updateRestaurantTable(userInfo,reminderMsg.getText().toString());
                startActivity(new Intent(ManagerReminder.this,ManagerProfile.class));
            }
        });


    }
}