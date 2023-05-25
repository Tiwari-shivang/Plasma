package com.example.plasma.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plasma.Adapters.PlasmaDonationRecyclerViewAdapter;
import com.example.plasma.R;
import com.example.plasma.Structure.plasmaCardValues;

import java.util.ArrayList;


public class PlasmaDonationFragment extends Fragment {


    ArrayList<plasmaCardValues> arrayOfValues = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup plasmaDonationFagment = (ViewGroup) inflater.inflate(R.layout.fragment_plasma_donation2, container, false);
        RecyclerView cards = plasmaDonationFagment.findViewById(R.id.allCardDetails);

        arrayOfValues.add(new plasmaCardValues("What is Plasma?", R.drawable.plasma));
        arrayOfValues.add(new plasmaCardValues("Plasma Donation", R.drawable.donate));
        arrayOfValues.add(new plasmaCardValues("Donor FAQ", R.drawable.donourfaq));
        arrayOfValues.add(new plasmaCardValues("Donor Stories", R.drawable.donorstories));
        arrayOfValues.add(new plasmaCardValues("Donor Center", R.drawable.donorcenter));

        PlasmaDonationRecyclerViewAdapter plasmaDonationRecyclerViewAdapter = new PlasmaDonationRecyclerViewAdapter(getActivity() , arrayOfValues);
        cards.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        cards.setAdapter(plasmaDonationRecyclerViewAdapter);

        return plasmaDonationFagment;

    }
}
