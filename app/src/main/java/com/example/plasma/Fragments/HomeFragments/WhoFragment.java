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

public class WhoFragment extends Fragment {

    public HomeActivity activity;

    @Override
    public void onAttach (Activity activity) {

        super.onAttach(activity);
        this.activity = (HomeActivity) activity;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup whoCanDonateLayout = (ViewGroup) inflater.inflate(R.layout.fragment_who,container,false);
        Button readMore = whoCanDonateLayout.findViewById(R.id.ReadMoreWho);
        Intent intent = new Intent(activity, ActivityWithHomeFrags.class);
        intent.putExtra("pos", 1);
        readMore.setOnClickListener(view -> startActivity(intent));
        return whoCanDonateLayout;
    }
}