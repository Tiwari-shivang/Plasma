package com.example.plasma;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import com.example.plasma.Adapters.ViewPagerAdapterForHomePage;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth userAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = new Intent(this, MainActivity.class); // phle yha Main.class tha
        ImageView back = findViewById(R.id.backArrow);
        ViewPager viewPager = findViewById(R.id.viewPagerSelectOption);
        userAuth = FirebaseAuth.getInstance();


        back.setOnClickListener(view -> {
            startActivity(intent);
            finish();
        });

        ViewPagerAdapterForHomePage viewPagerAdapterForHomePage = new ViewPagerAdapterForHomePage(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapterForHomePage);

    }
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = userAuth.getCurrentUser();
        Intent intentLogin = new Intent(HomeActivity.this, login_Activity.class);
        if (user == null){
            startActivity(intentLogin);
        }

    }
}