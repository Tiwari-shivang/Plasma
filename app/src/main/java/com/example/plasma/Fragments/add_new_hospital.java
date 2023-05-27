package com.example.plasma.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plasma.R;
import com.example.plasma.Structure.hospitalAddress;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;
import java.util.Objects;

public class add_new_hospital extends Fragment {
    Boolean lat = false, lon = false, name = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup addHospital = (ViewGroup) inflater.inflate(R.layout.fragment_add_new_hospital, container, false);
        EditText hospitalName = addHospital.findViewById(R.id.name);
        EditText latitude = addHospital.findViewById(R.id.latitude);
        EditText longitude = addHospital.findViewById(R.id.longitude);
        Button addButton = addHospital.findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(hospitalName.getText().toString()) || TextUtils.isEmpty(latitude.getText().toString()) || TextUtils.isEmpty(longitude.getText().toString())){
                    if (TextUtils.isEmpty(hospitalName.getText().toString())){
                        hospitalName.setError("Hospital name required");
                    }
                    if (TextUtils.isEmpty(latitude.getText().toString())){
                        latitude.setError("Please provide latitude");
                    }
                    if (TextUtils.isEmpty(longitude.getText().toString())){
                        longitude.setError("Please provide longitude");
                    }
                    return;
                }
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("HospitalLocations");
                hospitalAddress address = new hospitalAddress(latitude.getText().toString(), longitude.getText().toString(), hospitalName.getText().toString());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1:snapshot.getChildren()){
                            for (DataSnapshot snapshot2:snapshot1.getChildren()){
                                if (Objects.equals(snapshot2.getKey(), "lat")){
                                    if (snapshot2.getValue() == latitude.getText().toString()){
                                        lat = true;
                                    }
                                }
                                else if (Objects.equals(snapshot2.getKey(), "lon")){
                                    if (snapshot2.getValue() == longitude.getText().toString()){
                                        lon = true;
                                    }
                                }
                                else{
                                    if (Objects.requireNonNull(snapshot2.getValue()).toString().toUpperCase(Locale.ROOT).equals(hospitalName.getText().toString().toUpperCase(Locale.ROOT))){
                                        name = true;
                                    }
                                }
                                if (lat && lon || name){
                                    return;
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                if (lat && lon || name){
                    Toast.makeText(getActivity(), "Already Existed", Toast.LENGTH_SHORT).show();
                    return;
                }
                databaseReference.push().setValue(address);
            }
        });
        return addHospital;
    }
}