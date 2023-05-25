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
import android.os.Bundle;
import android.view.MenuItem;

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

        toolbar = findViewById(R.id.toolBar);
        drawerLayout = findViewById(R.id.Drawer_layout);
        navigationView = findViewById(R.id.NavigationDrawer);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        loadFragment(new addPlasmaRequest());
        Objects.requireNonNull(getSupportActionBar()).setTitle("Recipient");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.Home)
                { loadFragment(new Recipient_Home_Frag()); }
                else if (id == R.id.ViewDonor)
                { loadFragment(new view_all_donors()); }
                else if (id == R.id.Recipient)
                { loadFragment(new addPlasmaRequest());}
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
}