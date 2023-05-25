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

public class HowFragment extends Fragment {

    HomeActivity activity;

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);
        this.activity = (HomeActivity) activity;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup howToDonate = (ViewGroup) inflater.inflate(R.layout.fragment_how,container, false);
        Button readMore = howToDonate.findViewById(R.id.ReadMoreHow);
        readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ActivityWithHomeFrags.class);
                intent.putExtra("pos", 2);
                startActivity(intent);
            }
        });
        return howToDonate;
    }
}