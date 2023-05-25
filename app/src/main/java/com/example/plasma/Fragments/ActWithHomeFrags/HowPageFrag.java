package com.example.plasma.Fragments.ActWithHomeFrags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plasma.R;

public class HowPageFrag extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup HowPgFrag = (ViewGroup) inflater.inflate(R.layout.fragment_how_page, container, false);

        return HowPgFrag;
    }
}