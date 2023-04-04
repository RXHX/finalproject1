package com.example.foodwaste;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class RestaurantMenu extends AppCompatActivity {


    public  int pos;
   public String UserName;

    ArrayList<HashMap<String,String>> aList1 = null;
    ArrayList<Menu> Cart = new ArrayList<>();
   ArrayList<Integer> menuItem = new ArrayList<Integer>();


    public ArrayList createMenu(String UserName)
    {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String[] user =  {UserName};
        Cursor c = databaseHelper.getClickedResaturantMenuInfo(user);

        String[] description = new String[c.getCount()];
        int[] images = new int[c.getCount()];
        int[] qty = new int[c.getCount()];
        int[] price = new int[c.getCount()];


        ArrayList<HashMap<String,String>> aList1 = new ArrayList<HashMap<String,String>>();
        if(c.getCount() >0)
        {
            for(int i=0; c.moveToNext() != false; i++)
            {

                System.out.println("Column 1"+c.getString(2));
                System.out.println("Column 2"+c.getInt(3));
                description[i] = c.getString(2);
                images[i] = R.drawable.burger;
                price[i] = c.getInt(3);
                qty[i] = c.getInt(4);


            }
        }

       for(int i=0;i<description.length;i++)
       {
           HashMap<String,String> hm = new HashMap<>();
           System.out.println("des"+description[i]);
           System.out.println("img"+images[i]);
           hm.put("txt",description[i]);
           hm.put("images",Integer.toString(images[i]));
           hm.put("price",Integer.toString(price[i]));
           hm.put("qty",Integer.toString(qty[i]));
           hm.put("check"," ");

           aList1.add(hm);

       }


  return aList1;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);
        Button AddToCart =   findViewById(R.id.AddToCart);

        Intent intent = getIntent();
        int position = intent.getIntExtra("pos",-1);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        System.out.println("position is"+position);
        String[] pos =  {String.valueOf(position+1)};
        System.out.println("Information: ");
        System.out.println(pos[0]);
        Cursor c = databaseHelper.getClickedResaturantInfo(pos);
        for(int i=0; c.moveToNext() != false; i++)
        {
           UserName = c.getString(1);
        }

        System.out.println("position is"+position);
        System.out.println("UserName is"+UserName);


        ArrayList<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
       aList = createMenu(UserName);
       aList1 = aList;
        String from [] = {"images","txt","qty","price","check"};
        int to [] = {R.id.itemImage,R.id.txtDescription,R.id.qty,R.id.price,R.id.checkBox};

        SimpleAdapter adapter = new SimpleAdapter(RestaurantMenu.this,aList,R.layout.menulist_container,from,to);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //here you can use the position to determine what checkbox to check
                //this assumes that you have an array of your checkboxes as well. called checkbox
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
                checkBox.setVisibility(View.VISIBLE);
                System.out.println("Item Selected");
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                        if(isChecked)
                        {
                            System.out.println("Item Checked");
                            menuItem.add(position);

                        }
                        else{
                            System.out.println("Item Unchecked");
                            for(int i = 0 ;i<menuItem.size();i++)
                            {
                                if(menuItem.get(i) == position)
                                {
                                    System.out.println("Item to be removed at position"+position);
                                    menuItem.remove(i);
                                }
                            }
                        }



                    }
                });
            }

        });


         AddToCart.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
               System.out.println("Got Here MenuItem"+aList1);
               System.out.println("Got Here SelectedItem"+menuItem);



                  for(int j =0; j <menuItem.size();j++)
                  {

                       System.out.println("Menu Item at Pos: "+menuItem.get(j));
                      HashMap<String,String> items = new HashMap<>();
                      String itemName = aList1.get(menuItem.get(j)).get("txt");
                      String price = aList1.get(menuItem.get(j)).get("price");
                      String Qty = aList1.get(menuItem.get(j)).get("qty");
                        items.put("itemName",itemName);
                        items.put("price",price);
                        items.put("qty",Qty);
                      System.out.println("Name:"+itemName);
                      System.out.println("Price:"+price);
                      System.out.println("Qty:"+Qty);
                      Menu menu1 = new Menu();
                      menu1.setItemName(itemName);
                      menu1.setPrice(price);
                      menu1.setQty(Qty);
                      Cart.add(menu1);
                  }



                    Intent intent = new Intent(RestaurantMenu.this,CustomerOrder.class);
                 Intent intent1 = getIntent();
                 String resName = intent1.getStringExtra("selectedRestaurantName");

                 intent.putExtra("MenuItem", Cart);
                 intent.putExtra("resName", resName);
                 startActivity(intent);
                 Cart.clear();


             }
         });







    }

}
