package com.example.plasma.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plasma.Adapters.AllRecipientRequestAdapter;
import com.example.plasma.Adapters.allRequestsAdapter;
import com.example.plasma.R;
import com.example.plasma.Structure.Request;
import com.example.plasma.Structure.recipientStructure;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class view_all_recipient extends Fragment {
    RecyclerView recyclerView;
    AllRecipientRequestAdapter adapter;
    DatabaseReference databaseReference;
    ArrayList<recipientStructure> arrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup all_recipient = (ViewGroup) inflater.inflate(R.layout.fragment_view_all_recipient, container, false);
        recyclerView = all_recipient.findViewById(R.id.recyclerViewRecipient);
        arrayList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("RecipientRequest");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String patientName="", phno="", requirements="", age="";
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    for (DataSnapshot snapshot2:snapshot1.getChildren()){
                        if (Objects.equals(snapshot2.getKey(), "patientName")){ patientName = Objects.requireNonNull(snapshot2.getValue()).toString(); }
                        else if (Objects.equals(snapshot2.getKey(), "phno")){
                            phno =  Objects.requireNonNull(snapshot2.getValue()).toString();}
                        else if (Objects.equals(snapshot2.getKey(), "requirements")){
                            requirements =  Objects.requireNonNull(snapshot2.getValue()).toString();}
                        else age = Objects.requireNonNull(snapshot2.getValue()).toString();
                    }
                    arrayList.add(new recipientStructure(patientName, phno, requirements, age));
                }
                adapter = new AllRecipientRequestAdapter(arrayList, getActivity());
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return all_recipient;
    }
}