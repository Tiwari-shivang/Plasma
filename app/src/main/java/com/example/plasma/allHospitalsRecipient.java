package com.example.plasma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.plasma.Adapters.hospitalNameAdapter;
import com.example.plasma.Adapters.hospitalNameAdapterRecipient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class allHospitalsRecipient extends AppCompatActivity {

    ArrayList<String> hospitals;
    hospitalNameAdapterRecipient adapter2;
    RecyclerView recyclerView1;
    String fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_hospitals_recipient);
        recyclerView1 = findViewById(R.id.recyclerDonors);

        fragment = getIntent().getStringExtra("fragment");

        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("HospitalNames");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Set<String> set = new HashSet<>();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    set.add(Objects.requireNonNull(snapshot1.getValue()).toString());
                }
                hospitals = new ArrayList<>(set);
                adapter2 = new hospitalNameAdapterRecipient(hospitals, allHospitalsRecipient.this, fragment);
                recyclerView1.setLayoutManager(new LinearLayoutManager(allHospitalsRecipient.this, LinearLayoutManager.VERTICAL, false));
                recyclerView1.setAdapter(adapter2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}