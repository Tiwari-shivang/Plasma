package com.example.plasma.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plasma.R;
import com.example.plasma.Structure.Request;

import java.util.ArrayList;

public class allRequestsAdapter extends RecyclerView.Adapter<allRequestsAdapter.ViewHolder> {
    ArrayList<Request> array;
    Context context;
    public allRequestsAdapter(ArrayList<Request> array, Context context){
        this.array = array;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View singleCardDetail = LayoutInflater.from(context).inflate(R.layout.all_requests,parent, false);
        return new ViewHolder(singleCardDetail);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Mobile.setText(array.get(position).Mobile);
        holder.Address.setText(array.get(position).Address);
        holder.Age.setText(array.get(position).Age);
        holder.Weight.setText(array.get(position).Weight);
        holder.BloodGroup.setText(array.get(position).BloodGroup);
        holder.BMI.setText(array.get(position).BMI);
        holder.BMR.setText(array.get(position).BMR);
        holder.Gender.setText(array.get(position).Gender);
        holder.hospitalName.setText(array.get(position).hospitalName);
    }

    @Override
    public int getItemCount() {
        return array.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView Age, Gender, BMI, BMR, Address, BloodGroup, Mobile, Weight, hospitalName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Age = itemView.findViewById(R.id.Age);
            Gender = itemView.findViewById(R.id.Gender);
            BMI = itemView.findViewById(R.id.BMI);
            BMR = itemView.findViewById(R.id.BMR);
            Address = itemView.findViewById(R.id.Address);
            BloodGroup = itemView.findViewById(R.id.BloodGroup);
            Mobile = itemView.findViewById(R.id.Mobile);
            Weight = itemView.findViewById(R.id.Weight);
            hospitalName = itemView.findViewById(R.id.hospitalFirebase);
        }
    }
}
