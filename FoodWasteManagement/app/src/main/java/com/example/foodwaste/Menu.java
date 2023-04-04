package com.example.foodwaste;

import java.io.Serializable;

public class Menu implements Serializable {

    String itemName;
    String price;
    String Qty;


    public Menu(String itemName, String price, String qty) {
        this.itemName = itemName;
        this.price = price;
        Qty = qty;
    }

    public Menu() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "itemName='" + itemName + '\'' +
                ", price='" + price + '\'' +
                ", Qty='" + Qty + '\'' +
                '}';
    }
}
