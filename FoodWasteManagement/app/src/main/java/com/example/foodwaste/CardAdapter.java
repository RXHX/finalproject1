package com.example.foodwaste;


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

import org.w3c.dom.Text;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {



      public String[] restaurantName;
      public String[] location;
      public String[] restaurantReminder;
      public int[] images;
      public int restaurantListLength;


    public CardAdapter(int  restaurantListLength) {

        this.restaurantListLength = restaurantListLength;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_restaurants,parent,false);
        DatabaseHelper  databaseHelper = new DatabaseHelper(parent.getContext());
        Cursor c = databaseHelper.getRestaurantInfo();
        restaurantName = new String[c.getCount()];
        location = new String[c.getCount()];
        restaurantReminder = new String[c.getCount()];

        images = new int[c.getCount()];

        if(c.getCount()>0){

            for(int i=0; c.moveToNext() != false; i++)
            {
                restaurantName[i] = c.getString(2);
                location[i] = c.getString(3);
                restaurantReminder[i] = c.getString(4);
                images[i] = R.drawable.chipotle;
            }

        }


        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemTitle.setText(restaurantName[position]);
        holder.itemDetail.setText(location[position]);
        holder.itemImage.setImageResource(images[position]);
        holder.reminder.setText(restaurantReminder[position]);

    }
    


    @Override
    public int getItemCount() {
        return restaurantListLength;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemTitle;
        TextView itemDetail;
        TextView reminder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemDetail = itemView.findViewById(R.id.item_detail);
            reminder = itemView.findViewById(R.id.reminder);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    CardAdapter.this.getItemId(position);


                    String SelectedRestaruantName = restaurantName[position];

                    Intent intent = new Intent(itemView.getContext(), RestaurantMenu.class);
                    intent.putExtra("pos",position);
                    intent.putExtra("selectedRestaurantName",SelectedRestaruantName);

                    itemView.getContext().startActivity(intent);

                }
            });


}
    }
}
