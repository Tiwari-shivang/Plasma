package com.example.plasma;

import android.os.Bundle;

import com.example.plasma.Adapters.allRequestsAdapter;
import com.example.plasma.Structure.Request;
import com.example.plasma.Structure.userStructure;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plasma.databinding.ActivityViewRequestsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class view_requests extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = findViewById(R.id.recyclerRequests);
        ArrayList<Request> reqArr = new ArrayList<>();
        reqArr.add(new Request("20", "male", "34","20.1", "20.1", "A+", "101", "9910"));
//        allRequestsAdapter all = new allRequestsAdapter(reqArr, this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        recyclerView.setAdapter(all);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Request");
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Request> arr = new ArrayList<>();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    String Age="", Gender="", Weight="", BMI="", BMR="", BloodGroup="", Address="", Mobile="";
                    for (DataSnapshot snapshot2: snapshot1.getChildren()){
                        if (Objects.equals(snapshot2.getKey(), "Age")){
                            Age = Objects.requireNonNull(snapshot2.getValue()).toString();
                            Toast.makeText(view_requests.this, ""+Age, Toast.LENGTH_SHORT).show();
                        }
                        else if (Objects.equals(snapshot2.getKey(), "Gender")){
                            Gender = Objects.requireNonNull(snapshot2.getValue()).toString();
                            Toast.makeText(view_requests.this, ""+Gender, Toast.LENGTH_SHORT).show();
                        }
                        else if (Objects.equals(snapshot2.getKey(), "Weight")){
                            Weight = Objects.requireNonNull(snapshot2.getValue()).toString();
                            Toast.makeText(view_requests.this, ""+Weight, Toast.LENGTH_SHORT).show();

                        }
                        else if (Objects.equals(snapshot2.getKey(), "BMI")){
                            BMI = Objects.requireNonNull(snapshot2.getValue()).toString();
                            Toast.makeText(view_requests.this, ""+BMI, Toast.LENGTH_SHORT).show();
                        }
                        else if (Objects.equals(snapshot2.getKey(), "BMR")){
                            BMR = Objects.requireNonNull(snapshot2.getValue()).toString();
                            Toast.makeText(view_requests.this, ""+BMR, Toast.LENGTH_SHORT).show();
                        }
                        else if (Objects.equals(snapshot2.getKey(), "BloodGroup")){
                            BloodGroup = Objects.requireNonNull(snapshot2.getValue()).toString();
                            Toast.makeText(view_requests.this, ""+BloodGroup, Toast.LENGTH_SHORT).show();
                        }
                        else if (Objects.equals(snapshot2.getKey(), "Address")){
                            Address = Objects.requireNonNull(snapshot2.getValue()).toString();
                            Toast.makeText(view_requests.this, ""+Address, Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Mobile = Objects.requireNonNull(snapshot2.getValue()).toString();
                            Toast.makeText(view_requests.this, ""+Mobile, Toast.LENGTH_SHORT).show();
                        }
                    }
                    Request request = new Request(Age, Gender, Weight, BMI, BMR, BloodGroup, Address, Mobile );
                    arr.add(request);
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}