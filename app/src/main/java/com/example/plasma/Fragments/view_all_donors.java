package com.example.plasma.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plasma.Adapters.hospitalNameAdapter;
import com.example.plasma.R;
import com.example.plasma.Structure.Request;
import com.example.plasma.allHospitals;
import com.example.plasma.allHospitalsRecipient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.plasma.Adapters.allRequestsAdapter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class view_all_donors extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Request> arrayList;
    DatabaseReference databaseReference;
    Request request;
    allRequestsAdapter adapter;
    RelativeLayout mainView;
    Button selectHosClicked;
    TextView hospitalName;
    String selectedHospital;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup allDonors = (ViewGroup) inflater.inflate(R.layout.fragment_view_all_donors, container, false);

        //binding with UI
        recyclerView = allDonors.findViewById(R.id.recyclerDonors);
        mainView = allDonors.findViewById(R.id.mainView);
        selectHosClicked = allDonors.findViewById(R.id.selectHosClicked);
        request = new Request("", "", "", "", "", "", "", "", "");
        arrayList = new ArrayList<>();
        hospitalName = allDonors.findViewById(R.id.donorsLabel);
        selectedHospital = "";

        if (requireActivity().getIntent().hasExtra("HospitalName")){
            hospitalName.setText(requireActivity().getIntent().getStringExtra("HospitalNameDonorHome"));
            Toast.makeText(getActivity(), "Hospital selected: "+requireActivity().getIntent().getStringExtra("HospitalName"), Toast.LENGTH_SHORT).show();
        }

        selectedHospital = hospitalName.getText().toString();

        // connection with firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("DonationRequests");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Age="", Gender="", Weight="", BMI="", BMR="", BloodGroup="", Address="", Mobile="", hospitalName="";
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    for (DataSnapshot snapshot2:snapshot1.getChildren()){
                        if (Objects.equals(snapshot2.getKey(), "Address")){ Address = Objects.requireNonNull(snapshot2.getValue()).toString(); }
                        else if (Objects.equals(snapshot2.getKey(), "Age")){
                            Age =  Objects.requireNonNull(snapshot2.getValue()).toString();}
                        else if (Objects.equals(snapshot2.getKey(), "BMI")){
                            BMI =  Objects.requireNonNull(snapshot2.getValue()).toString();}
                        else if (Objects.equals(snapshot2.getKey(), "BMR")){
                            BMR = Objects.requireNonNull(snapshot2.getValue()).toString();}
                        else if (Objects.equals(snapshot2.getKey(), "BloodGroup")){
                            BloodGroup =  Objects.requireNonNull(snapshot2.getValue()).toString();}
                        else if (Objects.equals(snapshot2.getKey(), "Gender")){
                            Gender =  Objects.requireNonNull(snapshot2.getValue()).toString();}
                        else if (Objects.equals(snapshot2.getKey(), "Mobile")){
                            Mobile =  Objects.requireNonNull(snapshot2.getValue()).toString();}
                        else if (Objects.equals(snapshot2.getKey(), "hospitalName")){
                            hospitalName = Objects.requireNonNull(snapshot2.getValue()).toString();
                        }
                        else Weight = Objects.requireNonNull(snapshot2.getValue()).toString();
                    }
                    if (selectedHospital.equals("All donors")){
                        arrayList.add(new Request(Age, Gender, Weight, BMI, BMR, BloodGroup, Address, Mobile, hospitalName));
                    }
                    else{
                        if (selectedHospital.equals(hospitalName)){
                            arrayList.add(new Request(Age, Gender, Weight, BMI, BMR, BloodGroup, Address, Mobile, hospitalName));
                        }
                    }
                }

                //adding values in adapter
                adapter = new allRequestsAdapter(arrayList, getActivity());
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        selectHosClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get all hospital list from allHospital activity for Hospital home page.
                Intent intent = new Intent(getActivity(), allHospitals.class);
                intent.putExtra("fragment", "view_donors");
                startActivity(intent);
                requireActivity().finish();
            }
        });
        return allDonors;
    }
}