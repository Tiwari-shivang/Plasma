package com.example.plasma.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plasma.Adapters.AllRecipientRequestAdapter;
import com.example.plasma.Adapters.allRequestsAdapter;
import com.example.plasma.Adapters.hospitalNameAdapter;
import com.example.plasma.R;
import com.example.plasma.Structure.Request;
import com.example.plasma.Structure.recipientStructure;
import com.example.plasma.allHospitals;
import com.example.plasma.allHospitalsDonor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class view_all_recipient extends Fragment {
    RecyclerView recyclerView;
    AllRecipientRequestAdapter adapter;
    DatabaseReference databaseReference;
    Button selectHospital;
    TextView allRecipientLabel;
    String selectedHospital;
    ArrayList<recipientStructure> arrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup all_recipient = (ViewGroup) inflater.inflate(R.layout.fragment_view_all_recipient, container, false);

        // bind with UI
        recyclerView = all_recipient.findViewById(R.id.recyclerViewRecipient);
        arrayList = new ArrayList<>();
        selectedHospital="";
        selectHospital = all_recipient.findViewById(R.id.hospitalSelButton);
        allRecipientLabel = all_recipient.findViewById(R.id.allRecipientLabel);

        //Hospital name from firebase
        if (requireActivity().getIntent().hasExtra("HospitalName")){
            allRecipientLabel.setText(requireActivity().getIntent().getStringExtra("HospitalName"));
            Toast.makeText(getActivity(), "Hospital selected: "+requireActivity().getIntent().getStringExtra("HospitalName"), Toast.LENGTH_SHORT).show();
        }

        selectedHospital = allRecipientLabel.getText().toString();

        //firebase connection
        databaseReference = FirebaseDatabase.getInstance().getReference("RecipientRequest");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String patientName="", phno="", requirements="", age="", hospitalName="";
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    for (DataSnapshot snapshot2:snapshot1.getChildren()){
                        if (Objects.equals(snapshot2.getKey(), "patientName")){ patientName = Objects.requireNonNull(snapshot2.getValue()).toString(); }
                        else if (Objects.equals(snapshot2.getKey(), "phno")){
                            phno =  Objects.requireNonNull(snapshot2.getValue()).toString();}
                        else if (Objects.equals(snapshot2.getKey(), "requirements")){
                            requirements =  Objects.requireNonNull(snapshot2.getValue()).toString();}
                        else if (Objects.equals(snapshot2.getKey(), "hospitalName")){
                            hospitalName = Objects.requireNonNull(snapshot2.getValue()).toString();
                        }
                        else age = Objects.requireNonNull(snapshot2.getValue()).toString();
                    }
                    if (selectedHospital.equals("All Recipient")){
                        arrayList.add(new recipientStructure(patientName, phno, requirements, age, hospitalName));
                    }
                    else{
                        if (selectedHospital.equals(hospitalName)){
                            arrayList.add(new recipientStructure(patientName, phno, requirements, age, hospitalName));
                        }
                    }
                }

                //adding with adapter
                adapter = new AllRecipientRequestAdapter(arrayList, getActivity());
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //set hospital name
        selectHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get all hospital list from allHospital activity for Hospital home page.
                Intent intent = new Intent(getActivity(), allHospitals.class);
                intent.putExtra("fragment", "view_recipient");
                startActivity(intent);
                requireActivity().finish();
            }
        });
        return all_recipient;
    }
}