package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText username, password;
    boolean isAllFieldsChecked = false ;

    private boolean CheckAllFields(){
        if(username.length() == 0){
            username.setError("This field is required");
            //Toast.makeText(username.getContext(),"This field is required" ,Toast.LENGTH_LONG).show();
            return false;
        }
        if (password.length() == 0){
            password.setError("password is required");
            // Toast.makeText(password.getContext(),"This field is required" ,Toast.LENGTH_LONG).show();
            return false;
        }
        else if(password.length() < 8){
            password.setError("password must be minimum 8 characters");
            return false ;
        }

        return true ;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseHelper=new DatabaseHelper(this);
        username=findViewById(R.id.edTxtUserName);
        password=findViewById(R.id.edTxtPassword);
        Button btnLogin=findViewById(R.id.btnLogin);
        Button btnForgot=findViewById(R.id.btnForgot);
        Button btnSignCust=findViewById(R.id.btnSignCust);
        Button btnSignManager=findViewById(R.id.btnSignManager);

        btnSignCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,CustomerRegistration.class));
            }
        });
        btnSignManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,ManagerRegistration.class));
            }
        });

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Login.this,CustomerProfile.class));
//            }
//        });


        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,ForgotPassword.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllFieldsChecked = CheckAllFields() ;
                Cursor c = databaseHelper.checkLogin();
                StringBuilder str = new StringBuilder();
                if(c.getCount()>0){
                    while(c.moveToNext()){

                        if(c.getString(0).equals(username.getText().toString()) && c.getString(1).equals(password.getText().toString()))
                        {
                            Intent intent = null;
                            if(c.getString(2).equals("Manager"))
                           {
                                intent = new Intent(Login.this,ManagerProfile.class);

                           }
                           else if(c.getString(2).equals("Customer")){
                                intent = new Intent(Login.this,CustomerProfile.class);
                           }
                            intent.putExtra("UserName",c.getString(0));

                            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

                            SharedPreferences.Editor myEdit = sharedPreferences.edit();

                            myEdit.putString("UserName", c.getString(0));
                            myEdit.commit();

                            startActivity(intent);

                        }
                        else {
                        System.out.println("Invalid Credentials");
                        }
                    }

                }
                else{

                }
                c.close();

            }
        });





    }



}