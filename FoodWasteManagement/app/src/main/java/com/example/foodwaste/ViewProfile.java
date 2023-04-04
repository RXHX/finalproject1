package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewProfile extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        TextView hobbies = findViewById(R.id.txtHobb);
        TextView favFood = findViewById(R.id.txtFood);
        TextView StudOpt = findViewById(R.id.txtStudOpt);
        TextView StudId = findViewById(R.id.txtStudId);
        TextView StudOrg = findViewById(R.id.txStudOrg);
        Button back = findViewById(R.id.Back);
        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String[] userInfo = {sh.getString("UserName","")};
           databaseHelper = new DatabaseHelper(this);
           Cursor c = databaseHelper.getProfileInfo(userInfo);

        if(c.getCount()>0){

            for(int i=0; c.moveToNext() != false; i++)
            {
                hobbies.setText(c.getString(1));
                 favFood.setText(c.getString(2));
                 StudOpt.setText(c.getString(3));
                 StudOrg.setText(c.getString(4));
                 StudId.setText(c.getString(5));
            }

        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewProfile.this,CustomerProfile.class));
            }
        });








    }
}