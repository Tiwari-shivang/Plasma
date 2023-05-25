package com.example.plasma.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plasma.R;

public class aboutUs extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup aboutUS = (ViewGroup) inflater.inflate(R.layout.fragment_about_us, container, false);

        return aboutUS;
    }
}