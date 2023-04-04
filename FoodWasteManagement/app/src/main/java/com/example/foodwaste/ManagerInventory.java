package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ManagerInventory extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_inventory);
        databaseHelper=new DatabaseHelper(this);
        TextView restaurantName = findViewById(R.id.restaurantName);
        TextView restaurantLocation = findViewById(R.id.restaurantLoc);
        EditText ItemName =  findViewById(R.id.ItemName);
        EditText ItemPrice =  findViewById(R.id.ItemPrice);
        EditText ItemQty =  findViewById(R.id.ItemQty);
        Button add = findViewById(R.id.btnAdd);
        Intent intent = getIntent();

        String Name = intent.getStringExtra("RestaurantName");
        String Location = intent.getStringExtra("RestaurantLocation");
        String UserName = intent.getStringExtra("UserName");

        System.out.println("Res Name: "+Name);
        System.out.println("Res Price: "+Location);
        restaurantName.setText(Name);
        restaurantLocation.setText(Location);


       add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String FoodItemName = ItemName.getText().toString();
               String FoodItemPrice = ItemPrice.getText().toString();
               String FoodItemQty =  ItemQty.getText().toString();
               System.out.println("FoodItemName: Cool"+FoodItemName);
               System.out.println("FoodItemPrice: P"+FoodItemPrice);
               System.out.println("FoodItemQty: Q"+FoodItemQty);
               databaseHelper.addRestaurantMenuInfo(UserName,FoodItemName,FoodItemPrice,FoodItemQty);
              ItemName.setText("");
              ItemQty.setText("");
              ItemPrice.setText("");

           }
       });








    }
}