package com.example.plasma.Fragments.ActWithHomeFrags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plasma.R;

public class WhoPageFrag extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup WhoPgFrag = (ViewGroup) inflater.inflate(R.layout.fragment_who_page, container, false);

        return WhoPgFrag;
    }
}