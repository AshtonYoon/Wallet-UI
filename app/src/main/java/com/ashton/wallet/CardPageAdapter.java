package com.ashton.wallet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardPageAdapter extends RecyclerView.Adapter<CardPageViewHolder> {
    private List<String> list;

    public CardPageAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CardPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.large_card, parent, false);
        return new CardPageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardPageViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
