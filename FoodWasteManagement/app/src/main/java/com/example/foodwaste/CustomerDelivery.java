package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

public class CustomerDelivery extends AppCompatActivity {

    String deliveryType = "";
    String deliveryLocation = "N/A";
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_delivery);
        Button btnConfirm = findViewById(R.id.btnConfirmDel);
        EditText txtLocation = findViewById(R.id.txtLoc);

        databaseHelper = new DatabaseHelper(this);

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String userName = sh.getString("UserName", "");
          btnConfirm.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = getIntent();
                  String[] ItemName =intent.getStringArrayExtra("ItemName");
                  String[] ItemQty =intent.getStringArrayExtra("ItemQty");
                  String[] ItemPrice =intent.getStringArrayExtra("ItemPrice");
                  String resName = intent.getStringExtra("resName");
                  SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                  try {
                      JSONArray delMenuIds = new JSONArray(sharedPreferences.getString("delItem", "[]"));
                      for (int i = 0; i < delMenuIds.length(); i++) {
                          System.out.println("Id to be deleted"+delMenuIds.getInt(i));

                      }
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
                    databaseHelper.addOrderInformation(userName,resName,deliveryType,txtLocation.getText().toString());


                  Toast.makeText(CustomerDelivery.this," delivery data added",Toast.LENGTH_LONG).show();
                  startActivity(new Intent(CustomerDelivery.this,CustomerProfile.class));
              }
          });


    }

    public void onClickText(View view)
    {
        TextView txtinf = findViewById(R.id.txtInfoDel);
        EditText txtLocation = findViewById(R.id.txtLoc);

        switch (view.getId())
        {
            case R.id.pickup :
                deliveryType = "PickUp";
                String deliveryMessage1 = "Thank you, for choosing Pick Up. You can pick up the Food after 30 minutes ";
                txtinf.setText(deliveryMessage1);
                txtinf.setVisibility(View.VISIBLE);
                txtLocation.setVisibility(View.INVISIBLE);
                break;
            case R.id.del:
                deliveryType = "Delivery at Location";
                String deliveryMessage2 = "Thank you, for choosing delivery at Location Option";
                txtinf.setText(deliveryMessage2);
                txtinf.setVisibility(View.VISIBLE);
                txtLocation.setVisibility(View.VISIBLE);
                System.out.println("Delivery Location: "+deliveryLocation);
                break;
            default:break;
        }




    }
}