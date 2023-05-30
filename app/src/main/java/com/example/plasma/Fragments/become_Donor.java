package com.example.plasma.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plasma.R;
import com.example.plasma.Structure.Request;
import com.example.plasma.allHospitals;
import com.example.plasma.allHospitalsDonor;
import com.example.plasma.allHospitalsRecipient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class become_Donor extends Fragment {

    EditText Age, Gender, Weight, BMI, BMR, BloodGroup, Address, Mobile;
    TextView hospitalName;
    Button addRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup becomeDonor = (ViewGroup) inflater.inflate(R.layout.fragment_become__donor, container, false);

        Address = becomeDonor.findViewById(R.id.addressText);
        Mobile = becomeDonor.findViewById(R.id.mobileText);
        Age = becomeDonor.findViewById(R.id.ageTextField);
        Gender = becomeDonor.findViewById(R.id.genderTextField);
        Weight = becomeDonor.findViewById(R.id.BodyWeightText);
        BMI = becomeDonor.findViewById(R.id.BMItextField);
        BMR = becomeDonor.findViewById(R.id.BMRtextField);
        BloodGroup = becomeDonor.findViewById(R.id.BloodGrptxt);
        hospitalName = becomeDonor.findViewById(R.id.selectedHospital);
        addRequest = becomeDonor.findViewById(R.id.AddRequest);

        if (requireActivity().getIntent().hasExtra("HospitalName")){
            hospitalName.setText(requireActivity().getIntent().getStringExtra("HospitalName"));
            Toast.makeText(getActivity(), "Hospital selected: "+requireActivity().getIntent().getStringExtra("HospitalName"), Toast.LENGTH_SHORT).show();
        }

        hospitalName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), allHospitals.class);
                intent.putExtra("fragment", "becomeDonor");
                startActivity(intent);
                requireActivity().finish();
            }
        });
        addRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Request request = new Request(Age.getText().toString(), Gender.getText().toString(), Weight.getText().toString(), BMI.getText().toString(), BMR.getText().toString(), BloodGroup.getText().toString(), Address.getText().toString(), Mobile.getText().toString(), hospitalName.getText().toString());
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("DonationRequests");
                databaseReference.push().setValue(request);
            }
        });
        return becomeDonor;
    }
}