package com.example.plasma.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.plasma.R;
import com.example.plasma.Structure.Request;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.plasma.Adapters.allRequestsAdapter;
import java.util.ArrayList;
import java.util.Objects;

public class view_all_donors extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Request> arrayList;
    DatabaseReference databaseReference;
    Request request;
    allRequestsAdapter adapter;
    Boolean abc = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup allDonors = (ViewGroup) inflater.inflate(R.layout.fragment_view_all_donors, container, false);
        recyclerView = allDonors.findViewById(R.id.recyclerDonors);
        request = new Request("", "", "", "", "", "", "", "");
        arrayList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("DonationRequests");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Age="", Gender="", Weight="", BMI="", BMR="", BloodGroup="", Address="", Mobile="";
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
                        else Weight = Objects.requireNonNull(snapshot2.getValue()).toString();
                    }
                    arrayList.add(new Request(Age, Gender, Weight, BMI, BMR, BloodGroup, Address, Mobile));
                }
                adapter = new allRequestsAdapter(arrayList, getActivity());
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return allDonors;
    }
    public void setArrayList (ArrayList<Request> arrayList){
        this.arrayList = arrayList;
        abc = true;
    }
}