package com.ashton.wallet;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransferAdapter extends RecyclerView.Adapter<TransferViewHolder> {
    private List<Transfer> list;

    public TransferAdapter(List<Transfer> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TransferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transfer_cell, parent, false);
        return new TransferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransferViewHolder holder, int position) {
        holder.BindData(list.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("TransferAdapter", "size : " + list.size());
        return list.size();
    }

    public void addTransfer(Transfer transfer){
        list.add(transfer);

        new Handler(Looper.getMainLooper()).post(() -> {
            notifyDataSetChanged();
            Log.d("TransferAdapter", "notifyDataSetChanged");
        });
    }
}
