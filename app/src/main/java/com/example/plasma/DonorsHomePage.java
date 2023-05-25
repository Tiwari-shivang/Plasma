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

import android.os.Bundle;
import android.view.MenuItem;

import com.example.plasma.Fragments.DonationAwareness;
import com.example.plasma.Fragments.Donor_Home_Frag;
import com.example.plasma.Fragments.become_Donor;
import com.example.plasma.Fragments.view_all_recipient;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class DonorsHomePage extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors_home_page);

        toolbar = findViewById(R.id.toolBar);
        drawerLayout = findViewById(R.id.Drawer_layout);
        navigationView = findViewById(R.id.NavigationDrawer);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        loadFragment(new Donor_Home_Frag());
        Objects.requireNonNull(getSupportActionBar()).setTitle("Donor");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.Home)
                { loadFragment(new Donor_Home_Frag()); }
                else if (id == R.id.Donor)
                { loadFragment(new become_Donor()); }
                else if (id == R.id.ViewRecipient)
                { loadFragment(new view_all_recipient());}
                else if (id == R.id.Awareness)
                { loadFragment(new DonationAwareness()); }
                else
                { }
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
}