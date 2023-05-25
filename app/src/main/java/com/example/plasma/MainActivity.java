package com.example.plasma;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.plasma.Fragments.PlasmaDonationFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolBar);
        drawerLayout = findViewById(R.id.Drawer_layout);
        navigationView = findViewById(R.id.NavigationDrawer);
        Intent intent = new Intent(this, HomeActivity.class);
        Intent addRequest = new Intent(this, add_Request.class);
        Intent viewRequests = new Intent(this, view_requests.class);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        loadFragment(new PlasmaDonationFragment());
        Objects.requireNonNull(getSupportActionBar()).setTitle("Plasma donation");

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                if (id == R.id.Home)
//                {
//                    startActivity(intent);
//                    finish();
//                }
//                else if (id == R.id.AddRequest)
//                {
//                    startActivity(addRequest);
//                }
//                else if (id == R.id.ViewDonors)
//                {
//                    startActivity(viewRequests);
//                }
//                else if (id == R.id.AwarenessWeek)                {
//                    loadFragment(new DonationAwareness());
//                    Objects.requireNonNull(getSupportActionBar()).setTitle("Donation Awareness");
//                }
//                else{
//                    loadFragment(new aboutUs());
//                    Objects.requireNonNull(getSupportActionBar()).setTitle("About Us");
//                }
//                drawerLayout.closeDrawer(GravityCompat.START);
//                return true;
//            }
//        });

    }

    private void loadFragment(Fragment aFragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.sideNavMenuFrameLayout,aFragment, "FragTag");
        ft.addToBackStack(null);
        ft.commit();

    }
}