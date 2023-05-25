package com.example.plasma.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plasma.R;

public class DonationAwareness extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup DonationAwareness = (ViewGroup) inflater.inflate(R.layout.fragment_donation_awareness, container, false);

        return DonationAwareness;
    }
}