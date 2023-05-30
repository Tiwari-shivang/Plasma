package com.example.plasma.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plasma.DonorsHomePage;
import com.example.plasma.R;
import com.example.plasma.RecipientHomePage;

import java.util.ArrayList;

public class hospitalNameAdapterRecipient extends RecyclerView.Adapter<hospitalNameAdapterRecipient.ViewHolder> {

    ArrayList<String> hospitalNames; Context context; String fragment;

    public hospitalNameAdapterRecipient (ArrayList<String> hospitalNames, Context context, String fragment){
        this.context = context;
        this.fragment = fragment;
        this.hospitalNames = hospitalNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View singleHospitalDetail = LayoutInflater.from(context).inflate(R.layout.hospital_names_recycler_layout, parent, false);
        hospitalNameAdapterRecipient.ViewHolder viewHolder = new hospitalNameAdapterRecipient.ViewHolder(singleHospitalDetail);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String temp = hospitalNames.get(position);
        holder.hospitalName.setText(hospitalNames.get(position).toString());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, RecipientHomePage.class);
                intent.putExtra("HospitalNameDonorHome", temp);
                intent.putExtra("fragment", fragment);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hospitalNames.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView hospitalName;
        CardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hospitalName = itemView.findViewById(R.id.hospitalNameCard);
            card = itemView.findViewById(R.id.hosCard);
        }
    }}
