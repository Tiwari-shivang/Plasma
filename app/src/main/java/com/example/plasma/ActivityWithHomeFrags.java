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
import android.util.Log;
import android.view.MenuItem;

import com.example.plasma.Fragments.ActWithHomeFrags.HowPageFrag;
import com.example.plasma.Fragments.ActWithHomeFrags.WherePageFrag;
import com.example.plasma.Fragments.ActWithHomeFrags.WhoPageFrag;
import com.example.plasma.Fragments.ActWithHomeFrags.WhyPageFrag;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class ActivityWithHomeFrags extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_home_frags);

        toolbar = findViewById(R.id.toolBar);
        drawerLayout = findViewById(R.id.DrawerLayout);
        navigationView = findViewById(R.id.Navigation_Drawer);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Bundle extrass = getIntent().getExtras();
        int i = extrass.getInt("Pos");
        Log.d("fragNO:","frag no is " + i);
        if (i == 1)
        {
            Log.d("Whocan", "who is clicked");
            loadFragment(new WhoPageFrag());
        }
        else if (i == 2){
            loadFragment(new HowPageFrag());
        }
        else if (i == 3){
            loadFragment(new WhyPageFrag());
        }
        else{
            loadFragment(new WherePageFrag());
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.WhoCan){
                    loadFragment(new WhoPageFrag());
                }

                else if (id == R.id.HowCan){
                    loadFragment(new HowPageFrag());
                }

                else if (id == R.id.WhyCan){
                    loadFragment(new WhyPageFrag());
                }

                else{
                    loadFragment(new WherePageFrag());
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    public void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.sideNavMenuFrameLayout, fragment, "FragTag");
        ft.commit();
    }
}