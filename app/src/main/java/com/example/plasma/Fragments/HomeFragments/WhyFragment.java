package com.example.plasma.Fragments.HomeFragments;
import com.example.plasma.ActivityWithHomeFrags;
import com.example.plasma.HomeActivity;
import com.example.plasma.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class WhyFragment extends Fragment {

    HomeActivity activity;

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        this.activity = (HomeActivity) activity;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup whyToDonateLayout = (ViewGroup) inflater.inflate(R.layout.fragment_why, container, false);
        Button readMore = whyToDonateLayout.findViewById(R.id.ReadMoreWhy);
        readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ActivityWithHomeFrags.class);
                intent.putExtra("Pos", 3);
                startActivity(intent);
            }
        });
        return whyToDonateLayout;
    }
}