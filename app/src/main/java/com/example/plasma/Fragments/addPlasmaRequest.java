package com.example.plasma.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plasma.Adapters.hospitalNameAdapter;
import com.example.plasma.R;
import com.example.plasma.allHospitals;
import com.example.plasma.allHospitalsRecipient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.plasma.Structure.recipientStructure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class addPlasmaRequest extends Fragment {
    EditText Name, PhoneNum, Age, Requirements;
    Button addButton;
    TextView selectedHospital;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup addPlasmaRequest = (ViewGroup) inflater.inflate(R.layout.fragment_add_plasma_request, container, false);
        addButton = addPlasmaRequest.findViewById(R.id.button);
        Name = addPlasmaRequest.findViewById(R.id.name);
        PhoneNum = addPlasmaRequest.findViewById(R.id.phno);
        Age = addPlasmaRequest.findViewById(R.id.age);
        selectedHospital = addPlasmaRequest.findViewById(R.id.selectHospital);
        Requirements = addPlasmaRequest.findViewById(R.id.require);

        if (requireActivity().getIntent().hasExtra("HospitalName")){
            selectedHospital.setText(requireActivity().getIntent().getStringExtra("HospitalName"));
            Toast.makeText(getActivity(), "Hospital selected: "+requireActivity().getIntent().getStringExtra("HospitalName"), Toast.LENGTH_SHORT).show();
        }

        selectedHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHospital = new Intent(getActivity(), allHospitals.class);
                intentHospital.putExtra("fragment", "addPlasmaRequest");
                startActivity(intentHospital);
                requireActivity().finish();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Name.getText().toString().equals("") || PhoneNum.getText().toString().equals("") || Age.getText().toString().equals("") || Requirements.getText().toString().equals("") || selectedHospital.toString().equals("Click to select Hospital Name")){
                    if (Name.getText().toString().equals("")){
                        Name.setError("Name required");
                    }
                    if (PhoneNum.getText().toString().equals("")){
                        PhoneNum.setError("Phone number required");
                    }
                    if (Age.getText().toString().equals("")){
                        Age.setError("Age of patient required");
                    }
                    if (selectedHospital.getText().toString().equals("Select Hospital")){
                        Toast.makeText(getActivity(), "Select hospital first", Toast.LENGTH_SHORT).show();
                    }
                    if (Requirements.getText().toString().equals("")) {
                        Requirements.setError("Please mention requirements First");
                    }
                    return;
                }
                recipientStructure recipient = new recipientStructure(Name.getText().toString(), PhoneNum.getText().toString(), Requirements.getText().toString(), Age.getText().toString(), selectedHospital.getText().toString());
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("RecipientRequest");
                databaseReference.push().setValue(recipient);
            }
        });
        return addPlasmaRequest;
    }
}