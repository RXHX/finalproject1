package com.example.foodwaste;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "WASTE_MANAGEMENT.db";

    final static int DATABASE_VERSION = 33;


    // Table for registration table
    final static String TABLE1_NAME ="Registration_table";
    final static String T1COL_1 = "UserName";
    final static String T1COL_2 = "Password";
    final static String T1COL_3 = "FullName";
    final static String T1COL_4 = "DOB";
    final static String T1COL_5 = "RestaurantName";
    final static String T1COL_6 = "Location";
    final static String T1COL_7 = "PhoneNumber";

    // Table for Profile
    final static String TABLE2_NAME = "Profile_table";
    final static String T2COL_1 = "Username";
    final static String T2COL_2 = "Hobbies";
    final static String T2COL_3 = "FavouriteFood";
    final static String T2COL_4 = "StudentOption";
    final static String T2COL_5 = "Organization";
    final static String T2COL_6 = "StudentID";


    // Table for Login table
    final static String TABLE3_NAME ="Login_table";
    final static String T3COL_1 = "UserName";
    final static String T3COL_2 = "Password";
    final static String T3COL_3 = "Type";

    // Table for Restaurant
    final static String TABLE4_NAME ="Restaurant_table";
    final static String T4COL_1 = "Id";
    final static String T4COL_2 = "UserName";
    final static String T4COL_3 = "RestaurantName";
    final static String T4COL_4 = "RestaurantLocation";
    final static String T4COL_5 = "RestaurantReminder";





    // Table for Menu
    final static String TABLE5_NAME ="Menu_table";
    final static String T5COL_1 = "Id";
    final static String T5COL_2 = "UserName";
    final static String T5COL_3 = "Item";
    final static String T5COL_4 = "Price";
    final static String T5COL_5 = "Qty";

    // Table for Order
    final static String TABLE6_NAME="Order_table";
    final static String T6COL_1 = "Id";
    final static String T6COL_2 = "UserName";
    final static String T6COL_3 = "RestaurantName";
    final static String T6COL_4 = "DeliveryType";
    final static String T6COL_5 = "DeliveryAddress";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase database = this.getWritableDatabase();
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query=  "CREATE TABLE " + TABLE1_NAME + "("  + T1COL_1 + " TEXT PRIMARY KEY, " + T1COL_2 + " TEXT, "+T1COL_3+" TEXT, "+T1COL_4+" DATE, "
                       +T1COL_5+" TEXT, "+T1COL_6+" TEXT, "+T1COL_7+" TEXT)";
        sqLiteDatabase.execSQL(query);
                query="CREATE TABLE " + TABLE2_NAME + "(" +  T2COL_1 + " TEXT PRIMARY KEY, " + T2COL_2 + " TEXT, "+T2COL_3+" TEXT, "+T2COL_4+" DATE, "
                        +T2COL_5+" TEXT, "+T2COL_6+" TEXT)" ;
                sqLiteDatabase.execSQL(query);

            query = "CREATE TABLE "+ TABLE3_NAME + "("+  T3COL_1+" TEXT PRIMARY KEY, "+T3COL_2+" TEXT, "+ T3COL_3 + " TEXT)";
            sqLiteDatabase.execSQL(query);



        query = "CREATE TABLE "+ TABLE4_NAME + "("+ T4COL_1 +" INTEGER PRIMARY KEY, "+ T4COL_2+" TEXT, "+T4COL_3+" TEXT, "+T4COL_4+" TEXT, "+T4COL_5+" TEXT)";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE "+ TABLE5_NAME + "("+ T5COL_1 +" INTEGER PRIMARY KEY, "+ T5COL_2+" TEXT, "+T5COL_3+" TEXT, "+T5COL_4+" INTEGER, "+T5COL_5 +" INTEGER)";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE "+ TABLE6_NAME + "("+ T6COL_1 +" INTEGER PRIMARY KEY, "+ T6COL_2+" TEXT, "+T6COL_3+" TEXT, "+T6COL_4+" TEXT, "+T6COL_5+" TEXT)";
        sqLiteDatabase.execSQL(query);




    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                          int olderVersion, int newerVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE3_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE4_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE5_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE6_NAME);
        onCreate(sqLiteDatabase);
    }



    public boolean addDataCustomerReg(String username,String password,String fullName,String DOB,String PhoneNumber){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL_1,username);
        values.put(T1COL_2,password);
        values.put(T1COL_3,fullName);
        values.put(T1COL_4,DOB);
        values.put(T1COL_7,PhoneNumber);

        long l = sqLiteDatabase.insert(TABLE1_NAME,null,values);
        if(l > 0)
            return true;
        else
            return false;
    }

    public boolean addLogin(String username,String password,String type){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        System.out.println(T3COL_1+":"+username);
        System.out.println(T3COL_2+":"+password);
        System.out.println(T3COL_3+":"+type);


        values.put(T3COL_1,username);
        values.put(T3COL_2,password);
        values.put(T3COL_3,type);
        long l = sqLiteDatabase.insert(TABLE3_NAME,null,values);
        if(l > 0)
            return true;
        else
            return false;
    }

    public Cursor getClickedResaturantInfo(String[] Id)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE4_NAME + " WHERE Id = ?";
        Cursor cursor = database.rawQuery(query,Id);
        return cursor;
    }



    public Cursor getClickedResaturantMenuInfo(String[] UserName)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE5_NAME + " WHERE UserName = ?";
        Cursor cursor = database.rawQuery(query,UserName);
        return cursor;
    }



    public boolean addDataManagerReg(String username,String password,String fullName,String restaurantName,String location,String phoneNumber){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL_1,username);
        values.put(T1COL_2,password);
        values.put(T1COL_3,fullName);
        values.put(T1COL_5,restaurantName);
        values.put(T1COL_6,location);
        values.put(T1COL_7,phoneNumber);

        long l = sqLiteDatabase.insert(TABLE1_NAME,null,values);
        if(l > 0)
            return true;
        else
            return false;
    }


    public boolean addOrderInformation(String username,String restaurantName, String deliveryType,String deliveryAddress)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T6COL_2,username);
        values.put(T6COL_3,restaurantName);
        values.put(T6COL_4,deliveryType);
        values.put(T6COL_5,deliveryAddress);

        long l = sqLiteDatabase.insert(TABLE6_NAME,null,values);
        if(l > 0)
            return true;
        else
            return false;

    }


    public Cursor checkLogin(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE3_NAME;
        Cursor cursor = database.rawQuery(query,null);
        return cursor;
    }

    public Cursor getRegistrationInfo(String[] UserName){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE1_NAME+" Where UserName = ?";
        Cursor cursor = database.rawQuery(query,UserName);
        return cursor;
    }


    public Cursor getRestaurantInfo(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE4_NAME;
        Cursor cursor = database.rawQuery(query,null);
        return cursor;
    }

    public Cursor getOrderInfo(String[] UserName)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE6_NAME + " WHERE UserName = ?";
        Cursor cursor = database.rawQuery(query,UserName);
        return cursor;
    }
    public Cursor getLoggedUserInfo(String[] UserName)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE1_NAME + " WHERE UserName = ?";
        Cursor cursor = database.rawQuery(query,UserName);
        return cursor;
    }
    public boolean addRestaurantInfo(String UserName,String RestaurantName,String RestaurantLocation,String RestaurantReminder) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        System.out.println("Username: "+UserName);
        System.out.println("Restaurant Name: "+RestaurantName);
        System.out.println("Restaurant Location"+RestaurantLocation);
        values.put(T4COL_2,UserName);
        values.put(T4COL_3,RestaurantName);
        values.put(T4COL_4,RestaurantLocation);
        values.put(T4COL_5,RestaurantReminder);
        long l = sqLiteDatabase.insert(TABLE4_NAME,null,values);
        if(l > 0)
            return true;
        else
            return false;
    }

    public boolean addRestaurantMenuInfo(String UserName,String Item,String Price, String Qty) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T5COL_2,UserName);
        values.put(T5COL_3,Item);
        values.put(T5COL_4,Price);
        values.put(T5COL_5,Qty);
        long l = sqLiteDatabase.insert(TABLE5_NAME,null,values);
        if(l > 0)
            return true;
        else
            return false;
    }



    public boolean updateRestaurantTable(String id, String c)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T4COL_5,c);
        int u = sqLiteDatabase.update(TABLE4_NAME,contentValues,"UserName=?",
                new String[]{id});
        if(u>0)
            return true;
        else
        {
            System.out.println("No update happened");
            return false;

        }


    }

    public boolean updatePassword(String id,String c){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T3COL_2,c);
        int u = sqLiteDatabase.update(TABLE3_NAME,contentValues,"UserName=?",
                new String[]{id});
        if(u>0)
            return true;
        else
        {
          System.out.println("No update happened");
            return false;

        }
                }

    public Cursor getProfileInfo(String[] UserName){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE2_NAME+" Where Username = ?";
        Cursor cursor = database.rawQuery(query,UserName);
        return cursor;
    }


    public boolean addDataProfile(String username/*,String favFood,String studentOption,String organization,String studentID,String hobbies*/){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T2COL_1,username);
        //values.put(T2COL_4,fullName);

        long l = sqLiteDatabase.insert(TABLE2_NAME,null,values);
        if(l > 0)
            return true;
        else
            return false;
    };


    public boolean updateProfile(String user,String hobbies,String food,String option,String org,String studentNumber){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T2COL_2,hobbies);
        contentValues.put(T2COL_3,food);
        contentValues.put(T2COL_4,option);
        contentValues.put(T2COL_5,org);
        contentValues.put(T2COL_6,studentNumber);
        int u = sqLiteDatabase.update(TABLE2_NAME,contentValues,"username=?",
                new String[]{user});
        if(u>0)
            return true;
        else
            return false;
    }
}











