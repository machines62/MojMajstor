package com.example.mojmajstor;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UslugaAdapter extends RecyclerView.Adapter<UslugaAdapter.ViewHolder> {

    private List<String> uslugeList;
    private List<String> uslugeListFull;
    private OnUslugaClickListener listener;

    public interface OnUslugaClickListener {
        void onUslugaClick(String usluga);
    }

    public UslugaAdapter(List<String> uslugeList, OnUslugaClickListener listener) {
        this.uslugeList = new ArrayList<>(uslugeList);
        this.uslugeListFull = new ArrayList<>(uslugeList);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usluga, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String usluga = uslugeList.get(position);
        holder.uslugaText.setText(usluga);
        holder.uslugaText.setTypeface(null, Typeface.BOLD);
        holder.uslugaText.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.colorPrimary));

        holder.setIsRecyclable(false);

        holder.itemView.setOnClickListener(v -> listener.onUslugaClick(usluga));
    }

    @Override
    public int getItemCount() {
        return uslugeList.size();
    }

    public void filter(String text) {
        uslugeList.clear();
        if (text.isEmpty()) {
            uslugeList.addAll(uslugeListFull);
        } else {
            for (String item : uslugeListFull) {
                if (item.toLowerCase().contains(text.toLowerCase())) {
                    uslugeList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView uslugaText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            uslugaText = itemView.findViewById(R.id.textViewUsluga);
        }
    }
}