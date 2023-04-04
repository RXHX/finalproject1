package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;

public class CustomerOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);
        Button deliveryType = findViewById(R.id.deliveryOption);
        ArrayList<Menu> menu = (ArrayList<Menu>) getIntent().getSerializableExtra("MenuItem");
        ArrayList<HashMap<String,String>> aList =
                new ArrayList<HashMap<String,String>>();
        String[] ItemName =  new String[menu.size()];
        String[] ItemQty = new String[menu.size()];
        String[] ItemPrice = new String[menu.size()];


         for(int i=0;i<menu.size();i++)
         {

             ItemName[i] = menu.get(i).itemName;
             ItemQty[i] = menu.get(i).Qty;
             ItemPrice[i] = menu.get(i).price;

         }
       int sum = 0;

        for(int i=0;i<menu.size();i++)
        {
            sum  = sum + Integer.parseInt(menu.get(i).price);
        }


        for(int i=0;i<menu.size();i++)
         {
             HashMap<String,String> hashMap = new HashMap<>();
              hashMap.put("name",ItemName[i]);
              hashMap.put("qty",ItemQty[i]);
              hashMap.put("price",ItemPrice[i]);
             aList.add(hashMap);

         }

        String[] from = {"name","qty","price"};
        int[] to = {R.id.ItmName,R.id.ItmQty,R.id.ItmPrice};

        TextView billBeforeTax = findViewById(R.id.billBeforeTax);
        TextView billAfterTax = findViewById(R.id.billAfterTax);




        System.out.println("List:"+aList);

        SimpleAdapter adapter = new SimpleAdapter(CustomerOrder.this,
                aList,R.layout.bill_generation,from,to);

        ListView listView = findViewById(R.id.listview);

        listView.setAdapter(adapter);

        billBeforeTax.setText(sum+"");
        double tax = 0.05;
        tax =   sum + sum * tax;
        billAfterTax.setText(tax+"");


        deliveryType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerOrder.this,CustomerDelivery.class);
               Intent intent1 = getIntent();
               String resName = intent1.getStringExtra("resName");
                intent.putExtra("ItemName",ItemName);
                intent.putExtra("ItemQty",ItemQty);
                intent.putExtra("ItemPrice",ItemPrice);
                intent.putExtra("resName", resName);
                   startActivity(intent);
            }
        });
    }





}