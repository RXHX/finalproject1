package com.example.foodwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ProfileCustomer2 extends AppCompatActivity {
    DatabaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_customer2);
        RadioGroup rdg = findViewById(R.id.radioGroup2);
        RadioButton yes_btn = findViewById(R.id.rdbtnYes);
        RadioButton no_btn = findViewById(R.id.rdBtnNo);
        EditText studentOrg = findViewById(R.id.edTxtOrganization);
        EditText studentNumber = findViewById(R.id.edTxtStudentNumber);
        Button update_profilebtn = findViewById(R.id.btnUpdateProfile);
        Button back_profilebtn = findViewById(R.id.btnBackProfile);
        dbh = new DatabaseHelper(this);
        Intent intent = getIntent();
       back_profilebtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
           startActivity(new Intent(ProfileCustomer2.this,ProfileCustomer.class));
           }
       });


        update_profilebtn.setOnClickListener(new View.OnClickListener() {
            boolean isUpdated;
            @Override
            public void onClick(View v) {
                String userName = intent.getStringExtra("user");
                String userFood = intent.getStringExtra("food");
                String userHobbies = intent.getStringExtra("hobbies");
                String studentOption ="Yes";
                if (yes_btn.isChecked())
                {
                    studentOption = "Yes";
                }
                else if (no_btn.isChecked())
                {
                    studentOption = "No";
                }
                String userOrganization = studentOrg.getText().toString();
                String userStudentId = studentNumber.getText().toString();

                isUpdated = dbh.updateProfile(userName,userHobbies,userFood,studentOption,userOrganization,userStudentId);
                if(isUpdated){
                    Toast.makeText(ProfileCustomer2.this,"record is updated",
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(ProfileCustomer2.this,"record is not updated",
                            Toast.LENGTH_LONG).show();
                }

                startActivity(new Intent(ProfileCustomer2.this,CustomerProfile.class));
            }
        });

    }
}