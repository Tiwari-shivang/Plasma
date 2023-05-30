package com.example.plasma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.plasma.Fragments.Recipient_Home_Frag;
import com.example.plasma.Fragments.addPlasmaRequest;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.plasma.Fragments.viewDonorRecipientFrag;
import com.example.plasma.Fragments.viewRecipientDonorFrag;
import com.example.plasma.Fragments.view_all_donors;
import com.google.android.material.navigation.NavigationView;
import java.util.Objects;

public class RecipientHomePage extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient_home_page);

        //binding with UI
        toolbar = findViewById(R.id.toolBar);
        drawerLayout = findViewById(R.id.Drawer_layout);
        navigationView = findViewById(R.id.NavigationDrawer);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // getting intent fragment
        Intent initialIntent = getIntent();
        setDefaultFragment(new viewDonorRecipientFrag());
        Objects.requireNonNull(getSupportActionBar()).setTitle("Recipient");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.Home)
                { loadFragment(new Recipient_Home_Frag()); }
                else if (id == R.id.ViewDonor)
                { loadFragment(new viewDonorRecipientFrag()); }
                else if (id == R.id.hospital){
                    Intent hospitalIntent = new Intent(RecipientHomePage.this, HospitalsLocation.class);
                    startActivity(hospitalIntent);
                }
                else if (id == R.id.registeredHospitals){
                    Intent regHospitalIntent = new Intent(RecipientHomePage.this, allHospitalsRecipient.class);
                    startActivity(regHospitalIntent);
                }
                else
                {  }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    private void loadFragment(Fragment aFragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.sideNavMenuFrameLayout,aFragment, "FragTag");
        ft.addToBackStack(null);
        ft.commit();
    }
    private void setDefaultFragment (Fragment aFragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.sideNavMenuFrameLayout,aFragment);
        ft.commit();
    }
}