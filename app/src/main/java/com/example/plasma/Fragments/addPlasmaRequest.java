package com.example.plasma.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.plasma.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.plasma.Structure.recipientStructure;

public class addPlasmaRequest extends Fragment {
    EditText Name, PhoneNum, Age, Requirements;
    Button addButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup addPlasmaRequest = (ViewGroup) inflater.inflate(R.layout.fragment_add_plasma_request, container, false);
        addButton = addPlasmaRequest.findViewById(R.id.button);
        Name = addPlasmaRequest.findViewById(R.id.name);
        PhoneNum = addPlasmaRequest.findViewById(R.id.phno);
        Age = addPlasmaRequest.findViewById(R.id.age);
        Requirements = addPlasmaRequest.findViewById(R.id.require);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Name.getText().toString().equals("") || PhoneNum.getText().toString().equals("") || Age.getText().toString().equals("") || Requirements.getText().toString().equals("")){
                    if (Name.getText().toString().equals("")){
                        Name.setError("Name required");
                    }
                    else if (PhoneNum.getText().toString().equals("")){
                        PhoneNum.setError("Phone number required");
                    }
                    else if (Age.getText().toString().equals("")){
                        Age.setError("Age of patient required");
                    }
                    else{
                        Requirements.setError("Please mention requirements First");
                    }
                    return;
                }
                recipientStructure recipient = new recipientStructure(Name.getText().toString(), PhoneNum.getText().toString(), Requirements.getText().toString(), Age.getText().toString());
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("RecipientRequest");
                databaseReference.push().setValue(recipient);
            }
        });
        return addPlasmaRequest;
    }
}