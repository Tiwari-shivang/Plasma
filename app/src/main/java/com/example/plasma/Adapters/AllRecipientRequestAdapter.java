package com.example.plasma.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.plasma.Description;
import com.example.plasma.R;
import com.example.plasma.Structure.recipientStructure;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllRecipientRequestAdapter extends RecyclerView.Adapter<AllRecipientRequestAdapter.ViewHolder>{
    public Context context;
    public ArrayList<recipientStructure> arrayList;
    public AllRecipientRequestAdapter (ArrayList<recipientStructure> arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View singleRequest = LayoutInflater.from(context).inflate(R.layout.all_plasma_recipient_card,parent,false);
        return new ViewHolder(singleRequest);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final recipientStructure temp = arrayList.get(position);
        holder.Name.setText(arrayList.get(position).patientName);
        holder.Age.setText(arrayList.get(position).age);
        holder.PhoneNumber.setText(arrayList.get(position).phno);
        holder.requirements.setText(arrayList.get(position).requirements);
        holder.clickIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Description.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Description", temp.requirements);
                intent.putExtra("Name", temp.patientName);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView Name, PhoneNumber, Age, requirements;
        Button clickIt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.NameRecipient);
            PhoneNumber = itemView.findViewById(R.id.PhoneNumber);
            Age = itemView.findViewById(R.id.Age);
            requirements = itemView.findViewById(R.id.requirementValue);
            clickIt = itemView.findViewById(R.id.viewdescriptionClick);
        }
    }
}
