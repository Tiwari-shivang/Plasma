package com.example.plasma.Fragments.ActWithHomeFrags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plasma.R;

public class WhyPageFrag extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup WhyPgFrag = (ViewGroup) inflater.inflate(R.layout.fragment_why_page, container, false);

        return WhyPgFrag;
    }
}