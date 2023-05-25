package com.example.plasma.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plasma.R;
import com.example.plasma.Structure.plasmaCardValues;

import java.util.ArrayList;

public class PlasmaDonationRecyclerViewAdapter extends RecyclerView.Adapter<PlasmaDonationRecyclerViewAdapter.ViewHolder> {

    Context contextOfFragment;
    ArrayList<plasmaCardValues> plasmaCardValuesArrayList;

    public PlasmaDonationRecyclerViewAdapter(Context context, ArrayList<plasmaCardValues> arrayList){
        contextOfFragment = context;
        plasmaCardValuesArrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View singleCardDetail = LayoutInflater.from(contextOfFragment).inflate(R.layout.plasma_collection_recycler_layout,parent, false);
        ViewHolder viewHolder = new ViewHolder(singleCardDetail);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(plasmaCardValuesArrayList.get(position).img);
        holder.HeadName.setText(plasmaCardValuesArrayList.get(position).cardHeading);
    }

    @Override
    public int getItemCount() {
        return plasmaCardValuesArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView HeadName;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            HeadName = itemView.findViewById(R.id.ImageTextName);
            imageView = itemView.findViewById(R.id.plasmaImg);
        }

    }
}
