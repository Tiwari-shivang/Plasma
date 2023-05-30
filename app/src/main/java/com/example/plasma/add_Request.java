package com.example.plasma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.plasma.Structure.Request;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class add_Request extends AppCompatActivity {

    EditText Age, Gender, Weight, BMI, BMR, BloodGroup, Address, Mobile;
    Button addRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);
        Log.d("Request" , "here i am shivang");

        Address = findViewById(R.id.addressText);
        Mobile = findViewById(R.id.mobileText);
        Age = findViewById(R.id.ageTextField);
        Gender = findViewById(R.id.genderTextField);
        Weight = findViewById(R.id.BodyWeightText);
        BMI = findViewById(R.id.BMItextField);
        BMR = findViewById(R.id.BMRtextField);
        BloodGroup = findViewById(R.id.BloodGrptxt);
        addRequest = findViewById(R.id.AddRequest);
//        addRequest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Request request = new Request(Age.getText().toString(), Gender.getText().toString(), Weight.getText().toString(), BMI.getText().toString(), BMR.getText().toString(), BloodGroup.getText().toString(), Address.getText().toString(), Mobile.getText().toString());
//                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("DonationRequests");
//                databaseReference.push().setValue(request);
//            }
//        });
    }
}