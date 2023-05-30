package com.example.plasma;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.plasma.Structure.hospitalAddress;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.plasma.databinding.ActivityHospitalsLocationBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class HospitalsLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityHospitalsLocationBinding binding;
    private static LatLngBounds BOUNDS_INDIA = new LatLngBounds(new LatLng(23.63936, 68.14712), new LatLng(28.20453, 97.34466));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHospitalsLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        ArrayList<hospitalAddress> allHospitals = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("HospitalLocations");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    allHospitals.add(new hospitalAddress(Objects.requireNonNull(snapshot1.child("lat").getValue()).toString(), Objects.requireNonNull(snapshot1.child("lon").getValue()).toString(), Objects.requireNonNull(snapshot1.child("name").getValue()).toString()));
                }
                mMap = googleMap;
                Toast.makeText(HospitalsLocation.this, ""+allHospitals.size(), Toast.LENGTH_SHORT).show();
                for (hospitalAddress address: allHospitals){
                    double latitude = Double.parseDouble(address.lat);
                    double longitude = Double.parseDouble(address.lon);
                    LatLng myLoc = new LatLng(latitude, longitude);
                    mMap.addMarker(new MarkerOptions().position(myLoc).title(address.name));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLoc,0.0f));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(myLoc));
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(BOUNDS_INDIA,0));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}