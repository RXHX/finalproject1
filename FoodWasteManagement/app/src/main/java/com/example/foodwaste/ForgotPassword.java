package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        databaseHelper=new DatabaseHelper(this);
        EditText username=findViewById(R.id.userId);
        EditText password=findViewById(R.id.newpass);
        EditText repassword=findViewById(R.id.reenterpass);

        Button btnSubmit=findViewById(R.id.submt);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            String Id;
            boolean isUpdated;
            @Override
            public void onClick(View v) {


                    if(password.getText().toString().equals(repassword.getText().toString()))
                    {
                        Id = username.getText().toString();
                        isUpdated = databaseHelper.updatePassword(Id,password.getText().toString());
                        if(isUpdated){
                            Toast.makeText(ForgotPassword.this,"record is updated",
                                    Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(ForgotPassword.this,"Please Enter Correct Username",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    else{


                        Toast.makeText(ForgotPassword.this,"Please type correct Password",Toast.LENGTH_LONG).show();
                    }






            }
        });



    }
}