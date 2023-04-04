package com.example.foodwaste;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{

    public String[] restaurantName;
    public String[] deliveryType;
    public int orderListLength;
    public String[] currentUserName;
    public OrderAdapter(int  orderListLength,String[] currentUserName) {

        this.orderListLength = orderListLength;
        this.currentUserName = currentUserName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_viewsolditems,parent,false);
        DatabaseHelper  databaseHelper = new DatabaseHelper(parent.getContext());


        Cursor c = databaseHelper.getOrderInfo(currentUserName);
        restaurantName = new String[c.getCount()];
        deliveryType = new String[c.getCount()];
        if(c.getCount()>0){

            for(int i=0; c.moveToNext() != false; i++)
            {
                restaurantName[i] = c.getString(2);
                deliveryType[i] = c.getString(3);
            }

        }
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
             holder.restName.setText(restaurantName[position]);
             holder.delType.setText(deliveryType[position]);
    }

    @Override
    public int getItemCount() {
        return orderListLength;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView restName;
        TextView delType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restName = itemView.findViewById(R.id.RestuarntName);
            delType = itemView.findViewById(R.id.DelType);

        }
    }
}
