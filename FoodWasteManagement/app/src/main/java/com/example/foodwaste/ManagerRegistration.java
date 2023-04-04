package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ManagerRegistration extends AppCompatActivity {
DatabaseHelper databaseHelper;
EditText uName,password,fullName,restName,location,phoneNumber;
    boolean isAllFieldsChecked = false ;


    private boolean CheckAllFields(){
        if(uName.length() == 0){
            uName.setError("This field is required");
            return false;
        }
        if (password.length() == 0){
            password.setError("password is required");
            return false;
        }
        else if(password.length() < 8){
            password.setError("password must be minimum 8 characters");
            return false ;
        }
        if(restName.length() == 0){
            restName.setError("This is required");
            return false;
        }
        if(location.length() == 0){
            location.setError("This is required");
            return false;
        }

        return true ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_registration);
        databaseHelper=new DatabaseHelper(this);
        uName=findViewById(R.id.edTxtUserNameManager);
        password=findViewById(R.id.edTxtPasswordManager);
        fullName=findViewById(R.id.edTxtFullNameManager);
        restName=findViewById(R.id.edTxtRestaurantName);
        location=findViewById(R.id.edTxtLocation);
        phoneNumber=findViewById(R.id.edTxtPhoneNumber);
        Button btnSubmit=findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            boolean isInserted;
            @Override
            public void onClick(View v) {
                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    isInserted = databaseHelper.addDataManagerReg(uName.getText().toString(),
                            password.getText().toString(),
                            fullName.getText().toString(),
                            restName.getText().toString(),
                            location.getText().toString(),
                            phoneNumber.getText().toString());
                    if(isInserted){
                        Toast.makeText(ManagerRegistration.this,"data added",Toast.LENGTH_LONG).show();
                        databaseHelper.addLogin(uName.getText().toString(),
                                password.getText().toString(),"Manager");

                        databaseHelper.addRestaurantInfo(uName.getText().toString(),restName.getText().toString(),
                                location.getText().toString()," ");
                        uName.setText("");
                        password.setText("");
                        fullName.setText("");
                        restName.setText("");
                        location.setText("");
                        phoneNumber.setText("");
                        startActivity(new Intent(ManagerRegistration.this,Login.class));
                    }
                    else{
                        Toast.makeText(ManagerRegistration.this,"Enter Unique UserName",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}